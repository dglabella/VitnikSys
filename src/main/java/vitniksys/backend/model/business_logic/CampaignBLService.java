package vitniksys.backend.model.business_logic;

import java.io.File;
import vitniksys.App;
import java.util.Set;
import java.util.List;
import java.time.Month;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.util.DetailFileInterpreter;
import org.apache.commons.io.FilenameUtils;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.interfaces.IOrderOperator;
import vitniksys.backend.model.persistence.OrderOperator;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.interfaces.IArticleOperator;
import vitniksys.backend.model.interfaces.IBalanceOperator;
import vitniksys.backend.model.persistence.ArticleOperator;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.interfaces.ICampaignOperator;
import vitniksys.backend.model.persistence.CampaignOperator;
import vitniksys.backend.model.persistence.CatalogueOperator;
import vitniksys.frontend.view_subscribers.CampaignBLServiceSubscriber;

public class CampaignBLService extends BLService
{
    public static final int MAX_LENGTH_CAMP_ALIAS = 60;
    public static final String SEPARATOR = "-";

    private static final int CAMP_MONTH = 0;
    private static final int CAMP_YEAR = 1;
    private static final int CAMP_ALIAS = 2;
    private static final int CAMP_NUMB = 3;

    public static Campaign parseCamp(String campAsString)
    {
        Campaign ret = null;
        String [] splitedString = campAsString.split(CampaignBLService.SEPARATOR);

        ret = new Campaign(Integer.parseInt(splitedString[CAMP_NUMB]), Month.valueOf(splitedString[CAMP_MONTH]).getValue(), Integer.parseInt(splitedString[CAMP_YEAR]));
        ret.setAlias(splitedString[CAMP_ALIAS]);

        return ret;
    }
    
    //Getters && Setters
    // ================================= private methods =================================
    private boolean allFieldsAreOk(Integer campNumb, Integer prefClientId)
    {
        boolean ret = false;

        if(campNumb != null && prefClientId != null)
        {
            ret = true;
        }
        return ret;
    }

    private boolean allFieldsAreOk(Integer campNumb, String campAlias, Integer month, Integer year, String catalogueCode, File detail)
    {
        boolean ret = false;

        if(campNumb != null && campAlias.length() <= CampaignBLService.MAX_LENGTH_CAMP_ALIAS && !campAlias.contains(CampaignBLService.SEPARATOR)
            && month != null && year != null && ExpressionChecker.getExpressionChecker().isCatalogueCode(catalogueCode, true) && detailFileIsOk(detail, true))
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
            else if(FilenameUtils.getExtension(detail.getName()).equalsIgnoreCase(App.ConstraitConstants.DETAIL_FILE_EXTENSION))
            {
                ret = true; 
            }
        }
        else
        {
            if (detail != null && FilenameUtils.getExtension(detail.getName()).equalsIgnoreCase(App.ConstraitConstants.DETAIL_FILE_EXTENSION))
                ret = true;
        }
            
