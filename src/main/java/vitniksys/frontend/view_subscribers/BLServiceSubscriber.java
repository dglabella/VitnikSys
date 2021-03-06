package vitniksys.frontend.view_subscribers;

import vitniksys.backend.model.business_logic.BLService;
import vitniksys.backend.util.CustomAlert;

public interface BLServiceSubscriber
{
    BLService getBLService(int location);

    int addService(BLService service);

    void closeSubscriberStage();

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

    /**
     * Called when some kind of refresh for a view is needed.
     */
    void refresh();
}