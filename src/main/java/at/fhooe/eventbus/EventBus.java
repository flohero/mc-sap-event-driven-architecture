package at.fhooe.eventbus;

import java.util.ArrayList;
import java.util.List;

public final class EventBus {
    private final List<Subscriber> subscribers = new ArrayList<>();

    public void register(Object listener) {
        if (!subscribers.contains(new Subscriber(listener))) {
            subscribers.add(new Subscriber(listener));
        }
    }

    public void unregister(Object listener) {
        subscribers.remove(new Subscriber(listener));
    }

    public void post(Object arg) {
        for (Subscriber subscriber : subscribers) {
            subscriber.invoke(arg);
        }
    }
}
