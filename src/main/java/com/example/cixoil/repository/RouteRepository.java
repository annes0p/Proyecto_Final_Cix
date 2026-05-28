package com.example.cixoil.repository;

import com.example.cixoil.enums.ProgressStatus;
import com.example.cixoil.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findAllByStatusNot(Integer status);

    List<Route> findByProgressStatus(ProgressStatus progressStatus);

    List<Route> findByUser_IdAndProgressStatus(Long idUser, ProgressStatus progressStatus);

}
