package com.desafio.pruebas.repository;

import com.desafio.pruebas.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Le avisa a Spring que este es nuestro acceso a datos
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Al extender de JpaRepository, ya tenemos gratis:
    // save(), findAll(), findById(), deleteById(), etc.
    // Justo lo que necesitamos para los 4 tests del desafío.
}
