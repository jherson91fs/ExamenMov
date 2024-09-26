package org.example.juegodetres.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
@Entity
public class Juegotres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResultado;

    private String nombrePartida;      // Nueva columna para representar el nombre de la partida
    @NotBlank(message = "El nombre del jugador 1 no puede estar vacío")
    private String nombreJugador1;
    @NotBlank(message = "El nombre del jugador 2 no puede estar vacío")
    private String nombreJugador2;
    private String ganador;            // Este campo está correcto
    private Integer punto;             // Nuevo campo para representar los puntos obtenidos
    private String estado;             // Cambio de isFinished a estado
    private String board;  // Representación del tablero, por ejemplo, "XOXOXO___"
    private boolean isFinished;
}
