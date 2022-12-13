package com.restapi.auth;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Collections;

    @Singleton
    public class CustomAuthProvider implements AuthenticationProvider {

        @Inject
        UserStore userStore;

        @Override
        public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
            return Flux.create(emitter -> {
                if (authenticationRequest.getIdentity().equals("ADMIN") &&authenticationRequest.getSecret().equals("ADMIN")||(authenticationRequest.getIdentity().equals("USER") &&authenticationRequest.getSecret().equals("USER")||(authenticationRequest.getIdentity().equals("USER1") &&authenticationRequest.getSecret().equals("USER1")))) {
                    emitter.next(AuthenticationResponse.success((String) authenticationRequest.getIdentity()));
                    emitter.complete();
                } else {
                    emitter.error(AuthenticationResponse.exception());
                }
            }, FluxSink.OverflowStrategy.ERROR);
        }
}
