package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "compras")
public class Compra implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "fecha_compra", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCompra;
    
    @NotNull(message = "El precio total es obligatorio")
    @Column(name = "precio_total", nullable = false, length = 25)
    private Integer precioTotal;
   
    public Compra() {
    }

    public Compra(Integer id, Date fechaCompra, Integer precioTotal) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.precioTotal = precioTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
