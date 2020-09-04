package vitniksys.backend.controllers;

import vitniksys.backend.util.OperationResult;
import vitniksys.frontend.views.OperationResultView;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.persistence.CampaignOperator;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.PreferentialClient;

public class ClientManagementController
{
    //Views
    private OperationResultView operationResultView;

    //Getters && Setters
    public OperationResultView getOperationResultView()
    {
        return this.operationResultView;
    }

    public void setOperationResultView(OperationResultView operationResultView)
    {
        this.operationResultView = operationResultView;
    }

    // ================================= private methods =================================

    // ================================= protected methods =================================

    // ================================= public methods =================================
    public void registerClient(PreferentialClient cp) throws Exception
    {
        OperationResult operationResult = new OperationResult();
        try
        {
            Connector.getConnector().startTransaction();

            cp.operator().insert(cp);

            Balance balance = new Balance();
            balance.setClient(cp);
            balance.setCamp(CampaignOperator.getOperator().findLast());
            BalanceOperator.getOperator().insert(balance);

            Connector.getConnector().commit();

            operationResult.setCode(OperationResult.SUCCES);
        }
        catch (Exception exception)
        {
            Connector.getConnector().rollBack();
            
            operationResult.setCode(OperationResult.ERROR);
            operationResult.setException(exception);
        }
        finally
        {
            Connector.getConnector().endTransaction();
            Connector.getConnector().closeConnection();
            this.operationResultView.showResult(operationResult);
        }
    }
}