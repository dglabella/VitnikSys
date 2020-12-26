package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.entities.ReturnedArticle;
import vitniksys.backend.model.interfaces.IReturnedArticleOperator;

public class ReturnedArticleOperator implements IReturnedArticleOperator
{
    private static ReturnedArticleOperator operator;
	private boolean activeRow;

	private ReturnedArticleOperator()
	{
		this.activeRow = true;
	}

	public static ReturnedArticleOperator getOperator()
	{
		if (ReturnedArticleOperator.operator == null)
        ReturnedArticleOperator.operator = new ReturnedArticleOperator();

		return ReturnedArticleOperator.operator;
	}

	/**
     * Get the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
     * @return The state of the entity.
     */
    public boolean isActiveRow()
    {
        return this.activeRow;
    }

    /**
     * Change the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
     * @param activeRow the value for the operation.
     */
    public ReturnedArticleOperator setActiveRow(boolean activeRow)
    {
		this.activeRow = activeRow;
		return ReturnedArticleOperator.operator;
    }

    @Override
    public int insert(ReturnedArticle entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertMany(List<ReturnedArticle> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(ReturnedArticle entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<ReturnedArticle> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ReturnedArticle> findAll(Integer prefClientId, Integer campNumb) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReturnedArticle find(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}