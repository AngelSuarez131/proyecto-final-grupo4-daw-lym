package pe.edu.cibertec.spring_proyecto.entity;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDetailDto;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDto;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();


    //AGREGAR AL CARRITO
    public void addItem(ProductoDetailDto producto) {
        // Busca si ya existe el producto en el carrito
        for (CartItem item : items) {
            if (item.getProducto().id().equals(producto.id())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        // Si no existe, lo agrega
        items.add(new CartItem(producto, 1));
    }


    //REMOVER CARRITO
    public void removeItem(Integer productoId) {
        items.removeIf(item -> item.getProducto().id().equals(productoId));
    }


    //mostart el total de
    public double getTotal() {
        return items.stream()
                .mapToDouble(item -> item.getProducto().precioU() * item.getQuantity())
                .sum();
    }

    public List<CartItem> getItems () {
        return items;
    }

    //removerTodoslosProductosDeLaLISTA
}

@Data
class CartItem {
    private ProductoDetailDto producto;
    private int quantity;

    public CartItem(ProductoDetailDto producto, int quantity) {
        this.producto = producto;
        this.quantity = quantity;
    }
}
