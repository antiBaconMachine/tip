package com.epicamble.tip.security;

import com.epicamble.tip.model.User;
import com.sun.istack.internal.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

@Service
public class SpringSecuritySignInAdapter implements SignInAdapter {

    protected static final Logger logger = LoggerFactory.getLogger(SpringSecuritySignInAdapter.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RememberMeServices rememberMeServices;

    @Nullable
    @Override
    public String signIn(String localUserId, Connection<?> connection, @NotNull NativeWebRequest request) {
        logger.debug("Social signIn({},{},{})", new Object[]{localUserId, connection, request});
        User user = (User) userDetailsService.loadUserByUsername(localUserId);
        if (user == null) {
            user = new User();
            user.setUsername(localUserId);
        }
        try {
            SecurityContextHolder.getContext().setAuthentication(
                    new SocialAuthenticationToken(user, null));
        } catch (Exception ex) {
            logger.error("error signing in: {}", ex.getMessage(), ex);
        }

        // add remember me
        rememberMeServices.loginSuccess(
                request.getNativeRequest(HttpServletRequest.class),
                request.getNativeResponse(HttpServletResponse.class),
                SecurityContextHolder.getContext().getAuthentication());
        return null;
    }
}
