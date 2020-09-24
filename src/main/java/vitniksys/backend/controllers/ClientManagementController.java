package vitniksys.backend.controllers;

import java.time.LocalDate;
import vitniksys.frontend.views.View;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.persistence.CampaignOperator;

public class ClientManagementController
{
    private ExpressionChecker expressionChecker;

    //Views
    private View view;

    public ClientManagementController(View view)
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

    // ================================= public methods =================================
    public void registerClient(String id, String dni, String name, String lastName, String location,
            LocalDate birthDate, String email, String phoneNumber, Boolean isLeader, String leaderId) throws Exception
    {
        if(allFieldsAreOk(id, dni, name, lastName, email, phoneNumber, leaderId))
        {
            this.view.showProcessIsWorking("Espere un momento mientras se realiza el proceso.");

            PreferentialClient cp;
            if(isLeader)
            {
                cp = new Leader(Integer.parseInt(id), name.toUpperCase(), lastName.toUpperCase());
            }
            else if(leaderId != null && !leaderId.isBlank())
            {
                cp = new SubordinatedClient(Integer.parseInt(id), name.toUpperCase(), lastName.toUpperCase());
                ((SubordinatedClient)cp).setLeader(new Leader(Integer.parseInt(leaderId)));
            }
            else
            {
                cp =  new BaseClient(Integer.parseInt(id), name.toUpperCase(), lastName.toUpperCase());
            }

            cp.setDni(!dni.isBlank()?Long.parseLong(dni):null);
            cp.setLocation(location.toUpperCase());
            cp.setBirthDate(birthDate);
            cp.setEmail(email);
            cp.setPhoneNumber(!phoneNumber.isBlank()?Long.parseLong(phoneNumber):null);

            try
            {
                Connector.getConnector().startTransaction();

                cp.operator().insert(cp);

                Balance balance = new Balance();
                balance.setClient(cp);
                balance.setCamp(CampaignOperator.getOperator().findLast());
                BalanceOperator.getOperator().insert(balance);

                Connector.getConnector().commit();
            }
            catch (Exception exception)
            {
                Connector.getConnector().rollBack();
                this.view.closeProcessIsWorking();
                this.view.showError("Error al intentar registrar la campaña.", exception);
            }
            finally
            {
                Connector.getConnector().endTransaction();
                Connector.getConnector().closeConnection();
                this.view.closeProcessIsWorking();
                this.view.showSucces("El Cliente se ha registrado exitosamente!");
            }
        }
        else
        {
            //Conflict with some fields.
            this.view.showError("Los campos deben completarse correctamente.");
        }
    }
}