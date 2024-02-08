package com.psm.bookingchallenge.configs.jwt;

import com.psm.bookingchallenge.dtos.requests.LoginDTO;
import com.psm.bookingchallenge.dtos.RoleDTO;
import com.psm.bookingchallenge.dtos.UserDTO;
import com.psm.bookingchallenge.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, UserService userService,
                                   AuthenticationManager authenticationManager) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)  {

        try {
            LoginDTO credentials = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
            List<RoleDTO> authorizations = userService.getAuthoritiesByUsername(credentials.email());

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    credentials.email(),
                    credentials.password(),
                    userService.generateGrantedAuthorities(authorizations)
            );
            return authenticationManager.authenticate(auth);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                         FilterChain chain, Authentication auth) throws IOException {

        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        String tokens = jwtTokenUtil.generateTokenForHeader(username);

        response.addHeader("Authorization", tokens);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        UserDTO userDTO = userService.getUser(username);
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(userDTO));
        response.getWriter().flush();
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request,
                                           HttpServletResponse response,
                                           AuthenticationException failed) throws IOException {
        BadCredentialsError error = new BadCredentialsError(new Date().getTime(), 401, "Email or password incorrect");
        response.setStatus(error.status);
        response.setContentType("application/json");
        response.getWriter().append(error.toString());

    }

    private record BadCredentialsError(Long timestamp, Integer status, String message) {

    }


}


