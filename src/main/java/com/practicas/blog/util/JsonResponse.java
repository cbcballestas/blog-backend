package com.practicas.blog.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class JsonResponse<T> {

	@JsonProperty("estado_proceso")
	private String estadoProceso;
	private String mensaje;
	private T data;

	public JsonResponse(String estadoProceso, String mensaje, T data) {
		this.estadoProceso = estadoProceso;
		this.mensaje = mensaje;
		this.data = data;
	}

	public JsonResponse(String estadoProceso, String mensaje) {
		this.estadoProceso = estadoProceso;
		this.mensaje = mensaje;
	}
}
