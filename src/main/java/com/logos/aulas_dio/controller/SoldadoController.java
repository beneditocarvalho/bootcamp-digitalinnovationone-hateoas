package com.logos.aulas_dio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.logos.aulas_dio.controller.request.SoldadoRequest;
import com.logos.aulas_dio.dto.Soldado;
import com.logos.aulas_dio.service.SoldadoService;

@RestController
@RequestMapping("/v1/soldado")
public class SoldadoController {

	private SoldadoService soldadoService;

	public SoldadoController(SoldadoService soldadoService) {
		this.soldadoService = soldadoService;
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<Soldado> buscarSoldado(@PathVariable() String cpf) {
		Soldado soldado = soldadoService.buscarSoldado(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(soldado);
	}

	@PostMapping
	public ResponseEntity<Soldado> criarSoldado(@RequestBody Soldado soldado) {
		soldadoService.criarSoldado(soldado);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{cpf}")
	public ResponseEntity<SoldadoRequest> editarSoldado(@PathVariable() String cpf,
														@RequestBody SoldadoRequest soldadoRequest) {
		soldadoService.alterarSoldado(cpf, soldadoRequest);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<Soldado> deletarSoldado(@PathVariable String cpf) {
		soldadoService.deletarSoldado(cpf);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<Soldado>> buscarSoldado() {
		List<Soldado> soldados = soldadoService.buscarSoldados();
		return ResponseEntity.status(HttpStatus.OK).body(soldados);
	}

}
