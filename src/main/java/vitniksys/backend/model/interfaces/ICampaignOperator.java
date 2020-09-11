package vitniksys.backend.model.interfaces;

import java.time.Month;
import java.util.List;
import vitniksys.backend.model.entities.Campaign;

public interface ICampaignOperator extends CrudOperator<Campaign>
{
    Campaign find(int id) throws Exception;

    Campaign find(String alias) throws Exception;

    Campaign find(int month, int year) throws Exception;

    List<Campaign> findAll(Month month) throws Exception;

    List<Campaign> findAll(int year) throws Exception;

    List<Campaign> findByCatalogue(int code) throws Exception;

    Campaign findLast() throws Exception;

    int delete(int id) throws Exception;
}