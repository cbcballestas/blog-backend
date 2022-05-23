package com.practicas.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practicas.blog.dto.PaginacionRespuestaDTO;
import com.practicas.blog.dto.PublicacionDTO;
import com.practicas.blog.exception.ResourceNotFoundException;
import com.practicas.blog.model.Publicacion;
import com.practicas.blog.repo.IPublicacionRepo;
import com.practicas.blog.service.IPublicacionService;
import com.practicas.blog.util.ConstantUtil;
import com.practicas.blog.util.JsonResponse;
import com.practicas.blog.util.UtilConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublicacionServiceImpl implements IPublicacionService {

	private final IPublicacionRepo repo;
	private final UtilConverter utilConverter;
	private final ConstantUtil utilKey;

	/**
	 * Método que se encarga de obtener todas las publicaciones de manera paginada
	 */
	@Override
	public ResponseEntity<JsonResponse<PaginacionRespuestaDTO<PublicacionDTO>>> obtenerPublicaciones(int numeroPagina,
			int medidaPagina, String sortBy, String sortDir) {

		// Se realiza ordenamiento por campo
		Sort sort = sortDir.equalsIgnoreCase(Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(numeroPagina, medidaPagina, sort);
		Page<Publicacion> publicaciones = repo.findAll(pageable);

		List<PublicacionDTO> listaPublicaciones = publicaciones.getContent().stream()
				.map(publicacion -> utilConverter.convertirAPublicacionDTO(publicacion)).collect(Collectors.toList());

		// Se construye DTO para paginación de registros
		PaginacionRespuestaDTO<PublicacionDTO> paginacionRespuestaDTO = new PaginacionRespuestaDTO<>();
		paginacionRespuestaDTO.setNumeroPagina(publicaciones.getNumber());
		paginacionRespuestaDTO.setMedidaPagina(publicaciones.getSize());
		paginacionRespuestaDTO.setLista(listaPublicaciones);
		paginacionRespuestaDTO.setTotalElementos(publicaciones.getTotalElements());
		paginacionRespuestaDTO.setTotalPaginas(publicaciones.getTotalPages());
		paginacionRespuestaDTO.setEsUltima(publicaciones.isLast());

		return new ResponseEntity<>(new JsonResponse<PaginacionRespuestaDTO<PublicacionDTO>>(utilKey.STATUS_SUCESSFUL,
				utilKey.MSG_SUCESSFUL, paginacionRespuestaDTO), HttpStatus.OK);
	}

	/**
	 * Método que se encarga de obtener una publicación por ID
	 */
	@Override
	public ResponseEntity<JsonResponse<PublicacionDTO>> obtenerPublicacionPorId(Long idPublicacion) {

		Publicacion publicacion = repo.findById(idPublicacion)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ REGISTRO CON ID: " + idPublicacion));

		PublicacionDTO publicacionRespuesta = utilConverter.convertirAPublicacionDTO(publicacion);

		return new ResponseEntity<>(
				new JsonResponse<PublicacionDTO>(utilKey.STATUS_SUCESSFUL, utilKey.MSG_SUCESSFUL, publicacionRespuesta),
				HttpStatus.OK);
	}

	/**
	 * Método que se encarga de crear una publicación
	 */
	@Override
	public ResponseEntity<JsonResponse<PublicacionDTO>> crearPublicacion(PublicacionDTO publicacionDTO) {

		Publicacion publicacion = utilConverter.convertirAPublicacion(publicacionDTO);

		Publicacion nuevaPublicacion = repo.save(publicacion);

		return new ResponseEntity<>(new JsonResponse<PublicacionDTO>(utilKey.STATUS_SUCESSFUL, utilKey.MSG_SUCESSFUL,
				utilConverter.convertirAPublicacionDTO(nuevaPublicacion)), HttpStatus.CREATED);

	}

	/**
	 * Método que se encarga de actualizar los datos de una publicación
	 */
	@Override
	public ResponseEntity<JsonResponse<PublicacionDTO>> actualizarPublicacion(Long idPublicacion,
			PublicacionDTO publicacionDTO) {

		Publicacion publicacion = repo.findById(idPublicacion)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ REGISTRO CON ID " + idPublicacion));

		// Se actualizan los datos en caso de que se halla encontrado el registro
		// buscado
		publicacion.setContenido(publicacionDTO.getContenido());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setTitulo(publicacionDTO.getTitulo());

		Publicacion publicacionActualizada = repo.save(publicacion);

		return new ResponseEntity<>(new JsonResponse<PublicacionDTO>(utilKey.STATUS_SUCESSFUL, utilKey.MSG_SUCESSFUL,
				utilConverter.convertirAPublicacionDTO(publicacionActualizada)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> eliminarPublicacion(Long idPublicacion) {

		Publicacion publicacion = repo.findById(idPublicacion)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ REGISTRO CON ID " + idPublicacion));

		repo.delete(publicacion);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
