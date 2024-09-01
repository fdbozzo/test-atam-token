package com.atam.pruebatecnica.testatamtoken.controller.adapter;

import com.atam.pruebatecnica.testatamtoken.application.model.generated.TokenDto;
import com.atam.pruebatecnica.testatamtoken.application.port.in.TokenService;
import com.atam.pruebatecnica.testatamtoken.controller.adapters.generated.GetTokenApiDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController implements GetTokenApiDelegate {
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public ResponseEntity<TokenDto> getToken() {
        return tokenService.getToken()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }

}
