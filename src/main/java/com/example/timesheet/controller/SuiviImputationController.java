package com.example.timesheet.controller;

import com.example.timesheet.model.ImputationSummaryGroup;
import com.example.timesheet.service.SuiviImputationServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class SuiviImputationController {
    @Autowired
    SuiviImputationServices suiviImputationServices;

    @GetMapping("/suiviImputations5/{year}/{month}")
    Map<String, String> getDaysList(@PathVariable int year, @PathVariable int month) {
        return suiviImputationServices.datesInMonth(year, month);
    }

    @ApiOperation(value = "Find all imputations By startDate")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/suiviImputations10/{startDate}")
    List<ImputationSummaryGroup> findAllImputationByStartDateGrouping10(@PathVariable String startDate) {
        return suiviImputationServices.listImputation(startDate);
    }


}
