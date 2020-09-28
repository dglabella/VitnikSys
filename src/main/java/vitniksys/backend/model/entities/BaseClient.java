package vitniksys.backend.model.entities;

import java.util.List;
import vitniksys.backend.model.persistence.BaseClientOperator;
import vitniksys.backend.model.persistence.PreferentialClientOperator;

public class BaseClient extends PreferentialClient
{
    //Entity properties

    //Domain Associations
    private List<Observation> observations;
    private List<CatalogueDeliver> catalogueDeliveries;

    //Others

    public BaseClient(int id)
    {
        super(id);
    }

    public BaseClient(int id, String name, String lastName)
    {
        super(id, name, lastName);
    }

    //Getters && Setters
    public List<Observation> getObservations()
    {
        return this.observations;
    }

    public void setObservations(List<Observation> observations)
    {
        this.observations = observations;
    }

    public List<CatalogueDeliver> getCatalogueDeliveries()
    {
        return this.catalogueDeliveries;
    }

    public void setCatalogueDeliveries(List<CatalogueDeliver> catalogueDeliveries)
    {
        this.catalogueDeliveries = catalogueDeliveries;
    }

    @Override
    public String toString()
    {
        return super.toString() + " - BASE CLIENT";
    }
    @Override
    public PreferentialClientOperator operator()
    {
        return BaseClientOperator.getOperator();
    }
}