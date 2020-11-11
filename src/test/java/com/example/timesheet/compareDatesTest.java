package com.example.timesheet;

import com.example.timesheet.model.Imputation;
import com.example.timesheet.repository.ImputationRepository;
import org.junit.Assert; // imports from the junit.jar
import org.junit.Test;
import org.junit.runner.RunWith;
import com.example.timesheet.service.ImputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class compareDatesTest {
    @Autowired
    ImputationService imputationService;
    @Autowired
    ImputationRepository imputationRepository;
    @Autowired
    Environment environment;

    @Test // Test annotation makes this method as a test case
    public void compareDates1Test() throws ParseException {
        Double expectedImputation0 = 0.0;
        Double expectedImputation1 = 0.25;
        Double expectedImputation2 = 0.25;
        Double expectedImputation3 = 0.5;
        Double expectedImputation4 = 0.5;
        Double expectedImputation5 = 0.75;
        Double expectedImputation6 = 0.75;
        Double expectedImputation7 = 1.0;
        Double expectedImputation8 = 1.0;
        Double expectedImputation9 = 1.25;
        Double expectedImputation10 = 1.25;
        Double expectedImputation11 = 1.5;
        Double expectedImputation12 = 1.5;
        Double expectedImputation13 = 1.75;
        Double expectedImputation14 = 1.75;
        Double expectedImputationWeekEnd = 2.0;
        Double actualImputation0 = imputationService.compareDates("2020-11-06T10:35:22", "2020-11-06T11:33:30");
        Double actualImputation1 = imputationService.compareDates("2020-11-06T10:35:22", "2020-11-06T11:39:30");
        Double actualImputation2 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T12:09:40");
        Double actualImputation3 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T13:09:57");
        Double actualImputation4 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T14:09:57");
        Double actualImputation5 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T15:09:57");
        Double actualImputation6 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T16:09:57");
        Double actualImputation7 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T17:09:57");
        Double actualImputation8 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T18:09:57");
        Double actualImputation9 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T19:09:57");
        Double actualImputation11 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T21:09:57");
        Double actualImputation12 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T22:09:57");
        Double actualImputation13 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T23:09:57");
        Double actualImputation14 = imputationService.compareDates("2020-11-06T10:09:57", "2020-11-06T24:09:57");
        Double actualImputation = imputationService.compareDates("2020-09-16T08:30:00", "2020-09-16T17:30:00");
        Double actualImputationWeekEnd = imputationService.compareDates("2020-09-19T08:30:00", "2020-09-19T17:30:00");
        Assert.assertEquals(expectedImputation10, actualImputation); //compares expected and actual value
        Assert.assertEquals(expectedImputation0, actualImputation0); //compares expected and actual value
        Assert.assertEquals(expectedImputation1, actualImputation1); //compares expected and actual value
        Assert.assertEquals(expectedImputation2, actualImputation2); //compares expected and actual value
        Assert.assertEquals(expectedImputation3, actualImputation3); //compares expected and actual value
        Assert.assertEquals(expectedImputation4, actualImputation4); //compares expected and actual value
        Assert.assertEquals(expectedImputation5, actualImputation5); //compares expected and actual value
        Assert.assertEquals(expectedImputation6, actualImputation6); //compares expected and actual value
        Assert.assertEquals(expectedImputation7, actualImputation7); //compares expected and actual value
        Assert.assertEquals(expectedImputation8, actualImputation8); //compares expected and actual value
        Assert.assertEquals(expectedImputation9, actualImputation9); //compares expected and actual value
        Assert.assertEquals(expectedImputation11, actualImputation11); //compares expected and actual value
        Assert.assertEquals(expectedImputation12, actualImputation12); //compares expected and actual value
        Assert.assertEquals(expectedImputation13, actualImputation13); //compares expected and actual value
        Assert.assertEquals(expectedImputation14, actualImputation14); //compares expected and actual value
        Assert.assertEquals(expectedImputationWeekEnd, actualImputationWeekEnd); //compares expected and actual value
    }

    @Test // Test annotation makes this method as a test case
    public void findAllTest() {
        Imputation expectedImputation = new Imputation();
        expectedImputation.setIdImputation(553274);
        expectedImputation.setTitle("BD CORPORATE FORFAIT DIVERS-Divers forfaits-Gestion SPI");
        //expectedImputation.setUser()
        Imputation actualImputation = imputationService.getByIdImputation(553274);
        System.out.println("actualImputation" + actualImputation);
        Assert.assertEquals(expectedImputation.getTitle(), actualImputation.getTitle()); //compares expected and actual value
    }

    @Test
    public void getActiveProfiles() {
        for (String profileName : environment.getActiveProfiles()) {
            System.out.println("Currently active profile - " + profileName);
        }
    }


    @Test // Test annotation makes this method as a test case
    public void compareToTest() throws ParseException {
        int expectedComparation = -1;
        int comparation = imputationService.compareTo("2020-11-06T10:35:22", "2020-11-06T11:33:30");
        Assert.assertEquals(expectedComparation, comparation); //compares expected and actual value
    }

    @Test
    public void loginIsTest() {
        String expectedlogin = "mzayen";
        String actuallogin = imputationService.loginIs("MohtaSdi Zayen");
        System.out.println("actuallogin" + actuallogin);
        Assert.assertEquals(expectedlogin, actuallogin);
    }

    @Test
    public void findByTest() {
        String expectedlogin = "mzayen";
        String expectedlogin2 = "sbenkhedija";
        String actuallogin = imputationService.loginIs("MohtaSdi Zayen");
        String actuallogin2 = imputationService.loginIs("Seifeddine BEN KHEDIJA");
        System.out.println("actuallogin" + actuallogin);
        Assert.assertEquals(expectedlogin, actuallogin);
        Assert.assertEquals(expectedlogin2, actuallogin2);
    }

    @Test
    public void updateImputationTest() {
        Imputation imputationTest;
        Imputation expectedimputation;
        imputationTest = imputationRepository.findByIdImputation(2521);
        expectedimputation = imputationRepository.findByIdImputation(1303);
        expectedimputation.setValidation(1);
        expectedimputation.setValidePar("10480");
        System.out.println("expectedImputation" + expectedimputation);
        Imputation imputation = imputationService.updateImputation(1303, imputationTest);
        Assert.assertEquals(expectedimputation, imputation);
    }

}
