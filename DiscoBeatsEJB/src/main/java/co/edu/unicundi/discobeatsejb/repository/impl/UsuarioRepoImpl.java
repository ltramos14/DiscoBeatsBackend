package co.edu.unicundi.discobeatsejb.repository.impl;

import co.edu.unicundi.discobeatsejb.entity.Usuario;
import co.edu.unicundi.discobeatsejb.repository.IUsuarioRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Stateless
public class UsuarioRepoImpl implements IUsuarioRepo {

    @PersistenceContext(unitName = "conexionPostgresql")
    private EntityManager em;
    
    @Override
    public List<Usuario> listarTodos() {
        TypedQuery<Usuario> query = this.em.createNamedQuery("Usuario.ListarTodos", Usuario.class);
        return query.getResultList();
    }

    @Override
    public Usuario listarPorId(Integer id) {
        TypedQuery<Usuario> query = this.em.createNamedQuery("Usuario.ObtenerUsuario", Usuario.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void guardar(Usuario obj) {
        this.em.persist(obj);
    }

    @Override
    public void editar(Usuario obj) {
        this.em.merge(obj);
    }

    @Override
    public void eliminar(Integer id) {
        Query query = this.em.createNamedQuery("Usuario.Inhabilitar");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Override
    public Long validarExistenciaPorId(Integer id) {
        Query query = this.em.createNamedQuery("Usuario.ContarPorId");
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long validarExistenciaCorreo(String correo) {
        Query query = this.em.createNamedQuery("Usuario.ValidarCorreo");
        query.setParameter(1, correo);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long validarExistenciaNombreUsuario(String nombreUsuario) {
        Query query = this.em.createNamedQuery("Usuario.ValidarNombreUsuario");
        query.setParameter(1, nombreUsuario);
        return (Long) query.getSingleResult();    
    }
    
    
    @Override
    public String validarContrasena(String correo) {
        Query query = this.em.createNamedQuery("Usuario.ValidarContrasena", Usuario.class);
        query.setParameter("correo", correo);
        return (String) query.getSingleResult();
    }

    @Override
    public Usuario login(String correo, String contrasena) {
        
        TypedQuery<Usuario> query = this.em.createNamedQuery("Usuario.Login", Usuario.class);
        query.setParameter("correo", correo);
        query.setParameter("contrasena", contrasena);
        return query.getSingleResult();
    }
    
    
    @Override
    public void logout(String correo) {
        Query query = this.em.createNamedQuery("Usuario.Logout", Usuario.class);
        query.setParameter(1, correo);
        query.executeUpdate();
    }

    @Override
    public void actualizarToken(String token, Integer id) {
        Query query = this.em.createNamedQuery("Usuario.ActualizarToken", Usuario.class);
        query.setParameter(1, token);
        query.setParameter(2, id);
        query.executeUpdate();
    }

}
