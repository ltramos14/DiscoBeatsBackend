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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
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
@Table(name = "compras_canciones")
@NamedQueries({
    @NamedQuery(name = "CompraCancion.ListarTodos", query = "SELECT a FROM CompraCancion a"),
    @NamedQuery(name = "CompraCancion.ContarPorId", query = "SELECT COUNT(t) FROM CompraCancion t WHERE t.id=:id"),
    @NamedQuery(name = "CompraCancion.ContarPorUsuario", query = "SELECT COUNT(u) FROM CompraCancion u WHERE u.usuarioCancion.id=:idUsuario AND u.cancionCompra.id=:idCancion")
})
public class CompraCancion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "La compra debe tener una canción obligatoriamente")
    @ManyToOne
    @JoinColumn(name = "id_cancion", nullable = false )
    private Cancion cancionCompra;
    
    @NotNull(message = "La compra debe pertenecer a un usuario obligatoriamente")
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false )
    private Usuario usuarioCancion;
     
    @Column(name = "fecha_compra", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCompra;
    
    @NotNull(message = "El precio total es obligatorio")
    @Column(name = "precio_total", nullable = false, length = 25)
    private Integer precioTotal;
    
    public CompraCancion() {
    }

    public CompraCancion(Cancion cancionCompra, Usuario usuarioCancion, Date fechaCompra, Integer precioTotal) {
        this.cancionCompra = cancionCompra;
        this.usuarioCancion = usuarioCancion;
        this.fechaCompra = fechaCompra;
        this.precioTotal = precioTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    @XmlTransient
    public Cancion getCancion() {
        return cancionCompra;
    }

    public void setCancion(Cancion cancionCompra) {
        this.cancionCompra = cancionCompra;
    }

    @JsonIgnore
    @XmlTransient
    public Usuario getUsuario() {
        return usuarioCancion;
    }

    public void setUsuario(Usuario usuarioCancion) {
        this.usuarioCancion = usuarioCancion;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }
    
}
