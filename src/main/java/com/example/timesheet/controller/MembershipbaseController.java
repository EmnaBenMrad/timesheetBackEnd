package com.example.timesheet.controller;


import com.example.timesheet.model.Membershipbase;
import com.example.timesheet.service.MembershipbaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class MembershipbaseController {


    @Autowired
    MembershipbaseService membershipbaseService;

    /***Lister***/
    @ApiOperation(value = "Lister les groupes")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/membershipbaseList")
    List<Membershipbase> membershipbaseList() {
        return membershipbaseService.ListeUtilisateurDistinct();
    }

    @ApiOperation(value = "Lister groupName par user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/membershipbaseList/{userName}")
    public List<String> membershipbaseList(@PathVariable String userName) {
        return membershipbaseService.groupBU(userName);
    }

    @ApiOperation(value = "Tester si le user est un manager ou pas ")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/membershipbaseManager/{userName}")
    public boolean isManger(@PathVariable String userName) {
        return membershipbaseService.groupManager(userName);
    }

    @ApiOperation(value = "Lister users by BU")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/membershipbaseUser/{userName}")
    public List<String> membershipbaseUsers(@PathVariable String userName) {
        return membershipbaseService.userListByBU(userName);
    }

}
