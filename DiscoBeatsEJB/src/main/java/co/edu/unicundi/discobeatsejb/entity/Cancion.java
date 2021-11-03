
package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "cancion")
public class Cancion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "El nombre de la cancion es obligatorio")
    @Size(min = 3, max = 25, message = "El nombre de la cancion debe estar entre 3 y 25 caracteres")
    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;
    
    @NotNull(message = "El genero es obligatorio")
    @Size(min = 3, max = 25, message = "El genero debe estar entre 3 y 25 caracteres")
    @Column(name = "genero", nullable = false, length = 25)
    private String genero;
    
    @NotNull(message = "El numero de reproducciones es obligatoria")
    @Column(name = "reproducciones", nullable = false)
    private Integer reproducciones;
    
    @Column(name = "fecha_lanzamiento", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaLanzamiento;
    
    @NotNull(message = "El precio de la cancion es obligatorio")
    @Column(name = "precio", nullable = false)
    private Integer precio;
 
    @NotNull(message = "La duracion es obligatoria")
    @Column(name = "duracion", nullable = false)
    private Time duracion;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false )
    private Artista artista;
    
    //@JsonIgnore
    //@JoinColumn(name = "id_genero_musical", nullable = false )
    //private GeneroMusical generoMusical;
    
    public Cancion() {
    }

    public Cancion(Integer id, String nombre, String genero, Integer reproducciones, Date fechaLanzamiento, Integer precio, Time duracion, Artista artista, GeneroMusical generoMusical) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.reproducciones = reproducciones;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precio = precio;
        this.duracion = duracion;
        this.artista = artista;
        //this.generoMusical = generoMusical;
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

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

//    public GeneroMusical getGeneroMusical() {
//        return generoMusical;
//    }
//
//    public void setGeneroMusical(GeneroMusical generoMusical) {
//        this.generoMusical = generoMusical;
//    }
    
    
 
}
