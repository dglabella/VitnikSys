package vitniksys.backend.model.entities;

import vitniksys.backend.model.persistence.SubordinatedClientOperator;

import java.util.Iterator;

import vitniksys.backend.model.persistence.PreferentialClientOperator;

public class SubordinatedClient extends PreferentialClient
{
    //fk id
    private Integer leaderId;

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

    //Getters && Setters
    public Integer getLeaderId()
    {
        return this.leaderId;
    }

    public void setLeaderId(Integer leaderId)
    {
        this.leaderId = leaderId;
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
        return super.toString()+" SUB CLIENT -- Leader: "+this.leader.getId();
    }

    @Override
    public PreferentialClientOperator operator()
    {
        return SubordinatedClientOperator.getOperator();
    }
}