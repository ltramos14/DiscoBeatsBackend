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
public class AlbumDto implements Serializable {
    
    private Integer id;

    private ArtistaDto artista;
    
    private GeneroMusicalDto generoMusical;
    
    private String nombre;
    
    private String descripcion;
    
    private Date fechaLanzamiento;
    
    private String imagen;
    
    private Integer precio;
    
    private List<CancionDto> listaCanciones;
    
    private List<CompraAlbumDto> listaCompras;
        
    public AlbumDto() {
    }

    public AlbumDto(Integer id, ArtistaDto artista, GeneroMusicalDto generoMusical, String nombre, String descripcion, Date fechaLanzamiento, String imagen, Integer precio, List<CancionDto> listaCanciones, List<CompraAlbumDto> listaCompras) {
        this.id = id;
        this.artista = artista;
        this.generoMusical = generoMusical;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagen = imagen;
        this.precio = precio;
        this.listaCanciones = listaCanciones;
        this.listaCompras = listaCompras;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArtistaDto getArtista() {
        return artista;
    }

    public void setArtista(ArtistaDto artista) {
        this.artista = artista;
    }

    public GeneroMusicalDto getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusicalDto generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public List<CancionDto> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<CancionDto> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    public List<CompraAlbumDto> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<CompraAlbumDto> listaCompras) {
        this.listaCompras = listaCompras;
    }
    
}
