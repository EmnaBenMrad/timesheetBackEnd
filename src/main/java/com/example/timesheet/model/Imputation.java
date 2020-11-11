package com.example.timesheet.model;

import java.util.Date;
import java.util.List;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "imputation")
public class Imputation {

    @Id
    private String id;
    private int idImputation;
    private String title;
    // private String date;
    private Double imputation;
    private Double raf;
    private int validation;
    private String commentaire;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String startDate;
    //private String  Date;
    //private Date dateValidation;
    // private Date dateImputation;
    private String endDate;
    private String validePar;
    private Jiraissue jiraissue;
    private Project project;
    private Userbase user;
    private Projectrole role;

    // private InvAppliUserRole invAppliUserRole;


    public Imputation(int i) {
        this.idImputation = i;
    }

    public Imputation() {
    }


}
