package com.epicamble.tip.security;

import com.epicamble.tip.model.Authority;
import com.epicamble.tip.model.User;
import com.epicamble.tip.repository.UserRepository;
import java.util.Random;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

/**
 * Performs implicit local account creation when a user first signs in with
 * a social provider
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
public class AccountConnectionSignUp implements ConnectionSignUp {
    
    private static Logger logger = LoggerFactory.getLogger(AccountConnectionSignUp.class);

    private final UserRepository userRepository;
    
    @Inject
    public AccountConnectionSignUp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(@NotNull Connection<?> connection) {
        UserProfile profile = connection.fetchUserProfile();
        logger.debug("Creating new user account using social provider {}", profile);
        User user = new User(profile);
        user.setAuthority(new Authority(Authority.ROLE.ROLE_USER, user));
		user.setPassword(generatePassword());
        userRepository.save(user);
        return user.getUsername();
    }

	@NotNull
	private String generatePassword() {
		Random random = new Random();
		byte [] bytes = new byte[20];
		random.nextBytes(bytes);
		return new String(Hex.encode(bytes));
	}
}
