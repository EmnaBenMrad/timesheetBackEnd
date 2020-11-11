package com.example.timesheet.service;

import com.example.timesheet.model.*;
import com.example.timesheet.repository.ImputationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImputationService {
    @Autowired
    private ImputationRepository imputationRepository;


    public Imputation getByIdImputation(int test) {
        return imputationRepository.findByIdImputation(test);
    }

    public int getNext() {
        Imputation last = imputationRepository.findTopByOrderByIdDesc();
        int lastNum = last.getIdImputation();
        Imputation next = new Imputation(lastNum + 1);
        return next.getIdImputation();
    }

    public Double compareDates(String sDate1, String sDate2) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sDate1);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sDate2);
        long diff = endDate.getTime() - startDate.getTime();
        long diffDay = diff / (24 * 60 * 60 * 1000);
        diff = diff - (diffDay * 24 * 60 * 60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        if (startDate.getDay() == 0 || startDate.getDay() == 6) {
            return 2.0;
        } else {
            switch ((int) diffHours) {
                case 1:
                case 2:
                    return 0.25;
                case 3:
                case 4:
                    return 0.5;
                case 5:
                case 6:
                    return 0.75;
                case 7:
                case 8:
                    return 1.0;
                case 9:
                case 10:
                    return 1.25;
                case 11:
                case 12:
                    return 1.5;
                case 13:
                case 14:
                    return 1.75;
                default:
                    return 0.0;
            }
        }
    }

    /***Renvoyer la différence entre deux dates***/
    public int compareTo(String sDate, String eDate) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sDate);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(eDate);
        return startDate.compareTo(endDate);
    }

    /***Renvoyer le login qui convient à l'utilisateur (username) entrée***/
    public String loginIs(String username) {
        String login = null;
        String[] sp = username.split(" ");
        long spaces = username.chars().filter(c -> c == (int) ' ').count();
        String firstLetter = String.valueOf(username.charAt(0)).toLowerCase();
        if (spaces == 1) {
            login = firstLetter + sp[1].toLowerCase();
        } else if (spaces == 2) {
            login = firstLetter + sp[1].toLowerCase() + sp[2].toLowerCase();
        }
        return login;
    }

    /******Update Imputation*****/
    public Imputation updateImputation(int idImputation, Imputation impuationRequest) {
        Imputation updatedImputation = imputationRepository.findByIdImputation(idImputation);
        updatedImputation.setValidation(impuationRequest.getValidation());
        updatedImputation.setValidePar(impuationRequest.getValidePar());
        return imputationRepository.save(updatedImputation);
    }

    /****Liste des imputations qui appartiennent à l'intervalle de dates données****/
    public List<Imputation> findByIntervalleDates(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<String> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start.toString());
            start = start.plusDays(1);
        }
        return imputationRepository.findByStartDateIn(totalDates);
    }

    /****Liste des imputations qui appartiennent à l'intervalle de dates données****/
    public List<Imputation> findBy(String startDate, String endDate, String project, String username) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<String> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start.toString());
            start = start.plusDays(1);
        }
        return imputationRepository.findImputationsByStartDateInAndProject_PnameOrStartDateInAndUser_UsernameOrStartDateInAndProject_PnameAndUser_Username(totalDates, project, totalDates, loginIs(username), totalDates, project, loginIs(username));
    }


    public List<String> groupNameList(List<Imputation> liste) {
        List<String> projectNameList = liste.stream()
                .distinct()
                .map(Imputation::getProject)
                .map(Project::getPname)
                .distinct()
                .collect(Collectors.toList());
        return projectNameList;
    }


}
