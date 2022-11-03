package at.fhooe;

import at.fhooe.eventbus.EventBus;
import at.fhooe.eventbus.Listener;

public class Main {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        NumberListener numberListener = new NumberListener();
        eventBus.register(numberListener);
        eventBus.register(new StringListener());
        eventBus.post(1);
        eventBus.post("Hello");
        eventBus.unregister(numberListener);
        eventBus.post(1);
    }

    public static final class NumberListener {

        @Listener
        public void listen(Integer i) {
            System.out.println("Got Integer: " + i);
        }

    }

    public static final class StringListener {
        @Listener
        public void listen(String s) {
            System.out.println("Got String: " + s);
        }
    }
}
