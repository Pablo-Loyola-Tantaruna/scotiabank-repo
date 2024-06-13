package com.codechallenge.scotiabank.util.error;

import reactor.core.publisher.Mono;

/**
 * <p> Class that handles the error.</p>
 * <p> This class is responsible for handling the error.</p>
 * <p><b>Class</b>: Handle Error </p>
 * <p><b>Package</b>: com.codechallenge.scotiabank.util.error</p>
 * <p><b>Project</b>: codechallenge</p>
 * <p><b>Version</b>: 1.0.0</p>
 * <p><b>Creation Date</b>: 2024-06-11</p>
 * <p><b>Copyright</b>: Encora</p>
 *
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
public class HandleError {

  /**
   * Method that handles the error.
   *
   * @param error {@link Throwable}
   * @return {@link Mono}
   */
  public static Mono<Void> handleError(Throwable error) {
    if (error instanceof RuntimeException) {
      return Mono.error(error);
    } else {
      return Mono.error(new RuntimeException("Unexpected error occurred"));
    }
  }
}
