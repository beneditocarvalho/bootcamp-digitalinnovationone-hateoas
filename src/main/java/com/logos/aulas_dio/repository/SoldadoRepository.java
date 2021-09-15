package com.logos.aulas_dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logos.aulas_dio.entity.SoldadoEntity;

@Repository
public interface SoldadoRepository extends JpaRepository<SoldadoEntity, Long> {

}
