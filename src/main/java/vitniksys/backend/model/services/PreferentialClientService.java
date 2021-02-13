package vitniksys.backend.model.services;

import java.util.List;
import java.time.LocalDate;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.model.enums.ArticleType;
import vitniksys.backend.model.enums.Bank;
import vitniksys.backend.model.enums.PayItem;
import vitniksys.backend.model.enums.PayType;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.enums.PayStatus;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Payment;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.entities.Devolution;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.DevolutionOperator;
import vitniksys.backend.model.persistence.LeaderOperator;
import vitniksys.backend.model.persistence.PaymentOperator;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.ReturnedArticle;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.persistence.CampaignOperator;
import vitniksys.backend.model.persistence.BaseClientOperator;
import vitniksys.backend.model.persistence.PreferentialClientOperator;
import vitniksys.backend.model.persistence.ReturnedArticleOperator;
import vitniksys.backend.model.persistence.SubordinatedClientOperator;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;

public class PreferentialClientService extends Service
{
    public static final int MAX_LENGTH_PAYMENT_DESCRIPTOR = 60;

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

    private boolean allFieldsAreOk(Integer prefClientId, Integer campNumber, String descriptor, Float amount)
    {
        boolean ret = false;

        if(prefClientId != null && campNumber != null && descriptor.length() <= PreferentialClientService.MAX_LENGTH_PAYMENT_DESCRIPTOR && amount != null)
        {
            ret = true; 
        }

        return ret;
    }
    // ================================= protected methods =================================

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

    public void searchLeader(Integer id)
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
                    leader = LeaderOperator.getOperator().find(id);

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

    public void searchSubordinatedClient(Integer id)
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
                    subordinatedClient = SubordinatedClientOperator.getOperator().find(id);

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

    public void searchBaseClient(Integer id)
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
                    baseClient = BaseClientOperator.getOperator().find(id);

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

    public void registerPayment(Integer prefClientId, Integer campNumber, String descriptor, Float amount, PayItem item, 
        PayType paymentMethod, Bank bank, PayStatus paymentStatus) throws Exception
    {
        if(allFieldsAreOk(prefClientId, campNumber, descriptor, amount))
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
                    payment.setPrefClientId(prefClientId);
                    payment.setCampNumber(campNumber);
                    payment.setItem(item);
                    payment.setPaymentMethod(paymentMethod);
                    payment.setBank(bank);
                    payment.setPaymentStatus(paymentStatus);

                    Balance balance = new Balance();
                    balance.setPrefClientId(prefClientId);
                    balance.setCampNumber(campNumber);
                    balance.setTotalInPayments(amount);

                    try
                    {
                        Connector.getConnector().startTransaction();

                        PaymentOperator.getOperator().insert(payment);
                        BalanceOperator.getOperator().update(balance);

                        Connector.getConnector().commit();

                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showSucces("El pago se ha registrado exitosamente!");
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

    public void registerDevolution(Integer prefClientId, Integer campNumber, String articleId, String articleName, ArticleType articleType, 
        Integer quantity, Float cost, Reason reason) throws Exception
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        Task<Integer> task = new Task<>()
        {
            @Override
            protected Integer call() throws Exception
            {
                //returnCode is intended for future implementations
                int returnCode = 0;

                Devolution devolution = new Devolution(quantity, cost, reason);
                devolution.setPrefClientId(prefClientId);
                devolution.setCampNumber(campNumber);
                devolution.setArticleId(articleId);

                ReturnedArticle returnedArticle = new ReturnedArticle(reason);
                returnedArticle.setArticleId(articleId);

                Balance balance = new Balance();
                balance.setPrefClientId(prefClientId);
                balance.setCampNumber(campNumber);
                balance.setTotalInDevolutions(cost);

                try
                {
                    Connector.getConnector().startTransaction();

                    DevolutionOperator.getOperator().insert(devolution);
                    ReturnedArticleOperator.getOperator().insert(returnedArticle);
                    BalanceOperator.getOperator().update(balance);

                    Connector.getConnector().commit();

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showSucces("La devolución se ha registrado exitosamente!");
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack();
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al realizar la devolución.", null, exception);
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
}