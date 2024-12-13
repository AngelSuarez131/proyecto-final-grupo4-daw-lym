package pe.edu.cibertec.spring_proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.spring_proyecto.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

}
