/*package pe.edu.cibertec.spring_proyecto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.cibertec.spring_proyecto.dto.ProductosDto;
import pe.edu.cibertec.spring_proyecto.entity.Productos;
import pe.edu.cibertec.spring_proyecto.repository.ProductosRepository;
import pe.edu.cibertec.spring_proyecto.service.MaintenanceService;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    ProductosRepository productosRepository;

    @Override
    public List<ProductosDto> findAllProductos() {

        List<ProductosDto> Productos = new ArrayList<ProductosDto>();
        Iterable<Productos>iterable= productosRepository.findAll();
        iterable.forEach(productos ->{
            ProductosDto productosDto = new ProductosDto(productos.getId(),
                    productos.getNombre(),
                    productos.getPrecio_u(),
                    productos.getStock());
            productos.add(productosDto);
        });
        return productos;
    }
}*/
