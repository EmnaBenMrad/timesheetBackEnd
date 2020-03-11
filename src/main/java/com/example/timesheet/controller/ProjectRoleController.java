package com.example.timesheet.controller;


import com.example.timesheet.model.Imputation;
import com.example.timesheet.model.Projectrole;
import com.example.timesheet.repository.Sequence;
import com.example.timesheet.service.ProjectRoleService;
import com.example.timesheet.service.SequenceService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.timesheet.repository.ProjectRoleRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ProjectRoleController {
  @Autowired
  ProjectRoleRepository projectRoleRepository;
  @Autowired
  ProjectRoleService projectRoleService1;
  @Autowired
  SequenceService sequenceService;

  //private ProjectRoleService projectRoleService;

  @GetMapping("/read")
  public List<Projectrole> read() {
    return projectRoleService1.getAll();
  }

  @GetMapping("/read2")
  public List<Projectrole> read2() {
    return projectRoleRepository.findAll();
  }

  @RequestMapping(value = "/addProjectRole", method = RequestMethod.POST)
  Projectrole addProjectRole(@RequestBody Projectrole pro) {
    Projectrole i = new Projectrole();
    i.setId_role(projectRoleService1.getNext());
    i.setName(pro.getName());
    i.setDescription(pro.getDescription());
    return projectRoleService1.addProjectRole(i);
  }

  @RequestMapping(value = "/roleUpdate1", method = RequestMethod.PUT)
    // Projectrole updateImputation(@RequestBody Projectrole impu) {
  Projectrole updaterole(@RequestBody Projectrole role) {
    Projectrole pro = role;
    System.out.println("baha" + pro.getId_role());
    if (pro.getId_role() != null) {
      return projectRoleRepository.save(pro);
      //return projectRoleService1.updateImputation(role);
    } else {
      return null;
    }
  }


  @RequestMapping(value = "/roleUpdate11", method = RequestMethod.PUT)
    // Projectrole updateImputation(@RequestBody Projectrole impu) {
  Projectrole updateImputation(@RequestBody Projectrole role) throws Exception {
    Optional<Projectrole> pro;
    pro = projectRoleRepository.findById_role(role.getId_role());
    if (pro != null) {
      return pro.map(pr -> {
        pr.setId_role(role.getId_role());
        pr.setName(role.getName());
        pr.setDescription(role.getDescription());
        return projectRoleRepository.save(pr);
      }).orElseThrow(() -> new Exception("order not found with ref " + pro.get().getId_role()));
      //return projectRoleRepository.save(pro.get());
//    } else {
//      //System.out.println("zzzzzzzzzzzzz");
//      return null;
//    }
      // return null;
    } else {
      return null;
    }
  }

  @PutMapping("/orderUpdate/{id_role}")
  public Projectrole updateProduct(@PathVariable Double id_role,
                                   @Valid @RequestBody Projectrole order) throws Exception {
    Optional<Projectrole> updatedPerson = projectRoleRepository.findById_role(id_role);

    //Optional<Projectrole> updatedPerson = projectRoleRepository.findById(order.getId_role());
    System.out.println("aaaa" + updatedPerson.get());
    return updatedPerson
      .map(ordeer -> {
        // ordeer.setId_role(order.getId_role());
        ordeer.setName(order.getName());
        ordeer.setDescription(order.getDescription());

        //updatedPerson.setName(order.getName());
        // updatedPerson.setDescription(order.getDescription());
        // return projectRoleService1.updateProjectRole(ordeer);
        projectRoleRepository.save(ordeer);
        return ordeer;
      }).orElseThrow(() -> new Exception("order not found with ref " + id_role));
  }

  @PutMapping(value = "/update/{ID}")
  public String update(@PathVariable(value = "ID") Double id, @RequestBody Projectrole projectrole) {
    // logger.debug("Updating employee with employee-id= {}.", id);
    projectrole.setId_role(id);
    projectRoleService1.updateProjectRole(projectrole);
    return "role record for role-id= " + id + " updated.";
  }


  @RequestMapping(value = "/delete/{idRole}", method = RequestMethod.DELETE)
  void deleteProjet(@PathVariable Double idRole) {
    Optional<Projectrole> updatedPerson = projectRoleRepository.findById_role(idRole);
    projectRoleRepository.deleteById(updatedPerson.get().getId());

  }
}
