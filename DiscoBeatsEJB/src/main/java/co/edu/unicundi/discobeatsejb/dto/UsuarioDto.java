/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author nicon
 */
public class UsuarioDto implements Serializable {

    private Integer id;
    
    @NotNull(message = "El tipo de usuario es obligatorio")
    private RolDto rol;
    
    @NotNull(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 15, message = "El nombre de usuario debe estar entre 3 y 15 caracteres")
    private String nombreUsuario;
    
    @NotNull(message = "El correo es obligatorio")
    @Size(min = 5, max = 30, message = "El correo debe estar entre 3 y 25 caracteres")
    private String correo; 
    
    @NotNull(message = "El contraseña es obligatoria")
    @Size(min = 3, max = 25, message = "La contraseña debe tener entre 3 y 25 caracteres")
    private String contrasena;
    
    private Boolean estado;

    private List<CompraAlbumDto> listaComprasAlbumes;

    private List<CompraCancionDto> listaComprasCanciones;

    public UsuarioDto() {
    }

    public UsuarioDto(Integer id, RolDto rol, String nombreUsuario, String correo, String contrasena, Boolean estado, List<CompraAlbumDto> listaComprasAlbumes, List<CompraCancionDto> listaComprasCanciones) {
        this.id = id;
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

    public RolDto getRol() {
        return rol;
    }

    public void setRol(RolDto rol) {
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
