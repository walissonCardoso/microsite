package br.com.microssite.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.microssite.Autor.AutorRepository;
import br.com.microssite.Services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String tokenJWT = recuperarToken(request);
        
        if(tokenJWT != null){
            String autorEmail = tokenService.getSubject(tokenJWT);
            var autor = autorRepository.findByEmail(autorEmail);
            
            var authentication = new UsernamePasswordAuthenticationToken(autor, null, autor.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
    }
    
    
    private String recuperarToken(HttpServletRequest request){
        var authorizationHeader = request.getHeader("Authorization");
        
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        
        return null;
    }
}
