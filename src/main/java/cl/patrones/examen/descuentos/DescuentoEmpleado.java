package cl.patrones.examen.descuentos;

import cl.patrones.examen.productos.domain.Producto;
import cl.patrones.examen.auth.domain.Usuario;

public class DescuentoEmpleado implements Descuento {

    @Override
    public boolean aplica(Producto producto, Usuario usuario) {
        return usuario != null && usuario.esEmpleado();
    }

    @Override
    public int obtenerPorcentaje() {
        return 5;
    }
}
