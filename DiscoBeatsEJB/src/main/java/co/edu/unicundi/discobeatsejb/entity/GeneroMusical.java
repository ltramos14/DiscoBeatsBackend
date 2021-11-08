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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
@Table(name = "genero_musical")
@NamedQueries({
    @NamedQuery(name = "GeneroMusical.ListarTodos", query = "SELECT g FROM GeneroMusical g"),
    @NamedQuery(name = "GeneroMusical.EliminarGenero", query = "DELETE FROM GeneroMusical g WHERE g.id=:id"),
    @NamedQuery(name = "GeneroMusical.ContarPorId", query = "SELECT COUNT(g) FROM GeneroMusical g WHERE g.id=:id"),
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "GeneroMusical.ContarPorNombre", query = "SELECT COUNT(*) FROM genero_musical WHERE UPPER(nombre_genero_musical) = UPPER(?)")
})
public class GeneroMusical implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "El tipo de genero es obligatorio")
    @Size(min = 3, max = 15, message = "El nombre del genero musical debe estar entre 3 y 15 caracteres")
    @Column(name = "nombre_genero_musical", nullable = false, length = 15)
    private String nombreGeneroMusical;

    @OneToMany(mappedBy = "generoMusical", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artista> listaArtistas;
            
    public GeneroMusical() {
    }

    public GeneroMusical(Integer id, String nombreGeneroMusical, List<Artista> listaArtistas) {
        this.id = id;
        this.nombreGeneroMusical = nombreGeneroMusical;
        this.listaArtistas = listaArtistas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreGeneroMusical() {
        return nombreGeneroMusical;
    }

    public void setNombreGeneroMusical(String nombreGeneroMusical) {
        this.nombreGeneroMusical = nombreGeneroMusical;
    }

    public List<Artista> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(List<Artista> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }
}
