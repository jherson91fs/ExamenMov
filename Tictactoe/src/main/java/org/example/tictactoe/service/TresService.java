package org.example.tictactoe.service;

import org.example.tictactoe.entity.Tres;
import org.example.tictactoe.repository.TresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TresService {

    @Autowired
    private TresRepository resultadoRepository;

    public List<Tres> listarResultados() {
        return resultadoRepository.findAll();
    }

    public Optional<Tres> obtenerResultadoPorId(Long id) {
        return resultadoRepository.findById(id);
    }

    public Tres guardarResultado(Tres resultado) {
        return resultadoRepository.save(resultado);
    }

    public Tres actualizarResultado(Long id, Tres resultadoActualizado) throws Exception {
        Optional<Tres> optionalResultado = resultadoRepository.findById(id);
        if (optionalResultado.isPresent()) {
            Tres resultadoExistente = optionalResultado.get();
            resultadoExistente.setNombrePartida(resultadoActualizado.getNombrePartida());
            resultadoExistente.setNombreJugador1(resultadoActualizado.getNombreJugador1());
            resultadoExistente.setNombreJugador2(resultadoActualizado.getNombreJugador2());
            resultadoExistente.setGanador(resultadoActualizado.getGanador());
            resultadoExistente.setPunto(resultadoActualizado.getPunto());
            resultadoExistente.setEstado(resultadoActualizado.getEstado());  // Asegúrate de que se actualice el estado
            return resultadoRepository.save(resultadoExistente);             // Guarda el resultado
        } else {
            throw new Exception("Resultado no encontrado");
        }
    }




    public void eliminarResultado(Long id) {
        resultadoRepository.deleteById(id);
    }
}