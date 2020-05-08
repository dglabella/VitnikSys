package vitniksys.backend.interfaces;

import java.util.List;

public interface CrudOperator<ModelObject> 
{
    public int insert(ModelObject mo);
    
    public int update(ModelObject mo);

    public List<ModelObject> findAll();  
 }