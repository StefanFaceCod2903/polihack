package org.example.backend;

public class UrlMapping {
  public static final String API = "/api";
  public static final String ITEMS = API + "/items";

  public static final String AUTH = API + "/auth";

  public static final String SIGN_IN =  "/sign-in";
  public static final String SIGN_UP = "/sign-up";

  public static final String[] AUTH_WHITE_LIST = {
          "/v3/api-docs/**",
          "/swagger-ui/**",
          "/v2/api-docs/**",
          "/swagger-resources/**",
          AUTH + "/**"
  };

}
