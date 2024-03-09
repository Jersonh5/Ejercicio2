package com.example.demo.repositorios;

import com.example.demo.entidades.Sugerencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SugerenciaRepository extends JpaRepository<Sugerencia, Long> {
    List<Sugerencia> findByDescripcion(String descripcion);
    void updateDescripcionById(String descripcion, Long id);

    void deleteById(Long id);
}
