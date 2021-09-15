package com.logos.aulas_dio.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logos.aulas_dio.controller.SoldadoController;
import com.logos.aulas_dio.controller.response.SoldadoListResponse;
import com.logos.aulas_dio.controller.response.SoldadoResponse;
import com.logos.aulas_dio.entity.SoldadoEntity;

@Component
public class ResourceSoldado {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SoldadoController soldadoController;

	public SoldadoListResponse criarLink(SoldadoEntity soldadoEntity) {
		SoldadoListResponse soldadoListResponse = objectMapper.convertValue(soldadoEntity, SoldadoListResponse.class);
		Link link = WebMvcLinkBuilder
				.linkTo(SoldadoController.class, soldadoController.buscarSoldado(soldadoEntity.getId()))
				.withSelfRel();
		soldadoListResponse.add(link);
		return soldadoListResponse;
	}

	public SoldadoResponse criarLinkDetalhe(SoldadoEntity soldadoEntity) {
		SoldadoResponse soldadoResponse = objectMapper.convertValue(soldadoEntity, SoldadoResponse.class);
		if (soldadoEntity.getStatus().equals("morto")) {
			Link link = WebMvcLinkBuilder
					.linkTo(SoldadoController.class, soldadoController.deletarSoldado(soldadoEntity.getId()))
					.withRel("remover")
					.withTitle("Deletar Soldado")
					.withType("delete");
			soldadoResponse.add(link);
		} else if (soldadoEntity.getStatus().equals("vivo")) {
			Link link = WebMvcLinkBuilder
					.linkTo(SoldadoController.class, soldadoController.frenteCastelo(soldadoEntity.getId()))
					.withRel("batalhar")
					.withTitle("Ir pra frente do castelo")
					.withType("put");
			soldadoResponse.add(link);
		}
		return soldadoResponse;
	}

}
