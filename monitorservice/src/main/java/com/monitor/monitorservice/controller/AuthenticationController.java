package com.monitor.monitorservice.controller;

import com.monitor.monitorservice.dto.AuthInfoDTO;
import com.monitor.monitorservice.dto.AuthUserDTO;
import com.monitor.monitorservice.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login")
    public  ResponseEntity<AuthInfoDTO> authenticationUser(@RequestBody AuthUserDTO userDTO){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        return  ResponseEntity.ok(new AuthInfoDTO(jwtTokenProvider.generateToken(auth), auth.getAuthorities()));
    }

    @GetMapping("/is-auth")
    public ResponseEntity<Object> hasUserAuth(){
        return  ResponseEntity.ok(1);
    }


    @GetMapping("/role")
    public Collection<? extends GrantedAuthority> getUserRole(Authentication authentication){
        return  authentication.getAuthorities();
    }

}
