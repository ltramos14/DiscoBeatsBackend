
package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "genero_musical")
public class GeneroMusical implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "El tipo de genero es obligatorio")
    @Size(min = 3, max = 15, message = "El nombre del genero musical debe estar entre 3 y 15 caracteres")
    @Column(name = "genero_musical", nullable = false, length = 15)
    private String generoMusical;

    public GeneroMusical() {
    }

    public GeneroMusical(Integer id, String generoMusical) {
        this.id = id;
        this.generoMusical = generoMusical;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }
    
}
