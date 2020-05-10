package vitniksys.backend.model;

import java.util.List;
import vitniksys.backend.persistence.LiderOperator;
import vitniksys.backend.persistence.ClientePreferencialOperator;

public class Lider extends ClientePreferencial
{
    //Entity properties
    

    //Domain Associations
    private Comision comision;
    private List<EntregaDeCatalogo> entregasDeCatalogo;
    private  List<ClientePreferencialSubordinado> subordinados;

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

    public List<EntregaDeCatalogo> getEntregasDeCatalogos()
    {
        return this.entregasDeCatalogo;
    }

    public void setEntregaDeCatalogos(List<EntregaDeCatalogo> entregasDeCatalogo)
    {
        this.entregasDeCatalogo = entregasDeCatalogo;
    }

    public List<ClientePreferencialSubordinado> getSubordinados()
    {
        return this.subordinados;
    }

    public void setSubordinados(List<ClientePreferencialSubordinado> subordinados)
    {
        this.subordinados = subordinados;
    }

    @Override
    public ClientePreferencialOperator operator()
    {
        return LiderOperator.getOperator();
    }
}