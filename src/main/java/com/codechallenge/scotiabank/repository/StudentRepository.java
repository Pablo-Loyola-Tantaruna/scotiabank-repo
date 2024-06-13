package com.codechallenge.scotiabank.repository;

import com.codechallenge.scotiabank.model.cache.StudentCache;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * <p> StudentRepository class. </p>
 * <p> This class is a repository class for StudentCache. </p>
 * <p><b>Class</b>: StudentRepository</p>
 * <p><b>Package</b>: com.codechallenge.scotiabank.repository</p>
 * <p><b>Project</b>: codechallenge</p>
 * <p><b>Version</b>: 1.0.0</p>
 * <p><b>Creation Date</b>: 2024-06-11</p>
 * <p><b>Copyright</b>: Encora</p>
 * <p>@author Pablo Sergio Loyola Tantaruna</p>
 * <div>
 *   <u>Developed by</u>:
 *   <ul>
 *     <li>Pablo Sergio Loyola Tantaruna</li>
 *   </ul>
 * </div>
 * <div>
 *   <u>Reviewed by</u>:
 *   <ul>
 *     <li>Pablo Sergio Loyola Tantaruna</li>
 *   </ul>
 * </div>
 * <p><b>Version</b>: 1.0.0</p>
 */

@Repository
public class StudentRepository {

  private final Map<String, StudentCache> studentMap = new ConcurrentHashMap<>();

  public Mono<Void> save(StudentCache student) {
    return Mono.fromRunnable(() -> studentMap.put(student.getId_student(), student));
  }

  public Mono<Boolean> existsStudent(String id) {
    return Mono.fromSupplier(() -> studentMap.containsKey(id));
  }

  public Flux<StudentCache> findAll() {
    return Flux.fromIterable(studentMap.values());
  }

  public Mono<Void> deleteById(String id) {
    return Mono.fromRunnable(() -> studentMap.remove(id));
  }
}