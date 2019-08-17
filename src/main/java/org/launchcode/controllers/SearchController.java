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
        // ListController creates the hashmap object columnChoices which has the above choices
        // Uses the ListController hashmap columnChoices to send ("All", "position type", etc)
        // to the view via search.html Spring associates data sent through name "columns" in model.addAttribute
        // "columns" here = data ${columns} in search.html
        // After form input there, th:action="@{/search/results} sends control below to
        // @RequestMapping(value = "results") with searchType ("all" , "position type", etc.)
        // and searchTerm inputs.
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String processSearchForm(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        // data that is parsed by JobData.java is an ArrayList and jobs uses a Hashmap - gives a conversion error.
        // So to make it compatible with the ArrayList<HashMap<String, String>> below, first construct an empty
        // ArrayList<Hashmap<String, String>>
        ArrayList<HashMap<String, String>> jobs;

        // if "all", JobData.javafindByValue(searchTerm) method is executed with search term and returns the
        // hashmap "jobs" with data from there
        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
        } else {
            // if not "all", JobData.findAll(searchType) method is executed and returns the ArrayList
            // items with data
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);
        return "search";
        }

}
