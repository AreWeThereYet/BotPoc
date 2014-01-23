/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integralads.poc.baseItems;

/**
 *
 * @author geoff
 */
public class UidBaseElement {

    public static final int NUMBER_OF_OCCURRENCES_TO_BE_CONSIDERED_AS_BOT = 10;
    public static final int BOT_SECONDS_INTERVAL_EVALUATION_PERIOD = 60;
    private long uid;
    private int lastEntry = -1;
    private int oldestSlot = 0;
    private long[] slots = new long[NUMBER_OF_OCCURRENCES_TO_BE_CONSIDERED_AS_BOT];
    private int totalEvents = 0;
    private boolean isConsideredBot=false;

    public UidBaseElement(long uid) {
        this.uid = uid;
    }

    public synchronized boolean occurence() {

        boolean result = false;
        
        totalEvents++;       
        // figure out next slot and put it there
        lastEntry = (lastEntry+1)%NUMBER_OF_OCCURRENCES_TO_BE_CONSIDERED_AS_BOT;
        oldestSlot = (oldestSlot+1)%NUMBER_OF_OCCURRENCES_TO_BE_CONSIDERED_AS_BOT;
        
        slots[lastEntry] = System.currentTimeMillis() / 1000;
        
        if (totalEvents > (NUMBER_OF_OCCURRENCES_TO_BE_CONSIDERED_AS_BOT - 1)) {
            result = evaluateBot();
        }

        return result;
    }

    private boolean evaluateBot() {
        
        isConsideredBot = (slots[lastEntry] - slots[oldestSlot]) < BOT_SECONDS_INTERVAL_EVALUATION_PERIOD;
        return isConsideredBot;
    }
    
    public boolean lastBotStatus() {
        return isConsideredBot;
    }
   
    public long getUid() {
        return uid;
    }

    public int getLastEntry() {
        return lastEntry;
    }

    public int getOldestSlot() {
        return oldestSlot;
    }

    public long[] getSlots() {
        return slots;
    }

    public int getTotalEvents() {
        return totalEvents;
    }
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder("\n");
        for(int loop=0;loop<NUMBER_OF_OCCURRENCES_TO_BE_CONSIDERED_AS_BOT;loop++) {
            sb.append(loop).append(":").append(slots[loop]).append(loop==oldestSlot ? "<-- oldest " :"").append(loop==lastEntry ? "<-- lastEntry " :"").append("\n");
        }
      
        return "uid [" + uid + "] total number of events [" + totalEvents + "] current table: " +  sb.toString();
    }

    
}
