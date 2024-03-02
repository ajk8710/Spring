package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Airport;
import com.synergisticit.service.AirportService;
import com.synergisticit.validation.AirportValidator;

import jakarta.validation.Valid;

@Controller
public class AirportController {
    
    @Autowired AirportService airportService;
    @Autowired AirportValidator airportValidator;
    
    @PreAuthorize("hasAuthority('Admin')")
    @RequestMapping("airportForm")
    public String airportForm(Airport airport, Model model) {
        modelData(model);
        return "airportForm";
    }
    
    @RequestMapping("saveAirport")
    public String saveAirport(@Valid @ModelAttribute Airport airport, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        airportValidator.validate(airport, br);
        
        if (!br.hasErrors()) {
            airportService.save(airport);
            // do not need to call modelData(model) because it's in airportForm method
            return "redirect:airportForm";  // redirect to url airportForm which calls airportForm method
        }

        modelData(model);
        return "airportForm";  // do not redirect, keep the info entered and show error messages
    }
    
    @RequestMapping("updateAirport")
    public String updateAirport(Airport airport, Model model) {  // Airport object created with only airportId is passed in by request param
        Airport retrievedAirport = airportService.getById(airport.getAirportId());
        model.addAttribute("retrievedAirport", retrievedAirport);
        
        modelData(model);
        return "airportForm";
    }
    
    @RequestMapping("deleteAirport")
    public String deleteAirport(Airport airport, Model model) {
        airportService.deleteById(airport.getAirportId());
        
        modelData(model);
        return "redirect:airportForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("airports", airportService.getAll());
    }
}
