package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        // send data to view via model.addAttribute
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String processSearchForm(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        // The ArrayList in JobData.java is incompatible with jobs Hashmap - so first construct an empty one
        ArrayList<HashMap<String, String>> jobs;

        // if "all", JobData.javafindByValue(searchTerm) method is executed
        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
        } else {
            // if not "all", JobData.findAll(searchType) method is executed
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);
        return "search";
        }

}
