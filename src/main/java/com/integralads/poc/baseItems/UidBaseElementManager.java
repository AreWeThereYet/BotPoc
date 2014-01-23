/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integralads.poc.baseItems;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 *
 * @author geoff
 */
public class UidBaseElementManager {

    private final HashMap<Long, UidBaseElement> allElements = new HashMap<Long, UidBaseElement>();
    private final AtomicLong totalEvents = new AtomicLong();
    
    public UidBaseElementManager() {
        System.out.println("UidBaseElementManager has been instantiated.  You should see this message only once.");
    }
    
    public String handledEvents() {
        return totalEvents.toString();
    }

    public boolean isBot(long userId) {
        
        long currTotal = totalEvents.incrementAndGet();
        if (currTotal%10000 == 0) {
            System.out.println("Handled " + totalEvents + " requests and curent table entries " + allElements.size());
        }

        UidBaseElement uide = allElements.get(userId);
        if (uide == null) {
            createElement(userId);
            return false;
        }
        else {
            return uide.occurence();
        }
    }

    private synchronized void createElement(long userId) {

        // do another test just in case another thread beat us to the lock
        UidBaseElement uide = allElements.get(userId);
        if (uide == null) {
            uide = new UidBaseElement(userId);
            allElements.put(userId, uide);
        }
        else { // somebody beat the lock and created one so increment the counters
            uide.occurence();
        }
    }
}
