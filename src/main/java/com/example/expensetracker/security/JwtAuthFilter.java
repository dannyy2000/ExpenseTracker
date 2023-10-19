package com.example.expensetracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtProvider jwtProvider;
    private CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,@NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        try{
            if(request.getHeader("Authorization") == null){
                filterChain.doFilter(request,response);
                return;
            }
            String jwt = getJwtFromRequest(request);
            String username = jwtProvider.getUserNameFromJwtToken(jwt);

            if(username != null){
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                if(jwtProvider.isTokenValid(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request,response);
        }catch (Exception e){
            if(!response.isCommitted()){
                response.sendError(400,String.valueOf(HttpServletResponse.SC_BAD_REQUEST));
            }
            filterChain.doFilter(request,response);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) throws IllegalAccessException {
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        throw new IllegalAccessException("Token is empty");
    }
}
