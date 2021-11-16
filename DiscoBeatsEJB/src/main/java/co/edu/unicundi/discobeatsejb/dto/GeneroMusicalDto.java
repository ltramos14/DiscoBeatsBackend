package co.edu.unicundi.discobeatsejb.dto;

import java.util.List;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
public class GeneroMusicalDto {
    
    private Integer id;

    private String nombreGeneroMusical;
    
    private List<ArtistaDto> listaArtistas;
    
    private List<CancionDto> listaCanciones;
    
    private List<AlbumDto> listaAlbumes;

    public GeneroMusicalDto() {
    }

    public GeneroMusicalDto(Integer id, String nombreGeneroMusical, List<ArtistaDto> listaArtistas, List<CancionDto> listaCanciones, List<AlbumDto> listaAlbumes) {
        this.id = id;
        this.nombreGeneroMusical = nombreGeneroMusical;
        this.listaArtistas = listaArtistas;
        this.listaCanciones = listaCanciones;
        this.listaAlbumes = listaAlbumes;
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

    public List<ArtistaDto> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(List<ArtistaDto> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }

    public List<CancionDto> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<CancionDto> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    public List<AlbumDto> getListaAlbumes() {
        return listaAlbumes;
    }

    public void setListaAlbumes(List<AlbumDto> listaAlbumes) {
        this.listaAlbumes = listaAlbumes;
    }
    
}
