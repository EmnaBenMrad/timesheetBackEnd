package com.example.timesheet.service;

import com.example.timesheet.model.ImputationSummaryGroup;
import com.example.timesheet.model.UserSummary;
import com.example.timesheet.repository.ImputationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Service
public class SuiviImputationServices {
    @Autowired
    ImputationRepository imputationRepository;

    /*
     *Retourner une map <numJour,nomJour> du mois et année sélectionnés
     */
    public Map<String, String> datesInMonth(int year, int month) {
        Map<String, String> daysInMonthMap = new HashMap<>();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month - 1, 1);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < daysInMonth; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate date = LocalDate.parse(fmt.format(cal.getTime()), formatter); // LocalDate = 2010-02-23
            DayOfWeek dow = date.getDayOfWeek();
            String output = dow.getDisplayName(TextStyle.SHORT, Locale.FRANCE);
            IntStream.range(0, daysInMonth).forEach(j -> daysInMonthMap.put(fmt.format(cal.getTime()).substring(0, 2), output));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysInMonthMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    /*
     **La liste des suivi Imputations
     */
    public List<ImputationSummaryGroup> listImputation(String startDate) {
        List<ImputationSummaryGroup> listeInputationGrouped;
        List<ImputationSummaryGroup> listeImputationUserActive = new ArrayList<>();
        listeInputationGrouped = imputationRepository.aggregateImputations(startDate);
        System.out.println("listeInputationGrouped" + listeInputationGrouped);
        String year1 = startDate.substring(0, 4);
        String month1 = startDate.substring(5, 7);
        int year = Integer.parseInt(year1);
        int month = Integer.parseInt(month1);
        Map<String, String> datesInMonth = datesInMonth(year, month);
        Map<String, String> tessst = new HashMap<>();
        List<UserSummary> listeUser = imputationRepository.aggregateUsers(startDate);
        ImputationSummaryGroup imputationSummaryGroup;
        /*liste des user qui ont des imputations --> liste user d'après la liste des imputation groupés*/
        Set<String> userNames1 =
                listeInputationGrouped.stream()
                        .map(ImputationSummaryGroup::getUserName)
                        .collect(Collectors.toSet());
        /* faire un filtre sur la liste de tous les user actives*/
        List<UserSummary> listOutput1 =
                listeUser.stream()
                        .filter(e -> !userNames1.contains(e.getUserName()))
                        .collect(Collectors.toList());

        /*Ajouter au liste des imputations groupés l listes des users actives et qui n'ont pas faits des imputations*/
        for (UserSummary userSummary : listOutput1) {
            imputationSummaryGroup = new ImputationSummaryGroup(userSummary.getUserName(), null, null, null, null, null, null, null);
            listeImputationUserActive.add(imputationSummaryGroup);
        }

        /*Organiser la map Map<String,String> joursMois avec les dates du mois,année selectionnés*/
        for (int i = 0; i < listeInputationGrouped.size(); i++) {
            /* Remplir la map JourMois par les jours du mois seléctionnées*/
            datesInMonth.forEach((k2, v) -> IntStream.range(1, datesInMonth.size()).forEach(k1 -> tessst.put(k2, "0")));
            Map<String, String> joursMois = tessst.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
            List<String> iii;
            List<Double> iii2;
            Map<String, String> info = new HashMap<>();
            iii = listeInputationGrouped.get(i).getDateList();
            iii2 = listeInputationGrouped.get(i).getImputationList();

            /* Remplir la map info qui contient les imputations et leur date*/
            for (int p = 0; p < iii.size(); p++) {
                iii.get(p).substring(8, 10);
                int finalP = p;
                IntStream.range(0, listeInputationGrouped.size()).forEach(k1 -> info.put(iii.get(finalP).substring(8, 10), iii2.get(finalP).toString().substring(0, 3)));
            }
            listeInputationGrouped.get(i).setJoursMois(joursMois);

            /*Modifier la map JourMois  par les imputations faites dans chaque jour*/
            Map<String, String> iii3;
            iii3 = listeInputationGrouped.get(i).getJoursMois();
            info.forEach((k2, v) -> {
                if (iii3.containsKey(k2)) {
                    iii3.remove(k2);
                    iii3.put(k2, v);
                }
            });
            Map<String, String> joursMois2 = iii3.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
            listeInputationGrouped.get(i).setJoursMois(joursMois2);
            listeInputationGrouped.get(i).setJoursMois(listeInputationGrouped.get(i).getJoursMois());
        }
        /*Ajout de la liste des user qui n'ont pas imputé et qui sont actif à la liste des user qui ont imputés*/
        List<ImputationSummaryGroup> newList = Stream.concat(listeInputationGrouped.stream(), listeImputationUserActive.stream())
                .collect(Collectors.toList());
        /*Organiser la liste par les noms des collborateurs   */
        newList.sort(Comparator.comparing(ImputationSummaryGroup::getUserName));
        return newList;
    }

}
