package com.example.timesheet.service;

import com.example.timesheet.model.Imputation;
import com.example.timesheet.model.Projectrole;
import com.example.timesheet.repository.ImputationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImputationService {
  @Autowired
  private ImputationRepository imputationRepository;

  public List<Imputation> getAll() {
    return imputationRepository.findAll();
  }

  public Imputation updateImputation(Imputation impu) {
    return imputationRepository.save(impu);
  }

  public int getNext() {
    Imputation last = imputationRepository.findTopByOrderByIdDesc();
    int lastNum = last.getIdImputation();
    Imputation next = new Imputation(lastNum + 1);
    return next.getIdImputation();
  }
}
