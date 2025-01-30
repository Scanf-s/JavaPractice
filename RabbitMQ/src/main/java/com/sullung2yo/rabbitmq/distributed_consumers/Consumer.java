//package com.sullung2yo.rabbitmq.distributed_consumers;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class Consumer {
//
//    public void consume(String message) {
//        String[] messageSplit = message.split("\\|");
//        String originMessage = messageSplit[0];
//        int duration = Integer.parseInt(messageSplit[1].trim());
//        System.out.println("# Consumer [" + Thread.currentThread().getName() + "] Received : " + originMessage + " (Duration : " + duration + "ms)");
//
//        try {
//            int seconds = duration / 1000; // Convert ms to seconds
//            for (int i = 0; i < seconds; i++) {
//                Thread.sleep(1000); // Sleep for 1 second
//                System.out.print("."); // Print processing status
//            }
//            System.out.println();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//        System.out.println("[X] " + originMessage + " DONE!!");
//    }
//}
