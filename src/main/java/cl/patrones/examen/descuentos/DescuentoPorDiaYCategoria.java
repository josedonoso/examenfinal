package cl.patrones.examen.descuentos;

import cl.patrones.examen.productos.domain.Producto;
import cl.patrones.examen.auth.domain.Usuario;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DescuentoPorDiaYCategoria implements Descuento {

    private final DayOfWeek dia;
    private final String categoria;
    private final int porcentaje;

    public DescuentoPorDiaYCategoria(DayOfWeek dia, String categoria, int porcentaje) {
        this.dia = dia;
        this.categoria = categoria;
        this.porcentaje = porcentaje;
    }

    @Override
    public boolean aplica(Producto producto, Usuario usuario) {
        return LocalDate.now().getDayOfWeek().equals(dia)
                && producto.getCategoria().getNombre().equalsIgnoreCase(categoria);
    }

    @Override
    public int obtenerPorcentaje() {
        return porcentaje;
    }
}
