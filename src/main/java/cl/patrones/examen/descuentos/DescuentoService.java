package cl.patrones.examen.descuentos;

import cl.patrones.examen.productos.domain.Producto;
import cl.patrones.examen.auth.domain.Usuario;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class DescuentoService {

    private final List<Descuento> descuentos = null;
        private LocalDate fechaActual = LocalDate.now();

    
 // Agrega este método para cambiar la fecha en tests
    public void setFechaActual(LocalDate fecha) {
        this.fechaActual = fecha;
    }

    // Constructor por defecto (usa fecha actual)
    public DescuentoService() {
        this(LocalDate.now());
    }


    // Constructor que permite inyectar fecha para test
    public DescuentoService(LocalDate fechaActual) {
        this.fechaActual = fechaActual;
    }

    public int obtenerMejorDescuento(Producto producto, Usuario usuario) {
        return descuentos.stream()
                .filter(d -> d.aplica(producto, usuario))
                .mapToInt(Descuento::obtenerPorcentaje)
                .max()
                .orElse(0);
    }

    public Long calcularPrecioFinal(Producto producto, Usuario usuario) {
        Long precioLista = producto.getPrecioLista();
        Long descuento = calcularDescuento(producto, usuario); // devuelve Long con el descuento en pesos

        return precioLista - descuento;
    }

    public ResultadoDescuento calcularDescuentoConDetalle(Producto producto, Usuario usuario) {
        Long precioLista = producto.getPrecioLista();
        double descuentoEmpleado = (usuario.getRol().equalsIgnoreCase("EMPLEADO") || usuario.getRol().equalsIgnoreCase("EMPLEADA")) ? 0.05 : 0.0;


        double descuentoDiaCategoria = 0.0;
        DayOfWeek diaSemana = fechaActual.getDayOfWeek();
        System.out.println("DIA:"+diaSemana);
        String categoria = producto.getCategoria().getNombre().toLowerCase();

        switch (diaSemana) {
            case MONDAY:
                if (categoria.contains("compresor")) descuentoDiaCategoria = 0.06;
                System.out.println("Descuento para " + categoria + " el " + diaSemana + ": " + (descuentoDiaCategoria * 100) + "%");
                break;
            case TUESDAY:
                if (categoria.contains("esmeril")) descuentoDiaCategoria = 0.08;
                System.out.println("Descuento para " + categoria + " el " + diaSemana + ": " + (descuentoDiaCategoria * 100) + "%");
                break;
            case WEDNESDAY:
                if (categoria.contains("taladro")) descuentoDiaCategoria = 0.10;
                System.out.println("Descuento para " + categoria + " el " + diaSemana + ": " + (descuentoDiaCategoria * 100) + "%");
                break;
            default:
                descuentoDiaCategoria = 0.0;
                System.out.println("No hay descuento para " + categoria + " el " + diaSemana);
        }

        // Sólo se aplica el mayor descuento
        double descuentoMayor = Math.max(descuentoEmpleado, descuentoDiaCategoria);
        Long precioFinal = Math.round(precioLista * (1 - descuentoMayor));

        return new ResultadoDescuento(precioFinal, descuentoMayor);
    }

    

    public Long calcularDescuento(Producto producto, Usuario usuario) {
        // lógica para obtener descuento según usuario y producto
        // Ejemplo simple: 10% descuento para clientes con rol CLIENTE
        if ("EMPLEADO".equals(usuario.getRol())) {
            return producto.getPrecioLista() / 5; // 5% de descuento
        }
        return 0L;
    }

    public static class ResultadoDescuento {
        private final Long precioFinal;
        private final double porcentajeDescuento;

        public ResultadoDescuento(Long precioFinal, double porcentajeDescuento) {
            this.precioFinal = precioFinal;
            this.porcentajeDescuento = porcentajeDescuento;
        }

        public Long getPrecioFinal() {
            return precioFinal;
        }

        public double getPorcentajeDescuento() {
            return porcentajeDescuento;
        }
    }

    

}
