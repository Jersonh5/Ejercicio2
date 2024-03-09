package com.example.demo.repositorios;

import com.example.demo.entidades.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByCreador(String creador);
    List<Mensaje> findByDestinario(String destinario);
    void updateContenidoById(String contenido, Long id);
    void deleteById(Long id);
}
