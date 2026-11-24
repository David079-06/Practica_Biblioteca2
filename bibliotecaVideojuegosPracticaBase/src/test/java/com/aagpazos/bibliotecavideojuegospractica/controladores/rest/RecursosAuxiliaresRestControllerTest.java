package com.aagpazos.bibliotecavideojuegospractica.controladores.rest;

import com.aagpazos.bibliotecavideojuegospractica.dto.DesarrolladoraDTO;
import com.aagpazos.bibliotecavideojuegospractica.dto.ResumenDTO;
import com.aagpazos.bibliotecavideojuegospractica.service.DesarrolladoraService;
import com.aagpazos.bibliotecavideojuegospractica.service.PrestamoService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecursosAuxiliaresRestControllerTest {
    @Test
    void listarDesarrolladorasDevuelveLaListaCon200() {
        DesarrolladoraService desarrolladoraService = mock(DesarrolladoraService.class);
        PrestamoService prestamoService = mock(PrestamoService.class);
        ArrayList<DesarrolladoraDTO> desarrolladoras = new ArrayList<>();
        desarrolladoras.add(new DesarrolladoraDTO(4, "FromSoftware", "Japón"));
        when(desarrolladoraService.listarDesarrolladoras()).thenReturn(desarrolladoras);
        RecursosAuxiliaresRestController controller = new RecursosAuxiliaresRestController(
                desarrolladoraService, prestamoService);

        var respuesta = controller.listarDesarrolladoras();

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(desarrolladoras, respuesta.getBody());
    }

    @Test
    void obtenerEstadisticasDevuelveElResumenCon200() {
        DesarrolladoraService desarrolladoraService = mock(DesarrolladoraService.class);
        PrestamoService prestamoService = mock(PrestamoService.class);
        ResumenDTO resumen = new ResumenDTO(21, 9, 7, 2);
        when(prestamoService.resumen()).thenReturn(resumen);
        RecursosAuxiliaresRestController controller = new RecursosAuxiliaresRestController(
                desarrolladoraService, prestamoService);

        var respuesta = controller.obtenerEstadisticas();

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(resumen, respuesta.getBody());
    }
}
