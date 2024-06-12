package com.codechallenge.scotiabank.repository;

import com.codechallenge.scotiabank.model.cache.StudentCache;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheRepository extends ReactiveCrudRepository<StudentCache, String> {}
