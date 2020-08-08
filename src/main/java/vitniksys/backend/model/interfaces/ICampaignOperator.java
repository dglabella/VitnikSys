package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.Campaign;

public interface ICampaignOperator extends CrudOperator<Campaign>
{
    public Campaign find(int id) throws Exception;

    public Campaign findLast() throws Exception;

    public int delete(int id) throws Exception;
}