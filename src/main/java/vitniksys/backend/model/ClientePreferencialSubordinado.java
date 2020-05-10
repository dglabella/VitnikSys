package vitniksys.backend.model;

import java.util.List;
import vitniksys.backend.persistence.ClientePreferencialOperator;
import vitniksys.backend.persistence.ClientePreferencialSubordinadoOperator;

public class ClientePreferencialSubordinado extends ClientePreferencial
{
    //Entity properties
    

    //Domain Associations
    private Lider lider;

    //Others


    public ClientePreferencialSubordinado(int id, String name, String lastName)
    {
        super(id, name, lastName);
    }

    public Lider getLider()
    {
        return this.lider;
    }

    public void setLider(Lider lider)
    {
        this.lider = lider;
    }

    @Override
    public ClientePreferencialOperator operator()
    {
        return ClientePreferencialSubordinadoOperator.getOperator();
    }
}