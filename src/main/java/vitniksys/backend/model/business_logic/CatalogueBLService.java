package vitniksys.backend.model.business_logic;

import vitniksys.App;
import java.util.List;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.CatalogueOperator;
import vitniksys.frontend.view_subscribers.CatalogueBLServiceSubscriber;

public class CatalogueBLService extends BLService
{
    private boolean allFieldsAreOk(String code) 
    {
        boolean ret = false;

        if( code != null && ExpressionChecker.getExpressionChecker().isCatalogueCode(code, false) )
        {
            ret =  true;
        }

        return ret;
    }

    private boolean allFieldsAreOk(String code, Integer initialStock, String price, String link)
    {
        boolean ret = false;

        if((code != null && ExpressionChecker.getExpressionChecker().isCatalogueCode(code, false)) &&
            initialStock != null && (price != null && 
            ExpressionChecker.getExpressionChecker().moneyValue(price, App.ConstraitConstants.MAX_LENGTH_MONEY_LEFT_DIGITS, App.ConstraitConstants.MAX_LENGTH_MONEY_RIGHT_DIGITS, false)) && 
            (link == null || link.length() <= App.ConstraitConstants.MAX_LENGTH_CATALOGUE_LINK))
        {
            ret =  true;
        }

        return ret;
    }

    public void registerCatalogue(String code, Integer initialStock, Integer actualStock, String price, String link) throws Exception
    {
        //If all fields are OK...
        if(allFieldsAreOk(code, initialStock, price, link))
        {
            CustomAlert customAlert = this.getBLServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");

            Catalogue catalogue = new Catalogue(Integer.valueOf(code), initialStock, Float.valueOf(price));
            catalogue.setActualStock(actualStock);
            catalogue.setLink(link);
            Task<Integer> task = new Task<>()
            {
                @Override
                protected Integer call() throws Exception
                {
                    //returnCode is intended for future implementations
                    int returnCode = 0;
                    try
                    {
                        getConnector().startTransaction();

                        CatalogueOperator.getOperator().insert(catalogue);

                        getConnector().commit();
                        getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                        getBLServiceSubscriber().showSucces("El catálogo se ha registrado/actualizado exitosamente!");

                        //Calling this method will update the auto-completiont tool
                        searchCatalogues();
                    }
                    catch (Exception exception)
                    {
                        getConnector().rollBack();
                        returnCode = 0;
                        getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                        getBLServiceSubscriber().showError("Error al intentar registrar el catálogo", null, exception);
                        throw exception;
                    }
                    finally
                    {
                        getConnector().endTransaction();
                        getConnector().closeConnection();
                    }
                    return returnCode;
                }
            };

            Platform.runLater(task);
        }
        else
        {
            //Conflict with some fields.
            this.getBLServiceSubscriber().showError("Los campos deben completarse correctamente.");
        }
    }

    public void searchCatalogue(String code)
    {
        if(allFieldsAreOk(code))
        {
            CustomAlert customAlert = this.getBLServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");

            Task<Integer> task = new Task<>()
            {
                @Override
                protected Integer call() throws Exception
                {
                    //returnCode is intended for future implementations
                    int returnCode = 0;
                    Catalogue catalogue = null;
                    try
                    {
                        catalogue = CatalogueOperator.getOperator().find(Integer.parseInt(code));
                        getBLServiceSubscriber().closeProcessIsWorking(customAlert);

                        if(catalogue != null)
                        {
                            ((CatalogueBLServiceSubscriber)getBLServiceSubscriber()).showQueriedCatalogue(catalogue);
                        }
                        else
                        {
                            getBLServiceSubscriber().showNoResult("No hay catálogos registrados.");
                        }
                    }
                    catch (Exception exception)
                    {
                        returnCode = 0;
                        getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                        getBLServiceSubscriber().showError("Error al buscar el catálogo", null, exception);
                        throw exception;
                    }
                    finally
                    {
                        getConnector().closeConnection();
                    }
                    return returnCode;
                }
            };

            Platform.runLater(task);
        }
        else
        {
            //Conflict with some fields.
            this.getBLServiceSubscriber().showError("Los campos deben completarse correctamente.");
        }

        
	}

    public void searchCatalogues()
    {
        //CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        Task<Integer> task = new Task<>()
        {
            @Override
            protected Integer call() throws Exception
            {
                //returnCode is intended for future implementations
                int returnCode = 0;
                List<Catalogue> catalogues = null;
                try
                {
                    catalogues = CatalogueOperator.getOperator().findAll();

                    if(catalogues != null)
                    {
                        ((CatalogueBLServiceSubscriber)getBLServiceSubscriber()).showQueriedCatalogues(catalogues);
                    }
                    else
                    {
                        getBLServiceSubscriber().showNoResult("No hay catálogos registrados.");
                    }
                    //getServiceSubscriber().closeProcessIsWorking(customAlert);
                    //getServiceSubscriber().showSucces("El catálogo se ha registrado exitosamente!");
                }
                catch (Exception exception)
                {
                    returnCode = 0;
                    //getServiceSubscriber().closeProcessIsWorking(customAlert);
                    //getServiceSubscriber().showError("Error al intentar registrar el catálogo", null, exception);
                    throw exception;
                }
                finally
                {
                    getConnector().closeConnection();
                }
                return returnCode;
            }
        };

        Platform.runLater(task);
        //ExecutorService executorService = Executors.newFixedThreadPool(1);
        //executorService.execute(task);
    }
}