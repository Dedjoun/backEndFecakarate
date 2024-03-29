package com.fecakarate.backendfecakarate.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService = new JwtService();

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, // our request
            @NonNull HttpServletResponse response, // our response... We can intercept requests and maybe add headers to// our requests
            @NonNull FilterChain filterChain// to filter our requests
    ) throws ServletException, IOException {

        // we check if we have the jwt token

        final String authHeader = request.getHeader("Authorization");// we get the header from the request that contains
        final String jwt;
        final String userEmail;
        // checking if we have a token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {// false token. A token must start with 'Bearer '
            filterChain.doFilter(request, response);

            return;
        }
        System.out.println("Auth header = "+authHeader);
        jwt = authHeader.substring(7);// the jwt token starts at index 7
        System.out.println("JWT = "+jwt);
        userEmail = jwtService.extractUsername(jwt);// TODO extract user-email from JWT token
        System.out.println(jwtService.extractUsername(jwt));
        //f the user isn't authenticated,
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {// user not yet
            // authenticated
            // we get the user from the DB
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {//we check if the user is valid or not
                System.out.println("1--------------------------");

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // credentials
                        userDetails.getAuthorities());
                System.out.println("2--------------------------");

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                System.out.println("3-------------------------");

                // we update the security context holder
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("4--------------------------");

            }
        }
        System.out.println("5--------------------------");

        //for the next filter
        filterChain.doFilter(request, response);

        System.out.println("6--------------------------");
    }
}
