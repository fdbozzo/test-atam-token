package com.atam.pruebatecnica.testatamtoken.application.service.impl;

import com.atam.pruebatecnica.testatamtoken.application.model.generated.TokenDto;
import com.atam.pruebatecnica.testatamtoken.application.port.in.TokenService;
import com.atam.pruebatecnica.testatamtoken.application.port.out.TokenServiceRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenServiceRequest tokenServiceRequest;

    public TokenServiceImpl(TokenServiceRequest tokenServiceRequest) {
        this.tokenServiceRequest = tokenServiceRequest;
    }

    @Override
    public Optional<TokenDto> getToken() {
        var tokenDtoRequestOptional = tokenServiceRequest.getToken();

        if (tokenDtoRequestOptional.isPresent()) {
            var token = tokenDtoRequestOptional.get().getToken();
            return Optional.of(new TokenDto(token, getLocalDateNow()));
        } else {
            return Optional.empty();
        }
    }

    private String getLocalDateNow() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL dd, yyyy", Locale.ENGLISH);
        return date.format(formatter);
    }
}
