package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.service.AirlinesService;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.FlightService;
import com.synergisticit.validation.FlightValidator;

import jakarta.validation.Valid;

@Controller
public class FlightController {
    
    @Autowired FlightService flightService;
    @Autowired AirlinesService airlinesService;
    @Autowired AirportService airportService;
    @Autowired FlightValidator flightValidator;
    
    @RequestMapping("flightForm")
    public String flightForm(Flight flight, Model model) {
        modelData(model);
        return "flightForm";
    }
    
    @RequestMapping("saveFlight")
    public String saveFlight(@Valid @ModelAttribute Flight flight, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        flightValidator.validate(flight, br);
        
        if (!br.hasErrors()) {
            flightService.save(flight);
            // do not need to call modelData(model) because it's in flightForm method
            return "redirect:flightForm";  // redirect to url flightForm which calls flightForm method
        }

        modelData(model);
        return "flightForm";  // do not redirect, keep the info entered and show error messages
    }
    
    @RequestMapping("updateFlight")
    public String updateFlight(Flight flight, Model model) {  // Flight object created with only flightId is passed in by request param
        Flight retrievedFlight = flightService.getById(flight.getFlightId());
        model.addAttribute("retrievedFlight", retrievedFlight);
        
        Airlines selectedAirlines = retrievedFlight.getOperatingAirlines();
        model.addAttribute("selectedAirlines", selectedAirlines);
        
        Airport selectedDepartureCity = retrievedFlight.getDepartureCity();
        model.addAttribute("selectedDepartureCity", selectedDepartureCity);
        
        Airport selectedArrivalCity = retrievedFlight.getArrivalCity();
        model.addAttribute("selectedArrivalCity", selectedArrivalCity);
        
        modelData(model);
        return "flightForm";
    }
    
    @RequestMapping("deleteFlight")
    public String deleteFlight(Flight flight, Model model) {
        flightService.deleteById(flight.getFlightId());
        
        modelData(model);
        return "redirect:flightForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("flights", flightService.getAll());
        model.addAttribute("airlinesList", airlinesService.getAll());
        model.addAttribute("airports", airportService.getAll());
    }
}
