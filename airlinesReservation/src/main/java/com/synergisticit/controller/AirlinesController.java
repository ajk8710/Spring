package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Airlines;
import com.synergisticit.service.AirlinesService;
import com.synergisticit.validation.AirlinesValidator;

import jakarta.validation.Valid;

@Controller
public class AirlinesController {
    
    @Autowired AirlinesService airlinesService;
    @Autowired AirlinesValidator airlinesValidator;
    
    @PreAuthorize("hasAuthority('Admin')")
    @RequestMapping("airlinesForm")
    public String airlinesForm(Airlines airlines, Model model) {
        modelData(model);
        return "airlinesForm";
    }
    
    @RequestMapping("saveAirlines")
    public String saveAirlines(@Valid @ModelAttribute Airlines airlines, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        airlinesValidator.validate(airlines, br);
        
        if (!br.hasErrors()) {
            airlinesService.save(airlines);
            // do not need to call modelData(model) because it's in airlinesForm method
            return "redirect:airlinesForm";  // redirect to url airlinesForm which calls airlinesForm method
        }

        modelData(model);
        return "airlinesForm";  // do not redirect, keep the info entered and show error messages
    }
    
    @RequestMapping("updateAirlines")
    public String updateAirlines(Airlines airlines, Model model) {  // Airlines object created with only airlinesId is passed in by request param
        Airlines retrievedAirlines = airlinesService.getById(airlines.getAirlinesId());
        model.addAttribute("retrievedAirlines", retrievedAirlines);
        
        modelData(model);
        return "airlinesForm";
    }
    
    @RequestMapping("deleteAirlines")
    public String deleteAirlines(Airlines airlines, Model model) {
        airlinesService.deleteById(airlines.getAirlinesId());
        
        modelData(model);
        return "redirect:airlinesForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("airlinesList", airlinesService.getAll());
    }
}
