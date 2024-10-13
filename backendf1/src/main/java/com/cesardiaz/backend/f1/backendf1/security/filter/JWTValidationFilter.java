package com.cesardiaz.backend.f1.backendf1.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.cesardiaz.backend.f1.backendf1.dtos.DetailsUserData;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.cesardiaz.backend.f1.backendf1.security.TokenJwtConfig.CONTENT_TYPE;
import static com.cesardiaz.backend.f1.backendf1.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.cesardiaz.backend.f1.backendf1.security.TokenJwtConfig.PREFIX_TOKEN;
import static com.cesardiaz.backend.f1.backendf1.security.TokenJwtConfig.SECRET_KEY;

public class JWTValidationFilter extends BasicAuthenticationFilter {

    public JWTValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(PREFIX_TOKEN, "");

        try {

            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String username = claims.get("username").toString();
            String clientId = claims.get("clientId").toString();
            Object authoritiesClaims = claims.get("authorities");

            // se agrega un mixing porque en la primera clase tiene la propiedad como role y
            // nosotros lo nombramos authority, y se define en la segunda clase
            // perzonalizada.
            Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
                    .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                    .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));

            // aqui no se valida el password por solo se valida el token, en la creacion del
            // token si es importante validar el password (JWRAuthenticationFilter).
            UsernamePasswordAuthenticationToken autAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    username, null, authorities);
            DetailsUserData detailsUserData = DetailsUserData.builder().userId(clientId).build();
            autAuthenticationToken.setDetails(detailsUserData);
            SecurityContextHolder.getContext().setAuthentication(autAuthenticationToken);
            chain.doFilter(request, response);
        } catch (JwtException e) {
            // TODO: handle exception
            Map<String, String> body = new HashMap<>();

            body.put("error", e.getMessage());
            body.put("message", "El token no es valido.");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ;
            response.setContentType(CONTENT_TYPE);
            ;
        }

    }

}
