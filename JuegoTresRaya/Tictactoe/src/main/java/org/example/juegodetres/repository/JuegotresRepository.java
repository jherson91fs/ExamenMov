package org.example.juegodetres.repository;

import org.example.juegodetres.entity.Juegotres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuegotresRepository extends JpaRepository<Juegotres, Long> {
    // Aquí puedes definir consultas personalizadas si es necesario
}
