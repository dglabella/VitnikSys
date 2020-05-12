package vitniksys.backend.model;

import java.util.List;
import vitniksys.backend.persistence.ClienteBaseOperator;
import vitniksys.backend.persistence.ClientePreferencialOperator;

public class ClienteBase extends ClientePreferencial
{
    //Entity properties


    //Domain Associations
    private List<EntregaCatalogo> entregasCatalogo;

    //Others


    public ClienteBase(int id, String name, String lastName)
    {
        super(id, name, lastName);
    }

    public List<EntregaCatalogo> getEntregasCatalogo()
    {
        return this.entregasCatalogo;
    }

    public void setEntregasCatalogo(List<EntregaCatalogo> entregasCatalogo)
    {
        this.entregasCatalogo = entregasCatalogo;
    }

    @Override
    public ClientePreferencialOperator operator()
    {
        return ClienteBaseOperator.getOperator();
    }
}