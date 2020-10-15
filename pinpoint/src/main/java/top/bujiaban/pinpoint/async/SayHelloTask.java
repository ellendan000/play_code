package top.bujiaban.pinpoint.async;

public class SayHelloTask implements Runnable {
    private String name;
    public SayHelloTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Hello ~" + this.name);
    }
}
