package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuario.ListarTodos", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.UsuarioDto" 
            + "(a.id, a.rol.id, a.nombreUsuario, a.correo, a.contrasena, a.estado) FROM Usuario a ORDER BY a.id"),
    @NamedQuery(name = "Usuario.ObtenerUsuario", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.UsuarioDto" 
            + "(a.id, a.rol.id,  a.nombreUsuario, a.correo, a.contrasena, a.estado) FROM Usuario a WHERE a.id = :id"),    
    @NamedQuery(name = "Usuario.Inhabilitar", query = "UPDATE Usuario u SET u.estado = false WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.ContarPorId", query = "SELECT COUNT(u) FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.Login", query = "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasena = :contrasena"),
    @NamedQuery(name = "Usuario.Logout", query = "UPDATE Usuario u SET u.token = null WHERE u.correo = ?1 "),
    @NamedQuery(name = "Usuario.ActualizarToken", query = "UPDATE Usuario u SET u.token = ?1 WHERE u.id = ?2"),
    @NamedQuery(name = "Usuario.ValidarContrasena", query = "SELECT u.contrasena FROM Usuario u WHERE u.correo = :correo")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Usuario.ValidarCorreo", query = "SELECT COUNT(*) FROM usuarios WHERE UPPER(correo) = UPPER(?)"),
    @NamedNativeQuery(name = "Usuario.ValidarNombreUsuario", query = "SELECT COUNT(*) FROM usuarios WHERE UPPER(nombre_usuario) = UPPER(?)")
})
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @Column(name = "nombre_usuario", nullable = false, length = 15, unique = true)
    private String nombreUsuario;

    @Column(name = "correo", nullable = false, length = 30, unique = true)
    private String correo;

    @Column(name = "contrasena", nullable = false, length = 25)
    private String contrasena;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "token", nullable = true, columnDefinition = "text")
    private String token;

    @OneToMany(mappedBy = "usuarioAlbum", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraAlbum> listaComprasAlbumes;

    @OneToMany(mappedBy = "usuarioCancion", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraCancion> listaComprasCanciones;

    public Usuario() {
    }

    public Usuario(Integer id, Rol rol, String nombreUsuario, String correo, String contrasena, Boolean estado, String token, List<CompraAlbum> listaComprasAlbumes, List<CompraCancion> listaComprasCanciones) {
        this.id = id;
        this.rol = rol;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.estado = estado;
        this.token = token;
        this.listaComprasAlbumes = listaComprasAlbumes;
        this.listaComprasCanciones = listaComprasCanciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    @XmlTransient
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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

    public List<CompraAlbum> getListaComprasAlbumes() {
        return listaComprasAlbumes;
    }

    public void setListaComprasAlbumes(List<CompraAlbum> listaComprasAlbumes) {
        this.listaComprasAlbumes = listaComprasAlbumes;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<CompraCancion> getListaComprasCanciones() {
        return listaComprasCanciones;
    }

    public void setListaComprasCanciones(List<CompraCancion> listaComprasCanciones) {
        this.listaComprasCanciones = listaComprasCanciones;
    }
}
