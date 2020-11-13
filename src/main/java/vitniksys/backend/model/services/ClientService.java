package vitniksys.backend.model.services;

import java.time.LocalDate;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.persistence.CampaignOperator;
import vitniksys.frontend.views_subscriber.ServiceSubscriber;

public class ClientService
{
    private ExpressionChecker expressionChecker;

    //Views
    private ServiceSubscriber view;

    public ClientService(ServiceSubscriber view)
    {
        this.view = view;
        this.expressionChecker = ExpressionChecker.getExpressionChecker();
    }

    //Getters && Setters

    // ================================= private methods =================================
    private boolean allFieldsAreOk(String id, String dni, String name, String lastName, 
        String email, String phoneNumber, String leaderId)
    {
        boolean ret = false;
        if(this.expressionChecker.onlyNumbers(id, false) && this.expressionChecker.onlyNumbers(dni, true)
            && this.expressionChecker.composedName(name) && this.expressionChecker.composedName(lastName)
            && this.expressionChecker.isEmail(email, true) && this.expressionChecker.onlyNumbers(phoneNumber, true)
            && this.expressionChecker.onlyNumbers(leaderId, true))
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
            CustomAlert customAlert = this.view.showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
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
                        view.closeProcessIsWorking(customAlert);
                        view.showError("Error al intentar registrar la campaña.", null, exception);
                    }
                    finally
                    {
                        Connector.getConnector().endTransaction();
                        Connector.getConnector().closeConnection();
                        view.closeProcessIsWorking(customAlert);
                        view.showSucces("El Cliente se ha registrado exitosamente!");
                    }
                    return returnCode;
                }
            };

            Platform.runLater(task);
        }
        else
        {
            //Conflict with some fields.
            this.view.showError("Los campos deben completarse correctamente.");
        }
    }
}