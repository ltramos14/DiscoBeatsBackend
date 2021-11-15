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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Usuario.ListarTodos", query = "SELECT u FROM Usuario u WHERE u.estado = true"),
    @NamedQuery(name = "Usuario.ObtenerUsuario", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.Inhabilitar", query = "UPDATE Usuario u SET u.estado = false WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.ContarPorId", query = "SELECT COUNT(u) FROM Usuario u WHERE u.id = :id")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Usuario.ValidarCorreo", query = "SELECT COUNT(*) FROM usuarios WHERE UPPER(correo) = UPPER(?)"),
    @NamedNativeQuery(name = "Usuario.ValidarNombreUsuario", query = "SELECT COUNT(*) FROM usuarios WHERe UPPER(nombre_usuario) = UPPER(?)")
})
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "El tipo de usuario es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
    
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
    
    @Column(name = "estado", nullable = false)
    private Boolean estado;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraAlbum> listaComprasAlbumes;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraCancion> listaComprasCanciones;

    public Usuario() {
    }

    public Usuario(Rol rol, String nombreUsuario, String correo, String contrasena, Boolean estado, List<CompraAlbum> listaComprasAlbumes, List<CompraCancion> listaComprasCanciones) {
        this.rol = rol;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.estado = estado;
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
    
    public List<CompraCancion> getListaComprasCanciones() {
        return listaComprasCanciones;
    }

    public void setListaComprasCanciones(List<CompraCancion> listaComprasCanciones) {
        this.listaComprasCanciones = listaComprasCanciones;
    }

}
