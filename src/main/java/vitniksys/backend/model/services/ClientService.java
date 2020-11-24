package vitniksys.backend.model.services;

import java.time.LocalDate;
import java.util.List;

import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.PreferentialClientOperator;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.persistence.CampaignOperator;

public class ClientService extends Service
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
                        balance.setClient(prefClient);
                        balance.setCamp(CampaignOperator.getOperator().findLast());
                        returnCode += BalanceOperator.getOperator().insert(balance);

                        Connector.getConnector().commit();
                    }
                    catch (Exception exception)
                    {
                        Connector.getConnector().rollBack();
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showError("Error al intentar registrar la campaña.", null, exception);
                    }
                    finally
                    {
                        Connector.getConnector().endTransaction();
                        Connector.getConnector().closeConnection();
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showSucces("El Cliente se ha registrado exitosamente!");
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

    public void searchPreferentialClients()
    {
        //CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        Task<Integer> task = new Task<>()
        {
            @Override
            protected Integer call() throws Exception
            {
                //returnCode is intended for future implementations
                int returnCode = 0;
                List<PreferentialClient> prefClients = PreferentialClientOperator.findAllPrefClients(true);

                //getServiceSubscriber().closeProcessIsWorking(customAlert);

                if(prefClients != null)
                {
                    ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showQueriedPrefClients(prefClients);
                }
                else
                {
                    ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showNoResult("No se encuentra registrado ningún cliente preferencial.");
                }

                return returnCode;
            }
        };
        Platform.runLater(task);  
    }
}