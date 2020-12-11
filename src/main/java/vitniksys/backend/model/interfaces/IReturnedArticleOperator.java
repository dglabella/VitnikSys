package vitniksys.backend.model.interfaces;

import java.util.List;
import vitniksys.backend.model.entities.ReturnedArticle;

public interface IReturnedArticleOperator extends CrudOperator<ReturnedArticle>
{
    List<ReturnedArticle> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    ReturnedArticle find(int id) throws Exception;

    int delete(int id) throws Exception;
}