package pe.edu.cibertec.spring_proyecto.service;

import pe.edu.cibertec.spring_proyecto.dto.ProductoDetailDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDto;

import java.util.List;

public interface MaintenanceService {
    List<ProductoDto> findAllProductos();

    ProductoDetailDto findProductoById(int id);

    Boolean updateProducto(ProductoDetailDto productoDetailDto);

    void deleteProductoById(int id);
}
