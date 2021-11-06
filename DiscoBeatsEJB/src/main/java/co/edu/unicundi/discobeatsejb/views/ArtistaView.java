package co.edu.unicundi.discobeatsejb.views;

import co.edu.unicundi.discobeatsejb.entity.Cancion;
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
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Immutable
@Table(name = "artista_view")
@NamedQueries({
    @NamedQuery(name = "ArtistaView.ArtistasConCanciones", query = "SELECT av FROM ArtistaView av")
})
public class ArtistaView implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombre_artistico", insertable = false, updatable = false, length = 25 )
    private String nombreArtistico;
    
    @Column(name = "fecha_nacimiento", insertable = false, updatable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    
    @Column(name = "nacionalidad", insertable = false, updatable = false)
    private String nacionalidad;
    
    @Column(name = "ocupacion", insertable = false, updatable = false)
    private String ocupacion; 
    
    @Column(name = "imagen", insertable = false, updatable = false)
    private String imagen;
    
    @Column(name = "genero_musical", insertable = false, updatable = false)
    private String generoMusical;
    
    @Column(name = "descripcion", insertable = false, updatable = false)
    private String descripcion;
    
    @Column(name = "numero_canciones", insertable = false, updatable = false)
    private Integer numeroCanciones;
    
    @Column(name = "maximas_reproducciones", insertable = false, updatable = false)
    private Integer maximasReproducciones;
    
    public ArtistaView() {
    }

    public ArtistaView(Integer id, String nombreArtistico, Date fechaNacimiento, String nacionalidad, String ocupacion, String imagen, String generoMusical, String descripcion, Integer numeroCanciones, Integer maximasReproducciones) {
        this.id = id;
        this.nombreArtistico = nombreArtistico;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.ocupacion = ocupacion;
        this.imagen = imagen;
        this.generoMusical = generoMusical;
        this.descripcion = descripcion;
        this.numeroCanciones = numeroCanciones;
        this.maximasReproducciones = maximasReproducciones;
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

    public Integer getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(Integer numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public Integer getMaximasReproducciones() {
        return maximasReproducciones;
    }

    public void setMaximasReproducciones(Integer maximasReproducciones) {
        this.maximasReproducciones = maximasReproducciones;
    }
    
}
