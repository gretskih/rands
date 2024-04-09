package ru.job4j.restxml.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.job4j.restxml.dto.TokenRequest;
import ru.job4j.restxml.service.TokenService;

import java.io.IOException;

@AllArgsConstructor
public class JWTAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {

    public static final String HEADER_STRING = "Authorization";
    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse res)
            throws AuthenticationException {
        try {
            TokenRequest creds =  parseCredentials(request);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(creds.username(), creds.password());
            return authManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Failed to parse authentication request", e);
        }
    }

    private TokenRequest parseCredentials(HttpServletRequest request) throws IOException {
        return objectMapper.readValue(request.getInputStream(), TokenRequest.class);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) {
        String token = tokenService.generateAccessToken(authResult);
        response.addHeader(HEADER_STRING, token);
    }
}
