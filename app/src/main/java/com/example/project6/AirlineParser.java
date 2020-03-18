package com.example.project6;

public interface AirlineParser<T extends AbstractAirline> {
    T parse() throws ParserException;
}
