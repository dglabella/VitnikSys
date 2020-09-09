package vitniksys.backend.controllers;

import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.util.OperationResult;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.persistence.CampaignOperator;

public class ClientManagementController
{
    //Views

    //Getters && Setters

    // ================================= private methods =================================

    // ================================= protected methods =================================

    // ================================= public methods =================================
    public void registerClient(PreferentialClient cp) throws Exception
    {
        OperationResult operationResult = new OperationResult();
        operationResult.setCode(OperationResult.SUCCES);
        operationResult.setShortMessage(OperationResult.DEFAULT_SUCCES_MESSAGE);
        try
        {
            Connector.getConnector().startTransaction();

            cp.operator().insert(cp);

            Balance balance = new Balance();
            balance.setClient(cp);
            balance.setCamp(CampaignOperator.getOperator().findLast());
            BalanceOperator.getOperator().insert(balance);

            Connector.getConnector().commit();
        }
        catch (Exception exception)
        {
            Connector.getConnector().rollBack();
            
            operationResult.setCode(OperationResult.ERROR);
            operationResult.setShortMessage(OperationResult.DEFAULT_ERROR_MESSAGE);
            operationResult.setException(exception);
        }
        finally
        {
            Connector.getConnector().endTransaction();
            Connector.getConnector().closeConnection();
            new CustomAlert().customShow(operationResult);
        }
    }
}