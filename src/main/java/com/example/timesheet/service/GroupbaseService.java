package com.example.timesheet.service;

import com.example.timesheet.model.Groupbase;
import com.example.timesheet.repository.GroupbaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupbaseService {
    @Autowired
    private GroupbaseRepository groupbaseRepository;

    /***Afficher les groupes ***/
    public List<Groupbase> getAll() {
        return groupbaseRepository.findAll();
    }

    /***Générate IdGroupbase***/
    public Double getNext() {
        Groupbase last = groupbaseRepository.findTopByOrderByIdDesc();
        Double lastNum = last.getIdGroupbase();
        Groupbase next = new Groupbase(lastNum + 1);
        return next.getIdGroupbase();
    }

    /***Ajouter un groupe***/
    public Groupbase addGroupe(Groupbase groupbase) {
        Groupbase groupbaseAdded = new Groupbase();
        groupbaseAdded.setIdGroupbase(getNext());
        groupbaseAdded.setGroupname(groupbase.getGroupname());
        groupbaseAdded.setStatut(groupbase.getStatut());
        return groupbaseRepository.save(groupbaseAdded);
    }

    /***modifier un groupe***/
    public Groupbase updateGroupe(String id, Groupbase groupbase) {
        Groupbase groupbaseToUpdate = groupbaseRepository.findGroupbaseById(id);
        groupbaseToUpdate.setGroupname(groupbase.getGroupname());
        groupbaseToUpdate.setStatut(groupbase.getStatut());
        return groupbaseRepository.save(groupbaseToUpdate);
    }

    /***Supprimer un projet non imputable de la liste***/
    public void deleteGroupe(String id) {
        groupbaseRepository.deleteGroupbaseById(id);
    }

}
