package com.example.timesheet.service;

import com.example.timesheet.model.Groupbase;
import com.example.timesheet.model.Membershipbase;
import com.example.timesheet.repository.MembershipbaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class MembershipbaseService {
    @Autowired
    private MembershipbaseRepository membershipbaseRepository;
    private final MongoTemplate mongoTemplate;

    public MembershipbaseService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /***Afficher les groupes ***/
    public List<Membershipbase> getAll() {
        return membershipbaseRepository.findAll();
    }

    public List<Membershipbase> getGroupName(String userName) {
        return membershipbaseRepository.findByUserNameEquals(userName);
    }


    List<String> groupNameList(List<Membershipbase> liste) {
        List<String> groupNameList = new ArrayList<>();
        for (Membershipbase membershipbase : liste) {
            groupNameList.add(membershipbase.getGroupbase().getGroupname());
        }
        return groupNameList;
    }

    /*===============Liste des utilisateurs =================*/
    public List<Membershipbase> ListeUtilisateurDistinct() {
        Query query1 = new Query();
        //query1.addCriteria(where("status").in(0));
        Query query2 = new Query();
        query2.addCriteria(where("groupbase.groupname").regex("BD-users"));
        List<Membershipbase> userTest2 = mongoTemplate.find(query2, Membershipbase.class);
        return userTest2.stream()
                //.map(c -> c.getUserName())
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * La liste de BU par user
     **/
    public List<String> groupBU(String userName) {
        //la liste memberShip en fonction de userName
        List<Membershipbase> membershipbaseList = membershipbaseRepository.findByUserNameEquals(userName);
        //Retourner la liste des BU
        return membershipbaseList.stream()
                .filter(x -> (x.getGroupbase().getGroupname()).contains("BU"))
                .map(Membershipbase::getGroupbase)
                .map(Groupbase::getGroupname)
                .collect(Collectors.toList());
    }

    /**
     * tester si le user connecté est manager
     **/
    public boolean groupManager(String userName) {
        //la liste memberShip en fonction de userName
        List<Membershipbase> membershipbaseList = membershipbaseRepository.findByUserNameEquals(userName);
        //Tester si le user connecté est un manager
        return membershipbaseList.stream()
                .anyMatch(x -> (x.getGroupbase().getGroupname()).equals("BD-mangers"));
    }

    /*============= La liste des user par BU=============*/
    public List<String> userListByBU(String userName) {
        //la liste memberShip en fonction de groupBUlist
        List<Membershipbase> membershipbaseList = membershipbaseRepository.findByGroupbase_GroupnameInAndGroupbase_Statut(groupBU(userName), 1);
        //Retourner la liste des users
        return membershipbaseList.stream()
                .map(Membershipbase::getUserName)
                .collect(Collectors.toList());
    }

}
