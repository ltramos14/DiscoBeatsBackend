package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import java.util.List;
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
public class UsuarioDto implements Serializable {

    private Integer id;
    
    @NotNull(message = "El tipo de usuario es obligatorio")
    private Integer idRol;
    
    @NotNull(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 15, message = "El nombre de usuario debe estar entre 3 y 15 caracteres")
    private String nombreUsuario;
    
    @NotNull(message = "El correo es obligatorio")
    @Pattern(regexp = "\\w+@\\w+\\.\\w+(,\\s*\\w+@\\w+\\.\\w+)*", message = "Ingrese un correo electronico valido")
    @Size(min = 10, max = 30, message = "El correo debe estar entre 10 y 30 caracteres")
    private String correo; 
    
    @NotNull(message = "El contraseña es obligatoria")
    @Size(min = 6, max = 25, message = "La contraseña debe tener entre 6 y 25 caracteres")
    private String contrasena;
    
    private Boolean estado;

    private List<CompraAlbumDto> listaComprasAlbumes;

    private List<CompraCancionDto> listaComprasCanciones;

    public UsuarioDto() {
    }

    public UsuarioDto(Integer id, Integer idRol, String nombreUsuario, String correo, String contrasena, Boolean estado, List<CompraAlbumDto> listaComprasAlbumes, List<CompraCancionDto> listaComprasCanciones) {
        this.id = id;
        this.idRol = idRol;
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

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<CompraAlbumDto> getListaComprasAlbumes() {
        return listaComprasAlbumes;
    }

    public void setListaComprasAlbumes(List<CompraAlbumDto> listaComprasAlbumes) {
        this.listaComprasAlbumes = listaComprasAlbumes;
    }

    public List<CompraCancionDto> getListaComprasCanciones() {
        return listaComprasCanciones;
    }

    public void setListaComprasCanciones(List<CompraCancionDto> listaComprasCanciones) {
        this.listaComprasCanciones = listaComprasCanciones;
    }
}
