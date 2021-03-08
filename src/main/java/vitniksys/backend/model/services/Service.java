package vitniksys.backend.model.services;

import vitniksys.App;
//import javafx.concurrent.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import vitniksys.frontend.views_subscriber.ServiceSubscriber;

public abstract class Service
{
    private ServiceSubscriber serviceSubscriber;
    private ExecutorService executorService;
    
    public Service()
    {
        this.executorService = Executors.newFixedThreadPool(App.ConstraitConstants.THREAD_NUMBER);
    }

    // ================================= Getters && setters =================================

    public ServiceSubscriber getServiceSubscriber()
    {
        return this.serviceSubscriber;
    }

    public void setServiceSubscriber(ServiceSubscriber serviceSubscriber)
    {
        this.serviceSubscriber = serviceSubscriber;
    }

    // =================================== private methods ==================================


    // ================================= protected methods ==================================
    protected ExecutorService getExecutorService()
    {
		return this.executorService;
	}

    /*
    protected void setExecutorService(ExecutorService executorService)
    {
		this.executorService = executorService;
    }
    */

    // =================================== public methods ===================================
    

    /*
    public void executeTask(Task<Void> task)
    {

    }
    */
}
