package com.practicas.blog.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.practicas.blog.dto.ComentarioDTO;
import com.practicas.blog.dto.PublicacionDTO;
import com.practicas.blog.model.Comentario;
import com.practicas.blog.model.Publicacion;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UtilConverter {

	private final ModelMapper modelMapper;
	
	/**
	 * Método que se encarga de mapear de DTO a entidad
	 * 
	 * @param publicacionDTO
	 * @return Objeto de tipo Publicacion
	 */
	public Publicacion convertirAPublicacion(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = modelMapper.map(publicacionDTO, Publicacion.class);
		return publicacion;
	}

	/**
	 * Método que se encarga de mapear de entidad a DTO
	 * 
	 * @param publicacion
	 * @return Objeto de tipo PublicacionDTO
	 */
	public PublicacionDTO convertirAPublicacionDTO(Publicacion publicacion) {
		PublicacionDTO publicacionDTO = modelMapper.map(publicacion, PublicacionDTO.class);
		return publicacionDTO;
	}
	
	/**
	 * Método que se encarga de convertir un comentario a DTO
	 * 
	 * @param comentario
	 * @return
	 */
	public ComentarioDTO convertirAComentarioDTO(Comentario comentario) {
		ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
		return comentarioDTO;
	}

	/**
	 * Método que se encarga de convertir ComentarioDTO a entidad
	 * 
	 * @param comentarioDTO
	 * @return
	 */
	public Comentario convertirAComentario(ComentarioDTO comentarioDTO) {
		Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
		return comentario;
	}
	
}
