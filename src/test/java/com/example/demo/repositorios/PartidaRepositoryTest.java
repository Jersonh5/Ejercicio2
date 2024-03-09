package com.example.demo.repositorios;

import com.example.demo.entidades.Partida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;



class PartidaRepositoryTest {
    @Autowired
    private PartidaRepository partidaRepository;

    @BeforeEach
    void setUp() {
        partidaRepository.deleteAll();
    }

    @Test
    void givenAPartida_whenSave_thenPartidaWithId() {
        // Given
        Partida partida = Partida.builder()
                .creador("Creator")
                .deporte("Football")
                .comentarios("Exciting match")
                .build();

        // When
        Partida partidaSaved = partidaRepository.save(partida);

        // Then
        assertThat(partidaSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("given a creator when findByCreador then obtain a list of matches")
    void shouldFindByCreador() {
        // Given
        Partida partida = Partida.builder()
                .creador("Creator")
                .deporte("Football")
                .comentarios("Exciting match")
                .build();
        partidaRepository.save(partida);

        // When
        List<Partida> partidas = partidaRepository.findByCreador("Creator");

        // Then
        assertThat(partidas).isNotEmpty();
        assertThat(partidas).first().hasFieldOrPropertyWithValue("creador", "Creator");
    }

    @Test
    @DisplayName("given a sport when findByDeporte then obtain a list of matches")
    void shouldFindByDeporte() {
        // Given
        Partida partida = Partida.builder()
                .creador("Creator")
                .deporte("Football")
                .comentarios("Exciting match")
                .build();
        partidaRepository.save(partida);

        // When
        List<Partida> partidas = partidaRepository.findByDeporte("Football");

        // Then
        assertThat(partidas).isNotEmpty();
        assertThat(partidas).first().hasFieldOrPropertyWithValue("deporte", "Football");
    }

    @Test
    @DisplayName("given an id and comments when updateComentariosById then update the comments")
    void shouldUpdateComentariosById() {
        // Given
        Partida partida = Partida.builder()
                .creador("Creator")
                .deporte("Football")
                .comentarios("Exciting match")
                .build();
        partidaRepository.save(partida);

        // When
        partidaRepository.updateComentariosById("Updated comments", partida.getId());

        // Then
        Partida updatedPartida = partidaRepository.findById(partida.getId()).orElse(null);
        assertThat(updatedPartida).isNotNull();
        assertThat(updatedPartida.getComentarios()).isEqualTo("Updated comments");
    }

    @Test
    @DisplayName("given an id when deleteById then delete the match")
    void shouldDeleteById() {
        // Given
        Partida partida = Partida.builder()
                .creador("Creator")
                .deporte("Football")
                .comentarios("Exciting match")
                .build();
        partidaRepository.save(partida);

        // When
        partidaRepository.deleteById(partida.getId());

        // Then
        assertThat(partidaRepository.findById(partida.getId())).isEmpty();
    }
}