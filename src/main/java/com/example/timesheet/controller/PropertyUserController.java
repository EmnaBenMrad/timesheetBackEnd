package com.example.timesheet.controller;


import com.example.timesheet.service.MembershipbaseService;
import com.example.timesheet.service.PropertyUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class PropertyUserController {

    @Autowired
    PropertyUserService propertyUserService;
    @Autowired
    MembershipbaseService membershipbaseService;


    @ApiOperation(value = "Lister groupName par manager")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/propertyUserList/{userName}")
    public List<String> propertyUserList(@PathVariable String userName) {
        return propertyUserService.getUserName(membershipbaseService.userListByBU(userName));
    }

    @ApiOperation(value = "Find all users ")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllUsers/")
    Map<String, String> findUserActive() {
        return propertyUserService.getLoginUserName(propertyUserService.ListeUtilisateur());
    }

}
