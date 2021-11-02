/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Camilo Preciado
 */

@Entity
@Table(name = "cancion")
public class Cancion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
//    @NotNull(message = "El nombre de la cancion")
//    @Size(min = 3, max = 25, message = "El nombre de la cancion debe estar entre 3 y 25 caracteres")
    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;
    
//    @NotNull(message = "El genero es obligatorio")
//    @Size(min = 3, max = 25, message = "El genero debe estar entre 3 y 25 caracteres")
    @Column(name = "genero", nullable = false, length = 25)
    private String genero;
    
    @Column(name = "reproducciones", nullable = false)
    private Integer reproducciones;
    
    @Column(name = "anio_lanzamiento", nullable = false)
    private Integer anioLanzamiento;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false )
    private Artista artista;

    public Cancion() {
    }

    public Cancion(String nombre, String genero, Integer reproducciones, Integer anioLanzamiento, Artista artista) {
        this.nombre = nombre;
        this.genero = genero;
        this.reproducciones = reproducciones;
        this.anioLanzamiento = anioLanzamiento;
        this.artista = artista;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(Integer reproducciones) {
        this.reproducciones = reproducciones;
    }

    public Integer getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(Integer anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    
    
    
}
