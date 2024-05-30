package Birhan.Registrtion_login.security;

import Birhan.Registrtion_login.entity.Role;
import Birhan.Registrtion_login.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomeUserDetailService implements UserDetailsService {
    private UserRepository userRepository;
    public CustomeUserDetailService(UserRepository userRepository){
        this.UserRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email);

        if(user != null){
            return new org.springSecurity.core.userDeyails.User(user.getEmai),
            user.getPassword(),
            mapRolesToAuthorities(user.getRoles());
        }
        else{
            throw  new UsernameNotFOundExcepton("Invalid username or password");
        }
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(COllection <Role> roles){
     Collection< ? extends GrantedAuthority> mapRoles = roles.stream()
             .map(role -> new SimpleGrantedAuthority(role.getName))
             .collect(Collectors.toList());
     return mapRoles;
    }
}
