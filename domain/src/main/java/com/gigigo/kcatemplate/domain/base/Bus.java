package com.gigigo.kcatemplate.domain.base;

/**
 * Created by Omar on 6/6/17.
 */

public interface Bus {
    void post(Object event);
    void register(Object observer);
    void unregister(Object observer);
}
