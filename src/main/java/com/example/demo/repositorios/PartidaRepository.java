package com.example.demo.repositorios;

import com.example.demo.entidades.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByCreador(String creador);

    List<Partida> findByDeporte(String deporte);
    void updateComentariosById(String comentarios, Long id);

    void deleteById(Long id);
}
