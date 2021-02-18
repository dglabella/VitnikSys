package vitniksys.backend.model.persistence;

import java.util.List;
import java.util.Iterator;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.interfaces.IArticleOperator;

public class ArticleOperator implements IArticleOperator
{
    private static ArticleOperator operartor;
    private boolean activeRow;

    protected ArticleOperator()
    {
        this.activeRow = true;
    }

    public static ArticleOperator getOperator()
    {
        if(ArticleOperator.operartor == null)
            ArticleOperator.operartor = new ArticleOperator();

        return ArticleOperator.operartor;
    }

    public boolean isActiveRow()
    {
        return this.activeRow;
    }

    public ArticleOperator setActiveRow(boolean activeRow)
    {
        this.activeRow = activeRow;
        return ArticleOperator.operartor;
    }

    @Override
    public Integer insert(Article article) throws Exception
    {
        Integer returnCode = null;
        String sqlStmnt =
        "INSERT INTO `articulos`(`letra`, `nombre`, `tipo`, `precio_unitario`) VALUES "+
        "(?, ?, ?, ?) ON DUPLICATE KEY UPDATE `nombre` = ?,`tipo` = ?,`precio_unitario` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setString(1, article.getId());
        statement.setString(2, article.getName());
        statement.setInt(3, article.getType().ordinal());
        statement.setFloat(4, article.getUnitPrice());
        statement.setString(5, article.getName());
        statement.setInt(6, article.getType().ordinal());
        statement.setFloat(7, article.getUnitPrice());

        returnCode = statement.executeUpdate();
        statement.close();
  
        return returnCode;
    }

    @Override
    public Integer insertMany(List<Article> list) throws Exception
    {
        Integer returnCode = 0;
        String sqlStmnt =
        "INSERT INTO `articulos`(`letra`, `nombre`, `tipo`, `precio_unitario`) VALUES "+
        "(?, ?, ?, ?) ON DUPLICATE KEY UPDATE `nombre` = ?,`tipo` = ?,`precio_unitario` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        Article article;
        Iterator<Article> listIterator = list.iterator();

        while(listIterator.hasNext())
        {
            article = listIterator.next();

            statement.setString(1, article.getId());
            statement.setString(2, article.getName());
            statement.setInt(3, article.getType().ordinal());
            statement.setFloat(4, article.getUnitPrice());
            statement.setString(5, article.getName());
            statement.setInt(6, article.getType().ordinal());
            statement.setFloat(7, article.getUnitPrice());

            returnCode += statement.executeUpdate();
        }

        statement.close();

        return returnCode;
    }

    @Override
    public Integer update(Article article) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Article> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Article find(String id) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer delete(String id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }    
}