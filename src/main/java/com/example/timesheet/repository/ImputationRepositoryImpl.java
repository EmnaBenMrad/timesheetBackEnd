package com.example.timesheet.repository;

import com.example.timesheet.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

public class ImputationRepositoryImpl implements ImputationRepositoryCustom {

    private final MongoTemplate mongoTemplate;


    @Autowired
    public ImputationRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    /***Liste des utilisateurs Active***/
    @Override
    public List<String> ListeUtilisateurActive() {
        Query query1 = new Query();
        query1.addCriteria(where("status").in(0));
        Query query2 = new Query();
        query2.addCriteria(where("groupbase.groupname").regex("BD-users"));
        List<Membershipbase> userTest2 = mongoTemplate.find(query2, Membershipbase.class);
        return userTest2.stream()
                .map(Membershipbase::getUserName)
                .distinct()
                .collect(Collectors.toList());
    }

    /*** Liste des users groupés***/
    @Override
    public List<UserSummary> aggregateUsers(String date) {
        String dateTest = "^" + date;
        /*requete pour avoir les users actives*/
        Query query1 = new Query();
        query1.addCriteria(where("status").in(0));
        List<Userbasestatus> userTest1 = mongoTemplate.find(query1, Userbasestatus.class);
        List<Double> ListeUser = userTest1.stream()
                .map(Userbasestatus::getUser)
                .distinct()
                .collect(Collectors.toList());
        /*requete pour avoir les users qui sont des BD-users*/
        Query query2 = new Query();
        query2.addCriteria(where("groupbase.groupname").regex("BD-users"));
        List<Membershipbase> userTest2 = mongoTemplate.find(query2, Membershipbase.class);
        List<String> ListeUserName = userTest2.stream()
                .map(Membershipbase::getUserName)
                .distinct()
                .collect(Collectors.toList());
        /*requete pour avoir la liste des imputations qui ont startDate la date entrée */
        Query query3 = new Query();
        query3.addCriteria(where("startDate").regex(dateTest));
        List<Imputation> userTest3 = mongoTemplate.find(query3, Imputation.class);
        List<Double> ListeImputation = userTest3.stream()
                .map(c -> c.getUser().getIdUser())
                .distinct()
                .collect(Collectors.toList());
        // Group
        GroupOperation groupOperation = group("username")
                .last("username").as("userName")
                .last("idUser").as("userId");

        Aggregation aggregation = newAggregation(match(new Criteria().orOperator(Criteria.where("idUser").in(ListeImputation), Criteria.where("username").in(ListeUserName).and("idUser").nin(ListeUser))), groupOperation, sort(ASC, previousOperation(), "username"));

        AggregationResults<UserSummary> aggregationResults = mongoTemplate.aggregate(aggregation, Userbase.class, UserSummary.class);
        return aggregationResults.getMappedResults();
    }

    /***
     * Retourner une liste du modèle ImputationSummaryGroup :
     * une liste des imputations groupés par user, projet et jiraIssue
     *  qui ont la date entrée   ***/
    @Override
    public List<ImputationSummaryGroup> aggregateImputations(String date) {
        String dateTest = "^" + date;
        Criteria dateCriteria = where("startDate").regex(dateTest);
        MatchOperation matchOperation = match(dateCriteria);
        // Group :  créer groupOperation avec les différents fields
        GroupOperation groupOperation = group("user.username", "project.pname", "jiraissue.summary")
                .last("user.username").as("userName")
                .last("project.pname").as("project")
                .last("jiraissue.summary").as("jiraissueList")
                .push("imputation").as("imputationList")
                .push("startDate").as("dateList")
                .last("jiraissue.pkey").as("pkeyIssue")
                .sum("imputation").as("totalImputations");

        // créer aggregation avec les objets user, project et jiraissue tout en triant la liste des imputation par username
        Aggregation aggregation = newAggregation(matchOperation, unwind("user"), unwind("project"), unwind("jiraissue"), groupOperation, sort(ASC, previousOperation(), "user.username"));

        AggregationResults<ImputationSummaryGroup> aggregationResults = mongoTemplate.aggregate(aggregation, Imputation.class, ImputationSummaryGroup.class);

        return aggregationResults.getMappedResults();
    }


}



