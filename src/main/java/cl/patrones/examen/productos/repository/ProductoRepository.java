package cl.patrones.examen.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.patrones.examen.productos.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
