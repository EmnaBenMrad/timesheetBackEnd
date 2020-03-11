package com.example.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class TimesheetApplication {

  public static void main(String[] args) {
    SpringApplication.run(TimesheetApplication.class, args);
    /*http://localhost:8081/swagger-ui.html#/*/
  }

}
