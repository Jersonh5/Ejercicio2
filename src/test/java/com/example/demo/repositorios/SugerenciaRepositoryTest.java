package com.example.demo.repositorios;

import com.example.demo.entidades.Sugerencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.Assert.assertThat;

class SugerenciaRepositoryTest {

    @Autowired
    private SugerenciaRepository sugerenciaRepository;

    @BeforeEach
    void setUp() {
        sugerenciaRepository.deleteAll();
    }

    @Test
    void givenASugerencia_whenSave_thenSugerenciaWithId() {
        // Given
        Sugerencia sugerencia = Sugerencia.builder()
                .descripcion("New suggestion")
                .build();

        // When
        Sugerencia sugerenciaSaved = sugerenciaRepository.save(sugerencia);

        // Then
        assertThat(sugerenciaSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("given a description when findByDescripcion then obtain a list of suggestions")
    void shouldFindByDescripcion() {
        // Given
        Sugerencia sugerencia = Sugerencia.builder()
                .descripcion("New suggestion")
                .build();
        sugerenciaRepository.save(sugerencia);

        // When
        List<Sugerencia> sugerencias = sugerenciaRepository.findByDescripcion("New suggestion");

        // Then
        assertThat(sugerencias).isNotEmpty();
        assertThat(sugerencias).first().hasFieldOrPropertyWithValue("descripcion", "New suggestion");
    }

    @Test
    @DisplayName("given an id and description when updateDescripcionById then update the description")
    void shouldUpdateDescripcionById() {
        // Given
        Sugerencia sugerencia = Sugerencia.builder()
                .descripcion("Old suggestion")
                .build();
        sugerenciaRepository.save(sugerencia);

        // When
        sugerenciaRepository.updateDescripcionById("Updated suggestion", sugerencia.getId());

        // Then
        Sugerencia updatedSugerencia = sugerenciaRepository.findById(sugerencia.getId()).orElse(null);
        assertThat(updatedSugerencia).isNotNull();
        assertThat(updatedSugerencia.getDescripcion()).isEqualTo("Updated suggestion");
    }

    @Test
    @DisplayName("given an id when deleteById then delete the suggestion")
    void shouldDeleteById() {
        // Given
        Sugerencia sugerencia = Sugerencia.builder()
                .descripcion("To be deleted")
                .build();
        sugerenciaRepository.save(sugerencia);

        // When
        sugerenciaRepository.deleteById(sugerencia.getId());

        // Then
        assertThat(sugerenciaRepository.findById(sugerencia.getId())).isEmpty();
    }
}