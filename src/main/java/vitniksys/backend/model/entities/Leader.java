package vitniksys.backend.model.entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import vitniksys.backend.util.VitnikSearchableList;
import vitniksys.backend.model.persistence.LeaderOperator;
import vitniksys.backend.model.persistence.PreferentialClientOperator;

public class Leader extends BaseClient
{
    //Entity properties
    

    //Domain Associations
    private VitnikSearchableList<Commission> commissions;
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
    public VitnikSearchableList<Commission> getCommissions() 
    {
        return this.commissions;
    }

    public void setCommissions(VitnikSearchableList<Commission> commissions)
    {
        this.commissions = commissions;
    }

    public List<SubordinatedClient> getSubordinates()
    {
        if(this.subordinates == null)
            this.subordinates = new ArrayList<>();
            
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