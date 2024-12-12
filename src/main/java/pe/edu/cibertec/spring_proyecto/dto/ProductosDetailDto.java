package pe.edu.cibertec.spring_proyecto.dto;

public record ProductosDetailDto(Integer id,
                                String nombre,
                                Double precio_u,
                                Integer stock,
                                String marca){
}
