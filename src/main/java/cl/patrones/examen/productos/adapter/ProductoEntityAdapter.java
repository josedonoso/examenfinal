package cl.patrones.examen.productos.adapter;

import cl.patrones.examen.productos.domain.Categoria;
import cl.patrones.examen.productos.domain.CategoriaImpl;
import cl.patrones.examen.productos.domain.Producto;
import cl.patrones.examen.productos.entity.ProductoEntity;

public class ProductoEntityAdapter implements Producto {

    private final ProductoEntity entity;

    public ProductoEntityAdapter(ProductoEntity entity) {
        this.entity = entity;
    }

    @Override
    public String getSku() {
        return entity.getSku();
    }

    @Override
    public String getNombre() {
        return entity.getNombre();
    }

    @Override
    public String getImagen() {
        return entity.getImagen();
    }

    @Override
    public Categoria getCategoria() {
        return new CategoriaImpl(entity.getCategoria());
    }

    @Override
    public Long getCosto() {
        return entity.getCosto();
    }

    @Override
    public Long getPrecioLista() {
        return entity.getPrecioLista();
    }

    @Override
    public Long getDescuento() {
        return entity.getDescuento();
    }

    @Override
    public Long getPrecioFinal() {
        return entity.getPrecioFinal();
    }
}
