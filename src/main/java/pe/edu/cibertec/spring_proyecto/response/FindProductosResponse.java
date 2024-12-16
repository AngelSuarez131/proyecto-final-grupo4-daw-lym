package pe.edu.cibertec.spring_proyecto.response;

import pe.edu.cibertec.spring_proyecto.dto.ProductoDto;

public record FindProductosResponse(String code,
                                    String error,
                                    Iterable<ProductoDto> productos) {

}
