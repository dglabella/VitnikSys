package vitniksys.backend.model;

import java.util.List;
import vitniksys.backend.persistence.LiderOperator;
import vitniksys.backend.persistence.ClientePreferencialOperator;

public class Lider extends ClientePreferencial
{
    //Entity properties
    

    //Domain Associations
    private Comision comision;
    private List<EntregaCatalogo> entregasCatalogo;
    private  List<ClienteSubordinado> subordinados;

    //Others
    

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

    public List<EntregaCatalogo> getEntregasCatalogo()
    {
        return this.entregasCatalogo;
    }

    public void setEntregasCatalogo(List<EntregaCatalogo> entregasCatalogo)
    {
        this.entregasCatalogo = entregasCatalogo;
    }

    public List<ClienteSubordinado> getSubordinados()
    {
        return this.subordinados;
    }

    public void setSubordinados(List<ClienteSubordinado> subordinados)
    {
        this.subordinados = subordinados;
    }

    @Override
    public ClientePreferencialOperator operator()
    {
        return LiderOperator.getOperator();
    }
}