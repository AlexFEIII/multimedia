package com.example.multimedia.service.ServiceImpl;

import com.example.multimedia.domain.MulUser;
import com.example.multimedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if(username.contains("@")){
            try{
                MulUser mulUser = userRepository.findByEmail(username);
                System.out.println(mulUser);
                return new User(mulUser.getEmail(),mulUser.getPassword(),AuthorityUtils.commaSeparatedStringToAuthorityList(mulUser.getRole()));
            }catch (Exception e){
                throw new BadCredentialsException("NoEMail");
            }
        }else{
            try{
                MulUser mulUser = userRepository.findByUsername(username);
                System.out.println(mulUser);
                return new User(mulUser.getUsername(),mulUser.getPassword(),AuthorityUtils.commaSeparatedStringToAuthorityList(mulUser.getRole()));
            }catch (Exception e){
                throw new BadCredentialsException("NoUser");
            }
        }
    }
}
