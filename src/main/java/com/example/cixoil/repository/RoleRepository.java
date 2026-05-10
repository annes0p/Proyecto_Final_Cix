package com.example.cixoil.repository;

import com.example.cixoil.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByStatusNot(Integer status);
    Optional<Role> findAllByNameIgnoreCase(String name);
}
