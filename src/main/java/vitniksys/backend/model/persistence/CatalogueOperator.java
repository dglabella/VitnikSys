package vitniksys.backend.model.persistence;

import java.util.ArrayList;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.backend.model.interfaces.ICatalogueOperator;

//This class intanciates the DAO Object for Catalogo
public class CatalogueOperator implements ICatalogueOperator
{

    private static CatalogueOperator operator;

    private CatalogueOperator()
    {
        //Empty constructor
    }

    public static CatalogueOperator getOperator()
    {
        if(CatalogueOperator.operator == null)
            CatalogueOperator.operator = new CatalogueOperator();
        
        return CatalogueOperator.operator;
	}

    public int insert(Catalogue catalogue)
    {
        int errorCode = 0;
        
        return errorCode;
    }

    public Catalogue find(int id)
    {
        return null;
    }

    public ArrayList<Catalogue> findAll()
    {
        ArrayList<Catalogue> catalogos = new ArrayList<>();
        return catalogos;
    }

    public int update(Catalogue catalogue)
    {
        int errorCode = 0;

        return errorCode;
    }

    public int delete(int id)
    {
        int errorCode = 0;

        return errorCode;
    }
}