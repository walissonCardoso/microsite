package br.com.microssite.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microssite.Autor.Autor;
import br.com.microssite.Services.TokenService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/login")
public class AuthConstroller {
    
    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private TokenService tokenService;
    
    @PostMapping
    @Transactional
    public ResponseEntity<TokenJwtDto> efetuarLogin(@RequestBody AuthData data) {
        
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var authentication = manager.authenticate(authenticationToken);
        String tokenJWT = tokenService.generateToken((Autor) authentication.getPrincipal());
        
        return ResponseEntity.ok(new TokenJwtDto(tokenJWT));
    }

}
