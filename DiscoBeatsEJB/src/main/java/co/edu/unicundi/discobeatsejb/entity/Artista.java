package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
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
@Entity
@Table(name = "artista")
@NamedQueries({
    @NamedQuery(name = "Artista.ListarTodos", query = "SELECT a FROM Artista a"),
    @NamedQuery(name = "Artista.ContarPorId", query = "SELECT COUNT(t) FROM Artista t WHERE t.id=:id"),
    @NamedQuery(name = "Artista.ContarPorNombre", query = "SELECT COUNT(t) FROM Artista t WHERE t.nombreArtistico=:nombre")
})
public class Artista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El nombre artistico es obligatorio")
    @Size(min = 3, max = 25, message = "El nombre artistico debe estar entre 3 y 25 caracteres")
    @Column(name = "nombre_artistico", nullable = false, length = 25, unique = true)
    private String nombreArtistico;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    @NotNull(message = "La nacionalidad es obligatoria")
    @Size(min = 5, max = 20, message = "La nacionalidad debe estar entre 5 y 20 caracteres")
    @Column(name = "nacionalidad", nullable = false, length = 20)
    private String nacionalidad;

    @NotNull(message = "La ocupacion es obligatoria")
    @Size(min = 5, max = 15, message = "La ocupacion debe estar entre 5 y 15 caracteres")
    @Column(name = "ocupacion", nullable = false, length = 15)
    private String ocupacion;

    @Column(name = "imagen", nullable = true, length = 255)
    private String imagen;

    @NotNull(message = "El genero musical es obligatorio")
    @Size(min = 3, max = 15, message = "El genero musical debe estar entre 5 y 15 caracteres")
    @Column(name = "genero_musical", nullable = false, length = 15)
    private String generoMusical;

    @NotNull(message = "La descripcion es obligatoria")
    @Size(min = 15, max = 255, message = "La descripcion debe estar entre 15 y 255 caracteres")
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    public Artista() {
    }

    public Artista(Integer id, String nombreArtistico, Date fechaNacimiento, String nacionalidad, String ocupacion, String imagen, String generoMusical, String descripcion) {
        this.id = id;
        this.nombreArtistico = nombreArtistico;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.ocupacion = ocupacion;
        this.imagen = imagen;
        this.generoMusical = generoMusical;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
