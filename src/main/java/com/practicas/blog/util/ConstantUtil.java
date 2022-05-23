package com.practicas.blog.util;

import org.springframework.stereotype.Component;

@Component
public class ConstantUtil {

	// status request
	public final String STATUS_SUCESSFUL = "OK";
	public final String STATUS_ERROR = "ERROR";

	// msg status request
	public final String MSG_SUCESSFUL = "Proceso realizado con éxito";
	public final String MSG_ERROR = "Se ha generado un error";
	
	// pagination
	public static final String NUMERO_PAGINA = "0";
	public static final String MEDIDA_PAGINA = "10";
	public static final String SORT_ORDER = "id";
	public static final String SORT_DIR = "asc";
}
