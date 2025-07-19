package cl.patrones.examen.productos.domain;


public class ProductoConPrecioFinal {
    private String codigo;
    private String nombre;
    private Long precioLista;
    private Long precioFinal;
    private String imagen;

    public ProductoConPrecioFinal(Producto producto) {
        this.codigo = producto.getSku();          // Aquí el método correcto
        this.nombre = producto.getNombre();
        this.precioLista = producto.getPrecioLista();
        this.precioFinal = producto.getPrecioFinal();
        this.imagen = producto.getImagen();
    }

    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public Long getPrecioLista() {
        return precioLista;
    }
    public Long getPrecioFinal() {
        return precioFinal;
    }
    public String getImagen() {
        return imagen;
    }

}
