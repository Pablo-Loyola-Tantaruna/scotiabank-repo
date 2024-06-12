package com.codechallenge.scotiabank.model.api.searchstudent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchStudentResponse {

  private String id_student;

  private String name_student;

  private String last_name_student;

  private int status_student;

  private int age_student;
}
