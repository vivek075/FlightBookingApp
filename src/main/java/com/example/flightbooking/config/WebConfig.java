package com.example.flightbooking.config;

import com.example.flightbooking.converter.StringToFlightConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* This configuration class (WebConfig) is used to register a custom converter (StringToFlightConverter) that converts String values to Flight objects.
* This is useful in a Spring MVC application where you might need to convert request parameters or form inputs to complex objects automatically.
* By adding this converter to the FormatterRegistry, Spring will automatically use it whenever necessary during the data binding process.*/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private StringToFlightConverter stringToFlightConverter;

    /* This method is used to add custom formatters and converters to the Spring MVC configuration */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        /* Registers the StringToFlightConverter with the FormatterRegistry. This tells Spring to use this converter whenever it needs to convert a String to a Flight object.*/
        registry.addConverter(stringToFlightConverter);
    }
}
