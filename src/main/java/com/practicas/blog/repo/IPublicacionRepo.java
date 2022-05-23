package com.practicas.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practicas.blog.model.Publicacion;

@Repository
public interface IPublicacionRepo extends JpaRepository<Publicacion, Long> {

}
