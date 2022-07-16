package com.devsuperiorProjeto.dsmeta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devsuperiorProjeto.dsmeta.entities.Sale;
import com.devsuperiorProjeto.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {



	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	
	@Autowired
	private SaleRepository saleRepository;
	
	// Método para envio de mensagem
	public void sendSms(Long saleId) {
		
		Sale sale = saleRepository.findById(saleId).get();// --->Busca todas as vendas pelo o ID que é passado como parâmetro
		String date = sale.getDate().getMonthValue() + "/"+ sale.getDate().getYear();
		String msg = "** RELATÓRIO DE VENDAS **\n Data:" + date +
				"\n Vendedor: " + sale.getSellerName()+
				"\n Total Vendas: R$ " + String.format("%.2f", sale.getAmount());
		// Código para envio de mesnsagens via sms
		Twilio.init(twilioSid, twilioKey); 
		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);
		Message message = Message.creator(to, from,msg).create();
		System.out.println(message.getSid());
	}
}