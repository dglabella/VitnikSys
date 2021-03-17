package vitniksys.backend.model.services;

import java.io.File;
import vitniksys.App;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.time.LocalDate;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.model.enums.Bank;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.enums.PayItem;
import vitniksys.backend.model.enums.PayType;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.util.IFileInterpreter;
import vitniksys.backend.model.enums.PayStatus;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Payment;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.entities.Devolution;
import vitniksys.backend.model.entities.Observation;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.util.CpLoaderFileInterpreter;
import vitniksys.backend.model.entities.ReturnedArticle;
import vitniksys.backend.model.persistence.OrderOperator;
import vitniksys.backend.model.persistence.LeaderOperator;
import vitniksys.backend.model.persistence.PaymentOperator;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.persistence.CampaignOperator;
import vitniksys.backend.model.persistence.CommissionOperator;
import vitniksys.backend.model.persistence.RepurchaseOperator;
import vitniksys.backend.model.persistence.BaseClientOperator;
import vitniksys.backend.model.persistence.DevolutionOperator;
import vitniksys.backend.model.persistence.ObservationOperator;
import vitniksys.backend.model.persistence.ReturnedArticleOperator;
import vitniksys.backend.model.persistence.PreferentialClientOperator;
import vitniksys.backend.model.persistence.SubordinatedClientOperator;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;

public class PreferentialClientService extends Service
{
    //Getters && Setters
    

    // ================================= private methods =================================
    private boolean allFieldsAreOk(String id, String dni, String name, String lastName, 
        String email, String phoneNumber, String leaderId)
    {
        boolean ret = false;
        ExpressionChecker ec = ExpressionChecker.getExpressionChecker();
        if(ec.onlyNumbers(id, false) && ec.onlyNumbers(dni, true) && ec.composedName(name) && ec.composedName(lastName) && 
            ec.isEmail(email, true) && ec.onlyNumbers(phoneNumber, true) && ec.onlyNumbers(leaderId, true))
        {
            ret = true;
        }
        return ret;
    }

    private boolean allFieldsAreOk(Integer campNumber, String descriptor, Float amount)
    {
        boolean ret = false;

        if(campNumber != null && descriptor.length() <= App.ConstraitConstants.MAX_LENGTH_PAYMENT_DESCRIPTOR && amount != null)
        {
            ret = true; 
        }

        return ret;
    }
    // ================================= protected methods =================================
    protected void createObservation(Integer prefClientId, Integer campNumber, String obs) throws Exception
    {
        Observation observation = new Observation(obs);
        observation.setPrefClientId(prefClientId);
        observation.setCampNumber(campNumber);
        
        ObservationOperator.getOperator().insert(observation);
    }

    protected float calculateTotalBalance(Integer prefClientId) throws Exception
    {
        float ret = 0f;
        try
        {
            List<Balance> balances = BalanceOperator.getOperator().findAll(prefClientId, null);

            if(balances != null)
            {
                Iterator<Balance> it = balances.iterator();

                while(it.hasNext())
                {
                    ret += it.next().getBalance();
                }
            }
        }
        catch (Exception exception)
        {
            throw exception;
        }
        return ret;
    }

    public void registerPrefClients(List<PreferentialClient> preferentialClients) throws Exception
    {
        PreferentialClient prefClient = null;
        Iterator<PreferentialClient> prefClientsIterator = preferentialClients.iterator();
        while(prefClientsIterator.hasNext())
        {
            prefClient = prefClientsIterator.next();
            prefClient.operator().insert(prefClient); // INSERT

            Balance balance = new Balance();
        
            //balance associations
            Campaign camp = CampaignOperator.getOperator().findLast();
            if(camp == null)
            {
                throw new Exception("No campaign are registered");
            }

            balance.setClient(prefClient);
            balance.setCamp(camp);
            //balance fk id
            balance.setPrefClientId(prefClient.getId());
            balance.setCampNumber(camp.getNumber());

            BalanceOperator.getOperator().insert(balance); // INSERT
        }

        //return returnCode;
    }

