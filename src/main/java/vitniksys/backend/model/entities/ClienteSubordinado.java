package vitniksys.backend.model.entities;

import vitniksys.backend.model.persistence.ClienteSubordinadoOperator;
import vitniksys.backend.model.persistence.ClientePreferencialOperator;

public class ClienteSubordinado extends ClientePreferencial
{
    //Entity properties
    

    //Domain Associations
    private Lider leader;

    //Others

    public ClienteSubordinado(int id)
    {
        super(id);
    }

    public ClienteSubordinado(int id, String name, String lastName)
    {
        super(id, name, lastName);
    }

    public Lider getLider()
    {
        return this.leader;
    }

    public void setLider(Lider lider)
    {
        this.leader = lider;
    }

    @Override
    public String toString()
    {
        return super.toString()+"\t|_ Lider: "+this.leader.toString();
    }

    @Override
    public ClientePreferencialOperator operator()
    {
        return ClienteSubordinadoOperator.getOperator();
    }
}