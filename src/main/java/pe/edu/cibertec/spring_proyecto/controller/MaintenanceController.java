package pe.edu.cibertec.spring_proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.spring_proyecto.dto.CategoriaDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDetailDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDto;
import pe.edu.cibertec.spring_proyecto.entity.Categoria;
import pe.edu.cibertec.spring_proyecto.entity.Producto;
import pe.edu.cibertec.spring_proyecto.repository.CategoriaRepository;
import pe.edu.cibertec.spring_proyecto.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;
    @Autowired
    private CategoriaRepository categoriaRepository;


//    @GetMapping("/login")
//    public String login(Model model) {
//        return "login";
//    }
//
//    @GetMapping("/restricted")
//    public String restricted(Model model) {
//        return "restricted";
//    }


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
    public String edit(@PathVariable Integer id, Model model) {
        ProductoDetailDto productoDetailDto = maintenanceService.findProductoById(id); // Obtienes el DTO con los datos del producto
        List<CategoriaDto> categorias = maintenanceService.getAllCategorias(); // Obtienes las categorías

        model.addAttribute("producto", productoDetailDto); // Le pasas el DTO al modelo
        model.addAttribute("categorias", categorias); // Y las categorías al modelo
        return "maintenance_edit";  // Vista de edición
    }

    // Procesa la edición de un producto
    @PostMapping("/edit-confirm")
    public String editConfirm(@ModelAttribute ProductoDetailDto productoDetailDto, Model model) {
        System.out.println("Datos recibidos del formulario: " + productoDetailDto);

        maintenanceService.updateProducto(productoDetailDto);
        return "redirect:/maintenance/start";
    }

    // Elimina un producto
    @PostMapping("/remove/{id}")
    public String remove(@PathVariable Integer id, Model model) {
        boolean isDeleted = maintenanceService.deleteProductoById(id);
        if (isDeleted) {
            return "redirect:/maintenance/start";  // Redirige a la página de inicio o listado de productos
        } else {
            model.addAttribute("errorMessage", "Producto no encontrado o no pudo ser eliminado.");
            return "maintenance";
        }
    }

    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        List<CategoriaDto> categorias = maintenanceService.getAllCategorias(); // Obtienes las categorías
        model.addAttribute("producto", new Producto()); // Asegúrate de pasar un objeto vacío para el formulario
        model.addAttribute("categorias", categorias); // Pasar las categorías al formulario
        return "maintenance_create"; // El nombre de la vista
    }

    @PostMapping("/create-confirm")
    public String createConfirm(@ModelAttribute Producto producto, Model model) {
        boolean isCreated = maintenanceService.createProducto(producto);
        if (isCreated) {
            return "redirect:/maintenance/start";  // Redirige al listado de productos (ajusta esta URL si es necesario)
        } else {
            model.addAttribute("error", "Error al crear el producto.");
            return "maintenance_create";  // Vuelve al formulario con el error
        }
    }




}
