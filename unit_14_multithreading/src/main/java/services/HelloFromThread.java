package services;

import java.util.concurrent.Callable;

public class HelloFromThread implements Callable<String> {

    private String name;
    private int index;

    public HelloFromThread(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String call() {
        if (index < 49) {
            Callable<String> thread = new HelloFromThread("Hello from thread " + (index + 1), ++index);
            try {
                thread.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(name);
        return "";
    }
}
