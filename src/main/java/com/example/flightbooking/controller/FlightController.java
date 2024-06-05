package com.example.flightbooking.controller;

import com.example.flightbooking.model.Flight;
import com.example.flightbooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
* The FlightController class is a Spring MVC controller that handles HTTP requests related to flights. It provides endpoints for listing flights, displaying forms to add or edit a flight, adding a flight, editing a flight, and deleting a flight.*/
@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    /* adds all flights to the model and returns the view name "flights". */
    @GetMapping
    public String listFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flights";
    }

    /* prepares the model with a new Flight object. */
    @GetMapping("/add")
    public String showAddFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flightForm";
    }

    /* Handles the form submission for adding a new flight.
    * Redirects the user to the list of flights after the new flight is saved.*/
    @PostMapping("/add")
    public String addFlight(@ModelAttribute Flight flight) {
        flightService.saveFlight(flight);
        return "redirect:/flights";
    }

    /* Prepares the model with the existing Flight object for the given ID.*/
    @GetMapping("/edit/{id}")
    public String showEditFlightForm(@PathVariable Long id, Model model) {
        /* Fetches the flight by its ID and adds it to the model if present. */
        flightService.getFlightById(id).ifPresent(flight -> model.addAttribute("flight", flight));
        return "flightForm";
    }

    /* Handles the form submission for editing an existing flight. */
    @PostMapping("/edit/{id}")
    public String editFlight(@PathVariable Long id, @ModelAttribute Flight flight) {
        flight.setId(id);
        flightService.saveFlight(flight);
        return "redirect:/flights";
    }

    /* Handles the deletion of a flight. */
    @GetMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return "redirect:/flights";
    }
}
