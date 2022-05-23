package com.practicas.blog.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginacionRespuestaDTO<T> {

	private int numeroPagina;
	private int medidaPagina;
	private int totalPaginas;
	private long totalElementos;
	private List<T> lista;
	private boolean esUltima;
}
