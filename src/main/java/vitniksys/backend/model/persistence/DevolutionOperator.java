package vitniksys.backend.model.persistence;

public class DevolutionOperator
{
    private static DevolutionOperator operator;
    private boolean activeRow;

    private DevolutionOperator()
    {
        this.activeRow = true;
    }
    
    /**
     * Get the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
     * @return The state of the entity.
     */
    public boolean isActiveRow()
    {
        return this.activeRow;
    }

    /**
     * Change the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
     * @param activeRow the value for the operation.
     */
    public DevolutionOperator setActiveRow(Boolean activeRow)
    {
        this.activeRow = activeRow;

        return DevolutionOperator.operator;
    }

    public static DevolutionOperator getOperator()
    {
        
    }
}