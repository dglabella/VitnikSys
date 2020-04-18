package vitniksys.backend.persistence;

import java.util.ArrayList;

public interface CrudOperator<BusinessObj> 
{
    public int insert(BusinessObj bo);
    
    public int update(BusinessObj bo);

    public ArrayList<BusinessObj> findAll();  
 }