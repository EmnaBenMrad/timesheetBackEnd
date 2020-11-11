package com.example.timesheet.controller;

import com.example.timesheet.model.Groupbase;
import com.example.timesheet.service.GroupbaseService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class GroupbaseController {
    @Autowired
    private GroupbaseService groupbaseService;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    /***Lister***/
    @ApiOperation(value = "Lister les groupes")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/groupbaseList")
    List<Groupbase> groupbaseList() {
        return groupbaseService.getAll();
    }

    /***Ajout***/
    @ApiOperation(value = "Creer nouveau groupe")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/addGroupe", method = RequestMethod.POST)
    Groupbase addGroupe(@RequestBody Groupbase groupe) {
        logger.info("processing authentication for '{}'", "list groupe");
        return groupbaseService.addGroupe(groupe);
    }

    /***Mise à jour***/
    @ApiOperation(value = "Mise à jour un groupe existant")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/groupeUpdate/{id}")
    public Groupbase updateImputation(@PathVariable String id,
                                      @RequestBody Groupbase groupbaseRequest) {
        return groupbaseService.updateGroupe(id, groupbaseRequest);
    }

    /***Supprimer***/
    @ApiOperation(value = "Supprimer un groupe par ID")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/deleteGroupe/{id}", method = RequestMethod.DELETE)
    void deleteImputation(@PathVariable String id) {
        groupbaseService.deleteGroupe(id);
    }
}
