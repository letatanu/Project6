package com.example.project6;

import java.io.IOException;

public interface AirlineDumper<T extends AbstractAirline> {
    void dump(T var1) throws IOException;
}