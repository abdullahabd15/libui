package com.ai.project.libui;

import java.util.ArrayList;
import java.util.List;

public class ScrollBroadcaster {
    private static List<ScrollListener> listenerList = new ArrayList<>();
    private final static Object lock = new Object();

    public static void registerReceiver(ScrollListener receiver) {
        synchronized (lock) {
            boolean isNeedToAdd = true;
            for (ScrollListener rec : listenerList) {
                if (rec.equals(receiver)) {
                    isNeedToAdd = false;
                    break;
                }
            }
            if (isNeedToAdd) {
                listenerList.add(receiver);
            }
        }
    }

    public static Boolean isListenerExist(ScrollListener receiver) {
        boolean isExist = false;
        for (ScrollListener rec : listenerList) {
            if (rec.equals(receiver)) {
                isExist = true;
                break;
            }
        }

        return isExist;

    }

    public static void unregisterAll() {
        synchronized (lock) {
            listenerList.clear();
        }
    }

    static void notifyAllReceiver() {
        for (ScrollListener receiver : listenerList) {
            if (receiver != null) {
                receiver.onScrollIdle();
            }
        }
    }
}
