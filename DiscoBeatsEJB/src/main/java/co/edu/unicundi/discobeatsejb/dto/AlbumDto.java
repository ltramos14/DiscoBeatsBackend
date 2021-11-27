package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
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
public class AlbumDto implements Serializable {
    
    private Integer id;

    private Integer idArtista;
    
    @NotNull(message = "El id del genero musical del album es obligatorio")
    private Integer idGeneroMusical;
    
    @NotNull(message = "El nombre del album es obligatorio")
    @Size(min = 3, max = 40, message = "El nombre del album debe estar entre 3 y 40 caracteres")
    private String nombre;
    
    @NotNull(message = "La descripcion del album es obligatoria")
    @Size(min = 3, max = 250, message = "La descripcion debe estar entre 3 y 250 caracteres")
    private String descripcion;
    
    @NotNull(message = "La fecha de lanzamiento del album es obligatoria")
    @Past(message = "La fecha de lanzamiento no puede ser una fecha futura al dia de hoy")
    private Date fechaLanzamiento;
    
    private String imagen;
    
    @NotNull(message = "El precio del album es obligatorio")
    private Integer precio;

        
    public AlbumDto() {
    }

    public AlbumDto(Integer id, Integer idArtista, Integer idGeneroMusical, String nombre, String descripcion, Date fechaLanzamiento, String imagen, Integer precio) {
        this.id = id;
        this.idArtista = idArtista;
        this.idGeneroMusical = idGeneroMusical;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagen = imagen;
        this.precio = precio;
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
   
}
