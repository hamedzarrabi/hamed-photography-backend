package com.hami.photo.security.repository;



import com.hami.photo.common.entity.security.Role;
import com.hami.photo.common.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
