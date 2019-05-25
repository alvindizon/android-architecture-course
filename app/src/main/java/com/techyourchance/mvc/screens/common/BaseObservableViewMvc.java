package com.techyourchance.mvc.screens.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseObservableViewMvc <ListenerType> extends BaseViewMvc
        implements ObservableViewMvc<ListenerType> {

    private Set<ListenerType> listeners = new HashSet<>();

    @Override
    public void registerListener(ListenerType listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterListener(ListenerType listener) {
        listeners.remove(listener);
    }

    /**
     * Returns the all the listeners as an unmodifiable set
     * @return Set of ListenerType
     */
    protected Set<ListenerType> getListeners() {
        return Collections.unmodifiableSet(listeners);
    }
}
