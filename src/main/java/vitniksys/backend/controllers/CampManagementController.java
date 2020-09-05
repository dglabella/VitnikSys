package vitniksys.backend.controllers;

import java.util.List;
import java.util.Iterator;
import javafx.application.Platform;
//import java.util.concurrent.Executors;
import vitniksys.backend.model.enums.Mes;
//import java.util.concurrent.ExecutorService;
import vitniksys.backend.util.OperationResult;
import vitniksys.backend.util.PedidosObtainer;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.frontend.views.OperationResultView;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.frontend.views.CampQueryRegisterView;
import vitniksys.backend.model.persistence.OrderOperator;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.persistence.CampaignOperator;
import vitniksys.backend.model.persistence.PreferentialClientOperator;

public class CampManagementController
{
    //Views
    private CampQueryRegisterView campQueryRegisterView;
    private OperationResultView operationResultView;

    public CampManagementController(CampQueryRegisterView campQueryRegisterView, OperationResultView operationResultView)
    {
        this.campQueryRegisterView = campQueryRegisterView;
        this.operationResultView = operationResultView;
    }

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
    public void searchCamp(int campNumb) throws Exception
    {
        OperationResult operationResult = new OperationResult();
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
            operationResult.setCode(OperationResult.ERROR);
            operationResult.setException(exception);
            this.operationResultView.showResult(operationResult);
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }

    public void searchCamp(String campAlias) throws Exception
    {
        OperationResult operationResult = new OperationResult();
        try
        {
            Campaign camp = CampaignOperator.getOperator().find(campAlias);
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
            operationResult.setCode(OperationResult.ERROR);
            operationResult.setException(exception);
            this.operationResultView.showResult(operationResult);
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }

    public void searchCamp(Mes month, int year) throws Exception
    {
        OperationResult operationResult = new OperationResult();
        try
        {
            Campaign camp = CampaignOperator.getOperator().find(month, year);
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
            operationResult.setCode(OperationResult.ERROR);
            operationResult.setException(exception);
            this.operationResultView.showResult(operationResult);
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }

    public void searchCamps(Mes month) throws Exception
    {
        OperationResult operationResult = new OperationResult();
        try
        {
            List<Campaign> camps = CampaignOperator.getOperator().findAll(month);
            if(camps != null)
            {
                OrderOperator.getOperator();
                this.campQueryRegisterView.showQueriedCamp(camps);
            }
            else
            {
                this.campQueryRegisterView.showNoResult();
            }
        }
        catch (Exception exception)
        {
            operationResult.setCode(OperationResult.ERROR);
            operationResult.setException(exception);
            this.operationResultView.showResult(operationResult);
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }

    public void searchCamps(int year) throws Exception
    {
        OperationResult operationResult = new OperationResult();
        try
        {
            List<Campaign> camps = CampaignOperator.getOperator().findAll(year);
            if(camps != null)
            {
                OrderOperator.getOperator();
                this.campQueryRegisterView.showQueriedCamp(camps);
            }
            else
            {
                this.campQueryRegisterView.showNoResult();
            }
        }
        catch (Exception exception)
        {
            operationResult.setCode(OperationResult.ERROR);
            operationResult.setException(exception);
            this.operationResultView.showResult(operationResult);
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }

    public void searchCampsByCatalogue(int code) throws Exception
    {
        OperationResult operationResult = new OperationResult();
        try
        {
            List<Campaign> camps = CampaignOperator.getOperator().findAll(code);
            if(camps != null)
            {
                OrderOperator.getOperator();
                this.campQueryRegisterView.showQueriedCamp(camps);
            }
            else
            {
                this.campQueryRegisterView.showNoResult();
            }
        }
        catch (Exception exception)
        {
            operationResult.setCode(OperationResult.ERROR);
            operationResult.setException(exception);
            this.operationResultView.showResult(operationResult);
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }

    public void searchLastCamp() throws Exception
    {
        OperationResult operationResult = new OperationResult();
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
            operationResult.setCode(OperationResult.SUCCES);
        }
        catch (Exception exception)
        {
            operationResult.setCode(OperationResult.ERROR);
            operationResult.setException(exception);
            this.operationResultView.showResult(operationResult);
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }

    public void obtainOrders(PedidosObtainer pedidosObtainer) throws Exception
    {
        //Executor service creates threads that are not JavaFX threads
        //and thread that are not javaFX threads cannot change the GUI
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //executorService.execute(pedidosObtainer);
        //executorService.submit(pedidosObtainer);
        Platform.runLater(pedidosObtainer);
    }

    public void registerOrders(List<PreferentialClient> cps) throws Exception
    {
        OperationResult operationResult = new OperationResult();
        try
        {
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

    public void registerCamp(int campNumb, String campAlias, Mes month, int year)
    {
        Campaign camp = new Campaign(campNumb, month, year);
        camp.setAlias(campAlias);
	}
}