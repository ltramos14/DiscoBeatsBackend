/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author nicon
 */
public class CancionDto implements Serializable {

    private Integer id;

    private ArtistaDto artista;

    @NotNull(message = "El genero musical de la canción es obligatorio")
    private GeneroMusicalDto generoMusical;
    
    private AlbumDto album;
       
    @NotNull(message = "El nombre de la cancion es obligatorio")
    @Size(min = 3, max = 25, message = "El nombre de la cancion debe estar entre 3 y 25 caracteres")
    private String nombre;

    private Time duracion;
    
    @NotNull(message = "El numero de reproducciones es obligatoria")
    private Integer reproducciones;

    private Date fechaLanzamiento;

    @NotNull(message = "El precio de la cancion es obligatorio")
    private Integer precio;

    private String imagen;
    
    private List<CompraCancionDto> listaCompras;

    public CancionDto() {
    }

    public CancionDto(Integer id, ArtistaDto artista, GeneroMusicalDto generoMusical, AlbumDto album, String nombre, Time duracion, Integer reproducciones, Date fechaLanzamiento, Integer precio, String imagen, List<CompraCancionDto> listaCompras) {
        this.id = id;
        this.artista = artista;
        this.generoMusical = generoMusical;
        this.album = album;
        this.nombre = nombre;
        this.duracion = duracion;
        this.reproducciones = reproducciones;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precio = precio;
        this.imagen = imagen;
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

    public AlbumDto getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDto album) {
        this.album = album;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public Integer getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(Integer reproducciones) {
        this.reproducciones = reproducciones;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<CompraCancionDto> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<CompraCancionDto> listaCompras) {
        this.listaCompras = listaCompras;
    }

    
}
