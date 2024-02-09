package com.psm.bookingchallenge.configs.jwt;

import com.psm.bookingchallenge.models.UserSecurity;
import com.psm.bookingchallenge.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService service;


    public JwtAuthorizationFilter(JwtTokenUtil jwtTokenUtil, UserService service,
                                  AuthenticationManager authManager) {
        super(authManager);
        this.jwtTokenUtil = jwtTokenUtil;
        this.service = service;
    }

    @Override
    public void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain
    ) throws ServletException, IOException {
        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }

        Authentication auth = getAuthentication(header.substring(7));
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        String username = jwtTokenUtil.getEmail(token);
        UserSecurity user = service.getUserSecurity(username);

        if (!jwtTokenUtil.isTokenValid(token)) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, user.getuAuthorities());
    }

}
