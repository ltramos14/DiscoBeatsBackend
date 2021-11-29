package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import java.sql.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
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

    private String nombreOcupacion;

    public ArtistaDto() {
    }

    public ArtistaDto(Integer id, Integer idGeneroMusical, Integer idOcupacion, String nombreArtistico, Date fechaNacimiento, String nacionalidad, String descripcion, String imagen) {
        this.id = id;
        this.idGeneroMusical = idGeneroMusical;
        this.idOcupacion = idOcupacion;
        this.nombreArtistico = nombreArtistico;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
    
    public ArtistaDto(Integer id, Integer idGeneroMusical, Integer idOcupacion, String nombreOcupacion, String nombreArtistico, Date fechaNacimiento, String nacionalidad, String descripcion, String imagen) {
        this.id = id;
        this.idGeneroMusical = idGeneroMusical;
        this.idOcupacion = idOcupacion;
        this.nombreOcupacion = nombreOcupacion;
        this.nombreArtistico = nombreArtistico;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.descripcion = descripcion;
        this.imagen = imagen;
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

    public String getNombreOcupacion() {
        return nombreOcupacion;
    }

    public void setNombreOcupacion(String nombreOcupacion) {
        this.nombreOcupacion = nombreOcupacion;
    }
      
}
