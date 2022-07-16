package com.devsuperiorProjeto.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperiorProjeto.dsmeta.entities.Sale;
import com.devsuperiorProjeto.dsmeta.services.SaleService;
import com.devsuperiorProjeto.dsmeta.services.SmsService;


@RestController
@RequestMapping(value = "/Sales")//--------------> Passa o caminho que deve ser acessado Ex:http:localhost:8080/sales
public class SaleController {
	  @Autowired
	  private SaleService service;
	  
	  @Autowired
	  private SmsService smsService;
	  
	  @GetMapping
	  public Page<Sale> findSales(//----------> Método  que vai disponibilizar os dados da venda para o front-end
			  @RequestParam(value = "minDate",defaultValue = "") 
			  String minDate, 
			  @RequestParam(value = "maxDate",defaultValue = "") 
			  String maxDate,
			  Pageable pageable){ 
		  return service.findSales(minDate,maxDate,pageable);//----------> Chama o service
		  
	  }
	  @GetMapping("/{id}/Notification")//--------------> Passa o caminho que deve ser acessado e o parâmetro a ser informado na requisição {id}
	  public void notifySms(@PathVariable Long id) {	
		  smsService.sendSms(id);
	  }

}
