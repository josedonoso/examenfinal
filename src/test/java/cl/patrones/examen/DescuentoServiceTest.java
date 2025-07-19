package cl.patrones.examen;

import cl.patrones.examen.auth.domain.Usuario;
import cl.patrones.examen.descuentos.DescuentoService;
import cl.patrones.examen.descuentos.DescuentoService.ResultadoDescuento;
import cl.patrones.examen.productos.domain.Categoria;
import cl.patrones.examen.productos.domain.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescuentoServiceTest {

    private DescuentoService descuentoService = new DescuentoService();

    @BeforeEach
    void setUp() {
        descuentoService = new DescuentoService();
    }

    // Prueba para descuento x rol (empleado)
    @Test
    void testDescuentoEmpleadoSiempre5Porciento() {

        descuentoService.setFechaActual(LocalDate.of(2025, 7, 14));

        Producto producto = new ProductoStub("Producto genérico", 120_000L, "Otro");
        Usuario empleado = new Usuario("andrea", "EMPLEADO");

        ResultadoDescuento resultado = descuentoService.calcularDescuentoConDetalle(producto, empleado);

        // Debe aplicar 5%
        assertEquals(114_000L, resultado.getPrecioFinal());
        assertEquals(0.05, resultado.getPorcentajeDescuento(), 0.001);
    }

    //Pruebas para descuento por día y categoría
    @Test
    void descuentoLunesCompresor() {
        descuentoService.setFechaActual(LocalDate.of(2025, 7, 14)); // Lunes

        Producto producto = new ProductoStub("Compresor X", 100_000L, "Compresor Industrial");
        Usuario cliente = new Usuario("juan", "CLIENTE");

        ResultadoDescuento resultado = descuentoService.calcularDescuentoConDetalle(producto, cliente);

        assertEquals(94_000L, resultado.getPrecioFinal(), "Compresor lunes debe tener 6% descuento");
        assertEquals(0.06, resultado.getPorcentajeDescuento(), 0.001);
    }

    @Test
    void descuentoMartesEsmeril() {
        descuentoService.setFechaActual(LocalDate.of(2025, 7, 15)); // Martes

        Producto producto = new ProductoStub("Esmeriladora", 100_000L, "Esmeril");
        Usuario cliente = new Usuario("ana", "CLIENTE");

        ResultadoDescuento resultado = descuentoService.calcularDescuentoConDetalle(producto, cliente);

        assertEquals(92_000L, resultado.getPrecioFinal(), "Esmeril martes debe tener 8% descuento");
        assertEquals(0.08, resultado.getPorcentajeDescuento(), 0.001);
    }

    @Test
    void descuentoMiercolesTaladro() {
        descuentoService.setFechaActual(LocalDate.of(2025, 7, 16)); // Miércoles

        Producto producto = new ProductoStub("Taladro", 100_000L, "Taladro Percutor");
        Usuario cliente = new Usuario("ana", "CLIENTE");

        ResultadoDescuento resultado = descuentoService.calcularDescuentoConDetalle(producto, cliente);

        assertEquals(90_000L, resultado.getPrecioFinal(), "Taladro miércoles debe tener 10% descuento");
        assertEquals(0.10, resultado.getPorcentajeDescuento(), 0.001);
    }

    @Test
    void descuentoMayorEntreEmpleadoYdiaCategoria() {
        descuentoService.setFechaActual(LocalDate.of(2025, 7, 16)); // Miércoles

        Producto producto = new ProductoStub("Taladro", 100_000L, "Taladro Percutor");
        Usuario empleado = new Usuario("juan", "EMPLEADO");

        ResultadoDescuento resultado = descuentoService.calcularDescuentoConDetalle(producto, empleado);

        // Taladro miércoles (10%) vs empleado (5%) → debe aplicar 10%
        assertEquals(90_000L, resultado.getPrecioFinal(), "Debe aplicar mayor descuento (10%)");
        assertEquals(0.10, resultado.getPorcentajeDescuento(), 0.001);
    }

    // --- Prueba sin descuentos ---
    @Test
    void sinDescuentoClienteOtroDiaYCategoria() {
        descuentoService.setFechaActual(LocalDate.of(2025, 7, 17)); // Jueves

        Producto producto = new ProductoStub("Producto normal", 100_000L, "Categoria sin descuento");
        Usuario cliente = new Usuario("pepe", "CLIENTE");

        ResultadoDescuento resultado = descuentoService.calcularDescuentoConDetalle(producto, cliente);

        assertEquals(100_000L, resultado.getPrecioFinal(), "No debe aplicarse descuento");
        assertEquals(0.0, resultado.getPorcentajeDescuento(), 0.001);
    }

    // Clase stub que implementa todos los métodos de Producto
    private static class ProductoStub implements Producto {
        private final String nombre;
        private final Long precioLista;
        private final Categoria categoria;

        public ProductoStub(String nombre, Long precioLista, String nombreCategoria) {
            this.nombre = nombre;
            this.precioLista = precioLista;
            this.categoria = () -> nombreCategoria;
        }

        @Override
        public String getSku() {
            return "SKU001";
        }

        @Override
        public String getNombre() {
            return nombre;
        }

        @Override
        public Categoria getCategoria() {
            return categoria;
        }

        @Override
        public Long getPrecioLista() {
            return precioLista;
        }

        @Override
        public Long getPrecioFinal() {
            return null;
        }

        @Override
        public Long getDescuento() {
            return null;
        }

        @Override
        public String getImagen() {
            return "imagen.jpg";
        }

        // 👉 Agregado para cumplir con la interfaz
        @Override
        public Long getCosto() {
            return 0L;
        }
    }
}
