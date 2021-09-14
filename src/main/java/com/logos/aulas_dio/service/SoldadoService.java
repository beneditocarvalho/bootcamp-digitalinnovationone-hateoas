package com.logos.aulas_dio.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.logos.aulas_dio.controller.request.SoldadoRequest;
import com.logos.aulas_dio.dto.Soldado;

@Service
public class SoldadoService {

	public Soldado buscarSoldado(String cpf) {
		Soldado soldado = new Soldado();
		soldado.setCpf(cpf);
		soldado.setNome("Legolas");
		soldado.setRaca("Elfo");
		soldado.setArma("Arco e flexa");
		return soldado;
	}

	public void criarSoldado(Soldado soldado) {

	}

	public void alterarSoldado(String cpf, SoldadoRequest soldadoRequest) {

	}

	public void deletarSoldado(String cpf) {

	}

	public List<Soldado> buscarSoldados() {

		Soldado soldado1 = new Soldado();
		soldado1.setCpf("12345");
		soldado1.setNome("Legolas");
		soldado1.setRaca("Elfo");
		soldado1.setArma("Arco e flecha");

		Soldado soldado2 = new Soldado();
		soldado2.setCpf("54321");
		soldado2.setNome("Darkfire");
		soldado2.setRaca("Mago");
		soldado2.setArma("Magia");

		return Arrays.asList(soldado1, soldado2);
	}

}
