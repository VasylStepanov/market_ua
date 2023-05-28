package com.application.security.jwt;

import com.application.dto.UserDTO;
import com.application.entity.User;
import com.application.exception.UserNotFoundException;
import com.application.repository.UserRepository;
import com.application.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtSecurityService securityService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        log.info("In jwt security filter chain");
        final String starts = "Bearer ";
        final String authHeader = request.getHeader("Authorization");

        if(request.getServletPath().matches("/authentication") ||
            authHeader == null ||
            !authHeader.startsWith(starts)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(starts.length()), userLogin = securityService.extractLogin(jwt);

        if(userLogin != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails user = userService.getUserByLogin(userLogin);

            if (securityService.isTokenValid(jwt, user)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
