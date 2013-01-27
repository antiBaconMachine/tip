package com.epicamble.tip.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.UserProfile;

@Entity
//user is a reserved word in postgres
@Table(name = "users")
public class User extends AbstractEntity<Long> implements UserDetails {

    private static Logger logger = LoggerFactory.getLogger(User.class);
    @Column(nullable = false, unique = true)
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean enabled;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;
    @OneToMany
    //@Where(clause="closed='false'")
    private List<Match> matches;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Authority authority;
    @OneToMany
    @JoinColumn(name = "userid", referencedColumnName = "username")
    private List<UserConnection> connections;

    //TODO: one to many relationship for social connections.
    public User() {
    }

    public User(UserProfile profile) {
        this.setUsername(profile.getUsername());
        this.setFirstname(profile.getFirstName());
        this.setLastname(profile.getLastName());
        this.setEmail(profile.getEmail());
        this.setEnabled(true);
        this.setCreated(new Date());
        logger.debug("Created new user from social provider {}", this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        User.logger = logger;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO make authorities manyToMany
        return Arrays.asList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<UserConnection> getConnections() {
        return connections;
    }
}
