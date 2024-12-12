package pe.edu.cibertec.spring_proyecto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_dis;
    private String nombre;
}

