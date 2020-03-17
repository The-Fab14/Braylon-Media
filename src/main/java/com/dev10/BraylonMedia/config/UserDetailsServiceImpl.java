package com.dev10.BraylonMedia.config;

import com.dev10.BraylonMedia.entities.User;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    //UserRepository users;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = users.findByUserName(username);
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        //grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserRole()));
        
        
        //return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
        return null; /*** Uncomment above lines and remove this line after User Repository is added to project ***/
    }
    
}
