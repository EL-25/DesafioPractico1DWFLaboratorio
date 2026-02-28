package com.desafio.pruebas;

import com.desafio.pruebas.domain.Producto;
import com.desafio.pruebas.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev") // Obligatorio para usar el perfil de la base H2
// Limpia la base de datos antes de cada test para que los datos
// de una prueba no afecten el resultado de la siguiente.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DesafioPractico1ApplicationTests {

    @Autowired
    private ProductoService service; // Inyectamos el servicio para probarlo

    @Test
    void insertar_registros_test() {
        // 1. Insertamos los 100 registros usando el metodo del service
        service.insertarLote(100);

        // 2. Obtenemos el total
        long total = service.contarTotal();

        // 3. Comprobamos que sean 100 exactamente
        assertEquals(100, total, "Debería haber 100 registros en la base de datos");
    }

    @Test
    void leer_registros_test() {
        // Nos aseguramos de que haya datos (por si los tests corren en desorden)
        if(service.contarTotal() < 100) service.insertarLote(100);

        // 1. Recuperamos solo los primeros 25
        List<Producto> lista = service.obtenerPrimeros(25);

        // 2. Comprobamos que el tamaño de la lista sea 25
        assertEquals(25, lista.size(), "La lista devuelta debe tener 25 elementos");
    }

    @Test
    void modificar_registro_test() {
        if(service.contarTotal() < 100) service.insertarLote(100);

        // 1. Buscamos el registro con ID 50
        Producto p = service.buscarPorId(50L);
        assertNotNull(p, "El producto con ID 50 debe existir");

        // 2. Modificamos su valor (el nombre por ejemplo)
        String nuevoNombre = "Producto Editado";
        p.setNombre(nuevoNombre);
        service.guardar(p);

        // 3. Lo volvemos a buscar para verificar el cambio
        Producto pEditado = service.buscarPorId(50L);
        assertEquals(nuevoNombre, pEditado.getNombre(), "El nombre debería haber cambiado");
    }

    @Test
    void borrar_registro_test() {
        if(service.contarTotal() < 100) service.insertarLote(100);

        // 1. Borramos el registro con ID 75
        service.eliminar(75L);

        // 2. Intentamos buscarlo
        Producto p = service.buscarPorId(75L);

        // 3. Comprobamos que sea nulo (que ya no existe)
        assertNull(p, "El registro con ID 75 ya no debería existir en la base");
    }
}
