package pe.edu.cibertec.spring_proyecto.response;

import pe.edu.cibertec.spring_proyecto.dto.ProductoDetailDto;

public record FindProductosByIdResponse(String code,
                                        String error,
                                        ProductoDetailDto producto) {

}
