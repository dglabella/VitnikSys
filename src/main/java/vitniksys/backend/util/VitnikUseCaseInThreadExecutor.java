package vitniksys.backend.util;

import vitniksys.backend.controllers.CampManagementController;
import vitniksys.backend.controllers.ClientManagementController;

public abstract class VitnikUseCaseInThreadExecutor implements Runnable
{
    private CampManagementController campManagementController;
    private ClientManagementController clientManagementController;
    
    @Override
    public void run()
    {

    }

    public void execute() throws Exception
    {

    }
}
