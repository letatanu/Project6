package com.example.project6;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractFlight implements Serializable {
    public AbstractFlight() {
    }

    public abstract int getNumber();

    public abstract String getSource();

    public Date getDeparture() {
        return null;
    }

    public abstract String getDepartureString();

    public abstract String getDestination();

    public Date getArrival() {
        return null;
    }

    public abstract String getArrivalString();

    public final String toString() {
        int var10000 = this.getNumber();
        return "Flight " + var10000 + " departs " + this.getSource() + " at " + this.getDepartureString() + " arrives " + this.getDestination() + " at " + this.getArrivalString();
    }
}
