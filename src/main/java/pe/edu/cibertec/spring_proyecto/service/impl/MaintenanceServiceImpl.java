package pe.edu.cibertec.spring_proyecto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDetailDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDto;
import pe.edu.cibertec.spring_proyecto.entity.Producto;
import pe.edu.cibertec.spring_proyecto.repository.ProductoRepository;
import pe.edu.cibertec.spring_proyecto.service.MaintenanceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDto> findAllProductos() {

        List<ProductoDto> productos = new ArrayList<ProductoDto>();
        Iterable<Producto> iterable = productoRepository.findAll();
        iterable.forEach(producto -> {
            ProductoDto productoDto = new ProductoDto(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecioU(),
                    producto.getStock()
            );
            productos.add(productoDto);
        });
        return productos;
    }

    @Override
    public ProductoDetailDto findProductoById(int id) {
        Optional<Producto> optional = productoRepository.findById(id);
        return optional.map(producto -> new ProductoDetailDto(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecioU(),
                producto.getUniMedida(),
                producto.getStock(),
                producto.getMarca()
        )).orElseThrow(() -> new RuntimeException("Producto no encontrado")); // Lanzar una excepción si no se encuentra
    }

    @Override
    public Boolean updateProducto(ProductoDetailDto productoDetailDto) {
        Optional<Producto> optional = productoRepository.findById(productoDetailDto.id());
        return optional.map(
                producto -> {
                    producto.setNombre(productoDetailDto.nombre());
                    producto.setPrecioU(productoDetailDto.precioU());
                    producto.setUniMedida(productoDetailDto.uniMedida());
                    producto.setStock(productoDetailDto.stock());
                    producto.setMarca(productoDetailDto.marca());
                    productoRepository.save(producto);
                    return true;
                }).orElseThrow(() -> new RuntimeException("Producto no encontrado para actualizar"));
    }

    @Transactional
    @Override
    public Boolean deleteProductoById(int id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);

        if (productoOptional.isPresent()) {
            productoRepository.deleteById(id);
            return true;  // Producto eliminado exitosamente
        } else {
            return false;  // Producto no encontrado
        }
    }
    @Override
    public Boolean createProducto(Producto producto) {
        try {
            // Se guarda el producto en la base de datos
            productoRepository.save(producto);
            return true;  // Indicar que el producto fue creado con éxito
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // Indicar que hubo un error
        }
    }
}



