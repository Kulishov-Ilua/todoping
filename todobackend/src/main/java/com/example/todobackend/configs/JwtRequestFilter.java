package com.example.todobackend.configs;


import com.example.todobackend.authentication.ExtendUserDetails;
import com.example.todobackend.authentication.JwtAuthentication;
import com.example.todobackend.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String mail = null;
        Integer id=null;
        String jwt = null;
        String role=null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                mail = jwtTokenUtil.getLogin(jwt);
                role=jwtTokenUtil.getRole(jwt);
                id=jwtTokenUtil.getId(jwt);
            } catch (ExpiredJwtException e) {
                System.out.println("Время жизни токена истекло!");
            } catch (SignatureException e) {
                System.out.println("Неправильная подпись!");
            }
        }
        if (mail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            ExtendUserDetails userDetails = new ExtendUserDetails(id,mail, role);
            JwtAuthentication token=new JwtAuthentication(userDetails,null);
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}
