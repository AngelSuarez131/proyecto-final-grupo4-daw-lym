package pe.edu.cibertec.spring_proyecto.service;

import pe.edu.cibertec.spring_proyecto.dto.CategoriaDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDetailDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDto;
import pe.edu.cibertec.spring_proyecto.entity.Categoria;
import pe.edu.cibertec.spring_proyecto.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface MaintenanceService {
    List<ProductoDto> findAllProductos();

    Optional<ProductoDto> findProdById(int id);

    ProductoDetailDto findProductoById(int id);

    Boolean updateProducto(ProductoDetailDto productoDetailDto);

    Boolean deleteProductoById(int id);

    Boolean createProducto(Producto producto);

    List<CategoriaDto> getAllCategorias();

}
