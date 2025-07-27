package com.qz.userservice.consts;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("keycloak")
@Component
@Data
public class KeyCloak {

    private String baseUrl;
    private String clientId;
    private String clientSecret;

    public enum Group {

        USER("qz-user");
        private final String role;

        Group(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

    public enum Type {

        PASSWORD("password");
        private final String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum Token {

        CLIENT_ID("client_id"), CLIENT_SECRET("client_secret"),
        GRANT_TYPE("grant_type"), CLIENT_CREDENTIALS("client_credentials");

        private final String value;

        Token(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

