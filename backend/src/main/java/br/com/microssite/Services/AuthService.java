package br.com.microssite.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.microssite.Autor.AutorRepository;

@Service
public class AuthService implements UserDetailsService {
    
    @Autowired
    private AutorRepository autorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return autorRepository.findByEmail(email);
    }
}
