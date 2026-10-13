package com.mycompany.sistema.reservas.dominio.modelo;

import Creacionales.singleton.GeneradorFolioFiscal;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class PruebasUnitarias {

    @Test
    void emailValido_seCreaCorrectamente() {
        Email email = new Email("daniel@correo.com");
        assertEquals("daniel@correo.com", email.valor());
    }

    @Test
    void emailSinArroba_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Email("correosinformato");
        });
    }

    @Test
    void rangoFechas_calculaDuracionCorrectamente() {
        LocalDateTime inicio = LocalDateTime.of(2026, 10, 1, 14, 0);
        LocalDateTime fin    = LocalDateTime.of(2026, 10, 4, 14, 0);
        RangoFechas rango = new RangoFechas(inicio, fin);
        assertEquals(3, rango.getDias());
    }

    @Test
    void cliente_conTresPenalizaciones_seDesactiva() {
        Cliente cliente = new Cliente("Ana López", new Email("ana@hotel.com"));
        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion();
        assertFalse(cliente.isActivo());
    }

    @Test
    void generadorFolio_produceFoliosDiferentes() {
        GeneradorFolioFiscal generador = GeneradorFolioFiscal.getInstance();
        String folio1 = generador.generarFolio();
        String folio2 = generador.generarFolio();
        assertNotEquals(folio1, folio2);
    }
}
