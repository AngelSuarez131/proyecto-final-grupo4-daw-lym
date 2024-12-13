package pe.edu.cibertec.spring_proyecto.dto;

public record ProductoDetailDto(Integer id,
                                String nombre,
                                Double precioU,
                                String uniMedida,
                                Integer stock,
                                String marca){
}
