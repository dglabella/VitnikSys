package vitniksys.frontend.views;

import vitniksys.backend.util.OperationResult;

public interface OperationResultView
{
    /**
     * This method should be called by a GUI controller if showing
     * an operation result is needed.
     * @param operationResult The operation result Object.
     */
    public void showResult(OperationResult operationResult);    
}