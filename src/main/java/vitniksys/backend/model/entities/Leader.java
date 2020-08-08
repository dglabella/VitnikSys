package vitniksys.backend.model.entities;

import java.util.List;
import java.util.ArrayList;
import vitniksys.backend.model.persistence.LeaderOperator;
import vitniksys.backend.model.persistence.PreferentialClientOperator;

public class Leader extends BaseClient
{
    //Entity properties
    

    //Domain Associations
    private Commission commission;
    private  List<SubordinatedClient> subordinates;

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

    public Commission getCommission()
    {
        return this.commission;
    }

    public void setCommission(Commission commission)
    {
        this.commission = commission;
    }

    public List<SubordinatedClient> getSubordinados()
    {
        if(this.subordinates == null)
            this.subordinates = new ArrayList<>();
            
        return this.subordinates;
    }

    public void setSubordinados(List<SubordinatedClient> subordinates)
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