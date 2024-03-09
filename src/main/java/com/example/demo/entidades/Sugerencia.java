package com.example.demo.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Table(name = "sugerencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sugerencia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuarios;
}
