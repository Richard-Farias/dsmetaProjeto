package com.devsuperiorProjeto.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperiorProjeto.dsmeta.entities.Sale;
import com.devsuperiorProjeto.dsmeta.repositories.SaleRepository;


@Service
public class SaleService {              // ---> Classe responsável por fazer as operações de negócio
	@Autowired 
	private SaleRepository repository; //  ---> Criado para acessar o BD
	// Método para buscar as vendas
	public Page<Sale>findSales(String minDate,String maxDate,Pageable pageable) {
		// Variável cria uma data do dia atual (Instant.now() e o fuso horário do sistema (ZoneId.systemDefault) 
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		// Condição ternária que se receber a minDate vazio, pega a data de 1 ano atrás (today.minusDays(365))
		LocalDate min = minDate.equals("") ? today.minusDays(365): LocalDate.parse(maxDate);
		
		// Condição ternária que se receber a maxDate vazio, pega a data atual (today)
		LocalDate max = maxDate.equals("") ? today: LocalDate.parse(maxDate);
		
		return repository.findSales(min,max,pageable); //    ---> Retorna busca de todas as Vendas
		
	}

}
