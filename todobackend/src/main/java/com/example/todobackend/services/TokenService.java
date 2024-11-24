package com.example.todobackend.services;

import com.example.todobackend.authentication.ExtendUserDetails;
import com.example.todobackend.entities.Token;
import com.example.todobackend.repositories.TokenRepository;
import com.example.todobackend.utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public String generateAccessToken(ExtendUserDetails user) {
        return jwtTokenUtil.generateToken(user);

    }

    public String generateRefreshToken(ExtendUserDetails user) {
        String refreshToken = jwtTokenUtil.generateRefreshToken(user);
        Token token = tokenRepository.findById(user.getId()).orElse(new Token(user.getId(), ""));
        token.setToken(refreshToken);
        tokenRepository.save(token);
        return refreshToken;
    }

    public String generateAccessToken(String refreshToken) {
        String accessToken = null;
        ExtendUserDetails user = getUserFromToken(refreshToken);
        Token savedToken=tokenRepository.findById(user.getId()).orElse(new Token(user.getId(), ""));
        if (savedToken.getToken().equals(refreshToken)) {
            accessToken = jwtTokenUtil.generateToken(user);
        }
        return accessToken;
    }

    private ExtendUserDetails getUserFromToken(String token) {
        String roles = jwtTokenUtil.getRole(token);
        String login = jwtTokenUtil.getLogin(token);
        Integer id = jwtTokenUtil.getId(token);
        return new ExtendUserDetails(id, login, roles);
    }

    public void deleteToken(Integer id) {
        tokenRepository.deleteById(id);
    }
}
