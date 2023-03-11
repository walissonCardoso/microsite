package br.com.microssite.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.microssite.Autor.Autor;
import br.com.microssite.Services.TokenService;
import jakarta.transaction.Transactional;

@RestController
public class AuthConstroller {
    
    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/api/login")
    @Transactional
    public ResponseEntity<TokenJwtDto> efetuarLogin(@RequestBody AuthData data) {
        
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var authentication = manager.authenticate(authenticationToken);
        var autor = (Autor) authentication.getPrincipal();
        
        String tokenJWT = tokenService.generateToken(autor);
        
        return ResponseEntity.ok(new TokenJwtDto(autor.getId(), tokenJWT));
    }

    @GetMapping("/api/token_reflesh")
    public ResponseEntity<TokenJwtDto> tokenReflesh(@AuthenticationPrincipal Autor loggeAutor) {    
        
        String tokenJWT = tokenService.generateToken(loggeAutor);
        
        return ResponseEntity.ok(new TokenJwtDto(loggeAutor.getId(), tokenJWT));
    }
}
