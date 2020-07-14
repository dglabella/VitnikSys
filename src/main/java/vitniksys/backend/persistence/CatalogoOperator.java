package vitniksys.backend.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import vitniksys.backend.interfaces.ICatalogoOperator;
import vitniksys.backend.model.Catalogo;

//This class intanciates the DAO Object for Catalogo
public class CatalogoOperator implements ICatalogoOperator
{
    private Connection connection;

    public CatalogoOperator()
    {
        Connector.getConnector()
    }

    public int insert(Catalogo catalogo)
    {
        int errorCode = 0;
        
        return errorCode;
    }

    public Catalogo find(int codigo)
    {
        Catalogo catalogo = new Catalogo(-1,-1,0f);

        return catalogo;
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