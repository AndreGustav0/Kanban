package com.atividade.kanban.controller;

import com.atividade.kanban.entities.User.AuthenticationDTO;
import com.atividade.kanban.entities.User.LoginResponseDTO;
import com.atividade.kanban.entities.User.RegisterDTO;
import com.atividade.kanban.entities.User.User;
import com.atividade.kanban.infra.security.TokenService;
import com.atividade.kanban.repositories.UserRepositories;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepositories repositories;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Validated AuthenticationDTO data){
        var usernamePassoword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassoword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if(this.repositories.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(),encryptedPassword,data.role());

        this.repositories.save(newUser);
        return ResponseEntity.ok().build();
    }
}
