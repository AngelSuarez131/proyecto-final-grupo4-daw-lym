package pe.edu.cibertec.spring_proyecto.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.spring_proyecto.dto.ProductoDetailDto;

import java.util.ArrayList;
import java.util.Iterator;
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
/*
    // Metodo para eliminar un item por ID
    public void removeItemById(Integer id) {
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            // Compara el id del producto
            if (item.getProducto().id().equals(id)) {
                iterator.remove(); // Elimina el item si su id coincide
                break;
            }
        }
    }
*/

    // Metodo para reducir la cantidad de un artículo
    public void removeItemById(Integer id) {
        // Buscamos el artículo por ID
        CartItem item = items.stream()
                .filter(cartItem -> cartItem.getProducto().id().equals(id))
                .findFirst()
                .orElse(null);

        if (item != null) {
            // Decrementamos la cantidad
            item.setQuantity(item.getQuantity() - 1);

            // Si la cantidad es 0, eliminamos el artículo del carrito
            if (item.getQuantity() == 0) {
                items.remove(item);
            }
        }
    }


    // Metodo para limpiar el carrito
    public void clear() {
        items.clear(); // Elimina todos los elementos del carrito
    }

    //mostart el total de
    public double getTotal() {
        return items.stream()
                .mapToDouble(item -> item.getProducto().precioU() * item.getQuantity())
                .sum();
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

