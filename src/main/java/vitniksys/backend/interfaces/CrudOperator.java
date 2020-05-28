package vitniksys.backend.interfaces;

import java.util.List;

/**
 * A CRUD operator object is a Data Access Object (DAO) and should be 
 * responable of all persistence operations for an specific entity of the domain.
 * @param <ModelObject> The class that represent an entity of the domain.
 */
public interface CrudOperator<ModelObject> 
{
    /**
     * Registers persistently an entity of the domain.
     * @param mo The entity to be registered.
     * @return A code for errors checking. 0 represent that no error has occurred.
     */
    public int insert(ModelObject mo);
    
    /**
     * Updates persistently an entity of the domain.
     * @param mo The entity to be updated.
     * @return A code for errors checking. 0 represent that no error has occurred.
     */
    public int update(ModelObject mo);

    /**
     * Find all the registered entities of that type.
     * @return A list of all entities found.
     */
    public List<ModelObject> findAll();  
 }