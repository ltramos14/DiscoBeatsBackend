package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
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
@Table(name = "artistas")
@NamedQueries({
    @NamedQuery(name = "Artista.ListarTodos", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.ArtistaDto" 
            + "(a.id, a.generoMusical.id, a.ocupacion.id, a.ocupacion.nombreOcupacion, a.nombreArtistico, a.fechaNacimiento, a.nacionalidad, a.descripcion, a.imagen ) FROM Artista a ORDER BY a.id"),
    @NamedQuery(name = "Artista.ObtenerPorId", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.ArtistaDto" 
            + "(a.id, a.generoMusical.id, a.ocupacion.id, a.nombreArtistico, a.fechaNacimiento, a.nacionalidad, a.descripcion, a.imagen ) FROM Artista a WHERE a.id = :id"),
    @NamedQuery(name = "Artista.ContarPorId", query = "SELECT COUNT(t) FROM Artista t WHERE t.id=:id"),
    @NamedQuery(name = "Artista.EliminarArtista", query = "DELETE FROM Artista a WHERE a.id=:id")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Artista.ContarPorNombre", query = "SELECT COUNT(*) FROM artistas WHERE UPPER(nombre_artistico) = UPPER(?)")
})

public class Artista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    @ManyToOne
    @JoinColumn(name = "id_ocupacion", nullable = false)
    private Ocupacion ocupacion;
    
    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private GeneroMusical generoMusical;
    
    @Column(name = "nombre_artistico", nullable = false, length = 25, unique = true)
    private String nombreArtistico;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;
  
    @Column(name = "nacionalidad", nullable = false, length = 20)
    private String nacionalidad;

    @Column(name = "imagen", columnDefinition = "text", nullable = true)
    private String imagen;

    @Column(name = "descripcion", columnDefinition = "text", nullable = false)
    private String descripcion;
    
    @OneToMany(mappedBy = "artista", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cancion> canciones;

    public Artista() {
    }

    public Artista(Ocupacion ocupacion, GeneroMusical generoMusical, String nombreArtistico, Date fechaNacimiento, String nacionalidad, String imagen, String descripcion, List<Cancion> canciones) {
        this.ocupacion = ocupacion;
        this.generoMusical = generoMusical;
        this.nombreArtistico = nombreArtistico;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.canciones = canciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    @XmlTransient
    public Ocupacion getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    @JsonIgnore
    @XmlTransient
    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
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

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
    
}
