package com.market.userauth.repository;

import java.util.Optional;

import com.market.userauth.models.ERole;
import com.market.userauth.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
