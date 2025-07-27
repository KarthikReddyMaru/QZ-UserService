package com.qz.userservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class TokenResponse {
    @JsonProperty(value = "access_token")
    private String token;
    @JsonProperty(value = "token_type")
    private String type;
    private int expires_in;
    private String scope;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant token_expiry;
}
