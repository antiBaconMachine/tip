package com.epicamble.tip.service.impl;

import com.epicamble.tip.model.User;
import com.epicamble.tip.repository.UserRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * Custom jpa backed implementation of UserDetailsService. 
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsManager {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User u = repository.findByUsername(username).get(0);
            logger.debug("XXX loading username from username {} resulting in {}", username, u);
            return u;
        } catch (IndexOutOfBoundsException ex) {
            throw new UsernameNotFoundException("could not load user "+username);
        }
    }
    
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    /**
     * we're ignore spring security create method in preference for jpa backed
     * save method, which allows us to use our own User model without casting
     * from the more general UserDetails
     *
     * @param user
     */
    public void createUser(User user) {
        repository.save(user);
    }

    @Override
    public void createUser(UserDetails ud) {
        //TODO ugly cast
        this.createUser((User) ud);
    }

    @Override
    public void updateUser(UserDetails ud) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteUser(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void changePassword(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean userExists(String string) {
        return loadUserByUsername(string) != null;
    }
}
