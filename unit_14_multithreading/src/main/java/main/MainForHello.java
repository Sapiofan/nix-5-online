package main;

import services.HelloFromThread;

import java.util.concurrent.Callable;

public class MainForHello {
    public static void main(String[] args) {
        Callable<String> thread = new HelloFromThread("Hello from thread " + 0, 0);
        try {
            thread.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
