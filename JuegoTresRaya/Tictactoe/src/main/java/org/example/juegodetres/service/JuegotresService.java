package org.example.juegodetres.service;

import org.example.juegodetres.entity.Juegotres;
import org.example.juegodetres.repository.JuegotresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JuegotresService {

    @Autowired
    private JuegotresRepository juegotresRepository;

    // Crear un nuevo juego
    public Juegotres createGame(Juegotres game) {
        game.setBoard("_________");  // Tablero vacío al inicio
        game.setEstado("En progreso"); // Inicializa el estado como "En progreso"
        game.setFinished(false);
        return juegotresRepository.save(game);
    }

    // Obtener un juego por ID
    public Juegotres getGameById(Long id) {
        Optional<Juegotres> game = juegotresRepository.findById(id);
        return game.orElseThrow(() -> new RuntimeException("Game not found"));
    }

    // Eliminar un juego por ID
    public void deleteGame(Long id) {
        Juegotres game = getGameById(id);  // Verificar que el juego existe
        juegotresRepository.delete(game);
    }

    // Actualizar el tablero y hacer un movimiento
    public Juegotres makeMove(Long id, int position, String player) {
        Juegotres game = getGameById(id);

        if (game.isFinished()) {
            throw new RuntimeException("Game is already finished");
        }

        char[] boardArray = game.getBoard().toCharArray();

        // Verificar si la posición está vacía
        if (boardArray[position] == '_') {
            boardArray[position] = player.charAt(0);
            game.setBoard(String.valueOf(boardArray));

            // Lógica para verificar si hay un ganador después de cada movimiento
            String ganador = checkWinner(game);
            if (!ganador.equals("no winner")) {
                game.setGanador(ganador);
                game.setEstado("Terminado");
                game.setFinished(true);
                // Aquí podrías actualizar los puntos del ganador
                updatePoints(game, ganador);
            }
        } else {
            throw new RuntimeException("Position already taken");
        }

        return juegotresRepository.save(game);
    }

    // Verificar si hay un ganador (ejemplo simplificado)
    public String checkWinner(Juegotres game) {
        // Lógica simplificada para verificar si hay un ganador
        String board = game.getBoard();
        String[][] winPatterns = {
                {"0", "1", "2"},
                {"3", "4", "5"},
                {"6", "7", "8"},
                {"0", "3", "6"},
                {"1", "4", "7"},
                {"2", "5", "8"},
                {"0", "4", "8"},
                {"2", "4", "6"}
        };

        for (String[] pattern : winPatterns) {
            if (board.charAt(Integer.parseInt(pattern[0])) != '_' &&
                    board.charAt(Integer.parseInt(pattern[0])) == board.charAt(Integer.parseInt(pattern[1])) &&
                    board.charAt(Integer.parseInt(pattern[1])) == board.charAt(Integer.parseInt(pattern[2]))) {
                return String.valueOf(board.charAt(Integer.parseInt(pattern[0])));
            }
        }
        return "no winner";
    }

    // Actualizar los puntos del jugador ganador
    private void updatePoints(Juegotres game, String ganador) {
        if (ganador.equals(game.getNombreJugador1())) {
            game.setPunto(game.getPunto() + 10); // Suma 10 puntos al ganador
        } else if (ganador.equals(game.getNombreJugador2())) {
            game.setPunto(game.getPunto() + 10);
        }
    }

    // Método para actualizar el ganador del juego
    public Juegotres updateWinner(Long gameId, String ganador) throws Exception {
        Optional<Juegotres> optionalGame = juegotresRepository.findById(gameId);

        if (optionalGame.isPresent()) {
            Juegotres game = optionalGame.get();
            game.setGanador(ganador);
            game.setEstado("Terminado");
            game.setFinished(true); // Marcar el juego como terminado
            return juegotresRepository.save(game);
        } else {
            throw new Exception("Juego no encontrado");
        }
    }
}
