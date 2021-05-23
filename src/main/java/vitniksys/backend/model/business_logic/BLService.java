package vitniksys.backend.model.business_logic;

import vitniksys.frontend.view_subscribers.BLServiceSubscriber;

public abstract class BLService
{
    private BLServiceSubscriber serviceSubscriber;
    //private ExecutorService executorService;
    
    public BLService()
    {
        //this.executorService = Executors.newFixedThreadPool(App.ConstraitConstants.THREAD_NUMBER);
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
