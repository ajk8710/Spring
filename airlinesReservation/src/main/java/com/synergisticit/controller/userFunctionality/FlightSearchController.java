package com.synergisticit.controller.userFunctionality;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.FlightService;


@Controller
public class FlightSearchController {
    
    @Autowired FlightService flightService;
    @Autowired AirportService airportService;
    
    @RequestMapping("flightSearch")
    public String searchFlight(Flight flight, Model model) {
        modelData(model);
        return "flightSearch";
    }
    
    @RequestMapping("flightSearchResults")
    public String flightSearchResults(Flight flight, Model model) {  // flight object with selected departure and arrival is created when submitting the form from jsp
        
        Airport selectedDepartureCity = flight.getDepartureCity();
        Airport selectedArrivalCity = flight.getArrivalCity();
        
        Long departureId = selectedDepartureCity.getAirportId();
        Long arrivalId = selectedArrivalCity.getAirportId();
        
        // Have a method to get a list of flights with given departure and arrival flights on repository, either by nomenclature or query.
        List<Flight> listOfSearchedFlights = flightService.findByDepartureIdAndArrivalId(departureId, arrivalId);
        
        model.addAttribute("selectedDepartureCity", selectedDepartureCity);
        model.addAttribute("selectedArrivalCity", selectedArrivalCity);
        model.addAttribute("listOfSearchedFlights", listOfSearchedFlights);
        
        modelData(model);
        return "flightSearch";
    }
    
    private void modelData(Model model) {
        model.addAttribute("airports", airportService.getAll());
    }
}
