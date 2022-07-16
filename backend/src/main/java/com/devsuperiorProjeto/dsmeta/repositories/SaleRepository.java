package com.devsuperiorProjeto.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperiorProjeto.dsmeta.entities.Sale;

// Classe compontente que vai ser responsável de acessar o BD
/*
 * - Consulta JPQL busca as vinte primeiras vendas entra a data mínima e máxima ordenada por vendas em ordem decrescente
 * - Selecionando objeto 'Sale'
 */
public interface SaleRepository extends JpaRepository<Sale, Long>{
	@Query("SELECT obj FROM Sale obj WHERE obj.date BETWEEN :min AND :max ORDER BY obj.amount DESC")
	Page<Sale> findSales(LocalDate min, LocalDate max, Pageable pageable);// método que pagína os 20 primeiros dados

}
