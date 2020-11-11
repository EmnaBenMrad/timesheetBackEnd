package com.example.timesheet.repository;

import com.example.timesheet.model.ImputationSummaryGroup;
import com.example.timesheet.model.UserSummary;

import java.util.List;

public interface ImputationRepositoryCustom {
    List<UserSummary> aggregateUsers(String date);

    List<ImputationSummaryGroup> aggregateImputations(String date);

    List<String> ListeUtilisateurActive();


}
