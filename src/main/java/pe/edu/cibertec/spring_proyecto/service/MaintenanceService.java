package pe.edu.cibertec.spring_proyecto.service;

import pe.edu.cibertec.spring_proyecto.dto.ProductoDetailDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDto;
import pe.edu.cibertec.spring_proyecto.entity.Producto;

import java.util.List;

public interface MaintenanceService {
    List<ProductoDto> findAllProductos();

    ProductoDetailDto findProductoById(int id);

    Boolean updateProducto(ProductoDetailDto productoDetailDto);

    Boolean deleteProductoById(int id);

    Boolean createProducto(Producto producto);
}
