package vitniksys.backend.model.services;

import java.io.File;
import java.util.List;
import java.time.Month;
import java.util.Iterator;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import org.apache.commons.io.FilenameUtils;
import vitniksys.backend.util.OrderObtainer;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.backend.util.DetailFileInterpreter;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.persistence.CampaignOperator;
import vitniksys.backend.model.persistence.CatalogueOperator;
import vitniksys.frontend.views_subscriber.CampaignServiceSubscriber;
import vitniksys.backend.model.persistence.PreferentialClientOperator;

public class CampaignService extends Service<Campaign>
{
    public static final int MAX_LENGTH_CAMP_ALIAS = 60;

    private ExpressionChecker expressionChecker;
    
    public CampaignService(CampaignServiceSubscriber campQueryRegisterView)
    {
        super(campQueryRegisterView);
        
        //this.campQueryRegisterView = campQueryRegisterView;
    }

    //Getters && Setters

    // ================================= private methods =================================
    private boolean allFieldsAreOk(Integer campNumb, String campAlias, Integer month, Integer year, String catalogueCode, File detail)
    {
        boolean ret = false;

        if(campNumb != null && campAlias.length() <= CampaignService.MAX_LENGTH_CAMP_ALIAS 
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
    public void searchCamps(String campNumb, String campAlias, Month month, Integer year, String catalogueCode) throws Exception
    {
        CustomAlert customAlert = this.serviceSubscriber.showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        
        Task<Void> task = new Task<>()
        {
            @Override
            protected Void call() throws Exception
            {
                int retCode = 0;
                Campaign camp = null;
                List<Campaign> camps = null;

                if(campNumb != null && !campNumb.isBlank())
                {
                    camp = CampaignOperator.getOperator().find(Integer.parseInt(campNumb));
                }
                else if(catalogueCode != null && !catalogueCode.isBlank())
                {
                    camps = CampaignOperator.getOperator().findByCatalogue(Integer.parseInt(catalogueCode));
                }
                else if(month != null && year != null)
                {
                    camp = CampaignOperator.getOperator().find(month.getValue(), year);
                }
                else if(campAlias != null && !campAlias.isBlank())
                {
                    camps = CampaignOperator.getOperator().findAll(campAlias.toUpperCase());
                }
                else
                {
                    camps = CampaignOperator.getOperator().findAll();
                }

                try
                {
                    serviceSubscriber.closeProcessIsWorking(customAlert);
                    if(camp != null)
                    {
                        retCode = 1;
                        ((CampaignServiceSubscriber)serviceSubscriber).showQueriedCamp(camp);
                    }
                    else if(camps != null)
                    {
                        retCode = 1;
                        serviceSubscriber.showQueriedCamp(camps);
                    }
                    else
                    {
                        serviceSubscriber.showNoResult("No se encontró la campaña especificada");
                    }
                }
                catch (Exception exception)
                {
                    retCode = 0;
                    serviceSubscriber.closeProcessIsWorking(customAlert);
                    serviceSubscriber.showError("Error al buscar la campaña especificada.", null, exception);
                    throw exception;
                }
                finally
                {
                    Connector.getConnector().closeConnection();
                }
                return null;
            }
        };
        
        Platform.runLater(task);
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
                this.serviceSubscriber.showQueriedCamp(camp);
            }
            else
            {
                this.serviceSubscriber.showNoResult("No se encontró la última campaña");
            }
        }
        catch (Exception exception)
        {
            this.serviceSubscriber.showError("Error al buscar la última campaña.", null, exception); 
            throw exception;
        }
        finally
        {
            Connector.getConnector().closeConnection();
        } 
    }

    public void registerCamp(Integer campNumb, String campAlias, Integer month, Integer year, String catalogueCode, File detail) throws Exception
    {
        //If all fields are OK...
        if(allFieldsAreOk(campNumb, campAlias, month, year, catalogueCode, detail))
        {
            CustomAlert customAlert = this.serviceSubscriber.showProcessIsWorking("Espere un momento mientras se realiza el proceso.");

            Task<Integer> task = new Task<>()
            {
                @Override
                protected Integer call() throws Exception
                {
                    //returnCode is intended for future implementations
                    int returnCode = 0;
                    Campaign camp = new Campaign(campNumb, month, year);
                    camp.setAlias(campAlias.isBlank()?null:campAlias.toUpperCase());

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
                                serviceSubscriber.closeProcessIsWorking(customAlert);
                                serviceSubscriber.showError("No existe el catálogo especificado. Si desea"+
                                " asociar el catálogo "+catalogueCode+" a la campaña "+campNumb+" puede registrar "+
                                "primero el catálogo presionando el botón \"mas\" cercano al campo del catálogo.");

                                throw new Exception("Irrelevant exception");
                            }
                        }
                        
                        returnCode += CampaignOperator.getOperator().insert(camp); // Balances insertion handler by a DB Trigger.
                        
                        //Campaing registration with orders associated
                        if(detail != null)
                        {
                            returnCode += registerIncomingOrders(detail);
                        }

                        Connector.getConnector().commit();
                        serviceSubscriber.closeProcessIsWorking(customAlert);
                        serviceSubscriber.showSucces("La campaña se ha registrado exitosamente!");
                    }
                    catch (Exception exception)
                    {
                        Connector.getConnector().rollBack();
                        returnCode = 0;
                        serviceSubscriber.closeProcessIsWorking(customAlert);
                        serviceSubscriber.showError("Error al intentar registrar la campaña", null, exception);
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
            this.serviceSubscriber.showError("Los campos deben completarse correctamente.");
        }
    }

    public void registerOrders(File detail) throws Exception
    {
        CustomAlert customAlert = this.serviceSubscriber.showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        try
        {
            Connector.getConnector().startTransaction();

            this.registerIncomingOrders(detail);

            Connector.getConnector().commit();

            this.serviceSubscriber.closeProcessIsWorking(customAlert);
            this.serviceSubscriber.showSucces("Las ordenes se agregaron exitosamente!");
        }
        catch (Exception exception)
        {
            Connector.getConnector().rollBack();
            this.serviceSubscriber.closeProcessIsWorking(customAlert);
            this.serviceSubscriber.showError("Error al intentar registrar los pedidos.", null, exception);
            throw exception;
        }
        finally
        {
            Connector.getConnector().endTransaction();
            Connector.getConnector().closeConnection();
        }
    }

    @Override
    protected Campaign search(Campaign entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Campaign insert(Campaign entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Campaign update(Campaign entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Campaign delete(Campaign entity) {
        // TODO Auto-generated method stub
        return null;
    }
}