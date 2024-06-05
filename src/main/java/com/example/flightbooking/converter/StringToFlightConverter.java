package com.example.flightbooking.converter;

import com.example.flightbooking.model.Flight;
import com.example.flightbooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/*
* This class is responsible for registering a custom converter, StringToFlightConverter, which converts a String to a Flight object.
* */
@Component
public class StringToFlightConverter implements Converter<String, Flight> {

    @Autowired
    private FlightService flightService;

    @Override
    public Flight convert(String source) {
        try {
            Long id = Long.parseLong(source);
            return flightService.getFlightById(id).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
