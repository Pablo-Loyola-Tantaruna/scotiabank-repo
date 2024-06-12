package com.codechallenge.scotiabank.model.cache;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Student")
@Getter
@Setter
@Builder
public class StudentCache {

  private String id_student;

  private String name_student;

  private String last_name_student;

  private int status_student;

  private int age_student;
}
