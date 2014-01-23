/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integralads.testing;

import com.integralads.poc.baseItems.UidBaseElement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geoff
 */
public class Tester {

    public static void main(String[] args) {

        Tester tester = new Tester();
        tester.doSomething();

    }

    public void doSomething() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long uid = 1234;
        int waitPeriod = 1;
        Random rnd = new Random();
        
        UidBaseElement uidb = new UidBaseElement(uid);
        
        ArrayList<ProcessingThread> al = new ArrayList<ProcessingThread>();
        for(int loop=0;loop<10;loop++) {
            ProcessingThread pt = new ProcessingThread(Integer.toString(loop));
            pt.setUIDB(uidb);
            al.add(pt);
        }
        for(ProcessingThread pt: al) {
            pt.start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println(uidb.toString());

//        int lastEntry = uidb.getLastEntry();
//        int oldestEntry = uidb.getOldestSlot();
//        long[] slots = uidb.getSlots();
//        System.out.println(uidb.toString());
//        for (loop = 0; loop < slots.length; loop++) {
//            long slot = slots[loop];
//            System.out.println(sdf.format(new Date(slot * 1000)) + (loop == lastEntry ? " <- lastEntry" : "") + (loop == oldestEntry ? " <- oldestEntry" : ""));
//        }

    }

    class ProcessingThread extends Thread {

        UidBaseElement uidb;

        public ProcessingThread(String name) {
            super(name);
        }

        public void setUIDB(UidBaseElement uidb) {
            this.uidb = uidb;
        }

        @Override
        public void run() {

            for (int i = 0; i < 1000; i++) {
                uidb.occurence();
                
            }
        }
    }
}
