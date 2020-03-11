package com.example.timesheet.repository;

import org.springframework.stereotype.Repository;

//@Repository
public interface Sequence {
  Double getNextSequenceId();
}
