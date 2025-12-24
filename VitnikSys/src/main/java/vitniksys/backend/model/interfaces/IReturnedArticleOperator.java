package vitniksys.backend.model.interfaces;

import java.util.List;

import vitniksys.backend.model.entities.ReturnedArticle;

public interface IReturnedArticleOperator extends CrudOperator<ReturnedArticle>
{
    List<ReturnedArticle> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    Integer updateAll(List<ReturnedArticle> returnedArticles) throws Exception;

    ReturnedArticle find(Integer id) throws Exception;

    Integer delete(Integer id) throws Exception;
}