package com.desafio.pruebas.service;

import com.desafio.pruebas.domain.Producto;
import com.desafio.pruebas.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository; // Aquí aplicamos Inyección de Dependencias (DI)

    // 1. Metodo para insertar registros masivamente (Prueba #1)
    public void insertarLote(int cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            Producto p = new Producto();
            p.setNombre("Producto " + i);
            p.setPrecio(10.0 * i);
            repository.save(p);
        }
    }

    // 2. Metodo para contar registros (Prueba #1)
    public long contarTotal() {
        return repository.count();
    }

    // 3. Metodo para obtener los primeros N registros (Prueba #2)
    public List<Producto> obtenerPrimeros(int limite) {
        return repository.findAll().stream()
                .limit(limite)
                .collect(Collectors.toList());
    }

    // 4. Metodo para buscar por ID (Prueba #3 y #4)
    public Producto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // 5. Metodo para guardar/actualizar (Prueba #3)
    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    // 6. Metodo para borrar (Prueba #4)
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
