package vitniksys.backend.model.services;

import vitniksys.App;
import java.util.List;
import java.time.LocalDate;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.model.enums.Bank;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.enums.PayItem;
import vitniksys.backend.model.enums.PayType;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.enums.PayStatus;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Observation;
import vitniksys.backend.model.entities.Payment;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.entities.Devolution;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.entities.ReturnedArticle;
import vitniksys.backend.model.persistence.OrderOperator;
import vitniksys.backend.model.persistence.LeaderOperator;
import vitniksys.backend.model.persistence.ObservationOperator;
import vitniksys.backend.model.persistence.PaymentOperator;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.persistence.CampaignOperator;
import vitniksys.backend.model.persistence.CommissionOperator;
import vitniksys.backend.model.persistence.RepurchaseOperator;
import vitniksys.backend.model.persistence.BaseClientOperator;
import vitniksys.backend.model.persistence.DevolutionOperator;
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
        if(this.getExpressionChecker().onlyNumbers(id, false) && this.getExpressionChecker().onlyNumbers(dni, true)
            && this.getExpressionChecker().composedName(name) && this.getExpressionChecker().composedName(lastName)
            && this.getExpressionChecker().isEmail(email, true) && this.getExpressionChecker().onlyNumbers(phoneNumber, true)
            && this.getExpressionChecker().onlyNumbers(leaderId, true))
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
                        prefClient =  new BaseClient(Integer.parseInt(id), name.toUpperCase(), lastName.toUpperCase());
                    }

                    prefClient.setDni(!dni.isBlank()?Long.parseLong(dni):null);
                    prefClient.setLocation(location.toUpperCase());
                    prefClient.setBirthDate(birthDate);
                    prefClient.setEmail(email);
                    prefClient.setPhoneNumber(!phoneNumber.isBlank()?Long.parseLong(phoneNumber):null);

                    try
                    {
                        Connector.getConnector().startTransaction();

                        returnCode += prefClient.operator().insert(prefClient);

                        Balance balance = new Balance();

                        //balance associations
                        balance.setClient(prefClient);
                        balance.setCamp(CampaignOperator.getOperator().findLast());
                        //balance fk id
                        balance.setPrefClientId(prefClient.getId());
                        balance.setCampNumber(balance.getCampaign().getNumber());

                        returnCode += BalanceOperator.getOperator().insert(balance);

                        Connector.getConnector().commit();

                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showSucces("El Cliente se ha registrado exitosamente!");
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
        
                        Devolution devolution = new Devolution(order.getArticle().getUnitPrice());
                        devolution.setPrefClientId(order.getPrefClientId()); // Register this devolution with the preferential Client id from the one that make the order.
                        devolution.setCampNumber(campNumber);

                        Balance balance = new Balance();
                        balance.setPrefClientId(prefClient.getId());
                        balance.setCampNumber(campNumber);
                        balance.setTotalInDevolutions(order.getArticle().getUnitPrice());
                        

                        devolution.setUnitCode(ReturnedArticleOperator.getOperator().insert(returnedArticle)); //INSERT
                        DevolutionOperator.getOperator().insert(devolution); //INSERT
                        OrderOperator.getOperator().incrementForDevolution(orderId); //UPDATE
                        BalanceOperator.getOperator().update(balance); //UPDATE


                        if(prefClient instanceof SubordinatedClient)
                        {
                            //update also the leader balance
                            balance.setPrefClientId(((SubordinatedClient)prefClient).getLeaderId());
                            BalanceOperator.getOperator().update(balance); //UPDATE
                        }

                        Commission commission = CommissionOperator.getOperator().find(prefClient.getId(), campNumber); //search for leader commission. (maybe is not a leader)
                        List<Order> orders = OrderOperator.getOperator().findAll(prefClient.getId(), campNumber); //search for leader orders for that camp number
                        List<Repurchase> repurchases = RepurchaseOperator.getOperator().findAll(prefClient.getId(), campNumber);

                        if(commission != null)
                        {
                            //The subscriber is supposed to be the ClientManagementViewCntlr, that is way is in the 2nd position
                            ((CommissionService)getServiceSubscriber().getService(2)).updateCommission(commission, orders, repurchases); //UPDATE
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
                    Connector.getConnector().rollBack(); //ROLLBACK
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
                    Observation observation = ObservationOperator.getOperator().find(prefClientId, campNumber);
                    getServiceSubscriber().closeProcessIsWorking(customAlert);

                    if(observation != null)
                    {
                        ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showObservation(observation);
                    }
                    else
                    {
                        getServiceSubscriber().showNoResult("No existe observación registrada del cliente "+prefClientId+" para la campaña "+campNumber+"\nSe creará la observación correspondiente...");
                        registerObservation(prefClientId, campNumber, "");
                    }
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