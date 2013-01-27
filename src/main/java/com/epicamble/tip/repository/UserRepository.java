package com.epicamble.tip.repository;

import com.epicamble.tip.model.User;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @NotNull
    List<User> findByUsername(String username);

    @NotNull
    List<User> findAll();
}
