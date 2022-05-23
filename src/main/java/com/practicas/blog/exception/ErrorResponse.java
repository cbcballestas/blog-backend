package com.practicas.blog.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	private LocalDateTime fecha;
	private String mensaje;
	private String urlRecurso;
}
