package com.example.demo.repositorios;

import com.example.demo.entidades.Mensaje;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

class MensajeRepositoryTest {

    @Autowired
    private MensajeRepository mensajeRepository;

    @BeforeEach
    void setUp() {
        mensajeRepository.deleteAll();
    }

    @Test
    void givenAMensaje_whenSave_thenMensajeWithId() {
        // Given
        Mensaje mensaje = Mensaje.builder()
                .creador("Sender")
                .destinatario("Recipient")
                .contenido("Hello, world!")
                .createAt(LocalDateTime.now())
                .build();

        // When
        Mensaje mensajeSaved = mensajeRepository.save(mensaje);

        // Then
        assertThat(mensajeSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("given a creator when findByCreador then obtain a list of messages")
    void shouldFindByCreador() {
        // Given
        Mensaje mensaje = Mensaje.builder()
                .creador("Sender")
                .destinatario("Recipient")
                .contenido("Hello, world!")
                .createAt(LocalDateTime.now())
                .build();
        mensajeRepository.save(mensaje);

        // When
        List<Mensaje> mensajes = mensajeRepository.findByCreador("Sender");

        // Then
        assertThat(mensajes).isNotEmpty();
        assertThat(mensajes).first().hasFieldOrPropertyWithValue("creador", "Sender");
    }

    @Test
    @DisplayName("given a recipient when findByDestinatario then obtain a list of messages")
    void shouldFindByDestinatario() {
        // Given
        Mensaje mensaje = Mensaje.builder()
                .creador("Sender")
                .destinatario("Recipient")
                .contenido("Hello, world!")
                .createAt(LocalDateTime.now())
                .build();
        mensajeRepository.save(mensaje);

        // When
        List<Mensaje> mensajes = mensajeRepository.findByDestinario("Recipient");

        // Then
        assertThat(mensajes).isNotEmpty();
        assertThat(mensajes).first().hasFieldOrPropertyWithValue("destinatario", "Recipient");
    }

    @Test
    @DisplayName("given an id and content when updateContenidoById then update the content")
    void shouldUpdateContenidoById() {
        // Given
        Mensaje mensaje = Mensaje.builder()
                .creador("Sender")
                .destinatario("Recipient")
                .contenido("Hello, world!")
                .createAt(LocalDateTime.now())
                .build();
        mensajeRepository.save(mensaje);

        // When
        mensajeRepository.updateContenidoById("Updated content", mensaje.getId());

        // Then
        Mensaje updatedMensaje = mensajeRepository.findById(mensaje.getId()).orElse(null);
        assertThat(updatedMensaje).isNotNull();
        assertThat(updatedMensaje.getContenido()).isEqualTo("Updated content");
    }

    @Test
    @DisplayName("given an id when deleteById then delete the message")
    void shouldDeleteById() {
        // Given
        Mensaje mensaje = Mensaje.builder()
                .creador("Sender")
                .destinatario("Recipient")
                .contenido("Hello, world!")
                .createAt(LocalDateTime.now())
                .build();
        mensajeRepository.save(mensaje);

        // When
        mensajeRepository.deleteById(mensaje.getId());

        // Then
        assertThat(mensajeRepository.findById(mensaje.getId())).isEmpty();
    }
}