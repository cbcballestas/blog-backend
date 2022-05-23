package com.practicas.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practicas.blog.dto.ComentarioDTO;
import com.practicas.blog.exception.BlogAppException;
import com.practicas.blog.exception.ResourceNotFoundException;
import com.practicas.blog.model.Comentario;
import com.practicas.blog.model.Publicacion;
import com.practicas.blog.repo.IComentarioRepo;
import com.practicas.blog.repo.IPublicacionRepo;
import com.practicas.blog.service.IComentarioService;
import com.practicas.blog.util.ConstantUtil;
import com.practicas.blog.util.JsonResponse;
import com.practicas.blog.util.UtilConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements IComentarioService {

	private final IComentarioRepo comentarioRepo;
	private final IPublicacionRepo publicacionRepo;
	private final UtilConverter utilConverter;
	private final ConstantUtil utilKey;

	/**
	 * Método que se encarga de consultar los comentarios por el id de una
	 * publicación
	 */
	@Override
	public ResponseEntity<JsonResponse<List<ComentarioDTO>>> obtenerComentariosPorPublicacion(long idPublicacion) {

		List<ComentarioDTO> comentarios = comentarioRepo.findByPublicacionId(idPublicacion).stream()
				.map(comentario -> utilConverter.convertirAComentarioDTO(comentario)).collect(Collectors.toList());
		return new ResponseEntity<>(
				new JsonResponse<List<ComentarioDTO>>(utilKey.STATUS_SUCESSFUL, utilKey.MSG_SUCESSFUL, comentarios),
				HttpStatus.OK);
	}

	/**
	 * Método que se encarga de consultar un comentario por id
	 */
	@Override
	public ResponseEntity<JsonResponse<ComentarioDTO>> obtenerComentarioPorId(Long idPublicacion, Long idComentario) {

		Publicacion publicacion = publicacionRepo.findById(idPublicacion).orElseThrow(
				() -> new ResourceNotFoundException("NO SE ENCONTRÓ PUBLICACIÓN CON ID: " + idPublicacion));

		Comentario comentarioBuscar = comentarioRepo.findById(idComentario)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ COMENTARIO CON ID: " + idPublicacion));

		if (!publicacion.getId().equals(comentarioBuscar.getPublicacion().getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST,
					"El comentario buscado NO pertenece a la publicación especificada");
		}

		return new ResponseEntity<>(new JsonResponse<ComentarioDTO>(utilKey.STATUS_SUCESSFUL, utilKey.MSG_SUCESSFUL,
				utilConverter.convertirAComentarioDTO(comentarioBuscar)), HttpStatus.OK);
	}

	/**
	 * Método que se encarga de agregarle un comentario a una publicación
	 */
	@Override
	public ResponseEntity<JsonResponse<ComentarioDTO>> agregarComentario(long idPublicacion,
			ComentarioDTO comentarioDTO) {

		Publicacion publicacion = publicacionRepo.findById(idPublicacion)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ REGISTRO CON ID: " + idPublicacion));

		Comentario comentario = utilConverter.convertirAComentario(comentarioDTO);
		comentario.setPublicacion(publicacion);

		Comentario nuevoRegistro = comentarioRepo.save(comentario);

		return new ResponseEntity<>(new JsonResponse<ComentarioDTO>(utilKey.STATUS_SUCESSFUL, utilKey.MSG_SUCESSFUL,
				utilConverter.convertirAComentarioDTO(nuevoRegistro)), HttpStatus.CREATED);
	}

	/**
	 * Método que se encarga de actualizar un comentario de una publicación
	 */
	@Override
	public ResponseEntity<JsonResponse<ComentarioDTO>> actualizarComentario(Long idPublicacion, Long idComentario,
			ComentarioDTO comentarioDTO) {
		Publicacion publicacion = publicacionRepo.findById(idPublicacion)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ REGISTRO CON ID: " + idPublicacion));

		Comentario comentario = comentarioRepo.findById(idComentario)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ COMENTARIO CON ID: " + idComentario));

		if (!publicacion.getId().equals(comentario.getPublicacion().getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST,
					"El comentario buscado NO pertenece a la publicación especificada");
		}

		comentario.setNombre(comentarioDTO.getNombre());
		comentario.setEmail(comentarioDTO.getEmail());
		comentario.setCuerpo(comentarioDTO.getCuerpo());

		Comentario registroActualizado = comentarioRepo.save(comentario);

		return new ResponseEntity<>(new JsonResponse<ComentarioDTO>(utilKey.STATUS_SUCESSFUL, utilKey.MSG_SUCESSFUL,
				utilConverter.convertirAComentarioDTO(registroActualizado)), HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<JsonResponse<Void>> eliminarComentario(Long idPublicacion, Long idComentario) {

		Publicacion publicacion = publicacionRepo.findById(idPublicacion)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ REGISTRO CON ID: " + idPublicacion));

		Comentario comentario = comentarioRepo.findById(idComentario)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ COMENTARIO CON ID: " + idComentario));

		if (!publicacion.getId().equals(comentario.getPublicacion().getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST,
					"El comentario buscado NO pertenece a la publicación especificada");
		}

		comentarioRepo.delete(comentario);

		return new ResponseEntity<JsonResponse<Void>>(HttpStatus.NO_CONTENT);
	}

}
