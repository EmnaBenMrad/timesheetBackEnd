package com.example.timesheet.controller;


import com.example.timesheet.model.*;
import com.example.timesheet.repository.*;
import com.example.timesheet.service.ImputationService;
import com.example.timesheet.service.PropertyUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class ImputationController {
    @Autowired
    ImputationService imputationService;

    @Autowired
    ImputationRepository imputationRepository;
    @Autowired
    JiraIssueRepository jiraIssueRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectRoleRepository projectRoleRepository;
    @Autowired
    UserbaseRepository userbaseRepository;
    @Autowired
    InvAppliUserRoleRepository invAppliUserRoleRepository;
    @Autowired
    PropertyUserService propertyUserService;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());


    @ApiOperation(value = "Find all imputations")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/imputations")
    List<Imputation> findAllImputation() {
        return imputationRepository.findAll();
    }

    @ApiOperation(value = "Find all imputations By User")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/imputations/{idUser}")
    List<Imputation> findAllImputationByUser(@PathVariable Double idUser) {
        return imputationRepository.findImputationsByUser_IdUser(idUser);
    }

    @ApiOperation(value = "Find all imputations By User")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/imputationsByUserName/{username}")
    List<Imputation> findAllImputationByUserName(@PathVariable String username) {
        return imputationRepository.findByUser_UsernameContaining(imputationService.loginIs(username));
    }

    @ApiOperation(value = "Find all imputations By startDate")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/suiviImputations/{hh}")
    List<Imputation> findAllImputationByStartDate(@PathVariable String hh) {
        return imputationRepository.findByStartDateIsStartingWith(hh);
    }


    @ApiOperation(value = "Find all imputations By Iterval of date")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getImputation/{startDate}/{endDate}")
    List<String> findProjectsImputationByStartAndEndDate(@PathVariable String startDate, @PathVariable String endDate) {
        return imputationService.groupNameList(imputationService.findByIntervalleDates(startDate, endDate));
    }

    @ApiOperation(value = "Find all users actives")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getUserActive/")
    List<String> findUserActive() {
        return propertyUserService.getUserName(imputationRepository.ListeUtilisateurActive());
    }


    @ApiOperation(value = "Find all imputations By Iterval of date")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getImputation/{startDate}/{endDate}/{project}/{username}")
    List<Imputation> findProjectsImputationBy(@PathVariable String startDate, @PathVariable String endDate, @PathVariable String project, @PathVariable String username) {
        return imputationService.findBy(startDate, endDate, project, username);
    }


    @ApiOperation(value = "Find all imputations By Iterval of date")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getImputation/{startDate}/{endDate}/{username}")
    List<Imputation> findProjectsImputationBy2(@PathVariable String startDate, @PathVariable String endDate, @PathVariable String username, @RequestBody ResquestModel resquestModel) {
        return imputationService.findBy(startDate, endDate, resquestModel.getProject(), username);
    }


    @ApiOperation(value = "Find all imputations By Iterval of date")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/getImputationsRapport")
    List<Imputation> findProjectsImputationBy3(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("project") String project, @RequestParam("username") String username) {
        ObjectMapper mapper = new ObjectMapper();
        String project1 = mapper.convertValue(project, String.class);
        return imputationService.findBy(startDate, endDate, project1, username);
    }


    @ApiOperation(value = "Create a new imputation")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/addImputation", method = RequestMethod.POST)
    Imputation addImputation(@RequestBody Imputation imp, HttpServletResponse response) throws IOException {
        logger.info("processing authentication for '{}'", "list imputations");
        Imputation imputation = new Imputation();
        imputation.setIdImputation(imputationService.getNext());
        imputation.setTitle(imp.getTitle());
        imputation.setJiraissue(jiraIssueRepository.findByIdIssue(imp.getJiraissue().getIdIssue()));
        imputation.setProject(projectRepository.findByIdProject(imp.getProject().getIdProject()));
        imputation.setUser(userbaseRepository.findByIdUser(imp.getUser().getIdUser()));
        try {
            String Sdate = (imp.getStartDate()).substring(0, 19);
            String Edate = (imp.getEndDate()).substring(0, 19);
            Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Sdate);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Edate);
            startDate.setHours(startDate.getHours() + 2);
            endDate.setHours(endDate.getHours() + 2);
            Format formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String start = formatter.format(startDate);
            String end = formatter.format(endDate);
            imputation.setStartDate(start);
            imputation.setEndDate(end);
            imputation.setImputation(imputationService.compareDates(start, end));

            imputation.setValidation(0);
            imputation.setCommentaire(imputation.getCommentaire());
            if ((imputation.getProject() != null) && imputation.getJiraissue() != null) {
                try {
                    imputation.setRole((invAppliUserRoleRepository.findInvAppliUserRoleByProject_IdProjectAndUserbase_IdUser(imp.getProject().getIdProject(), imp.getUser().getIdUser())).getProjectrole());
                } catch (Exception ex) {
                    imputation.setRole(projectRoleRepository.findByIdRole(10000.0));
                }
                int compare = imputationService.compareTo(start, end);
                if (compare < 0) {
                    return imputationRepository.save(imputation);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Il faut avoir une date de début inférieur à celle de fin!");
                    return imputation;
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le projet et la tâche sont obligatoires!");
                return imputation;
            }
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Vérifier les heures d'imputation!");
            return imputation;
        }
    }


    @ApiOperation(value = "Update an existing imputation")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/imputationUpdate/{idImputation}")
    public Imputation updateImputation(@PathVariable int idImputation,
                                       @RequestBody Imputation impuationRequest) {
        return imputationService.updateImputation(idImputation, impuationRequest);
    }

    @ApiOperation(value = "Delete imputation by ID")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/deleteImputation/{idImputation}", method = RequestMethod.DELETE)
    void deleteImputation(@PathVariable int idImputation) {
        Imputation deletedImputation = imputationRepository.findByIdImputation(idImputation);
        imputationRepository.deleteByIdImputation(deletedImputation.getIdImputation());
    }


    @ApiOperation(value = "Find all imputations By Iterval of date")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getImputationSummary/{startDate}")
    List<ImputationSummaryGroup> findImputationSummaryGroup(@PathVariable String startDate) {
        return imputationRepository.aggregateImputations(startDate);
    }


}
