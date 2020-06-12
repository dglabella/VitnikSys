package vitniksys.backend.model;

import java.util.List;
import vitniksys.backend.persistence.ClienteSubordinadoOperator;
import vitniksys.backend.persistence.ClientePreferencialOperator;

public class ClienteSubordinado extends ClientePreferencial
{
    //Entity properties
    

    //Domain Associations
    private Lider lider;

    //Others


    public ClienteSubordinado(int id, String name, String lastName)
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
    public String toString()
    {
        return super.toString()+"\t|_ Lider: "+this.lider.toString();
    }

    @Override
    public ClientePreferencialOperator operator()
    {
        return ClienteSubordinadoOperator.getOperator();
    }
}