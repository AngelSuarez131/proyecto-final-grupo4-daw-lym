package pe.edu.cibertec.spring_proyecto.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDetailDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDto;
import pe.edu.cibertec.spring_proyecto.response.FindProductosByIdResponse;
import pe.edu.cibertec.spring_proyecto.response.FindProductosResponse;
import pe.edu.cibertec.spring_proyecto.response.UpdateProductosResponse;
import pe.edu.cibertec.spring_proyecto.service.MaintenanceService;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-producto")
public class ManageApi {

    @Autowired
    MaintenanceService maintenanceService;

    @GetMapping("/all")
    public FindProductosResponse findProductos(@RequestParam(value = "id", defaultValue = "0") String id) {
        try {
            if (Integer.parseInt(id) > 0) {
                Optional<ProductoDto> optional = maintenanceService.findProdById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    ProductoDto productoDto = optional.get();
                    return new FindProductosResponse("01", "", List.of(productoDto));
                } else {
                    return new FindProductosResponse("02", "No se encontro producto", null);
                }
            } else {
                List<ProductoDto> productos = maintenanceService.findAllProductos();
                if (!productos.isEmpty())
                    return new FindProductosResponse("01", "", productos);
                else
                    return new FindProductosResponse("03", "No se encontro producto", productos);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new FindProductosResponse("99", "Error en el servicio", null);
        }
    }


    @GetMapping("/detail")
    public FindProductosByIdResponse findProductoById(@RequestParam(value = "id", defaultValue = "0") String id) {
        try {
            if (Integer.parseInt(id) > 0) {
                Optional<ProductoDetailDto> optional = Optional.ofNullable(maintenanceService.findProductoById(Integer.parseInt(id)));
                if (optional.isPresent()) {
                    ProductoDetailDto productoDetailDto = optional.get();
                    return new FindProductosByIdResponse("01", "", productoDetailDto);
                } else {
                    return new FindProductosByIdResponse("02", "No se encontro producto", null);
                }
            } else {
                return new FindProductosByIdResponse("03", "No se encontro producto", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new FindProductosByIdResponse("99", "Error en el servicio", null);
        }
    }

    @PostMapping("/update")
    public UpdateProductosResponse updateProducto(@RequestBody ProductoDetailDto productoDetailDto) {
        try {
                if (maintenanceService.updateProducto(productoDetailDto)){
                    return new UpdateProductosResponse("01", "");
                } else {
                    return new UpdateProductosResponse("02", "No se encontro producto");
                }
        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateProductosResponse("99", "Error en el servicio");
        }
    }


}