package org.example.juegodetres.controller;

import org.example.juegodetres.entity.Juegotres;
import org.example.juegodetres.service.JuegotresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/juegotres")
public class JuegotresController {

    @Autowired
    private JuegotresService juegotresService;

    // Crear un nuevo juego
    @PostMapping
    public Juegotres createNewGame(@RequestBody Juegotres game) {
        return juegotresService.createGame(game);
    }

    // Obtener un juego por ID
    @GetMapping("/{id}")
    public Juegotres getGameById(@PathVariable Long id) {
        return juegotresService.getGameById(id);
    }

    // Realizar un movimiento en el juego
    @PutMapping("/{id}/move")
    public ResponseEntity<Juegotres> makeMove(@PathVariable Long id, @RequestParam int position, @RequestParam String player) {
        try {
            Juegotres updatedGame = juegotresService.makeMove(id, position, player);
            return ResponseEntity.ok(updatedGame);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);  // Devuelve un error si el movimiento es inválido
        }
    }

    // Eliminar un juego por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        try {
            juegotresService.deleteGame(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Devuelve un error si el juego no se encuentra
        }
    }

    // Método para actualizar el ganador
    @PatchMapping("/{id}/winner")
    public ResponseEntity<Juegotres> updateWinner(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        try {
            if (updates.containsKey("winner")) {
                Juegotres updatedGame = juegotresService.updateWinner(id, updates.get("winner"));
                return ResponseEntity.ok(updatedGame);
            } else {
                return ResponseEntity.badRequest().body(null); // Si no contiene el campo "winner"
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Juego no encontrado
        }
    }
}
