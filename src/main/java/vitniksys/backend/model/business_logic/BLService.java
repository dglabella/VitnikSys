package vitniksys.backend.model.business_logic;

import vitniksys.backend.model.persistence.Connector;
import vitniksys.frontend.view_subscribers.BLServiceSubscriber;

public abstract class BLService
{
    private BLServiceSubscriber serviceSubscriber;
    private Connector connector;

    //private ExecutorService executorService;
    
    public BLService()
    {
        //this.executorService = Executors.newFixedThreadPool(App.ConstraitConstants.THREAD_NUMBER);
        this.connector = Connector.getInstance();
    }

    // ================================= Getters && setters =================================

    public BLServiceSubscriber getBLServiceSubscriber()
    {
        return this.serviceSubscriber;
    }

    public void setBLServiceSubscriber(BLServiceSubscriber serviceSubscriber)
    {
        this.serviceSubscriber = serviceSubscriber;
    }

    public Connector getConnector()
    {
        return this.connector;
    }

    public void setConnector(Connector connector)
    {
        this.connector = connector;
    }

    // =================================== private methods ==================================


    // ================================= protected methods ==================================
    // protected ExecutorService getExecutorService()
    // {
	// 	return this.executorService;
	// }

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
