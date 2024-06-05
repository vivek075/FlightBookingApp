package com.example.flightbooking.controller;

import com.example.flightbooking.model.Booking;
import com.example.flightbooking.service.BookingService;
import com.example.flightbooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/* The BookingController class is a Spring MVC controller that handles HTTP requests related to bookings. It provides endpoints for listing bookings, displaying a form to add a new booking, adding a booking, and deleting a booking. */
@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private FlightService flightService;

    /* Adds all bookings to the model and returns the view name "bookings".
    * Returns the name of the Thymeleaf template to be rendered (bookings.html).*/
    @GetMapping
    public String listBookings(Model model) {
        /* Adds the list of all bookings to the model so that it can be accessed in the Thymeleaf template*/
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings";
    }

    /* prepares the model with a new Booking object and a list of all flights.
    * Returns the name of the Thymeleaf template to be rendered (bookingForm.html).*/
    @GetMapping("/add")
    public String showAddBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("flights", flightService.getAllFlights());
        return "bookingForm";
    }

    /* handles the form submission for adding a new booking.*/
    @PostMapping("/add")
    public String addBooking(@ModelAttribute Booking booking) {
        booking.setBookingTime(LocalDateTime.now());
        System.out.println("Booking Time: " + booking.getBookingTime());
        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    /* handles the deletion of a booking. */
    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }
}
