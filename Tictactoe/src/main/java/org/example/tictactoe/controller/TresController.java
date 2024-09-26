package org.example.tictactoe.controller;

import org.example.tictactoe.entity.Tres;
import org.example.tictactoe.service.TresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/juegotres")
public class TresController {

    @Autowired
    private TresService resultadoService;

    // Obtener todos los resultados
    @GetMapping
    public List<Tres> listarResultados() {
        return resultadoService.listarResultados();
    }

    // Obtener un resultado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tres> obtenerResultadoPorId(@PathVariable Long id) {
        Optional<Tres> resultado = resultadoService.obtenerResultadoPorId(id);
        return resultado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo resultado
    @PostMapping
    public Tres guardarResultado(@RequestBody Tres resultado) {
        return resultadoService.guardarResultado(resultado);
    }

    // Actualizar un resultado existente
    @PutMapping("/{id}")
    public ResponseEntity<Tres> actualizarResultado(@PathVariable Long id, @RequestBody Tres resultado) {
        try {
            Tres resultadoActualizado = resultadoService.actualizarResultado(id, resultado);
            return ResponseEntity.ok(resultadoActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un resultado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResultado(@PathVariable Long id) {
        resultadoService.eliminarResultado(id);
        return ResponseEntity.ok().build();
    }
}