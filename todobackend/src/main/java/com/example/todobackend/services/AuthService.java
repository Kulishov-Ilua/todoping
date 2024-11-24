package com.example.todobackend.services;

import com.example.todobackend.authentication.ExtendUserDetails;
import com.example.todobackend.authentication.JwtAuthentication;
import com.example.todobackend.dto.AuthRequest;
import com.example.todobackend.dto.JwtResponse;
import com.example.todobackend.dto.RegRequest;
import com.example.todobackend.entities.User;
import com.example.todobackend.exception.PasswordsDoNotMatchException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public JwtResponse login(AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        ExtendUserDetails user = (ExtendUserDetails) authentication.getPrincipal();
        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);
        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse getAccessToken(String refreshToken) {
        String accessToken=tokenService.generateAccessToken(refreshToken);
        return new JwtResponse(accessToken, null);
    }


    public JwtResponse regUser(RegRequest regRequest){
        if(!(regRequest.getPassword().equals(regRequest.getPasswordRepeat()))){
            throw new PasswordsDoNotMatchException("Пароли не совпадают");
        }
        User user=new User(regRequest.getLogin(),regRequest.getName(),passwordEncoder.encode(regRequest.getPassword()));
        ExtendUserDetails userDetails=userService.createUser(user);
        String accessToken = tokenService.generateAccessToken(userDetails);
        String refreshToken = tokenService.generateRefreshToken(userDetails);
        return new JwtResponse(accessToken, refreshToken);
    }


    public void logout() {
        ExtendUserDetails user=getUserFromContext();
        tokenService.deleteToken(user.getId());
    }

    public ExtendUserDetails getUserFromContext(){
        JwtAuthentication authentication= (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return (ExtendUserDetails) authentication.getPrincipal();
    }
}
