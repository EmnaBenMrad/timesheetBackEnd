package com.example.timesheet.controller;


import com.example.timesheet.model.InvAppliUserRole;
import com.example.timesheet.repository.InvAppliUserRoleRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class InvAppliUserRoleController {
    @Autowired
    InvAppliUserRoleRepository invAppliUserRoleRepository;

    @ApiOperation(value = "Lister Issue")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/InvAppliUserRoleList/{idUser}")
    public List<InvAppliUserRole> jiraIssueList(@PathVariable Double idUser) {
        return invAppliUserRoleRepository.findInvAppliUserRolesByUserbase_IdUser(idUser);
    }
}
