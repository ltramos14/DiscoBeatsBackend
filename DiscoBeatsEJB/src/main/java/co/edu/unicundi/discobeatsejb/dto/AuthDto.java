package co.edu.unicundi.discobeatsejb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
public class AuthDto {

    @NotNull(message = "El correo electrónico es obligatorio")
    @Pattern(regexp = "\\w+@\\w+\\.\\w+(,\\s*\\w+@\\w+\\.\\w+)*", message = "Ingrese un correo electronico valido")
    @Size(min = 10, message = "El correo debe tener mínimo 10 caracteres")
    private String correo;
    
    @Size(min = 5, max = 25, message = "La contraseña debe tener entre 5 y 25 caracteres")
    private String contrasena;
    
    private String token;

    public AuthDto() {
    }

    public AuthDto(String correo, String contrasena, String token) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
