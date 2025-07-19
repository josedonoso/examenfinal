package cl.patrones.examen.productos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductoEntity {

    @Id
    private String sku;
    private String nombre;
    private String imagen;
    private String categoria;
    private Long costo;
    private Long precioLista;
    private Long descuento;
    private Long precioFinal;

    public ProductoEntity() {}

    public ProductoEntity(String sku, String nombre, String imagen, String categoria,
                          Long costo, Long precioLista, Long descuento, Long precioFinal) {
        this.sku = sku;
        this.nombre = nombre;
        this.imagen = imagen;
        this.categoria = categoria;
        this.costo = costo;
        this.precioLista = precioLista;
        this.descuento = descuento;
        this.precioFinal = precioFinal;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }

    public Long getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(Long precioLista) {
        this.precioLista = precioLista;
    }

    public Long getDescuento() {
        return descuento;
    }

    public void setDescuento(Long descuento) {
        this.descuento = descuento;
    }

    public Long getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(Long precioFinal) {
        this.precioFinal = precioFinal;
    }
}
