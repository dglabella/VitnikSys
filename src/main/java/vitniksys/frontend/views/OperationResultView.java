package vitniksys.frontend.views;

import vitniksys.backend.util.OperationResult;

public interface OperationResultView
{
    /**
     * This method should be called by a GUI controller if is nedded 
     * to show an operation result.
     * @param operationResult The operation result Object.
     */
    public void showResult(OperationResult operationResult);    
}