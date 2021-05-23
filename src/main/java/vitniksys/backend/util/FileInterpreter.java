package vitniksys.backend.util;

import java.io.File;
import java.util.List;

import vitniksys.backend.model.business_logic.BLService;

import java.util.ArrayList;

public abstract class FileInterpreter
{
    private File file;
    private List<BLService> services;

    public FileInterpreter(File file, BLService ... services)
    {
        this.file = file;
        this.services = new ArrayList<>();
        for(BLService service: services)
        {
            this.services.add(service);
        }
    }

    // Getters && Setters
    public File getFile()
    {
        return this.file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public BLService getService(int location)
    {
        return this.services.get(location);
    }

    /**
     * Interprets the file using the implementation 
     * corresponding to the class that is instantiated.
     */
    public abstract void interpret();
}