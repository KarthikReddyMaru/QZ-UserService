package com.qz.userservice.client;

import com.qz.userservice.consts.KeyCloak;
import com.qz.userservice.model.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneId;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeyCloakTokenService {

    private TokenResponse token;
    private final RestTemplate restTemplate;
    private final KeyCloak keyCloak;

    public TokenResponse fetchToken() {
        if (token == null || Instant.now().isAfter(token.getToken_expiry())) {
            TokenResponse newToken = getToken();
            Instant instant = Instant.now();
            log.info("Token fetched at {}", instant.atZone(ZoneId.systemDefault()));
            newToken.setToken_expiry(Instant.now().plusSeconds(newToken.getExpires_in() - 60));
            return newToken;
        }
        return token;
    }

    public TokenResponse getToken() {
        String uri = keyCloak.getBaseUrl() + "/realms/qz/protocol/openid-connect/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add(KeyCloak.Token.CLIENT_ID.getValue(), keyCloak.getClientId());
        body.add(KeyCloak.Token.CLIENT_SECRET.getValue(), keyCloak.getClientSecret());
        body.add(KeyCloak.Token.GRANT_TYPE.getValue(), KeyCloak.Token.CLIENT_CREDENTIALS.getValue());
        log.info("URI: {}\n Body: {}", uri, body);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<TokenResponse> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, TokenResponse.class);
        return token = response.getBody();
    }

}
