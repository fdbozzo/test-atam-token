package com.atam.pruebatecnica.testatamtoken.application.port.out;

import com.atam.pruebatecnica.testatamtoken.application.model.generated.TokenDtoApiResponse;

import java.util.Optional;

public interface TokenServiceRequest {

    Optional<TokenDtoApiResponse> getToken();

}
