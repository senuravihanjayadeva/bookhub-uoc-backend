package com.hexagon.bookhub.security.services;

import com.hexagon.bookhub.entity.Admin;
import com.hexagon.bookhub.entity.GuestUser;
import com.hexagon.bookhub.entity.User;
import com.hexagon.bookhub.repository.AdminRepository;
import com.hexagon.bookhub.repository.GuestUserRepository;
import com.hexagon.bookhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    GuestUserRepository guestUserRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<GuestUser> guest = guestUserRepository.findByEmail(email);
        User user = new User();
        if(guest.isPresent()){
            user = guest.get();
        }else{
            Optional<Admin> admin = Optional.ofNullable(adminRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email)));
            user = admin.get();
        }

        return UserDetailsImpl.build(user);
    }

}