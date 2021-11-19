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
public class CompraAlbumDto implements Serializable{
    
    private Integer id;
    
    @NotNull(message = "La compra debe tener un album obligatoriamente")
    private Integer idAlbum;
    
    @NotNull(message = "La compra debe pertenecer a un usuario obligatoriamente")
    private Integer idUsuario;
     
    @NotNull(message = "El precio total es obligatorio")
    private Integer precioTotal;

    public CompraAlbumDto() {
    }

    public CompraAlbumDto(Integer id, Integer idAlbum, Integer idUsuario, Integer precioTotal) {
        this.id = id;
        this.idAlbum = idAlbum;
        this.idUsuario = idUsuario;
        this.precioTotal = precioTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
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
   
    
    
}
