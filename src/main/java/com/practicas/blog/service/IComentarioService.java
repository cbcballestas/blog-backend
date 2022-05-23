package com.practicas.blog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practicas.blog.dto.ComentarioDTO;
import com.practicas.blog.util.JsonResponse;

public interface IComentarioService {

	ResponseEntity<JsonResponse<List<ComentarioDTO>>> obtenerComentariosPorPublicacion(long idPublicacion);

	ResponseEntity<JsonResponse<ComentarioDTO>> obtenerComentarioPorId(Long idPublicacion, Long idComentario);

	ResponseEntity<JsonResponse<ComentarioDTO>> agregarComentario(long idPublicacion, ComentarioDTO comentarioDTO);

	ResponseEntity<JsonResponse<ComentarioDTO>> actualizarComentario(Long idPublicacion, Long idComentario, ComentarioDTO comentarioDTO);
	
	ResponseEntity<JsonResponse<Void>> eliminarComentario(Long idPublicacion, Long idComentario);

}
