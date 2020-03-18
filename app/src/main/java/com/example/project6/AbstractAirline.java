package com.example.project6;

import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractAirline<T extends AbstractFlight> implements Serializable {
    public AbstractAirline() {
    }

    public abstract String getName();

    public abstract void addFlight(T var1);

    public abstract Collection<T> getFlights();

    public final String toString() {
        String var10000 = this.getName();
        return var10000 + " with " + this.getFlights().size() + " flights";
    }
}
