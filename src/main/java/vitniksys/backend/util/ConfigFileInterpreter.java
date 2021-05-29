package vitniksys.backend.util;

import java.io.File;
import vitniksys.backend.model.business_logic.BLService;

public class ConfigFileInterpreter extends FileInterpreter
{
    public ConfigFileInterpreter(File file, BLService service)
    {
        super(file, service);
    }

    @Override
    public void interpret() throws Exception
    {
        
    }
}