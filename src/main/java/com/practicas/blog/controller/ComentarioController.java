package com.practicas.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practicas.blog.dto.ComentarioDTO;
import com.practicas.blog.service.IComentarioService;
import com.practicas.blog.util.JsonResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class ComentarioController {

	private final IComentarioService service;

	@GetMapping("/publicaciones/{idPublicacion}/comentarios")
	public ResponseEntity<JsonResponse<List<ComentarioDTO>>> obtenerComentariosPorPublicacion(
			@PathVariable("idPublicacion") long idPublicacion) {
		return service.obtenerComentariosPorPublicacion(idPublicacion);
	}

	@GetMapping("/publicaciones/{idPublicacion}/comentarios/{idComentario}")
	public ResponseEntity<JsonResponse<ComentarioDTO>> obtenerComentarioPorId(
			@PathVariable("idPublicacion") Long idPublicacion, @PathVariable("idComentario") Long idComentario) {
		return service.obtenerComentarioPorId(idPublicacion, idComentario);
	}

	@PostMapping("/publicaciones/{idPublicacion}/comentarios")
	public ResponseEntity<JsonResponse<ComentarioDTO>> guardarComentario(
			@PathVariable("idPublicacion") long idPublicacion, @Valid @RequestBody ComentarioDTO comentarioDTO) {
		return service.agregarComentario(idPublicacion, comentarioDTO);
	}

	@PutMapping("/publicaciones/{idPublicacion}/comentarios/{idComentario}")
	public ResponseEntity<JsonResponse<ComentarioDTO>> actualizarComentario(
			@PathVariable("idPublicacion") long idPublicacion, @PathVariable("idComentario") long idComentario,
			@Valid @RequestBody ComentarioDTO comentarioDTO) {
		return service.actualizarComentario(idPublicacion, idComentario, comentarioDTO);
	}

	@DeleteMapping("/publicaciones/{idPublicacion}/comentarios/{idComentario}")
	public ResponseEntity<JsonResponse<Void>> eliminarComentario(@PathVariable("idPublicacion") Long idPublicacion,
			@PathVariable("idComentario") Long idComentario) {
		return service.eliminarComentario(idPublicacion, idComentario);
	}

}
