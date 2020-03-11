package com.example.timesheet.service;

import com.example.timesheet.model.Projectrole;
import com.example.timesheet.repository.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.sound.midi.InvalidMidiDataException;

//import org.springframework.data.mongodb.repository.Query;
@Service
public class SequenceService {
  @Autowired
  private MongoOperations mongoOperation;
  //@Autowired
  Sequence sequence;

  public Double getNextSequenceId() {
    Double key = null;
    //get sequence id
    Query query = new Query(Criteria.where("role_id").is(key));

    //increase sequence id by 1
    Update update = new Update();
    update.inc("seq", 1);

    //return new increased id
    FindAndModifyOptions options = new FindAndModifyOptions();
    options.returnNew(true);

    //this is the magic happened.
    Projectrole seqId =
      mongoOperation.findAndModify(query, update, options, Projectrole.class);

    //if no id, throws SequenceException
    //optional, just a way to tell user when the sequence id is failed to generate.
    if (seqId == null) {
      try {
        throw new SequenceException("Unable to get sequence id for key : " + key);
      } catch (SequenceException e) {
        e.printStackTrace();
      }
    }

    return seqId.getId_role();

  }
}
