package vitniksys.backend.model.interfaces;

import java.util.List;

/**
 * A CRUD operator object is a Data Access Object (DAO) and should be 
 * responable of all persistence operations for an specific entity of the domain.
 * @param <Entity> The class that represent an entity of the domain.
 */
public interface CrudOperator<Entity> 
{
    /**
     * Registers persistently an entity of the domain.
     * @param e The entity to be registered.
     * @return A code for errors checking. 0 represent that an error has occurred.
     */
    int insert(Entity entity) throws Exception;

    /**
     * Registers persistently a list of entities of the domain.
     * This method is supposed to be more eficient when a large number
     * of insertion is needed.
     * @param e The list of entities to be registered.
     * @return A code for errors checking. 0 represent that an error has occurred.
     */
    int insertMany(List<Entity> list) throws Exception;
    
    /**
     * Updates persistently an entity of the domain.
     * @param e The entity to be updated.
     * @return A code for errors checking. 0 represent that an error has occurred.
     */
    int update(Entity entity) throws Exception;

    /**
     * Find all the registered entities of that type.
     * @return A list of all entities found.
     */
    List<Entity> findAll() throws Exception;
 }