package vitniksys.backend.util;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import vitniksys.backend.model.entities.VitnikSearchableEntity;

public class VitnikSearchableList<T extends VitnikSearchableEntity> extends ArrayList<T>
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VitnikSearchableList()
    {
        super();
    }

    public VitnikSearchableList(List<T> vitnikList)
    {
        super();
        if(vitnikList != null)
            this.addAll(vitnikList);
    }
    
    /**
     * This method locates the entity of this preferential client
     * for a certain campaign number.
     * @param campNumb The campaign number.
     * @return The entity.
     * If null is returned, no entity with that campaign number exist in the list.
     */
    public T locateWithCampNumb(Integer campNumb)
    {
        int i = 0;
        T ret = null;
        try
        {
            if(campNumb != null)
            {
                while(i < this.size() && !this.get(i).getCampNumber().equals(campNumb))
                    i++;
            }
            else
            {
                throw new Exception("Campaign number is null");
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            if(i < this.size())
                ret = this.get(i);
        }

        return ret;
    }

    /**
     * This method locates all entities of this preferential client
     * for a certain campaign number.
     * @param campNumb The campaign number.
     * @return The entities list.
     * If null is returned, no entity with that campaign number exist in the list.
     */
    public List<T> locateAllWithCampNumb(Integer campNumb)
    {
        int i = 0;
        T aux = null;
        List<T> ret = new ArrayList<>();
        try
        {
            if(campNumb != null)
            {
                while(i < this.size())
                {
                    aux = this.get(i);
                    if(aux.getCampNumber().equals(campNumb))
                    {
                        ret.add(aux);
                    }
                    i++;
                }
            }
            else
            {
                throw new Exception("Campaign number is null");
            }   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            if(ret.size() == 0)
                ret = null;
        }

        return ret;
    }

    /**
     * This method locates the entity of this Campaign for a certain preferential client.
     * @param prefClientId The preferential client id.
     * @return The entity.
     * If null is returned, no entity with that preferential client id exist in the list.
     */
    public T locateWithPrefClient(Integer prefClientId)
    {
        int i = 0;
        T ret = null;
        try
        {
            if(prefClientId != null)
            {   
                while(i < this.size() && !this.get(i).getPrefClientId().equals(prefClientId))
                    i++;
            }
            else
            {
                throw new Exception("Preferential client id is null");
            }   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            if(i < this.size())
                ret = this.get(i);
        }

        return ret;
    }

    /**
     * This method locates all entities of this Campaign for a 
     * certain preferential client.
     * @param prefClientId The preferential client id.
     * @return The entities list.
     * If null is returned, no entity with that preferential client id exist in the list.
     */
    public List<T> locateAllWithPrefClient(Integer prefClientId)
    {
        int i = 0;
        T aux = null;
        List<T> ret = new ArrayList<>();
        try
        {
            if(prefClientId != null)
            {
                while(i < this.size())
                {
                    aux = this.get(i);
                    if(aux.getPrefClientId().equals(prefClientId))
                    {
                        ret.add(aux);
                    }
                    i++;
                }
            }
            else
            {
                throw new Exception("Preferential client id is null");
            }   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            if(ret.size() == 0)
                ret = null;
        }

        return ret;
    }

    public void shuffle()
    {
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < this.size(); i++)
            this.add(this.remove(rand.nextInt(this.size())));
    }   
}