package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Branch;
import com.synergisticit.service.BranchService;
import com.synergisticit.validation.BranchValidator;

import jakarta.validation.Valid;

@Controller
public class BranchController {
    
    @Autowired BranchService branchService;   
    @Autowired BranchValidator branchValidator;
    
    @RequestMapping("branchForm")
    public String branchForm(Branch branch, Model model) {
        modelData(model);
        return "branchForm";
    }
    
    @RequestMapping("saveBranch")
    public String saveBranch(@Valid @ModelAttribute Branch branch, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        branchValidator.validate(branch, br);
        
        if (!br.hasErrors()) {
            branchService.saveBranch(branch);
            // do not need to call modelData(model) because it's in branchForm method
            return "redirect:branchForm";  // redirect to url branchForm which calls branchForm method
        }
        
        modelData(model);
        return "branchForm";  // do not redirect, keep the info entered and show error messages
    }
    
    @RequestMapping("updateBranch")
    public String updateBranch(Branch branch, Model model) {  // Branch object created with only id is passed in by request param
        Branch retrievedBranch = branchService.getBranchById(branch.getBranchId());
        model.addAttribute("retrievedBranch", retrievedBranch);
        
        modelData(model);
        return "branchForm";
    }
    
    @RequestMapping("deleteBranch")
    public String deleteBranch(Branch branch, Model model) {
        branchService.deleteBranchById(branch.getBranchId());
        
        modelData(model);
        return "redirect:branchForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("branches", branchService.getAllBranches());
    }
}
