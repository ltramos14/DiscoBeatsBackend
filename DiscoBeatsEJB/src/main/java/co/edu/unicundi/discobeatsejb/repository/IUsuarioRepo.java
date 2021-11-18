package co.edu.unicundi.discobeatsejb.repository;

import co.edu.unicundi.discobeatsejb.entity.Usuario;
import javax.ejb.Local;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Local
public interface IUsuarioRepo extends ICrud<Usuario, Integer> {
    
    public Long validarExistenciaPorId(Integer id);
    
    public Long validarExistenciaCorreo(String correo);
    
    public Long validarExistenciaNombreUsuario(String nombreUsuario);
    
    public String validarContrasena(String correo);
    
    public Usuario login(String correo, String contrasena);
    
    public void logout(String correo);
    
    public void actualizarToken(String token, Integer id);
    
}
