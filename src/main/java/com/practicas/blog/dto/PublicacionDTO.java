package com.practicas.blog.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.practicas.blog.model.Comentario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionDTO {

	private long id;

	@NotEmpty
	@Size(min = 2, message = "El título de la publicación DEBE tener al menos 2 caracteres")
	private String titulo;

	@NotEmpty
	@Size(min = 2, message = "La descripción de la publicación DEBE tener al menos 10 caracteres")
	private String descripcion;

	@NotEmpty
	private String contenido;

	private Set<Comentario> comentarios;
}
