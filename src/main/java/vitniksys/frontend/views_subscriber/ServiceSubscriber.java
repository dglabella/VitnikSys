package vitniksys.frontend.views_subscriber;

import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.model.services.Service;

public interface ServiceSubscriber
{

    Service getService();

    void setService(Service service);
    /**
     * Called everytime that a use case is initiated.
     * IMPORTANT: DO NOT USE THREAD BLOCKING INSTRUCTIONS HERE.
     * OTHERWISE, THE USE CASE CANNOT CONTINUE.
     */
    CustomAlert showProcessIsWorking(String message);

    void closeProcessIsWorking(CustomAlert customAlert);

    /**
     * Called when a use case has succed.
     */
    void showSucces(String message);

    /**
     * Called when a use case has not succed.
     */
    void showError(String message);

    /**
     * Called when a use case has not succed.
     */
    void showError(String message, String description, Exception exception);

    /**
     * Called when a use case does not return an expected result.
     */
    void showNoResult(String message);
}