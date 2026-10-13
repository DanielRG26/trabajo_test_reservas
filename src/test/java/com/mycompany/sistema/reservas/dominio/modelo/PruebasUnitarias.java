package com.mycompany.sistema.reservas.dominio.modelo;

import Creacionales.singleton.ConfiguracionSistema;
import Creacionales.singleton.GeneradorFolioFiscal;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 5 pruebas unitarias simples del sistema de reservas.
 */
public class PruebasUnitarias {

    // -------------------------------------------------------
    // Prueba 1: Email válido se crea sin errores
    // -------------------------------------------------------
    @Test
    void emailValido_seCreaCorrectamente() {
        Email email = new Email("daniel@correo.com");
        assertEquals("daniel@correo.com", email.valor());
    }

    // -------------------------------------------------------
    // Prueba 2: Email con formato inválido lanza excepción
    // -------------------------------------------------------
    @Test
    void emailSinArroba_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Email("correosinformato");
        });
    }

    // -------------------------------------------------------
    // Prueba 3: RangoFechas calcula bien la duración en días
    // -------------------------------------------------------
    @Test
    void rangoFechas_calculaDuracionCorrectamente() {
        LocalDateTime inicio = LocalDateTime.of(2026, 10, 1, 14, 0);
        LocalDateTime fin    = LocalDateTime.of(2026, 10, 4, 14, 0);

        RangoFechas rango = new RangoFechas(inicio, fin);

        assertEquals(3, rango.getDias());
    }

    // -------------------------------------------------------
    // Prueba 4: Cliente acumula 3 penalizaciones y se desactiva
    // -------------------------------------------------------
    @Test
    void cliente_conTresPenalizaciones_seDesactiva() {
        Cliente cliente = new Cliente("Ana López", new Email("ana@hotel.com"));

        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion();

        assertFalse(cliente.isActivo());
    }

    // -------------------------------------------------------
    // Prueba 5: GeneradorFolioFiscal produce folios únicos
    // -------------------------------------------------------
    @Test
    void generadorFolio_produceFoliosDiferentes() {
        GeneradorFolioFiscal generador = GeneradorFolioFiscal.getInstance();

        String folio1 = generador.generarFolio();
        String folio2 = generador.generarFolio();

        assertNotEquals(folio1, folio2);
    }
}
