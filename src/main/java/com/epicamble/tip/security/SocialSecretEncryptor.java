package com.epicamble.tip.security;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.security.crypto.encrypt.TextEncryptor;

/**
 * Heroku doesn't support 256 bit AES which is what spring security uses, therefore
 * this class exists as a workaround until such time as it does
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
public class SocialSecretEncryptor implements TextEncryptor {
    
    private BasicTextEncryptor basicTextEncryptor;
    
    public SocialSecretEncryptor(String password) {
        basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(password);
    }
    
    @Override
    public String encrypt(String string) {
        return basicTextEncryptor.encrypt(string);
    }

    @Override
    public String decrypt(String string) {
        return basicTextEncryptor.decrypt(string);
    }
    
}
