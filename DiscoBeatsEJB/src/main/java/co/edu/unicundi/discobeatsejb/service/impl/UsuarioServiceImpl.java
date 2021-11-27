package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.dto.AuthDto;
import co.edu.unicundi.discobeatsejb.dto.UsuarioDto;
import co.edu.unicundi.discobeatsejb.entity.Rol;
import co.edu.unicundi.discobeatsejb.entity.Usuario;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.repository.IRolRepo;
import co.edu.unicundi.discobeatsejb.repository.IUsuarioRepo;
import co.edu.unicundi.discobeatsejb.service.IUsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
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
    public List<Usuario> obtenerUsuarioPorId(Integer id) throws ResourceNotFoundException {
        Long validarUsuario = localRepo.validarExistenciaPorId(id);
        
        if(validarUsuario==0){
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        
        List<Usuario> usuario = this.localRepo.obtenerPorId(id);
        if (!usuario.isEmpty()) {
            return usuario;
        }
        return null;
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

        // Validación del rol
        if (usuario.getIdRol() == null) {
            throw new LogicBusinessException("El id del rol de usuario es obligatorio");
        }
        Long validarExistenciaRol = rolRepo.validarExistenciaRol(usuario.getIdRol());
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
    public AuthDto editarUsuario(UsuarioDto usuarioEditado) throws ResourceNotFoundException, LogicBusinessException, ConflictException {
        
        Long contId = localRepo.validarExistenciaPorId(usuarioEditado.getId());
        if(contId == 0){
            throw new ResourceNotFoundException("EL id del usuario no existe");
        }
        
        if (usuarioEditado.getId() == null) {
         throw new LogicBusinessException("El id del usuario a editar es obligatorio");
        }
        
        
        if(usuarioEditado.getNombreUsuario() == null){
            throw new LogicBusinessException("El nombre de usuario no puede ser nulo");
        }
        
        
        Long countC = localRepo.validarExistenciaCorreo(usuarioEditado.getCorreo());
        
        Usuario usuario = new Usuario();
        Long count = localRepo.validarExistenciaPorId(usuario.getId());
   
        Rol rol = new Rol();
        
        if (countC == 0 || usuarioEditado.getCorreo().equalsIgnoreCase(usuario.getCorreo()) 
            || count == 0 || usuarioEditado.getNombreUsuario().equalsIgnoreCase(usuario.getNombreUsuario())) {
            rol.setId(usuarioEditado.getIdRol());       
            usuario.setId(usuarioEditado.getId());
            usuario.setRol(rol);
            usuario.setNombreUsuario(usuarioEditado.getNombreUsuario());
            usuario.setCorreo(usuarioEditado.getCorreo());
            usuario.setContrasena(usuarioEditado.getContrasena());
            usuario.setEstado(true);       

            localRepo.editar(usuario);
            
            AuthDto relogin = new AuthDto();
            relogin.setCorreo(usuario.getCorreo());
            relogin.setContrasena(usuario.getContrasena());
            
            return login(relogin);
            
            
        }else{
            throw new ConflictException("El usuario o el correo ya existe");
        }
        
    }

    @Override
    public void eliminarUsuario(Integer id) throws ResourceNotFoundException {

        Long count = this.localRepo.validarExistenciaPorId(id);
        if (count == 0) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }

        this.localRepo.eliminar(id);

    }

    private Usuario convertToEntity(UsuarioDto usuarioDto) {

        Usuario usuario = new Usuario();
        Rol rol = new Rol();

        rol.setId(usuarioDto.getIdRol());
        usuario.setRol(rol);
        usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setEstado(true);

        return usuario;
    }

    @Override
    public AuthDto login(AuthDto login) throws ResourceNotFoundException, LogicBusinessException {

        //Validacion de contraseña
        if (login.getContrasena() == null) {
            throw new LogicBusinessException("La contraseña es obligatoria");
        }

        // Validación de correo
        Long validarExistenciaCorreo = localRepo.validarExistenciaCorreo(login.getCorreo());
        if (validarExistenciaCorreo == 0) {
            throw new ResourceNotFoundException("El correo electrónico ingresado no existe");
        }

        String cotrasenaBD = this.localRepo.validarContrasena(login.getCorreo());
        if (!login.getContrasena().equals(cotrasenaBD)) {
            throw new LogicBusinessException("Correo electrónico o contrasena incorrectos");
        }

        Usuario usuario = localRepo.login(login.getCorreo(), login.getContrasena());
        
        if(!usuario.getEstado()){
            throw new LogicBusinessException("Este usuario se encuentra inactivo");
        }

        String key = "gcn0%I46jY^Njx0gEacNa9";
        Long tiempo = System.currentTimeMillis();

        Rol rol = rolRepo.obtenerRol(usuario.getRol().getId());

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, key)
                .setSubject(usuario.getId().toString())
                .setIssuedAt(new Date(tiempo))
                .setExpiration(new Date(tiempo + 1000000 * 10))
                .claim("rol", rol.getRol())
                .claim("usuario", usuario.getNombreUsuario())
                .claim("correo", usuario.getCorreo())
                .compact();

        AuthDto token = new AuthDto();
        token.setToken(jwt);

        localRepo.actualizarToken(token.getToken(), usuario.getId());

        return token;
    }

    @Override
    public void logout(String correo) throws ResourceNotFoundException {

        // Validación de correo
        Long validarExistenciaCorreo = localRepo.validarExistenciaCorreo(correo);
        if (validarExistenciaCorreo == 0) {
            throw new ResourceNotFoundException("El correo electrónico no existe");
        } else {
            localRepo.logout(correo);
        }

    }

    @Override
    public Usuario obtenerUsuarioToken(Integer id) throws ResourceNotFoundException {
        Long count = this.localRepo.validarExistenciaPorId(id);
            if (count == 0) {
                throw new ResourceNotFoundException("Usuario no encontrado");
            }
            return this.localRepo.listarPorId(id);
    }
}
