package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.Article;

public interface IArticleOperator extends CrudOperator<Article>
{
    Article find(String id) throws Exception;

    int delete(String id) throws Exception;
}
