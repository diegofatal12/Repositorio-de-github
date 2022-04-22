package com.sistema.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sistema.blog.entity.Rol;
@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
	public Optional<Rol> findByNombre(String nombre);
}
