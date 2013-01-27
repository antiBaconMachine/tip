package com.epicamble.tip.security;

import com.sun.istack.internal.Nullable;
import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
public class SocialAuthenticationToken extends AbstractAuthenticationToken {

    private UserDetails principal;

    public SocialAuthenticationToken(UserDetails principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
    }

    /**
     * We delegate all authentication to social provider so no credentials
     * required
     *
     * @return
     */
    @Nullable
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
