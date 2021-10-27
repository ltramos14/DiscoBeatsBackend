package co.edu.unicundi.discobeatsejb.exception;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
public class ResourceNotFoundException  extends Exception {

    /**
     * Constructor de la clase ResourceNotFoundException
     *
     * @param message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
}
