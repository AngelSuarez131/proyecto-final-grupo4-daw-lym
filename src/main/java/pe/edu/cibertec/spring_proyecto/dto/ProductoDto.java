package pe.edu.cibertec.spring_proyecto.dto;

public record ProductoDto(Integer id,
                          String nombre,
                          Double precioU,
                          Integer stock,
                          String categoriaNombre) {
}
