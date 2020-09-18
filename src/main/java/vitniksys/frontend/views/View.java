package vitniksys.frontend.views;

public interface View
{
    /**
     * Called everytime that a use case is initiated.
     * IMPORTANT: DO NOT USE THREAD BLOCKING INSTRUCTIONS HERE.
     * OTHERWISE, THE USE CASE CANNOT CONTINUE.
     */
    void showProcessIsWorking(String message);

    void closeProcessIsWorking();

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
    void showError(String message, Exception exception);

    /**
     * Called when a use case does not return an expected result.
     */
    void showNoResult(String message);
}
