package pe.edu.cibertec.spring_proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDetailDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDto;
import pe.edu.cibertec.spring_proyecto.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    // Muestra la lista de productos
    @GetMapping("/start")
    public String start(Model model) {
        List<ProductoDto> productos = maintenanceService.findAllProductos();
        model.addAttribute("productos", productos);
        return "maintenance";
    }

    // Muestra los detalles de un producto
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        ProductoDetailDto productoDetailDto = maintenanceService.findProductoById(id);
        model.addAttribute("producto", productoDetailDto);
        return "maintenance_detail";
    }

    // Muestra el formulario de edición de un producto
    @GetMapping("/edit/{id}")
    public String editarProducto(@PathVariable Integer id, Model model) {
        ProductoDetailDto productoDetailDto = maintenanceService.findProductoById(id);
        model.addAttribute("producto", productoDetailDto);
        return "maintenance_edit";
    }

    // Procesa la edición de un producto
    @PostMapping("/edit-confirm")
    public String editarProductoConfirmar(@ModelAttribute ProductoDetailDto productoDetailDto) {
        maintenanceService.updateProducto(productoDetailDto);
        return "redirect:/maintenance/start";
    }

    // Elimina un producto
    @PostMapping("/remove/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        maintenanceService.deleteProductoById(id); // Método para eliminar el producto
        return "redirect:/maintenance/start";
    }
}
