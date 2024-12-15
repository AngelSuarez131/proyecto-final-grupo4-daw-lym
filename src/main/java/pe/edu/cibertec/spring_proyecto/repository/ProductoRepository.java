package pe.edu.cibertec.spring_proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.spring_proyecto.entity.Producto;

import java.util.Optional;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {
    Optional<Producto> findById(Integer id);  // Método para buscar por ID

}
