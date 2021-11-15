/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author nicon
 */
public class RolDto implements Serializable {
    
    private Integer id;
    
    private String rol;
    
    private List<UsuarioDto> listaUsuarios;

    public RolDto() {
    }

    public RolDto(Integer id, String rol, List<UsuarioDto> listaUsuarios) {
        this.id = id;
        this.rol = rol;
        this.listaUsuarios = listaUsuarios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<UsuarioDto> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioDto> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
}
