/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.dto;

import co.edu.unicundi.discobeatsejb.entity.Cancion;
import java.io.Serializable;
import java.sql.Date;

import java.util.List;
import javax.persistence.Column;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author nicon
 */
public class ArtistaDto implements Serializable {
    
    private Integer id;
    
    private Integer idGeneroMusical;
    
    private Integer idOcupacion;

    @NotNull(message = "El nombre artistico es obligatorio")
    @Size(min = 3, max = 25, message = "El nombre artistico debe estar entre 3 y 25 caracteres")
    private String nombreArtistico;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private Date fechaNacimiento;

    @NotNull(message = "La nacionalidad es obligatoria")
    @Size(min = 5, max = 20, message = "La nacionalidad debe estar entre 5 y 20 caracteres")
    private String nacionalidad;

    private String imagen;

    @NotNull(message = "La descripcion es obligatoria")
    @Size(min = 15, max = 255, message = "La descripcion debe estar entre 15 y 255 caracteres")
    private String descripcion;
    
    private List<Cancion> canciones;


    public ArtistaDto() {
    }

    public ArtistaDto(Integer id, Integer idGeneroMusical, Integer idOcupacion, String nombreArtistico, Date fechaNacimiento, String nacionalidad, String imagen, String descripcion, List<Cancion> canciones) {
        this.id = id;
        this.idGeneroMusical = idGeneroMusical;
        this.idOcupacion = idOcupacion;
        this.nombreArtistico = nombreArtistico;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.canciones = canciones;
    }

    public Integer getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(Integer idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdGeneroMusical() {
        return idGeneroMusical;
    }

    public void setIdGeneroMusical(Integer idGeneroMusical) {
        this.idGeneroMusical = idGeneroMusical;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }   

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    
       
}
