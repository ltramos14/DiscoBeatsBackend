package co.edu.unicundi.discobeatsejb.service;

import co.edu.unicundi.discobeatsejb.dto.UsuarioDto;
import co.edu.unicundi.discobeatsejb.entity.Rol;
import co.edu.unicundi.discobeatsejb.entity.Usuario;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import java.util.List;
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
public interface IUsuarioService {
    
    public List<Usuario> listarUsuarios();
    
    public Usuario obtenerUsuarioPorId(Integer id) throws ResourceNotFoundException;
    
    public List<Rol> obtenerRoles();
    
    public void registrarUsuario(UsuarioDto usuario) throws ResourceNotFoundException, LogicBusinessException, ConflictException;
    
    public void editarUsuario(UsuarioDto usuario) throws ResourceNotFoundException, LogicBusinessException, ConflictException;
    
    public void eliminarUsuario(Integer id) throws ResourceNotFoundException;
    
}
