package services;

import java.util.concurrent.Callable;

public class HelloFromThread implements Callable<String> {

    private String name;
    private int index;

    public HelloFromThread(String name, int index){
        this.name = name;
        this.index = index;
    }

    public String call(){
        String hello = null;
        if(index <= 49){
            Callable<String> thread1 = new HelloFromThread("Hello from thread " + index, ++index);
            try {
                hello = thread1.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(hello != null){
            System.out.println(hello);
        }
        return name;
    }
}
