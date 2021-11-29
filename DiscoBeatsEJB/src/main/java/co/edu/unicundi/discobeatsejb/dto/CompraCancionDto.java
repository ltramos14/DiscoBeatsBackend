package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import java.sql.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nicon
 */
public class CompraCancionDto implements Serializable {
    
    private Integer id;
    
    @NotNull(message = "La compra debe tener una canción obligatoriamente")
    private Integer idCancion;
    
    @NotNull(message = "El id del usuario es obligatorio")
    private Integer idUsuario;

    @NotNull(message = "El precio total de la compra es obligatorio")
    private Integer precioTotal;
    
    private Date fechaCompra;

    private String cancion;
   
    private String usuario;
    
    public CompraCancionDto() {
    }

    public CompraCancionDto(Integer id, Integer idCancion, Integer idUsuario, Integer precioTotal) {
        this.id = id;
        this.idCancion = idCancion;
        this.idUsuario = idUsuario;
        this.precioTotal = precioTotal;
    }
    
    public CompraCancionDto(Integer id, Integer idCancion, Integer idUsuario, String cancion, String usuario, Integer precioTotal, Date fechaCompra) {
        this.id = id;
        this.idCancion = idCancion;
        this.idUsuario = idUsuario;
        this.cancion = cancion;
        this.usuario = usuario;
        this.precioTotal = precioTotal;
        this.fechaCompra = fechaCompra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(Integer idCancion) {
        this.idCancion = idCancion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
