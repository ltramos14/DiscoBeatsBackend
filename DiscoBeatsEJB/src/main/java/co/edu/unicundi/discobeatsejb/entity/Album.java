package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
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
@Table(name = "albumes")
public class Album implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false )
    private Artista artista;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false )
    private GeneroMusical generoMusical;
    
    @NotNull(message = "El nombre del album es obligatorio")
    @Size(min = 3, max = 20, message = "El nombre del album debe estar entre 3 y 25 caracteres")
    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;
    
    @NotNull(message = "La descripcion es obligatoria")
    @Size(min = 3, max = 250, message = "La descripcion debe estar entre 3 y 250 caracteres")
    @Column(name = "descripcion", nullable = false, columnDefinition = "text")
    private String descripcion;
    
    @Column(name = "fecha_lanzamiento", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaLanzamiento;
    
    @Column(name = "imagen", nullable = true, columnDefinition = "text")
    private String imagen;
    
    @NotNull(message = "El precio del album es obligatorio")
    @Column(name = "precio", nullable = false)
    private Integer precio;
 
    public Album() {
    }

    public Album(Integer id, String nombre, String descripcion, Date fechaLanzamiento, String imagen, Integer precio, Artista artista, GeneroMusical generoMusical) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagen = imagen;
        this.precio = precio;
        this.artista = artista;
        this.generoMusical = generoMusical;
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

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
    }
    
}
