package ada.santander.coders.mod05.ecommerce02.infra.security;

import ada.santander.coders.mod05.ecommerce02.entities.Cliente;
import ada.santander.coders.mod05.ecommerce02.repositories.ClienteRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final ClienteRepository clienteRepository;

    public CustomUserDetailsService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository
                .findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Usuário não encontrado!")
                );
        Set<GrantedAuthority> authorities = cliente
                .getRoles()
                .stream()
                .map(
                        role -> new SimpleGrantedAuthority(
                                role.getName()
                        )
                )
                .collect(
                        Collectors.toSet()
                );

        return new User(
                cliente.getEmail(),
                cliente.getSenha(),
                authorities
        );
    }
}
