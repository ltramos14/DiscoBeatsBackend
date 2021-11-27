package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
public class CancionDto implements Serializable {

    private Integer id;

    private Integer idArtista;
    
    private Integer idAlbum;
    
    @NotNull(message = "El genero musical de la canción es obligatorio")
    private Integer idGeneroMusical;
       
    @NotNull(message = "El nombre de la cancion es obligatorio")
    @Size(min = 3, max = 25, message = "El nombre de la cancion debe estar entre 3 y 25 caracteres")
    private String nombre;

    @NotNull(message = "La duracion de la cancion es obligatoria")
    private Time duracion;
    
    @NotNull(message = "El numero de reproducciones de la canción es obligatorio")
    private Integer reproducciones;

    @NotNull(message = "La fecha de lanzamiento de la cancion es obligatoria")
    @Past(message = "La fecha de lanzamiento no puede ser una fecha futura al dia de hoy")
    private Date fechaLanzamiento;

    @NotNull(message = "El precio de la cancion es obligatorio")
    private Integer precio;

    private String imagen;
    
    private String nombreArtistico;
    
    private String nombreAlbum;
    
    public CancionDto() {
    }

    public CancionDto(Integer id, Integer idArtista, Integer idGeneroMusical, Integer idAlbum, String nombre, Time duracion, Integer reproducciones, Date fechaLanzamiento, Integer precio, String imagen) {
        this.id = id;
        this.idArtista = idArtista;
        this.idGeneroMusical = idGeneroMusical;
        this.idAlbum = idAlbum;
        this.nombre = nombre;
        this.duracion = duracion;
        this.reproducciones = reproducciones;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precio = precio;
        this.imagen = imagen;
    }
    
    public CancionDto(Integer id, Integer idArtista, Integer idGeneroMusical, Integer idAlbum, String nombreArtistico, String nombreAlbum, String nombre, Time duracion, Integer reproducciones, Date fechaLanzamiento, Integer precio, String imagen) {
        this.id = id;
        this.idArtista = idArtista;
        this.idGeneroMusical = idGeneroMusical;
        this.nombreArtistico = nombreArtistico;
        this.nombreAlbum = nombreAlbum;
        this.idAlbum = idAlbum;
        this.nombre = nombre;
        this.duracion = duracion;
        this.reproducciones = reproducciones;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precio = precio;
        this.imagen = imagen;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public Integer getIdGeneroMusical() {
        return idGeneroMusical;
    }

    public void setIdGeneroMusical(Integer idGeneroMusical) {
        this.idGeneroMusical = idGeneroMusical;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
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

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

}
