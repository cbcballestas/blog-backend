package com.practicas.blog.service;

import org.springframework.http.ResponseEntity;

import com.practicas.blog.dto.PaginacionRespuestaDTO;
import com.practicas.blog.dto.PublicacionDTO;
import com.practicas.blog.util.JsonResponse;

public interface IPublicacionService {

	ResponseEntity<JsonResponse<PaginacionRespuestaDTO<PublicacionDTO>>> obtenerPublicaciones(int numeroPagina, int medidaPagina,
			String sortBy, String sortDir);

	ResponseEntity<JsonResponse<PublicacionDTO>> obtenerPublicacionPorId(Long idPublicacion);

	ResponseEntity<JsonResponse<PublicacionDTO>> crearPublicacion(PublicacionDTO publicacionDTO);

	ResponseEntity<JsonResponse<PublicacionDTO>> actualizarPublicacion(Long idPublicacion,
			PublicacionDTO publicacionDTO);

	ResponseEntity<Void> eliminarPublicacion(Long idPublicacion);
}
