package pe.edu.cibertec.spring_proyecto.dto;

public record ProductosDto(Integer id,
                          String nombre,
                          Double precio_u,
                          Integer stock) {
}
