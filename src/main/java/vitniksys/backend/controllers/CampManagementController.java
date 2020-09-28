package vitniksys.backend.controllers;

import java.io.File;
import java.util.List;
import java.time.Month;
import java.util.Iterator;
//import javafx.application.Platform;
//import java.util.concurrent.Executors;
import org.apache.commons.io.FilenameUtils;

import javafx.application.Platform;
import javafx.concurrent.Task;
import vitniksys.backend.util.OrderObtainer;
//import java.util.concurrent.ExecutorService;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.backend.util.DetailFileInterpreter;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.frontend.views.CampQueryRegisterView;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.persistence.CampaignOperator;
import vitniksys.backend.model.persistence.CatalogueOperator;
import vitniksys.backend.model.persistence.PreferentialClientOperator;

public class CampManagementController
{
    public static final int MAX_LENGTH_CAMP_ALIAS = 60;

    private ExpressionChecker expressionChecker;
    
    //Views
    private CampQueryRegisterView campQueryRegisterView;

    public CampManagementController(CampQueryRegisterView campQueryRegisterView)
    {
        this.expressionChecker = ExpressionChecker.getExpressionChecker();
        this.campQueryRegisterView = campQueryRegisterView;
    }

    //Getters && Setters

    // ================================= private methods =================================
    private boolean allFieldsAreOk(String campNumb, String campAlias, Integer month, Integer year, String catalogueCode, File detail)
    {
        boolean ret = false;
        if(this.expressionChecker.onlyNumbers(campNumb, false) && campAlias.length() <= CampManagementController.MAX_LENGTH_CAMP_ALIAS 
            && month != null && year != null && this.expressionChecker.isCatalogueCode(catalogueCode, true) && detailFileIsOk(detail, true))
        {
            ret = true;
        }
        return ret;
    }

    private boolean detailFileIsOk(File detail, boolean allowEmptyFile)
    {
        boolean ret = false;

        if(allowEmptyFile)
        {
            if(detail == null)
            {
                ret = true;
            }
            else if(FilenameUtils.getExtension(detail.getName()).equalsIgnoreCase(DetailFileInterpreter.FILE_EXTENSION))
            {
                ret = true; 
            }
        }
        else
        {
            if (detail != null && FilenameUtils.getExtension(detail.getName()).equalsIgnoreCase(DetailFileInterpreter.FILE_EXTENSION))
                ret = true;
        }
            
        return ret;
    }

    private Integer registerIncomingOrders(File detail) throws Exception
    {
        Integer returnCode = 0;
        
        OrderObtainer orderObtainer = new DetailFileInterpreter(detail);
        List<PreferentialClient> cps = orderObtainer.getOrderMakers();
        
        PreferentialClient prefClient;
        PreferentialClientOperator cpOperator;
        Iterator<PreferentialClient> cpsIterator = cps.iterator();

        while(cpsIterator.hasNext())
        {
            prefClient = cpsIterator.next();
            cpOperator = prefClient.operator();
            returnCode += cpOperator.registerOrders(prefClient);
        }

        return returnCode;
    }

    // ================================= protected methods =================================

    // ================================= public methods =================================
    public void searchCamp(String campNumb, String campAlias, Month month, Integer year, String catalogueCode) throws Exception
    {
        this.campQueryRegisterView.showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        try
        {
            Campaign camp = CampaignOperator.getOperator().find(campNumb);
            if(camp != null)
            {        
                this.campQueryRegisterView.closeProcessIsWorking();
                this.campQueryRegisterView.showQueriedCamp(camp);
            }
            else
            {
                this.campQueryRegisterView.closeProcessIsWorking();
                this.campQueryRegisterView.showNoResult("No se encontró la campaña especificada");
            }
        }
        catch (Exception exception)
        {
            this.campQueryRegisterView.closeProcessIsWorking();
            this.campQueryRegisterView.showError("Error al buscar la campaña especificada.", exception);
            throw exception;
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }

    /**
     * Search for the last registered campaing.
     * This method do not show alerts messages.
     * @throws Exception connection error
     */
    public void searchLastCamp() throws Exception
    {
        try
        {
            Campaign camp = CampaignOperator.getOperator().findLast();
            if(camp != null)
            {
                this.campQueryRegisterView.showQueriedCamp(camp);
            }
            else
            {
                this.campQueryRegisterView.showNoResult("No se encontró la última campaña");
            }
        }
        catch (Exception exception)
        {
            this.campQueryRegisterView.showError("Error al buscar la última campaña.", exception); 
            throw exception;
        }
        finally
        {
            Connector.getConnector().closeConnection();
        } 
    }

    public void registerCamp(String campNumb, String campAlias, Integer month, Integer year, String catalogueCode, File detail) throws Exception
    {
        //If all fields are OK...
        if(allFieldsAreOk(campNumb, campAlias, month, year, catalogueCode, detail))
        {
            this.campQueryRegisterView.showProcessIsWorking("Espere un momento mientras se realiza el proceso.");

            Task<Integer> task = new Task<>()
            {
                @Override
                protected Integer call() throws Exception
                {
                    //returnCode is intended for future implementations
                    int returnCode = 0;
                    Campaign camp = new Campaign(Integer.valueOf(campNumb), month, year);
                    camp.setAlias(campAlias.isBlank()?null:campAlias);

                    try
                    {
                        Connector.getConnector().startTransaction();

                        //Campaing registration with catalogue associated
                        if(catalogueCode != null && !catalogueCode.isEmpty())
                        {
                            Catalogue catalogue = CatalogueOperator.getOperator().find(Integer.valueOf(catalogueCode));

                            if(catalogue != null)
                            {
                                camp.setCatalogue(catalogue);
                            }
                            else
                            {
                                returnCode = 0;
                                campQueryRegisterView.closeProcessIsWorking();
                                campQueryRegisterView.showError("No existe el catálogo especificado. Si desea"+
                                " asociar el catálogo "+catalogueCode+" a la campaña "+campNumb+" puede registrar "+
                                "primero el catálogo presionando el botón \"mas\" cercano al campo del catálogo.");

                                throw new Exception("Irrelevant exception");
                            }
                        }

                        returnCode += CampaignOperator.getOperator().insert(camp);

                        //Campaing registration with orders associated
                        if(detail != null)
                        {
                            returnCode += registerIncomingOrders(detail);
                        }

                        Connector.getConnector().commit();
                        campQueryRegisterView.closeProcessIsWorking();
                        campQueryRegisterView.showSucces("La campaña se ha registrado exitosamente!");
                    }
                    catch (Exception exception)
                    {
                        Connector.getConnector().rollBack();
                        returnCode = 0;
                        campQueryRegisterView.closeProcessIsWorking();
                        campQueryRegisterView.showError("Error al intentar registrar la campaña", exception);
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
            this.campQueryRegisterView.showError("Los campos deben completarse correctamente.");
        }
    }

    public void registerOrders(File detail) throws Exception
    {
        this.campQueryRegisterView.showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        try
        {
            Connector.getConnector().startTransaction();

            this.registerIncomingOrders(detail);

            Connector.getConnector().commit();

            this.campQueryRegisterView.closeProcessIsWorking();
            this.campQueryRegisterView.showSucces("Las ordenes se agregaron exitosamente!");
        }
        catch (Exception exception)
        {
            Connector.getConnector().rollBack();
            this.campQueryRegisterView.closeProcessIsWorking();
            this.campQueryRegisterView.showError("Error al intentar registrar los pedidos.", exception);
            throw exception;
        }
        finally
        {
            Connector.getConnector().endTransaction();
            Connector.getConnector().closeConnection();
        }
    }
}