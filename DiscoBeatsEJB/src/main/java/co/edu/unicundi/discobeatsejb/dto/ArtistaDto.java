/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nicon
 */
public class ArtistaDto implements Serializable {
    
    private Integer id;

    private OcupacionDto ocupacion;
    
    private GeneroMusicalDto generoMusical;
    
    private String nombreArtistico;

    private Date fechaNacimiento;
    
    private String nacionalidad;
    
    private String imagen;
    
    private String descripcion;
    
    private List<CancionDto> canciones;

    public ArtistaDto() {
    }

    public ArtistaDto(Integer id, OcupacionDto ocupacion, GeneroMusicalDto generoMusical, String nombreArtistico, Date fechaNacimiento, String nacionalidad, String imagen, String descripcion, List<CancionDto> canciones) {
        this.id = id;
        this.ocupacion = ocupacion;
        this.generoMusical = generoMusical;
        this.nombreArtistico = nombreArtistico;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.canciones = canciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OcupacionDto getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(OcupacionDto ocupacion) {
        this.ocupacion = ocupacion;
    }

    public GeneroMusicalDto getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusicalDto generoMusical) {
        this.generoMusical = generoMusical;
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

    public List<CancionDto> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<CancionDto> canciones) {
        this.canciones = canciones;
    }
    
}
