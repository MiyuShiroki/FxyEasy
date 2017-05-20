package com.fxy.frame.event;


import org.greenrobot.eventbus.EventBus;

/**
 * EventBus 工厂
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public class BusFactory {
    private static EventBus bus;

    public static EventBus getBus() {
        if (bus == null) {
            synchronized (BusFactory.class) {
                if (bus == null) {
                    bus =EventBus.getDefault();
                }
            }
        }
        return bus;
    }
}
