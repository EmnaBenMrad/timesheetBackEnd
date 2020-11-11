package com.example.timesheet.service;

import com.example.timesheet.model.Membershipbase;
import com.example.timesheet.model.PropertyUser;
import com.example.timesheet.repository.PropertyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.data.mongodb.core.query.Criteria.where;


@Service
public class PropertyUserService {
    @Autowired
    private PropertyUserRepository propertyUserRepository;

    private final MongoTemplate mongoTemplate;

    public PropertyUserService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /*
     * Retourner la liste des noms des utilisateurs à partir leur login
     * */
    public List<String> getUserName(List<String> logins) {
        List<String> result = new ArrayList<>();
        List<PropertyUser> propertyUserList = propertyUserRepository.findByPropertyvalueNotContaining("@");
        List<PropertyUser> propertyUserList3 = propertyUserRepository.findByPropertyvalueNotNull();
        List<PropertyUser> intersect = propertyUserList3.stream()
                .filter(propertyUserList::contains)
                .collect(Collectors.toList());
        for (String login : logins) {
            String log = String.valueOf(login.charAt(0));
            String log2 = login.substring(1);
            String log3 = log2.substring(0, 1).toUpperCase() + log2.substring(1);
            List<String> users = intersect.stream()
                    .filter(x -> (((x.getPropertyvalue().replaceAll("\\s", "").toUpperCase()).contains(log2.toUpperCase())) && (String.valueOf(x.getPropertyvalue().charAt(0))).equals(log.toUpperCase()))
                            || (((x.getPropertyvalue().replaceAll("\\s", "")).contains(log2)) && (String.valueOf(x.getPropertyvalue().charAt(0))).equals(log))
                            || (((x.getPropertyvalue().replaceAll("\\s", "")).contains(log3)) && (String.valueOf(x.getPropertyvalue().charAt(0))).equals(log.toUpperCase())))
                    .map(PropertyUser::getPropertyvalue)
                    .collect(Collectors.toList());
            for (String user : users) {
                result.add(user);
            }

        }
        return result;
    }

    /*===============Liste des utilisateurs =================*/
    public List<String> ListeUtilisateur() {
        Query query1 = new Query();
        //query1.addCriteria(where("status").in(0));
        Query query2 = new Query();
        query2.addCriteria(where("groupbase.groupname").regex("BD-users"));
        List<Membershipbase> userTest2 = mongoTemplate.find(query2, Membershipbase.class);
        return userTest2.stream()
                .map(c -> c.getUserName())
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, String> getLoginUserName(List<String> logins) {
        List<String> result = new ArrayList<>();
        Map<String, String> resultMap = new HashMap<>();
        List<PropertyUser> propertyUserList = propertyUserRepository.findByPropertyvalueNotContaining("@");
        List<PropertyUser> propertyUserList3 = propertyUserRepository.findByPropertyvalueNotNull();
        List<PropertyUser> intersect = propertyUserList3.stream()
                .filter(propertyUserList::contains)
                .collect(Collectors.toList());
        for (String login : logins) {
            String log = String.valueOf(login.charAt(0));
            String log2 = login.substring(1);
            String log3 = log2.substring(0, 1).toUpperCase() + log2.substring(1);
            List<String> users = intersect.stream()
                    .filter(x -> (((x.getPropertyvalue().replaceAll("\\s", "").toUpperCase()).contains(log2.toUpperCase())) && (String.valueOf(x.getPropertyvalue().charAt(0))).equals(log.toUpperCase()))
                            || (((x.getPropertyvalue().replaceAll("\\s", "")).contains(log2)) && (String.valueOf(x.getPropertyvalue().charAt(0))).equals(log))
                            || (((x.getPropertyvalue().replaceAll("\\s", "")).contains(log3)) && (String.valueOf(x.getPropertyvalue().charAt(0))).equals(log.toUpperCase())))
                    .map(PropertyUser::getPropertyvalue)
                    .collect(Collectors.toList());
            for (String user : users) {
                result.add(user);
                resultMap.put(login, user);
            }

        }
        return resultMap;
    }

    public Map<String, String> getUserName(String login) {
        List<String> result = new ArrayList<>();
        List<PropertyUser> propertyUserList = propertyUserRepository.findByPropertyvalueNotContaining("@");
        List<PropertyUser> propertyUserList3 = propertyUserRepository.findByPropertyvalueNotNull();

        List<PropertyUser> intersect = propertyUserList3.stream()
                .filter(propertyUserList::contains)
                .collect(Collectors.toList());

        //for (String login : logins) {
        String log = String.valueOf(login.charAt(0));
        String log2 = login.substring(1);
        String log3 = log2.substring(0, 1).toUpperCase() + log2.substring(1);
        List<String> users = intersect.stream()
                .filter(x -> (((x.getPropertyvalue().replaceAll("\\s", "").toUpperCase()).contains(log2.toUpperCase())) && (String.valueOf(x.getPropertyvalue().charAt(0))).equals(log.toUpperCase()))
                        || (((x.getPropertyvalue().replaceAll("\\s", "")).contains(log2)) && (String.valueOf(x.getPropertyvalue().charAt(0))).equals(log))
                        || (((x.getPropertyvalue().replaceAll("\\s", "")).contains(log3)) && (String.valueOf(x.getPropertyvalue().charAt(0))).equals(log.toUpperCase())))
                .map(PropertyUser::getPropertyvalue)
                .collect(Collectors.toList());

        //System.out.println("useeer " +users);
        result.addAll(users);
        return IntStream.range(0, users.size())
                .collect(
                        HashMap::new,
                        (m, i) -> m.put(users.get(i), login),
                        Map::putAll
                );
    }


    /**
     * get userEmail by login
     **/
    public List<String> getUserEmail(String login) {
        List<String> result = new ArrayList<>();
        List<PropertyUser> propertyUser = propertyUserRepository.findByPropertyvalueContaining("@");
        List<PropertyUser> propertyUserList3 = propertyUserRepository.findByPropertyvalueNotNull();
        String log = String.valueOf(login.charAt(0));
        String log2 = login.substring(1);
        String log3 = log2.substring(0, 1).toUpperCase() + log2.substring(1);
        try {
            List<String> users = propertyUser.stream()
                    .filter(x -> (((x.getPropertyvalue().contains(log2)) && (String.valueOf(x.getPropertyvalue().charAt(0))).equals(log))))
                    .map(PropertyUser::getPropertyvalue)
                    .collect(Collectors.toList());
            for (String user : users) {
                result.add(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if (login.equals("ebenmrad")) {
            result.add("bmrad.emna@gmail.com");
        }
        if (login.equals("blimem")) {
            result.clear();
            result.add("bmrad.emna@gmail.com");
        }
        //   }
        System.out.println("result" + result);
        return result;
    }

}
