package com.ajwalker.constant;

public class RestApis {
    private static final String VERSION = "/v1";
    private static final String API = "/api";
    private static final String DEVELOPER = "/dev";
    private static final String TEST = "/test";
    private static final String PROD = "/prod";

    private static final String ROOT = VERSION+ DEVELOPER;

    public static final String USER = ROOT+ "/kullanici";
    public static final String REGISTER = "/register";
    public static final String VERIFY = "/verify";
    public static final String DOLOGIN = "/do-login";
    public static final String RESET_PASSWORD = "/reset-password";
    public static final String FORGOT_PASSWORD = "/forgot-password";
}