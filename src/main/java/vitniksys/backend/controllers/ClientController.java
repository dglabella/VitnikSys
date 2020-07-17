package vitniksys.backend.controllers;

import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.util.ExceptionAlert;
import vitniksys.backend.persistence.Connector;
import vitniksys.backend.model.ClientePreferencial;

public class ClientController
{
    public int registerClient(ClientePreferencial cp) throws Exception
    {
        int returnCode = 0;
        try
        {
            Connector.getConnector().startTransaction();

            cp.operator().insert(cp);

            Connector.getConnector().commit();
        }
        catch (Exception exception)
        {
            Connector.getConnector().rollBack();
            ExceptionAlert exceptionAlert = new ExceptionAlert(AlertType.ERROR, exception);
            exceptionAlert.showAndWait();
            returnCode = -1;
        }
        finally
        {
            Connector.getConnector().endTransaction();
            Connector.getConnector().closeConnection();
        }
        return returnCode;
    }
}