    // ================================= public methods ====================================
    public void registerClient(String id, String dni, String name, String lastName, String location,
            LocalDate birthDate, String email, String phoneNumber, Boolean isLeader, String leaderId) throws Exception
    {
        if(allFieldsAreOk(id, dni, name, lastName, email, phoneNumber, leaderId))
        {
            CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
            Task<Integer> task = new Task<>()
            {
                @Override
                protected Integer call() throws Exception
                {
                    //returnCode is intended for future implementations
                    int returnCode = 0;
                    PreferentialClient prefClient;
                    try
                    {
                        Connector.getConnector().startTransaction(); //START TRANSACTION

                        if(isLeader)
                        {
                            prefClient = new Leader(Integer.parseInt(id), name.toUpperCase(), lastName.toUpperCase());
                        }
                        else if(leaderId != null && !leaderId.isBlank())
                        {
                            prefClient = new SubordinatedClient(Integer.parseInt(id), name.toUpperCase(), lastName.toUpperCase());
                            ((SubordinatedClient)prefClient).setLeader(new Leader(Integer.parseInt(leaderId)));
                        }
                        else
                        {
                            prefClient = new BaseClient(Integer.parseInt(id), name.toUpperCase(), lastName.toUpperCase());
                        }

                        prefClient.setDni(!dni.isBlank()?Long.parseLong(dni):null);
                        prefClient.setLocation(location != null ? location.toUpperCase() : null);
                        prefClient.setBirthDate(birthDate);
                        prefClient.setEmail(email);
                        prefClient.setPhoneNumber(!phoneNumber.isBlank()?Long.parseLong(phoneNumber):null);

                        returnCode += prefClient.operator().insert(prefClient); // INSERT

                        Balance balance = new Balance();

                        //balance associations
                        balance.setClient(prefClient);
                        balance.setCamp(CampaignOperator.getOperator().findLast());
                        //balance fk id
                        balance.setPrefClientId(prefClient.getId());
                        balance.setCampNumber(balance.getCampaign().getNumber());

                        returnCode += BalanceOperator.getOperator().insert(balance); // INSERT

                        Connector.getConnector().commit(); // COMMIT

                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showSucces("El cliente se ha registrado exitosamente!");
                    }
                    catch (Exception exception)
                    {
                        Connector.getConnector().rollBack();
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showError("Error al intentar registrar el cliente.", null, exception);
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

    public void registerPrefClientsAuto(File file)
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        IFileInterpreter cpLoader = new CpLoaderFileInterpreter(file, this);
        Task<Integer> task = new Task<>()
        {
            @Override
            protected Integer call() throws Exception
            {
                //returnCode is intended for future implementations
                int returnCode = 0;
                try
                {
                    Connector.getConnector().startTransaction(); //START TRANSACTION

                    // -- cpLoader.interpret() is doing the inserts into the DATA BASE
                    cpLoader.interpret();

                    Connector.getConnector().commit(); // COMMIT

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showSucces("Los clientes se han registrado exitosamente!");
                    getServiceSubscriber().refresh();
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack();
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al intentar registrar los clientes.", null, exception);
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

    public void updateClient(String id, String dni, String name, String lastName, String location,
            LocalDate birthDate, String email, String phoneNumber, Boolean isLeader, String leaderId) throws Exception
    {
        if(allFieldsAreOk(id, dni, name, lastName, email, phoneNumber, leaderId))
        {
            CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Actualizando Datos...");
            Task<Integer> task = new Task<>()
            {
                @Override
                protected Integer call() throws Exception
                {
                    //returnCode is intended for future implementations
                    int returnCode = 0;
                    PreferentialClient prefClient;
                    try
                    {
                        Connector.getConnector().startTransaction(); //START TRANSACTION

                        if(isLeader)
                        {
                            prefClient = new Leader(Integer.parseInt(id), name.toUpperCase(), lastName.toUpperCase());
                        }
                        else if(leaderId != null && !leaderId.isBlank())
                        {
                            prefClient = new SubordinatedClient(Integer.parseInt(id), name.toUpperCase(), lastName.toUpperCase());
                            ((SubordinatedClient)prefClient).setLeader(new Leader(Integer.parseInt(leaderId)));
                        }
                        else
                        {
                            prefClient = new BaseClient(Integer.parseInt(id), name.toUpperCase(), lastName.toUpperCase());
                        }

                        prefClient.setDni(!dni.isBlank()?Long.parseLong(dni):null);
                        prefClient.setLocation(location != null ? location.toUpperCase() : null);
                        prefClient.setBirthDate(birthDate);
                        prefClient.setEmail(email);
                        prefClient.setPhoneNumber(!phoneNumber.isBlank()?Long.parseLong(phoneNumber):null);

                        returnCode += prefClient.operator().update(prefClient); // UPDATE

                        Connector.getConnector().commit(); // COMMIT

                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showSucces("La actualización se ha registrado exitosamente!");
                    }
                    catch (Exception exception)
                    {
                        Connector.getConnector().rollBack();
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showError("Error al actualizar los datos del cliente.", null, exception);
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

    public void searchLeader(Integer id, Integer campNumber)
    {   
        CustomAlert customAlert = getServiceSubscriber().showProcessIsWorking("Recuperando datos del Lider "+id);
        Task<Void> task = new Task<>()
        {
            @Override
            protected Void call() throws Exception
            {
                Leader leader = null;
                try
                {
                    leader = LeaderOperator.getOperator().find(id, campNumber);

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    if(leader != null)
                    {
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showQueriedPrefClient(leader);
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showTotalBalance(calculateTotalBalance(id));
                    }
                    else
                    {
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showNoResult("No se encuentra registrado ningún líder.");
                    }
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al intentar recuperar el lider "+id, null, exception);
                }
                finally
                {
                    Connector.getConnector().closeConnection();
                }

                return null;
            }
        };
        Platform.runLater(task);
        //this.getExecutorService().execute(task);    
    }

    public void searchSubordinatedClient(Integer id, Integer campNumber)
    {
        CustomAlert customAlert = getServiceSubscriber().showProcessIsWorking("Recuperando datos del cliente preferencial "+id);
        Task<Void> task = new Task<>()
        {
            @Override
            protected Void call() throws Exception
            {
                SubordinatedClient subordinatedClient = null;
                try
                {
                    subordinatedClient = (SubordinatedClient) SubordinatedClientOperator.getOperator().find(id, campNumber);

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    if(subordinatedClient != null)
                    {
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showQueriedPrefClient(subordinatedClient);
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showTotalBalance(calculateTotalBalance(id));
                    }
                    else
                    {
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showNoResult("No se encuentra registrado ningún cliente preferencial con líder.");
                    }
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                finally
                {
                    Connector.getConnector().closeConnection();
                }

                return null;
            }
        };
        Platform.runLater(task);
        //this.getExecutorService().execute(task);
    }

    public void searchBaseClient(Integer id, Integer campNumber)
    {
        CustomAlert customAlert = getServiceSubscriber().showProcessIsWorking("Recuperando datos del cliente preferencial base "+id);
        Task<Void> task = new Task<>()
        {
            @Override
            protected Void call() throws Exception
            {
                BaseClient baseClient = null;
                try
                {
                    baseClient = (BaseClient) BaseClientOperator.getOperator().find(id, campNumber);

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    if(baseClient != null)
                    {
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showQueriedPrefClient(baseClient);
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showTotalBalance(calculateTotalBalance(id));
                    }
                    else
                    {
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showNoResult("No se encuentra registrado ningún cliente preferencial base.");
                    }
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                finally
                {
                    Connector.getConnector().closeConnection();   
                }

                return null;
            }
        };
        Platform.runLater(task);
        //this.getExecutorService().execute(task);
    }

    public void searchPreferentialClients() throws Exception
    {
        //CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        Task<Integer> task = new Task<>()
        {
            @Override
            protected Integer call() throws Exception
            {
                //returnCode is intended for future implementations
                int returnCode = 0;
                List<PreferentialClient> prefClients = null;
                try
                {
                    prefClients = PreferentialClientOperator.findAllPrefClients(true);
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                finally
                {
                    //getServiceSubscriber().closeProcessIsWorking(customAlert);
                    if(prefClients != null)
                    {
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showQueriedPrefClients(prefClients);
                    }
                    else
                    {
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showNoResult("No se encuentra registrado ningún cliente preferencial.");
                    }

                    Connector.getConnector().closeConnection();
                }

                return returnCode;
            }
        };
        Platform.runLater(task);
        //this.getExecutorService().execute(task);
    }

    public void searchDevolutions(PreferentialClient prefClient) throws Exception
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        Task<Integer> task = new Task<>()
        {
            @Override
            protected Integer call() throws Exception
            {
                //returnCode is intended for future implementations
                int returnCode = 0;
                List<Devolution> devolutions = new ArrayList<>();
                try
                {
                    List<Devolution> devsAux = null;
                    if(prefClient instanceof Leader)
                    {
                        Iterator<SubordinatedClient> it = ((Leader)prefClient).getSubordinates().iterator();
                        while(it.hasNext())
                        {
                            devsAux = DevolutionOperator.getOperator().findAll(it.next().getId(), null);
                            if(devsAux != null)
                                devolutions.addAll(devsAux);
                        }
                    }

                    devsAux = DevolutionOperator.getOperator().findAll(prefClient.getId(), null);
                    if(devsAux != null)
                    {
                        devolutions.addAll(devsAux);
                    }

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    if(devolutions.size() > 0)
                    {
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showDevolutions(devolutions);
                    }
                    else
                    {
                        getServiceSubscriber().showNoResult("El cliente "+prefClient.getId()+" no tiene registrado devoluciones.");
                    }
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                finally
                {
                    Connector.getConnector().closeConnection();
                }

                return returnCode;
            }
        };
        Platform.runLater(task);
        //this.getExecutorService().execute(task);
    }

    public void registerPayment(PreferentialClient prefClient, Integer campNumber, String descriptor, Float amount, PayItem item, 
        PayType paymentMethod, Bank bank, PayStatus paymentStatus) throws Exception
    {
        if(allFieldsAreOk(campNumber, descriptor, amount))
        {
            CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
            Task<Integer> task = new Task<>()
            {
                @Override
                protected Integer call() throws Exception
                {
                    //returnCode is intended for future implementations
                    int returnCode = 0;

                    Payment payment = new Payment(descriptor, amount);
                    payment.setPrefClientId(prefClient.getId());
                    payment.setCampNumber(campNumber);
                    payment.setItem(item);
                    payment.setPaymentMethod(paymentMethod);
                    payment.setBank(bank);
                    payment.setPaymentStatus(paymentStatus);

                    Balance balance = new Balance();
                    balance.setPrefClientId(prefClient.getId());
                    balance.setCampNumber(campNumber);
                    balance.setTotalInPayments(amount);

                    try
                    {
                        Connector.getConnector().startTransaction();

                        PaymentOperator.getOperator().insert(payment);
                        BalanceOperator.getOperator().update(balance);

                        if(prefClient instanceof SubordinatedClient)
                        {
                            //update also the leader balance
                            balance.setPrefClientId(((SubordinatedClient)prefClient).getLeaderId());
                            BalanceOperator.getOperator().update(balance);
                        }

                        Connector.getConnector().commit();

                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showSucces("El pago se ha registrado exitosamente!");
                        getServiceSubscriber().refresh();
                    }
                    catch (Exception exception)
                    {
                        Connector.getConnector().rollBack();
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showError("Error al intentar registrar el pago.", null, exception);
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

    public void registerDevolution(PreferentialClient prefClient, Integer campNumber, Integer orderId, Reason reason) throws Exception
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
                    Connector.getConnector().startTransaction(); //START TRANSACTION

                    Order order = OrderOperator.getOperator().find(orderId); //SEARCH IN DB

                    if(order.getQuantity() > order.getReturnedQuantity()) //No client can return an article if already all of its units were returned
                    {
                        ReturnedArticle returnedArticle = new ReturnedArticle(reason);
                        returnedArticle.setOrderId(orderId);
                        returnedArticle.setRepurchased(false);


                        Commission commission = CommissionOperator.getOperator().find(prefClient.getId(), campNumber); //search for leader commission. (maybe is not a leader)
                        int comFactor = commission != null ? commission.getActualRate() : 0;
                        int fpComFactor = commission != null ? commission.getFpFactor() : 0;
                        int otherComFactor = commission != null ? commission.getOtherFactor() : 0;

                        float comF = comFactor/App.ConstraitConstants.COMMISSION_RATIO_FACTOR;
                        float fpComF = fpComFactor/App.ConstraitConstants.COMMISSION_RATIO_FACTOR;
                        float otherComF = otherComFactor/App.ConstraitConstants.COMMISSION_RATIO_FACTOR;

                        float devCost = order.getCost();
                        
                        if(order.isCommissionable())
                        {
                            switch(order.getType())
                            {
                                case PEDIDO:
                                case OPORTUNIDAD:
                                    devCost -= (order.getCost() / order.getQuantity())*comF;
                                break;

                                case FREEPREMIUM:
                                case PROMO:
                                    devCost -= (order.getCost() / order.getQuantity())*fpComF;   
                                break;

                                default:
                                    devCost -= (order.getCost() / order.getQuantity())*otherComF;
                            }
                        }
                        
                        Devolution devolution = new Devolution(order.getCost() / order.getQuantity()); //in DB, is saved the unit cost.
                        devolution.setPrefClientId(order.getPrefClientId()); // Register this devolution with the preferential Client id from the one that make the order.
                        devolution.setCampNumber(campNumber);


                        Balance balance = new Balance();
                        balance.setPrefClientId(prefClient.getId());
                        balance.setCampNumber(campNumber);
                        balance.setTotalInDevolutions(devCost);
                        

                        devolution.setUnitCode(ReturnedArticleOperator.getOperator().insert(returnedArticle)); // INSERT
                        DevolutionOperator.getOperator().insert(devolution); //INSERT
                        OrderOperator.getOperator().incrementForDevolution(orderId); //UPDATE
                        BalanceOperator.getOperator().update(balance); //UPDATE


                        if(prefClient instanceof SubordinatedClient)
                        {
                            //update also the leader balance
                            balance.setPrefClientId(((SubordinatedClient)prefClient).getLeaderId());
                            BalanceOperator.getOperator().update(balance); //UPDATE
                        }
                        
                        List<Order> orders = OrderOperator.getOperator().findAll(prefClient.getId(), campNumber); //search for leader orders for that camp number                        
                        List<Repurchase> repurchases = RepurchaseOperator.getOperator().findAll(prefClient.getId(), campNumber);
                        if(prefClient instanceof Leader)
                        {
                            SubordinatedClient subordinatedClient = null;
                            List<Order> auxOrders = null;
                            List<Repurchase> auxRepurchases = null;
                            Iterator<SubordinatedClient> subsIterator = ((Leader)prefClient).getSubordinates().iterator();
                            while(subsIterator.hasNext())
                            {
                                subordinatedClient = subsIterator.next();
                                auxOrders = OrderOperator.getOperator().findAll(subordinatedClient.getId(), campNumber);
                                auxRepurchases = RepurchaseOperator.getOperator().findAll(subordinatedClient.getId(), campNumber);

                                if (auxOrders != null)
                                {
                                    orders.addAll(auxOrders);
                                }
                                if(auxRepurchases != null)
                                {
                                    repurchases.addAll(auxRepurchases);
                                }
                            }
                        }

                        if(commission != null)
                        {
                            //The subscriber is supposed to be the ClientManagementViewCntlr, that is why that service is in the 2nd position
                            ((CommissionService)getServiceSubscriber().getService(2)).updateCommission(commission, orders, repurchases); // UPDATE
                        }
                        //otherwise, register the devolution but just ignore update commission becuase, once the commission is created, it will self update.

                        Connector.getConnector().commit(); // COMMIT

                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showSucces("La devolución se ha registrado exitosamente!\nCÓDIGO DE ARTÍCULO EN STOCK PARA RECOMPRA = "+ devolution.getUnitCode());
                        getServiceSubscriber().refresh();
                    }
                    else
                    {
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showError("No quedan artículos por devolver.");
                    }
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack(); //ROLLBACK
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al realizar la devolución.", null, exception);
                }
                finally
                {
                    Connector.getConnector().endTransaction(); //END TRANSACTION
                    Connector.getConnector().closeConnection();
                }
                return returnCode;
            }
        };

        Platform.runLater(task);
    }
    
    public void registerDevolution(PreferentialClient prefClient, Integer campNumber, Integer repurchaseId) throws Exception
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
                    Connector.getConnector().startTransaction(); //START TRANSACTION

                    Repurchase repurchase = RepurchaseOperator.getOperator().find(repurchaseId);

                    if(!repurchase.isReturned())
                    {
                        ReturnedArticle returnedArticle = new ReturnedArticle();
                        returnedArticle.setUnitCode(repurchase.getReturnedArticleId());
                        returnedArticle.setRepurchased(false);
                        
                        Devolution devolution = new Devolution(repurchase.getCost());
                        devolution.setPrefClientId(prefClient.getId());
                        devolution.setCampNumber(campNumber);
                        devolution.setUnitCode(returnedArticle.getUnitCode());

                        Balance balance = new Balance();
                        balance.setPrefClientId(prefClient.getId());
                        balance.setCampNumber(campNumber);
                        balance.setTotalInDevolutions(repurchase.getCost());


                        ReturnedArticleOperator.getOperator().update(returnedArticle);
                        DevolutionOperator.getOperator().insert(devolution);
                        BalanceOperator.getOperator().update(balance);

                        if(prefClient instanceof SubordinatedClient)
                        {
                            //update also the leader balance
                            balance.setPrefClientId(((SubordinatedClient)prefClient).getLeaderId());
                            BalanceOperator.getOperator().update(balance); //UPDATE
                        }

                        Commission commission = CommissionOperator.getOperator().find(prefClient.getId(), campNumber);
                        List<Order> orders = OrderOperator.getOperator().findAll(prefClient.getId(), campNumber);
                        List<Repurchase> repurchases = RepurchaseOperator.getOperator().findAll(prefClient.getId(), campNumber);

                        if(commission != null)
                        {
                            //The subscriber is supposed to be the ClientManagementViewCntlr, that is way is in the 2nd position
                            ((CommissionService)getServiceSubscriber().getService(2)).updateCommission(commission, orders, repurchases); //UPDATE
                        }

                        Connector.getConnector().commit(); // COMMIT

                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showSucces("La devolución se ha registrado exitosamente!\nCÓDIGO DE ARTÍCULO EN STOCK PARA RECOMPRA = "+ repurchase.getReturnedArticleId());
                        getServiceSubscriber().refresh();
                    }
                    else
                    {
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showError("La recompra ya ha sido devuelta.");
                    }
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack(); //ROLLBACK
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al realizar la devolución.", null, exception);
                }
                finally
                {
                    Connector.getConnector().endTransaction(); //END TRANSACTION
                    Connector.getConnector().closeConnection();
                }
                return returnCode;
            }
        };

        Platform.runLater(task);    
    }

    public void registerWithdrawals(List<Order> orders) throws Exception
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
                    Connector.getConnector().startTransaction(); //START TRANSACTION

                    OrderOperator.getOperator().registerWithdrawals(orders);

                    Connector.getConnector().commit(); // COMMIT

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showSucces("Los retiros se han registrado exitosamente!");
                    getServiceSubscriber().refresh();
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack(); // ROLLBACK
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al realizar los retiros.", null, exception);
                }
                finally
                {
                    Connector.getConnector().endTransaction(); //END TRANSACTION
                    Connector.getConnector().closeConnection();
                }
                return returnCode;
            }
        };

        Platform.runLater(task);    
    }

    public void registerObservation(Integer prefClientId, Integer campNumber, String obs) throws Exception
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
                    Connector.getConnector().startTransaction(); //START TRANSACTION
                    
                    createObservation(prefClientId, campNumber, obs);

                    Connector.getConnector().commit(); // COMMIT

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showSucces("Observación registrada exitosamente!");
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack(); //ROLLBACK
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al guardar la observación.", null, exception);
                }
                finally
                {
                    Connector.getConnector().endTransaction(); //END TRANSACTION
                    Connector.getConnector().closeConnection();
                }
                return returnCode;
            }
        };
        
        Platform.runLater(task);
    }

    public void searchObservation(Integer prefClientId, Integer campNumber)
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Buscando observación...");
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

                    Observation observation = ObservationOperator.getOperator().find(prefClientId, campNumber);
                    getServiceSubscriber().closeProcessIsWorking(customAlert);

                    if(observation != null)
                    {
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showObservation(observation);
                    }
                    else
                    {
                        getServiceSubscriber().showNoResult("No existe observación registrada del cliente "+prefClientId+" para la campaña "+campNumber+"\nSe creará la observación correspondiente...");

                        createObservation(prefClientId, campNumber, "");
                        getServiceSubscriber().refresh();
                    }

                    Connector.getConnector().commit();
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack(); //ROLLBACK
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al guardar la observación.", null, exception);
                }
                finally
                {
                    Connector.getConnector().endTransaction(); //END TRANSACTION
                    Connector.getConnector().closeConnection();
                }
                return returnCode;
            }
        };
        
        Platform.runLater(task);
    }
}