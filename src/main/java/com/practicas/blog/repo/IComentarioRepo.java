package com.practicas.blog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practicas.blog.model.Comentario;

@Repository
public interface IComentarioRepo extends JpaRepository<Comentario, Long> {

	List<Comentario> findByPublicacionId(long idPublicacion);
}
