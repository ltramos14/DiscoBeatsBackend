/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nicon
 */
public class CompraCancionDto implements Serializable {
    
    private Integer id;
    
    @NotNull(message = "La canción no es obligatoria")
    private Integer idCancion;
    
    @NotNull(message = "El usuario el obligatorio")
    private Integer idUsuario;

    @NotNull(message = "El precio total de la compra es obligatorio")
    private Integer precioTotal;

    private String cancion;
   
    private String usuario;
    
    public CompraCancionDto() {
    }

    public CompraCancionDto(Integer id, Integer idCancion, Integer idUsuario, Integer precioTotal) {
        this.id = id;
        this.idCancion = idCancion;
        this.idUsuario = idUsuario;
        this.precioTotal = precioTotal;
    }
    
    public CompraCancionDto(Integer id, Integer idCancion, String cancion, String usuario,  Integer idUsuario, Integer precioTotal) {
        this.id = id;
        this.idCancion = idCancion;
        this.idUsuario = idUsuario;
        this.cancion = cancion;
        this.usuario = usuario;
        this.precioTotal = precioTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(Integer idCancion) {
        this.idCancion = idCancion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
