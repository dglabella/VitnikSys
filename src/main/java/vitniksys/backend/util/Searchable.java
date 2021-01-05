package vitniksys.backend.util;

public interface Searchable 
{
    boolean exist(Integer key1, Integer key2);

    Integer locate(Integer key1, Integer key2);
}