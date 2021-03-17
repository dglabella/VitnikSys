package vitniksys.backend.util;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import vitniksys.backend.model.services.Service;

public abstract class FileInterpreter
{
    private File file;
    private List<Service> services;

    public FileInterpreter(File file, Service ... services)
    {
        this.file = file;
        this.services = new ArrayList<>();
        for(Service service: services)
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

    public Service getService(int location)
    {
        return this.services.get(location);
    }
}