package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.dto.UsuarioDto;
import co.edu.unicundi.discobeatsejb.entity.Rol;
import co.edu.unicundi.discobeatsejb.entity.Usuario;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.repository.IRolRepo;
import co.edu.unicundi.discobeatsejb.repository.IUsuarioRepo;
import co.edu.unicundi.discobeatsejb.service.IUsuarioService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Stateless
public class UsuarioServiceImpl implements IUsuarioService {

    @EJB
    private IUsuarioRepo localRepo;
    
    @EJB
    private IRolRepo rolRepo;
    
    @Override
    public List<Usuario> listarUsuarios() {
        
        List<Usuario> listaUsuarios = this.localRepo.listarTodos();
        return listaUsuarios.isEmpty() ? null : listaUsuarios;
        
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) throws ResourceNotFoundException {
        
        Long count = this.localRepo.validarExistenciaPorId(id);
        if (count == 0) 
            throw new ResourceNotFoundException("Usuario no encontrado");
  
        return this.localRepo.listarPorId(id);
    }
    
    @Override
    public List<Rol> obtenerRoles() {
        List<Rol> listaRoles = this.rolRepo.obtenerRoles();
        return listaRoles.isEmpty() ? null : listaRoles;
    }

    @Override
    public void registrarUsuario(UsuarioDto usuario) throws ResourceNotFoundException, LogicBusinessException, ConflictException {
        
        // Validación de que no se ingrese un id al usuario
        if (usuario.getId() != null) {
            throw new LogicBusinessException("El id del usuario se asigna automáticamente");
        }
        
        if (usuario.getListaComprasAlbumes() != null && usuario.getListaComprasCanciones() != null) {
            throw new LogicBusinessException("No se pueden enviar listas de compras en esta operación");
        }

        // Validación del rol
        if (usuario.getRol().getId() == null) {
            throw new LogicBusinessException("El id del rol de usuario es obligatorio");
        } 
        Long validarExistenciaRol = rolRepo.validarExistenciaRol(usuario.getRol().getId());
        if (validarExistenciaRol == 0) {
            throw new ConflictException("El rol de usuario no existe");
        }
        
        // Validación de correo
        Long validarExistenciaCorreo = localRepo.validarExistenciaCorreo(usuario.getCorreo());
        if (validarExistenciaCorreo > 0) {
            throw new ConflictException("El correo electrónico ingresado ya existe");
        }
        
        //Validación de nombre de usuario
        Long validarExistenciaNombreUsuario = this.localRepo.validarExistenciaNombreUsuario(usuario.getNombreUsuario());
        if (validarExistenciaNombreUsuario > 0) {
            throw new ConflictException("Ya existe ese nombre de usuario");
        }

        Usuario usuarioEntity = convertToEntity(usuario);
        this.localRepo.guardar(usuarioEntity);
        
    }

    @Override
    public void editarUsuario(UsuarioDto usuario) throws ResourceNotFoundException, LogicBusinessException, ConflictException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarUsuario(Integer id) throws ResourceNotFoundException {
        
        Long count = this.localRepo.validarExistenciaPorId(id);
        if (count == 0)
            throw new ResourceNotFoundException("Usuario no encontrado");
        
        this.localRepo.eliminar(id);
        
    }
    
    private Usuario convertToEntity(UsuarioDto usuarioDto) {
        
        Usuario usuario = new Usuario();
        Rol rol = new Rol();
       
        rol.setId(usuarioDto.getRol().getId());
        usuario.setRol(rol);
        usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setEstado(true);
        
        return usuario;
        
    }

    
    
}
