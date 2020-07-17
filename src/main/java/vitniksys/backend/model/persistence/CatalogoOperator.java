package vitniksys.backend.model.persistence;

import java.util.ArrayList;
import vitniksys.backend.model.entities.Catalogo;
import vitniksys.backend.model.interfaces.ICatalogoOperator;

//This class intanciates the DAO Object for Catalogo
public class CatalogoOperator implements ICatalogoOperator
{
    public int insert(Catalogo catalogo)
    {
        int errorCode = 0;
        
        return errorCode;
    }

    public Catalogo find(int codigo)
    {
        return null;
    }

    public ArrayList<Catalogo> findAll()
    {
        ArrayList<Catalogo> catalogos =  new ArrayList();
        return catalogos;
    }

    public int update(Catalogo catalogo)
    {
        int errorCode = 0;

        return errorCode;
    }

    public int delete(int codigo)
    {
        int errorCode = 0;

        return errorCode;
    }
}