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
@Table(name = "usuario")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 15, message = "El nombre de usuario debe estar entre 3 y 15 caracteres")
    @Column(name = "nombre_usuario", nullable = false, length = 15, unique = true)
    private String nombreUsuario;
    
    @NotNull(message = "El correo es obligatorio")
    @Size(min = 5, max = 30, message = "El correo debe estar entre 3 y 25 caracteres")
    @Column(name = "correo", nullable = false, length = 30, unique = true)
    private String correo; 
    
    @NotNull(message = "El contraseña es obligatoria")
    @Size(min = 3, max = 25, message = "La contraseña debe tener entre 3 y 25 caracteres")
    @Column(name = "contrasena", nullable = false, length = 25)
    private String contrasena;

    public Usuario() {
    }

    public Usuario(Integer id, String nombreUsuario, String correo, String contrasena) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