        return ret;
    }

    private List<List<Order>> organizeOrdersByCamps(List<Order> orders)
    {
        List<List<Order>> ret = new ArrayList<>();
        HashMap<Integer,List<Order>> hashMap = new HashMap<>();
        Iterator<Order> orderIterator = orders.iterator();
        
        Order order;
        List<Order> ordersBySomeCamp;
        while(orderIterator.hasNext())
        {
            order = orderIterator.next();
            ordersBySomeCamp = hashMap.get(order.getCampNumber());

            if(ordersBySomeCamp == null)
            {
                hashMap.put(order.getCampNumber(), new ArrayList<Order>());
                hashMap.get(order.getCampNumber()).add(order);
            }
            else
            {
                ordersBySomeCamp.add(order);
            }
        }
        
        Set<Integer> keySet = hashMap.keySet();
        Iterator<Integer> keySetsIterator = keySet.iterator();

        while(keySetsIterator.hasNext())
        {
            ret.add(hashMap.get(keySetsIterator.next()));
        }

        return ret;
    }

    private Integer registerOrders(PreferentialClient prefClient) throws Exception
    {
        Integer returnCode = 0;

        List<Article> articles = new ArrayList<>();
        Iterator<Order> incomingOrdersIterator = prefClient.getIncomingOrders().iterator();

        while(incomingOrdersIterator.hasNext())
            articles.add(incomingOrdersIterator.next().getArticle());


        List<Order> orders = new ArrayList<>();
        incomingOrdersIterator = prefClient.getIncomingOrders().iterator();

        while(incomingOrdersIterator.hasNext())
            orders.add(incomingOrdersIterator.next());

        
        IArticleOperator articleOperator = ArticleOperator.getOperator();
        IOrderOperator orderOperator = OrderOperator.getOperator();
        IBalanceOperator balanceOperator = BalanceOperator.getOperator();
        
        // INSERT ARTICLES
        articleOperator.insertMany(articles);

        //Group orders by campaign number
        List<List<Order>> ordersByCamp = organizeOrdersByCamps(orders);

        Iterator<List<Order>> ordersByCampIterator = ordersByCamp.iterator();
        Iterator<Order> ordersIterator;

        while(ordersByCampIterator.hasNext())
        {
            Balance balance = new Balance();
            //re-using variable
            orders = ordersByCampIterator.next();
            
            // if orders with that camp number already exist in DB then this orders are aggregated.
            if(orderOperator.existOrders(orders.get(0).getCampNumber()))
            {
                for(int i = 0; i < orders.size(); i++)
                {
                    orders.get(i).setAggregated(true);
                }
            }
            //INSERT ORDERS
            orderOperator.insertMany(orders);
            
            balance.setPrefClientId(prefClient.getId());
            //Any camp is ok, since all orders from this sublist are from the same campaign
            balance.setCampNumber(orders.get(0).getCampNumber());

            ordersIterator = orders.iterator();
            while(ordersIterator.hasNext())
                balance.setTotalInOrders(balance.getTotalInOrders()+ordersIterator.next().getCost());
            
            returnCode += balanceOperator.update(balance);

            //Load subordinated Pref client balances to a leader
            if(prefClient instanceof SubordinatedClient)
            {
                //Changing the id in order to add the subordinated client balance to his leader
                balance.setPrefClientId(((SubordinatedClient)prefClient).getLeader().getId());
                BalanceOperator.getOperator().update(balance);
            }
        }

        return returnCode;
    }

    // ================================= protected methods =================================

    //USE CASE
    protected void registerIncomingOrders(List<PreferentialClient> prefClients) throws Exception
    {   
        PreferentialClient prefClient;
        Iterator<PreferentialClient> prefClientsIterator = prefClients.iterator();

        while(prefClientsIterator.hasNext())
        {
            prefClient = prefClientsIterator.next();
            this.registerOrders(prefClient);
        }   
    }

    /**
     * 
     * @return A list of camps with its respective balances
     */
    protected List<Campaign> findCampsWithBalances()
    {
        List<Campaign> ret = new ArrayList<>();

        ICampaignOperator campOperator = CampaignOperator.getOperator();
        IBalanceOperator balanceOperator = BalanceOperator.getOperator();
        Iterator<Campaign> campIterator;
        Campaign camp = null;
        try
        {
            campIterator = campOperator.findAll().iterator();

            while(campIterator.hasNext())
            {
                camp = campIterator.next();
                camp.setBalances(balanceOperator.findAll(null, camp.getNumber()));
                ret.add(camp);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    // ================================= public methods ====================================
    public void searchCampsWithBalances()
    {
        CustomAlert customAlert = this.getBLServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");

        List<Campaign> camps = findCampsWithBalances();

        this.getBLServiceSubscriber().closeProcessIsWorking(customAlert);

        try
        {
            if(camps != null)
            {
                ((CampaignBLServiceSubscriber)getBLServiceSubscriber()).showQueriedCamps(camps);
            }
            else
            {
                this.getBLServiceSubscriber().showNoResult("No se encontraron campañas...");
            }   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            this.getBLServiceSubscriber().showError("Error al intentar obtener las campañas", null, exception);
        }
    }

    public void searchCamps(String campNumb, String campAlias, Month month, Integer year, String catalogueCode)
    {
        CustomAlert customAlert = this.getBLServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");

        Campaign camp = null;
        List<Campaign> camps = null;
        try
        {
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

            this.getBLServiceSubscriber().closeProcessIsWorking(customAlert);
            if(camp != null)
            {   
                ((CampaignBLServiceSubscriber)getBLServiceSubscriber()).showQueriedCamp(camp);
            }
            else if(camps != null)
            {
                ((CampaignBLServiceSubscriber)getBLServiceSubscriber()).showQueriedCamps(camps);
            }
            else
            {
                getBLServiceSubscriber().showNoResult("No se encontró la campaña especificada");
            }
        }
        catch (Exception exception)
        {
            getBLServiceSubscriber().closeProcessIsWorking(customAlert);
            getBLServiceSubscriber().showError("Error al buscar la campaña especificada.", null, exception);
        }
        finally
        {
            Connector.getInstance().closeConnection();
        }
    }

    /**
     * Search for the last registered campaing.
     * This method do not show alerts messages.
     * @throws Exception connection error
     */
    public void searchLastCamp()
    {
        try
        {
            Campaign camp = CampaignOperator.getOperator().findLast();
            if(camp != null)
            {
                ((CampaignBLServiceSubscriber)this.getBLServiceSubscriber()).showQueriedCamp(camp);
            }
            else
            {
                this.getBLServiceSubscriber().showNoResult("No se encontró la última campaña");
            }
        }
        catch (Exception exception)
        {
            this.getBLServiceSubscriber().showError("Error al buscar la última campaña.", null, exception);
        }
        finally
        {
            Connector.getInstance().closeConnection();
        }
    }

    public void registerCamp(Integer campNumb, String campAlias, Integer month, Integer year, String catalogueCode, File detail)
    {
        //If all fields are OK...
        if(allFieldsAreOk(campNumb, campAlias, month, year, catalogueCode, detail))
        {
            CustomAlert customAlert = this.getBLServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
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
                        Connector.getInstance().startTransaction();

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
                                getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                                getBLServiceSubscriber().showError("No existe el catálogo especificado. Si desea"+
                                " asociar el catálogo "+catalogueCode+" a la campaña "+campNumb+" puede registrar "+
                                "primero el catálogo presionando el botón \"mas\" cercano al campo del catálogo.");

                                throw new Exception("Irrelevant exception");
                            }
                        }
                        
                        returnCode += CampaignOperator.getOperator().insert(camp); // Balances insertion handler by a DB Trigger.
                        
                        //Campaing registration with orders associated
                        if(detail != null)
                        {
                            DetailFileInterpreter detailFileInterpreter = new DetailFileInterpreter(detail);
                            detailFileInterpreter.interpret();
                            registerIncomingOrders(detailFileInterpreter.getOrderMakers());
                        }

                        Connector.getInstance().commit();
                        getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                        getBLServiceSubscriber().showSucces("La campaña se ha registrado exitosamente!");
                    }
                    catch (Exception exception)
                    {
                        Connector.getInstance().rollBack();
                        returnCode = 0;
                        getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                        getBLServiceSubscriber().showError("Error al intentar registrar la campaña", null, exception);
                        throw exception;
                    }
                    finally
                    {
                        Connector.getInstance().endTransaction();
                        Connector.getInstance().closeConnection();
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

    public void registerOrders(File detail)
    {
        if(detail != null)
        {
            CustomAlert customAlert = this.getBLServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
            Task<Void> task = new Task<>()
            {
                DetailFileInterpreter detailFileInterpreter;
                @Override
                protected Void call() throws Exception
                {
                    try
                    {
                        Connector.getInstance().startTransaction();

                        detailFileInterpreter = new DetailFileInterpreter(detail);
                        detailFileInterpreter.interpret();
                        registerIncomingOrders(detailFileInterpreter.getOrderMakers());
                        
                        detailFileInterpreter.getOrderMakers();

                        Connector.getInstance().commit();

                        getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                        getBLServiceSubscriber().showSucces("Los pedidos se registraron exitosamente!");
                    }
                    catch (Exception exception)
                    {
                        Connector.getInstance().rollBack();
                        getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                        getBLServiceSubscriber().showError("Error al intentar registrar los pedidos.", null, exception);
                        throw exception;
                    }
                    finally
                    {
                        Connector.getInstance().endTransaction();
                        Connector.getInstance().closeConnection();
                    }
                    return null;
                }
            };
            Platform.runLater(task);
        }
        else
        {
            this.getBLServiceSubscriber().showError("No hay un archivo de detalle cargado.");
        }
    }
}