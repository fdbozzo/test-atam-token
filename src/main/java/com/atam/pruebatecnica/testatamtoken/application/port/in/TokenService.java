package com.atam.pruebatecnica.testatamtoken.application.port.in;

import com.atam.pruebatecnica.testatamtoken.application.model.generated.TokenDto;

import java.util.Optional;

public interface TokenService {

    Optional<TokenDto> getToken();

}
