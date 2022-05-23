package com.practicas.blog.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practicas.blog.dto.PaginacionRespuestaDTO;
import com.practicas.blog.dto.PublicacionDTO;
import com.practicas.blog.service.IPublicacionService;
import com.practicas.blog.util.ConstantUtil;
import com.practicas.blog.util.JsonResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/publicaciones")
@RequiredArgsConstructor
public class PublicacionController {

	private final IPublicacionService service;

	@GetMapping
	public ResponseEntity<JsonResponse<PaginacionRespuestaDTO<PublicacionDTO>>> obtenerPublicaciones(
			@RequestParam(name = "pageNo", defaultValue = ConstantUtil.NUMERO_PAGINA, required = false) int numeroPagina,
			@RequestParam(name = "size", defaultValue = ConstantUtil.MEDIDA_PAGINA, required = false) int medidaPagina,
			@RequestParam(name = "sortBy", defaultValue = ConstantUtil.SORT_ORDER, required = false) String sortOrder,
			@RequestParam(name = "sortDir", defaultValue = ConstantUtil.SORT_DIR, required = false) String sortDir) {
		return service.obtenerPublicaciones(numeroPagina, medidaPagina, sortOrder, sortDir);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JsonResponse<PublicacionDTO>> obtenerPublicacionPorId(
			@PathVariable("id") Long idPublicacion) {
		return service.obtenerPublicacionPorId(idPublicacion);
	}

	@PostMapping
	public ResponseEntity<JsonResponse<PublicacionDTO>> crearPublicacion(
			@Valid @RequestBody PublicacionDTO publicacionDTO) {
		return service.crearPublicacion(publicacionDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<JsonResponse<PublicacionDTO>> actualizarPublicacion(@PathVariable("id") Long idPublicacion,
			@Valid @RequestBody PublicacionDTO publicacionDTO) {
		return service.actualizarPublicacion(idPublicacion, publicacionDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPublicacion(@PathVariable("id") Long idPublicacion) {
		return service.eliminarPublicacion(idPublicacion);
	}

}
