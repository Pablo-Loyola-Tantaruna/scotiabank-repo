package com.codechallenge.scotiabank.config;

import com.codechallenge.scotiabank.model.cache.StudentCache;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p> Clase de configuraci&oacute;n de Redis.</p>
 * <p> Esta clase es utilizada para la configuraci&oacute;n de Redis.</p>
 * <p><b>Class</b>: RedisConfig</p>
 * <p><b>Package</b>: com.codechallenge.scotiabank.config</p>
 * <p><b>Project</b>: codechallenge</p>
 * <p><b>Version</b>: 1.0.0</p>
 * <p><b>Creation Date</b>: 2024-06-11</p>
 * <p><b>Copyright</b>: Encora</p>
 * <p>@author Pablo Sergio Loyola Tantaruna</p>
 * <div>
 *     <u>Developed by</u>:
 *     <ul>
 *         <li>Pablo Sergio Loyola Tantaruna</li>
 *     </ul>
 * </div>
 * <div>
 *     <u>Reviewed by</u>:
 *     <ul>
 *         <li>Pablo Sergio Loyola Tantaruna</li>
 *     </ul>
 * </div>
 * <p><b>Version</b>: 1.0.0</p>
 */
@Configuration
@RequiredArgsConstructor
public class RedisConfig {

  /**
   * M&eacute;todo que permite la creaci&oacute;n de un ReactiveRedisTemplate.
   *
   * @param factory Factor&iacute;a de conexi&oacute;n de Redis.
   * @return ReactiveRedisTemplate
   */
  @Bean
  public ReactiveRedisTemplate<String, StudentCache>
      reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
    Jackson2JsonRedisSerializer<StudentCache> serializer =
            new Jackson2JsonRedisSerializer<>(StudentCache.class);
    RedisSerializationContext<String, StudentCache> serializationContext =
        RedisSerializationContext.<String, StudentCache>newSerializationContext(
                new StringRedisSerializer())
            .key(new StringRedisSerializer())
            .value(serializer)
            .hashKey(new StringRedisSerializer())
            .hashValue(new GenericJackson2JsonRedisSerializer())
            .build();

    return new ReactiveRedisTemplate<>(factory, serializationContext);
  }
}
