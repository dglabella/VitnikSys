package vitniksys.backend.model.services;

import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.CatalogueOperator;

public class CatalogueService extends Service
{
    public static final int MAX_LENGTH_LINK = 500;

    private boolean allFieldsAreOk(String code, Integer initialStock, String price, String link)
    {
        boolean ret = false;

        if((code != null && this.getExpressionChecker().isCatalogueCode(code, false)) &&
            initialStock != null && (price != null && this.getExpressionChecker().moneyValue(price, 2, 2, false)) && 
            (link != null && link.length() <= CatalogueService.MAX_LENGTH_LINK))
        {
            ret =  true;
        }

        return ret;
    }

    public void registerCatalogue(String code, Integer initialStock, String price, String link) throws Exception
    {
        //If all fields are OK...
        if(allFieldsAreOk(code, initialStock, price, link))
        {
            CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");

            Catalogue catalogue = new Catalogue(Integer.valueOf(code), initialStock, Float.valueOf(price));
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

                        CatalogueOperator.getOperator().insert(catalogue);

                        Connector.getConnector().commit();
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showSucces("El catálogo se ha registrado exitosamente!");
                    }
                    catch (Exception exception)
                    {
                        Connector.getConnector().rollBack();
                        returnCode = 0;
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showError("Error al intentar registrar el catálogo", null, exception);
                        throw exception;
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
        else
        {
            //Conflict with some fields.
            this.getServiceSubscriber().showError("Los campos deben completarse correctamente.");
        }
    }
}