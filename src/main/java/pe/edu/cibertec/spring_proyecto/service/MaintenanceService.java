package pe.edu.cibertec.spring_proyecto.service;

import pe.edu.cibertec.spring_proyecto.dto.ProductosDto;

import java.util.List;

public interface MaintenanceService {

    List<ProductosDto> findAllProductos();
}
