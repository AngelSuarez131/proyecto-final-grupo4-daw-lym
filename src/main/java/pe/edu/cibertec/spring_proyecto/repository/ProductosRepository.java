package pe.edu.cibertec.spring_proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.spring_proyecto.entity.Productos;

public interface ProductosRepository extends CrudRepository<Productos, Integer> {
}
