/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.views;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author Camilo Preciado
 */

@Entity
@Immutable
@Table(name = "view_ventas_cancion")
@NamedQueries({
    @NamedQuery(name = "CancionView.VentasCanciones", query = "SELECT cv FROM CancionView cv")
})
public class CancionView implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombrecancion", insertable = false, updatable = false, length = 25 )
    private String NombreCancion;
    
    @Column(name = "ventas", insertable = false, updatable = false, length = 25 )
    private Integer Ventas;

    public CancionView() {
    }

    public CancionView(Integer id, String NombreCancion, Integer Ventas) {
        this.id = id;
        this.NombreCancion = NombreCancion;
        this.Ventas = Ventas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCancion() {
        return NombreCancion;
    }

    public void setNombreCancion(String NombreCancion) {
        this.NombreCancion = NombreCancion;
    }

    public Integer getVentas() {
        return Ventas;
    }

    public void setVentas(Integer Ventas) {
        this.Ventas = Ventas;
    }
    
    
}
