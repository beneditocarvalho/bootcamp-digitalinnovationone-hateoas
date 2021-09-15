package com.logos.aulas_dio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logos.aulas_dio.controller.request.SoldadoRequest;
import com.logos.aulas_dio.controller.response.SoldadoListResponse;
import com.logos.aulas_dio.controller.response.SoldadoResponse;
import com.logos.aulas_dio.dto.Soldado;
import com.logos.aulas_dio.entity.SoldadoEntity;
import com.logos.aulas_dio.repository.SoldadoRepository;
import com.logos.aulas_dio.resource.ResourceSoldado;

@Service
public class SoldadoService {

	@Autowired
	private SoldadoRepository soldadoRepository;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ResourceSoldado resourceSoldado;


	public SoldadoResponse buscarSoldado(Long id) {
		SoldadoEntity soldado = soldadoRepository.findById(id).orElseThrow();
		SoldadoResponse soldadoResponse = resourceSoldado.criarLinkDetalhe(soldado);
		return soldadoResponse;
	}

	public void criarSoldado(Soldado soldado) {
		SoldadoEntity soldadoEntity = objectMapper.convertValue(soldado, SoldadoEntity.class);
		soldadoRepository.save(soldadoEntity);
	}

	public void alterarSoldado(Long id, SoldadoRequest soldadoEditRequest) {
		SoldadoEntity soldadoEntity = objectMapper.convertValue(soldadoEditRequest, SoldadoEntity.class);
		soldadoEntity.setId(id);
		soldadoRepository.save(soldadoEntity);
	}

	public void deletarSoldado(Long id) {
		SoldadoEntity soldado = soldadoRepository.findById(id).orElseThrow();
		soldadoRepository.delete(soldado);
	}

	public CollectionModel<SoldadoListResponse> buscarSoldados() {
		List<SoldadoEntity> all = soldadoRepository.findAll();
		List<SoldadoListResponse> soldadoStream = all.stream()
				.map(it -> resourceSoldado.criarLink(it))
				.collect(Collectors.toList());
		return CollectionModel.of(soldadoStream);
	}

}