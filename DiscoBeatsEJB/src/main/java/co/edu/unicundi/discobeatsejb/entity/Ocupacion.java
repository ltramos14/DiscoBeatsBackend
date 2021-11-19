package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "ocupaciones")
@NamedQueries({
    @NamedQuery(name = "Ocupacion.ListarTodas", query = "SELECT o FROM Ocupacion o"),
    @NamedQuery(name = "Ocupacion.ContarPorId", query = "SELECT COUNT(t) FROM Ocupacion t WHERE t.id=:id"),
})
public class Ocupacion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "La ocupacion del artista es obligatoria")
    @Size(min = 3, max = 15, message = "La ocupacion del artista debe estar entre 3 y 15 caracteres")
    @Column(name = "nombre_ocupacion", nullable = false, length = 15)
    private String nombreOcupacion;

    @OneToMany(mappedBy = "ocupacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artista> listaArtistas;
            
    public Ocupacion() {
    }

    public Ocupacion(String nombreOcupacion, List<Artista> listaArtistas) {
        this.nombreOcupacion = nombreOcupacion;
        this.listaArtistas = listaArtistas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreOcupacion() {
        return nombreOcupacion;
    }

    public void setNombreOcupacion(String nombreOcupacion) {
        this.nombreOcupacion = nombreOcupacion;
    }

    public List<Artista> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(List<Artista> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }  
}
