package com.epicamble.tip.model;



import com.epicamble.tip.model.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
@Entity
@Table(name="authorities")
public class Authority extends AbstractPersistable<Long> 
    implements GrantedAuthority {
    
    public static enum ROLE {
        ROLE_USER,
        ROLE_ADMIN;
    }
    
    public Authority(){}
    
    public Authority(ROLE role, User user) {
        authority = role.name();
        this.user=user;
    }
    
    @OneToOne
    @JoinColumn(name="userid", nullable=false)
    private User user;
    @Column(nullable=false)
    private String authority;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
}
