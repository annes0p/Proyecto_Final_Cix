package com.example.cixoil.repository;

import com.example.cixoil.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAllByStatusNot(Integer status);

    List<Client> findByNameContainingIgnoreCase(String part);

}
