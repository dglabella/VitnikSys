package vitniksys.backend.controllers;

import java.io.File;
import java.time.Month;
import java.util.List;
import java.util.Iterator;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
//import java.util.concurrent.Executors;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.util.OrderObtainer;
import vitniksys.backend.util.UseCaseThreadExecutor;
import javafx.scene.control.Alert.AlertType;
//import java.util.concurrent.ExecutorService;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.backend.util.DetailFileInterpreter;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.frontend.views.CampQueryRegisterView;
import vitniksys.backend.model.persistence.OrderOperator;
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

    // ================================= protected methods =================================

    // ================================= public methods =================================
    public void searchCamp(String campNumb, String campAlias, Month month, Integer year, String catalogueCode) throws Exception
    {
        //Succes on normal execution flow
        CustomAlert customAlert =  new CustomAlert();
        try
        {
            Campaign camp = CampaignOperator.getOperator().find(campNumb);
            if(camp != null)
            {
                OrderOperator.getOperator();
                this.campQueryRegisterView.showQueriedCamp(camp);
            }
            else
            {
                this.campQueryRegisterView.showNoResult();
            }
        }
        catch (Exception exception)
        {
            customAlert.setAlertType(AlertType.ERROR);
            customAlert.setTitle(CustomAlert.DEFAULT_ERROR_TITLE);
            customAlert.setHeaderText(CustomAlert.DEFAULT_ERROR_HEADER);
            customAlert.setException(exception);
        }
        finally
        {
            Connector.getConnector().closeConnection();
            customAlert.customShow();
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
                this.campQueryRegisterView.showNoResult();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }

    public void obtainOrders(OrderObtainer orderObtainer) throws Exception
    {
        //Executor service creates threads that are not JavaFX threads
        //and thread that are not javaFX threads cannot change the GUI
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //executorService.execute(pedidosObtainer);
        //executorService.submit(pedidosObtainer);
        Platform.runLater(orderObtainer);
    }

    public void registerCamp(String campNumb, String campAlias, Integer month, Integer year, String catalogueCode, File detail)
    {
        CustomAlert customAlert = new CustomAlert(AlertType.NONE, CustomAlert.DEFAULT_WORKING_ON_TITLE,
                                                    CustomAlert.DEFAULT_WORKING_ON_HEADER);
        customAlert.customShow();

        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                //If all fields are OK...
                if(expressionChecker.onlyNumbers(campNumb, false) && campAlias.length() <= CampManagementController.MAX_LENGTH_CAMP_ALIAS 
                    && month != null && year != null && expressionChecker.isCatalogueCode(catalogueCode, true))
                {
                    //Succes on normal execution flow
                    Campaign camp = new Campaign(Integer.valueOf(campNumb), month, year);
                    camp.setAlias(campAlias);

                    try
                    {
                        Connector.getConnector().startTransaction();

                        //Campaing registration with catalogue associated
                        if(catalogueCode != null)
                        {
                            Catalogue catalogue = CatalogueOperator.getOperator().find(Integer.valueOf(catalogueCode));

                            if(catalogue != null)
                            {
                                camp.setCatalogue(catalogue);
                            }
                            else
                            {
                                customAlert.setAlertType(AlertType.ERROR);
                                customAlert.setTitle(CustomAlert.DEFAULT_ERROR_TITLE);
                                customAlert.setHeaderText("No existe el catálogo especificado.");
                                customAlert.setDescription("Si desea asociar el catálogo "+catalogueCode+
                                " a la campaña "+campNumb+" puede registrar primero el catálogo presionando "+
                                "el botón \"mas\" cercano al campo del catálogo.");
                                customAlert.setException(CustomAlert.irrelevantException());
                                throw CustomAlert.irrelevantException();
                            }
                        }

                        //Campaing registration with orders associated
                        if(detail != null)
                        {
                            OrderObtainer orderObtainer = new DetailFileInterpreter(detail);
                            Platform.runLater(orderObtainer);
                        }

                        CampaignOperator.getOperator().insert(camp);

                        Connector.getConnector().commit();
                    }
                    catch (Exception exception)
                    {
                        try
                        {
                            Connector.getConnector().rollBack();
                        }
                        catch (Exception exception2)
                        {
                            customAlert.setAlertType(AlertType.ERROR);
                            customAlert.setTitle(CustomAlert.DEFAULT_ERROR_TITLE);
                            customAlert.setHeaderText(CustomAlert.DEFAULT_ERROR_HEADER);
                            customAlert.setException(exception2);
                        }
                        customAlert.setAlertType(AlertType.ERROR);
                        customAlert.setTitle(CustomAlert.DEFAULT_ERROR_TITLE);
                        customAlert.setHeaderText(CustomAlert.DEFAULT_ERROR_HEADER);
                        customAlert.setException(exception);
                    }
                    finally
                    {
                        try
                        {
                            Connector.getConnector().endTransaction();
                            Connector.getConnector().closeConnection();    
                        }
                        catch (Exception exception2)
                        {
                            //TODO: handle exception
                        }
                        customAlert.customShow();
                    }
                }
                else
                {//Conflict with some fields.
                    customAlert.setAlertType(AlertType.ERROR);
                    customAlert.setTitle(CustomAlert.DEFAULT_ERROR_TITLE);
                    customAlert.setHeaderText("Deben completarse al menos los campos obligatorios para registrar"+
                                            "una campaña");
                    customAlert.customShow();
                } 
            }  
        });   
	}

    public void registerOrders(File detail) throws Exception
    {

        CustomAlert customAlert = new CustomAlert(AlertType.NONE, CustomAlert.DEFAULT_WORKING_ON_TITLE,
                                                CustomAlert.DEFAULT_WORKING_ON_HEADER);
        customAlert.customShow();

        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    OrderObtainer orderObtainer = new DetailFileInterpreter(detail);

                    Connector.getConnector().startTransaction();

                    PreferentialClient cp;
                    PreferentialClientOperator cpOperator;
                    Iterator<PreferentialClient> cpsIterator = cps.iterator();

                    while(cpsIterator.hasNext())
                    {
                        cp = cpsIterator.next();
                        cpOperator = cp.operator();
                        cpOperator.registerOrders(cp);
                    }
                    
                    Connector.getConnector().commit();
                    customAlert.customClose();
                    customAlert = new CustomAlert(AlertType.INFORMATION, CustomAlert.DEFAULT_SUCCES_TITLE,
                                                CustomAlert.DEFAULT_SUCCES_HEADER);
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack();
                    customAlert.customClose();

                    customAlert =  new CustomAlert(AlertType.ERROR, CustomAlert.DEFAULT_ERROR_TITLE, CustomAlert.DEFAULT_ERROR_HEADER, 
                                                CustomAlert.DEFAULT_DESCRIPTION, exception);
                    exception.printStackTrace();
                }
                finally
                {
                    Connector.getConnector().endTransaction();
                    Connector.getConnector().closeConnection();
                }
            }
        });
    }
}