package com.practicas.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {

	private long id;
	
	@NotEmpty(message = "El nombre del comentario es obligatorio")
	private String nombre;
	
	@Email
	@NotEmpty(message = "El campo email es obligatorio")
	private String email;
	
	@NotEmpty(message = "DEBE ingresar el cuerpo del comentario")
	@Size(min = 10, message = "El cuerpo del comentario DEBE tener al menos 10 caracteres")
	private String cuerpo;

}
