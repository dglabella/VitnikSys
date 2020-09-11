package vitniksys.backend.controllers;

import vitniksys.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;
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
        CustomAlert customAlert = new CustomAlert(AlertType.INFORMATION, CustomAlert.DEFAULT_SUCCES_TITLE,
                                                CustomAlert.DEFAULT_SUCCES_HEADER);
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

            customAlert.setAlertType(AlertType.ERROR);
            customAlert.setTitle(CustomAlert.DEFAULT_ERROR_TITLE);
            customAlert.setHeaderText(CustomAlert.DEFAULT_ERROR_HEADER);
            customAlert.setException(exception);
        }
        finally
        {
            Connector.getConnector().endTransaction();
            Connector.getConnector().closeConnection();
            customAlert.customShow();
        }
    }
}