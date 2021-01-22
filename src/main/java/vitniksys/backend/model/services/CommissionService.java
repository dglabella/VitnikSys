package vitniksys.backend.model.services;

import java.util.List;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.CommissionOperator;

public class CommissionService extends Service
{

    public void createCommission(Integer prefClientId, Integer campNumb) throws Exception
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        Task<Integer> task = new Task<>()
        {
            @Override
            protected Integer call() throws Exception
            {
                //returnCode is intended for future implementations
                int returnCode = 0;

                try
                {
                    Connector.getConnector().startTransaction();
                    
                    //CommisionOperator.getOperator().insert(entity);

                    Connector.getConnector().commit();

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showSucces("Los niveles de comisión para el cliente preferencial " + prefClientId +
                        " en la campaña "+ campNumb +" se han registrado exitosamente!");
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack();
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al intentar registrar los niveles de comisión.", null, exception);
                }
                finally
                {
                    Connector.getConnector().endTransaction();
                    Connector.getConnector().closeConnection();
                }
                return returnCode;
            }
        };

        Platform.runLater(task);
	}
    
    public void updateCommission(List<Order> orders)
    {
        Task<Integer> task = new Task<>()
        {
            @Override
            protected Integer call() throws Exception
            {
                //returnCode is intended for future implementations
                int returnCode = 0;

                try
                {
                    Connector.getConnector().startTransaction();



                    Connector.getConnector().commit();
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                finally
                {
                    
                }

                return returnCode;
            }
        };

        Platform.runLater(task);
        //this.getExecutorService().execute(task);
    }
}