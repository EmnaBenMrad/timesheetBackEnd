package com.example.timesheet.security.jwt;

/****************************
 * Nous créons maintenant la classe
 * AuthEntryPointJwt qui implémente l'interface AuthenticationEntryPoint.
 * Ensuite, nous remplaçons la méthode commence ().
 * Cette méthode sera déclenchée à chaque fois qu'un utilisateur
 * non authentifié demande une ressource HTTP sécurisée et une AuthenticationException est levée
 * .*****************************/


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erreur : Le login et/ou le mot de passe sont incorrects!");
    }

}
