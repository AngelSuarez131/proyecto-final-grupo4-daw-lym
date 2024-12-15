package pe.edu.cibertec.spring_proyecto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.spring_proyecto.dto.CategoriaDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDetailDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDto;
import pe.edu.cibertec.spring_proyecto.entity.Categoria;
import pe.edu.cibertec.spring_proyecto.entity.Producto;
import pe.edu.cibertec.spring_proyecto.repository.CategoriaRepository;
import pe.edu.cibertec.spring_proyecto.repository.ProductoRepository;
import pe.edu.cibertec.spring_proyecto.service.MaintenanceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<ProductoDto> findAllProductos() {

        List<ProductoDto> productos = new ArrayList<ProductoDto>();
        Iterable<Producto> iterable = productoRepository.findAll();
        iterable.forEach(producto -> {
            ProductoDto productoDto = new ProductoDto(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecioU(),
                    producto.getStock(),
                    producto.getCategoria().getCategoriaNombre()
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
                producto.getMarca(),
                producto.getCategoria().getCategoriaNombre()
        )).orElseThrow(() -> new RuntimeException("Producto no encontrado")); // Lanzar una excepción si no se encuentra
    }

    @Transactional
    @Override
    public Boolean updateProducto(ProductoDetailDto productoDetailDto) {
        // Buscar el producto en el repositorio por su ID
        Optional<Producto> optionalProducto = productoRepository.findById(productoDetailDto.id());
        if (!optionalProducto.isPresent()) {
            // Si el producto no se encuentra, retornamos false
            return false;
        }

        // Buscar la categoría en el repositorio por su nombre
        Optional<Categoria> optionalCategoria = categoriaRepository.findByCategoriaNombre(productoDetailDto.categoriaNombre());
        if (!optionalCategoria.isPresent()) {
            // Si la categoría no se encuentra, retornamos false
            return false;
        }

        // Obtener el producto y actualizar sus valores
        Producto producto = optionalProducto.get();
        producto.setNombre(productoDetailDto.nombre());
        producto.setPrecioU(productoDetailDto.precioU());
        producto.setCategoria(optionalCategoria.get());

        // Agrega las líneas de debug para verificar los valores antes de guardar
        System.out.println("Producto a guardar: " + producto);

        // Guardar el producto actualizado en el repositorio
        productoRepository.save(producto);

        // Agrega una línea después de guardar para confirmar que se guardó
        System.out.println("Producto guardado correctamente");

        // Retornar true para indicar que la actualización fue exitosa
        return true;
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

    @Override
    public List<CategoriaDto> getAllCategorias() {
        List<CategoriaDto> categorias = new ArrayList<>();
        Iterable<Categoria> iterable = categoriaRepository.findAll();
        iterable.forEach(categoria -> {
            // Creamos el DTO solo con el ID y el nombre de la categoría
            CategoriaDto categoriaDto = new CategoriaDto(
                    categoria.getCategoriaId(),
                    categoria.getCategoriaNombre()
            );
            categorias.add(categoriaDto);
        });
        return categorias;
    }

}



