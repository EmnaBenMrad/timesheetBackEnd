package com.example.timesheet.controller;


import com.example.timesheet.model.Imputation;
import com.example.timesheet.model.Projectrole;
import com.example.timesheet.repository.ImputationRepository;
import com.example.timesheet.repository.InvAppliUserRoleRepository;
import com.example.timesheet.repository.JiraIssueRepository;
import com.example.timesheet.repository.ProjectRoleRepository;
import com.example.timesheet.service.ImputationService;
import com.sun.corba.se.spi.ior.ObjectId;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//@RequestMapping(value = "/imputation")
public class ImputationController {
  @Autowired
  ImputationService imputationService;
  @Autowired
  ImputationRepository imputationRepository;
  @Autowired
  private ProjectRoleRepository projectRoleRepository;
  @Autowired
  JiraIssueRepository jiraIssueRepository;
  @Autowired
  InvAppliUserRoleRepository invAppliUserRoleRepository;

  List<Imputation> imputations;
  private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());


  //@RequestMapping(value= "/imputations", method = RequestMethod.GET)
  @ApiOperation(value = "Find all imputations")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/imputations")
  List<Imputation> findAllImputation() {
    return imputationRepository.findAll();
//    imputations = imputationService.getAll();
//    if (imputations != null) {
//      return imputations;
//    } else {
//      logger.info("processing  for '{}'", "null list  imputations ");
//      return null;
//    }
  }


  @ApiOperation(value = "Create a new imputation")
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/addImputation2", method = RequestMethod.POST)
  Imputation addImputation2(@RequestBody Imputation imp) {
    logger.info("processing authentication for '{}'", "list articles");
    Imputation imputation = new Imputation();
    imputation.setIdImputation(imputationService.getNext());
    imputation.setJiraissue(jiraIssueRepository.findByIdIssue(imp.getJiraissue().getIdIssue()));
    imputation.setInvappliuserrole(invAppliUserRoleRepository.findInvAppliUserRoleByProjectIdtAndUserbaseId(imp.getInvappliuserrole().getProject().getIdProject(), imp.getInvappliuserrole().getUserbase().getIdUser()));
    imputation.setImputation(imp.getImputation());
    imputation.setDate(imp.getDate());
    imputation.setCommentaire(imp.getCommentaire());
    return imputationRepository.save(imputation);
  }

  @RequestMapping(value = "/impuUpdate", method = RequestMethod.PUT)
  Imputation updateImputation(@RequestBody Imputation impu) {
    Imputation pro = impu;
    if (pro != null) {
      return imputationService.updateImputation(impu);
    } else {
      return null;
    }
  }

  @ApiOperation(value = "Update an existing imputation")
  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/imputationUpdate/{idImputation}")
  public Imputation updateProduct(@PathVariable int idImputation,
                                  @Valid @RequestBody Imputation impuationRequest) throws Exception {
    Imputation updatedImputation = imputationRepository.findByIdImputation(idImputation);
    updatedImputation.setImputation(impuationRequest.getImputation());
    return imputationRepository.save(updatedImputation);
  }

  @ApiOperation(value = "Delete imputation by ID")
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/deleteImputation/{idImputation}", method = RequestMethod.DELETE)
  void deleteImputation(@PathVariable int idImputation) {
    Imputation deletedImputation = imputationRepository.findByIdImputation(idImputation);
    imputationRepository.deleteById(deletedImputation.getId());
  }


}
