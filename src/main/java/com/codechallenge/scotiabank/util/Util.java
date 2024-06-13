package com.codechallenge.scotiabank.util;

/**
 * <p> Util class.</p>
 * <p> This class is responsible for providing utility methods.</p>
 * <p><b>Class</b>: Util</p>
 * <p><b>Package</b>: com.codechallenge.scotiabank.util</p>
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
 *     <u>Reviewed by</u>:
 *     <ul>
 *         <li>Pablo Sergio Loyola Tantaruna</li>
 *     </ul>
 * </div>
 * <p><b>Version</b>: 1.0.0</p>
 */
public class Util {

  /**
   * Method that validates the status of a student.
   *
   * @param status Status of the student.
   * @return int
   */
  public static int validateStatus(String status) {
    if (status.equalsIgnoreCase("ACTIVO") || status.equals("INACTIVO")) {
      return status.equalsIgnoreCase("ACTIVO") ? 1 : 0;
    } else {
      throw new RuntimeException("Invalid status");
    }
  }

  /**
   * Method that validates the status of a student.
   *
   * @param status Status of the student.
   * @return String
   */
  public static String validateStatusCache(int status) {
    if (status == 1 || status == 0) {
      return status == 1 ? "ACTIVO" : "INACTIVO";
    } else {
      throw new RuntimeException("Invalid status");
    }
  }
}
