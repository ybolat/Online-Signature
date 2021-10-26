package com.example.teamalfa.constant;

public class SecurityConstant {

    public static final long EXPIRATION_TIME = 360_000_0 ;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String CLIENT_IP = "Client ip";
    public static final String JWT_TOKEN_HEADER = "jwt";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String ISSUER = "Astanait.edu.kz server";
    public static final String AUDIENCE = "Astanait.edu.kz client";
    public static final String SECRET = "SecretJWTKeyAlikhanSecretKeyLaLaLa5647ahfyenckzueicjaehgiahfnvjfyrheidhfjsnagfyeh";
    public static final String[] VALID_IP_HEADER_CANDIDATES = { "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };
}
