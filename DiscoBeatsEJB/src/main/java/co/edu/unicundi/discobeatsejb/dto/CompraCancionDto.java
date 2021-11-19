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
    
    private Integer idCancion;
    
    private Integer idUsuario;

    private Integer precioTotal;

    public CompraCancionDto() {
    }

    public CompraCancionDto(Integer id, Integer idCancion, Integer idUsuario, Integer precioTotal) {
        this.id = id;
        this.idCancion = idCancion;
        this.idUsuario = idUsuario;
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

    
}
