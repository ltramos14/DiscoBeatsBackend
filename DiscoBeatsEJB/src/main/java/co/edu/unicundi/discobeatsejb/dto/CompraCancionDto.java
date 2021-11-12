/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author nicon
 */
public class CompraCancionDto implements Serializable {
    
    private Integer id;
    
    private CancionDto cancion;
    
    private UsuarioDto usuario;
     
    private Date fechaCompra;

    private Integer precioTotal;

    public CompraCancionDto() {
    }

    public CompraCancionDto(Integer id, CancionDto cancion, UsuarioDto usuario, Date fechaCompra, Integer precioTotal) {
        this.id = id;
        this.cancion = cancion;
        this.usuario = usuario;
        this.fechaCompra = fechaCompra;
        this.precioTotal = precioTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CancionDto getCancion() {
        return cancion;
    }

    public void setCancion(CancionDto cancion) {
        this.cancion = cancion;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }
    
}
