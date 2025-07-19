package cl.patrones.examen.descuentos;

import cl.patrones.examen.productos.domain.Producto;
import cl.patrones.examen.auth.domain.Usuario;

public interface Descuento {
    boolean aplica(Producto producto, Usuario usuario);
    int obtenerPorcentaje();
}
