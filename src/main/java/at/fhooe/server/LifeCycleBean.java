package at.fhooe.server;

import at.fhooe.eventbus.EventBus;
import at.fhooe.server.listeners.LifeCycleListener;
import at.fhooe.server.listeners.NotFoundEventListener;
import at.fhooe.server.listeners.SiteInformation;

public class LifeCycleBean {

    private final EventBus eventBus;

    public LifeCycleBean(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void register() {
        eventBus.register(new SiteInformation());
        eventBus.register(new NotFoundEventListener());
        eventBus.register(new LifeCycleListener());
    }
}
