package cl.patrones.examen.productos.domain;

public class ProductoConDescuento {
    private final Producto productoOriginal;
    private final Long precioFinal;
    private final double porcentajeDescuento;

    public ProductoConDescuento(Producto productoOriginal, Long precioFinal, double porcentajeDescuento) {
        this.productoOriginal = productoOriginal;
        this.precioFinal = precioFinal;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Producto getProducto() {
        return productoOriginal;
    }

    public Long getPrecioFinal() {
        return precioFinal;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public String getImagen() {
        return productoOriginal.getImagen();
    }

    public Long getPrecioLista() {
        return productoOriginal.getPrecioLista();
    }

    public String getNombre() {
        return productoOriginal.getNombre();
    }

    public double getDescuento() {
        return porcentajeDescuento;
    }

    public String getSku() {
        return productoOriginal.getSku();
    }
    // agrega más getters si Thymeleaf los usa
}
