package com.epicamble.tip.security;

import com.epicamble.tip.model.User;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
public class SocialAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    /**
     * As we're relying on a 3rd party to do the actual authentication this
     * does only a basic sanity check to make sure we have all the information
     * we need before waving the user on, like a lovable but ineffective night 
     * watchman.
     * @param authentication
     * @return
     * @throws AuthenticationException 
     */
    @NotNull
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SocialAuthenticationToken authRequest = (SocialAuthenticationToken) authentication;
        User stored = (User) userDetailsService.loadUserByUsername(((User) authRequest.getPrincipal()).getUsername());
        if (stored == null) {
            throw new BadCredentialsException("There is no local account matching the social principal");
        }
        
        SocialAuthenticationToken authenticatedUser = 
                new SocialAuthenticationToken(stored, stored.getAuthorities());
        authenticatedUser.setAuthenticated(true);
        return authenticatedUser;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (SocialAuthenticationToken.class.isAssignableFrom(authentication));
    }
    
    

}
