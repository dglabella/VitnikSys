package vitniksys.backend.model.entities;

import java.util.List;
import vitniksys.backend.model.persistence.LeaderOperator;
import vitniksys.backend.model.persistence.PreferentialClientOperator;

public class Leader extends BaseClient
{
    //Entity properties
    

    //Domain Associations
    private List<Commission> commissions;
    private List<SubordinatedClient> subordinates;

    //Others
    
    //Constructors
    public Leader(int id)
    {
        super(id);
    }

    public Leader(int id, String name, String lastName)
    {
        super(id, name, lastName);
    }

    //Getters && setters
    public List<Commission> getCommissions() 
    {
        return this.commissions;
    }

    public void setCommissions(List<Commission> commissions)
    {
        this.commissions = commissions;
    }

    public List<SubordinatedClient> getSubordinates()
    {
        return this.subordinates;
    }

    public void setSubordinates(List<SubordinatedClient> subordinates)
    {
        this.subordinates = subordinates;
    }

    @Override
    public String toString()
    {
        return super.toString() + " - LEADER";
    }

    @Override
    public PreferentialClientOperator operator()
    {
        return LeaderOperator.getOperator();
    }
}