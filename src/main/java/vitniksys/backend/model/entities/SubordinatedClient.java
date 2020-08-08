package vitniksys.backend.model.entities;

import vitniksys.backend.model.persistence.SubordinatedClientOperator;
import vitniksys.backend.model.persistence.PreferentialClientOperator;

public class SubordinatedClient extends PreferentialClient
{
    //Entity properties
    

    //Domain Associations
    private Leader leader;

    //Others

    public SubordinatedClient(int id)
    {
        super(id);
    }

    public SubordinatedClient(int id, String name, String lastName)
    {
        super(id, name, lastName);
    }

    public Leader getLeader()
    {
        return this.leader;
    }

    public void setLeader(Leader leader)
    {
        this.leader = leader;
    }

    @Override
    public String toString()
    {
        return super.toString()+"\t|_ Lider: "+this.leader.toString();
    }

    @Override
    public PreferentialClientOperator operator()
    {
        return SubordinatedClientOperator.getOperator();
    }
}