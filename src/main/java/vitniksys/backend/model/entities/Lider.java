package vitniksys.backend.model.entities;

import java.util.List;
import java.util.ArrayList;
import vitniksys.backend.model.persistence.LiderOperator;
import vitniksys.backend.model.persistence.ClientePreferencialOperator;

public class Lider extends ClienteBase
{
    //Entity properties
    

    //Domain Associations
    private Comision comision;
    private  List<ClienteSubordinado> subordinados;

    //Others
    
    //Constructors
    public Lider(int id)
    {
        super(id);
    }

    public Lider(int id, String name, String lastName)
    {
        super(id, name, lastName);
    }

    public Comision getComision()
    {
        return this.comision;
    }

    public void setComision(Comision comision)
    {
        this.comision = comision;
    }

    public List<ClienteSubordinado> getSubordinados()
    {
        if(this.subordinados == null)
            this.subordinados = new ArrayList<>();
            
        return this.subordinados;
    }

    public void setSubordinados(List<ClienteSubordinado> subordinados)
    {
        this.subordinados = subordinados;
    }

    @Override
    public String toString()
    {
        return super.toString() + " - LEADER";
    }

    @Override
    public ClientePreferencialOperator operator()
    {
        return LiderOperator.getOperator();
    }
}