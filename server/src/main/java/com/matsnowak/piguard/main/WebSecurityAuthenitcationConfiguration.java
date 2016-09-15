package com.matsnowak.piguard.main;

import com.matsnowak.piguard.model.Settings;
import com.matsnowak.piguard.repositories.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Mateusz Nowak on 15.09.2016.
 */
//@Configuration
public class WebSecurityAuthenitcationConfiguration extends GlobalAuthenticationConfigurerAdapter {

//    @Autowired
//    SettingsRepository settingsRepository;
//
//
//    @Override
//    public void init(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
//    }
//
//    @Bean
//    UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//                Settings settings = settingsRepository.findOne(1);
//                if (settings != null) {
//                    return new User(Defaults.DEFAULT_USER, settings.getPass(), true, true, true, true, AuthorityUtils.createAuthorityList(Defaults.DEFAULT_USER_AUTHORITY));
//                } else {
//                    throw new UsernameNotFoundException("could not find the user");
//                }
//            }
//        };
//    }
}
