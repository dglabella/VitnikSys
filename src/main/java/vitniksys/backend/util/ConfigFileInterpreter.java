package vitniksys.backend.util;

import java.io.File;

import vitniksys.backend.model.business_logic.BLService;

public class ConfigFileInterpreter extends FileInterpreter
{
    public ConfigFileInterpreter(File file, BLService[] services)
    {
        super(file, services);
    }

    @Override
    public void interpret()
    {

    }
}