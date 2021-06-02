package vitniksys.backend.util;

/**
 * This class is an utility for the detail file interpreter.
 * A DetailFileInterpreter Object
 */
public class DetailFileRow
{    
    private int leaderId;
    private int clientId;
    private int deliveryNumber;
    private boolean comp;
    private String letters;
    private String barCode;
    private String name;
    private int quant;
    private float unitPrice;
    private float descCP;
    private float price;
    private float agentComm;
    private float finalPrice;
    private int campNumb;
    private String obs;

    public DetailFileRow()
    {
        //Empty construsctor
    }

    public DetailFileRow(String leaderId, String clientId, String deliveryNumber, String comp,String letters, String barCode, 
            String name, String quant, String unitPrice, String descCP, String price, String agentComm, String finalPrice, 
            String campNumb, String obs)
    {
        this.leaderId = (leaderId.isEmpty()||leaderId.isBlank())? -1 : Integer.parseInt(leaderId);
        this.clientId = Integer.parseInt(clientId);
        this.deliveryNumber = Integer.parseInt(deliveryNumber);
        this.comp = comp.isEmpty()||comp.isBlank()?false:true;
        this.letters = letters;
        this.barCode = barCode;
        this.name = name;
        this.quant = Integer.parseInt(quant);
        this.unitPrice = this.customParseFloat(unitPrice);
        this.descCP = this.customParseFloat(descCP);
        this.price = this.customParseFloat(price);
        this.agentComm = this.customParseFloat(agentComm);
        this.finalPrice = this.customParseFloat(finalPrice);
        this.campNumb = Integer.parseInt(campNumb);
        this.obs = obs;
    }

    //Getters && Setters
    public int getLeaderId()
    {
        return this.leaderId;
    }

    public void setLeaderId(int leaderId)
    {
        this.leaderId = leaderId;
    }

    public int getClientId()
    {
        return this.clientId;
    }

    public void setClientId(int clientId)
    {
        this.clientId = clientId;
    }

    public int getDeliveryNumber()
    {
        return this.deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber)
    {
        this.deliveryNumber = deliveryNumber;
    }

    public boolean isComp()
    {
        return this.comp;
    }

    public void setComp(boolean comp)
    {
        this.comp = comp;
    }

    public String getLetters()
    {
        return this.letters;
    }

    public void setLetters(String letters)
    {
        this.letters = letters;
    }

    public String getBarCode()
    {
        return this.barCode;
    }

    public void setBarCode(String barCode)
    {
        this.barCode = barCode;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getQuant()
    {
        return this.quant;
    }

    public void setQuant(int quant)
    {
        this.quant = quant;
    }

    public float getUnitPrice()
    {
        return this.unitPrice;
    }

    public void setUnitPrice(float unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public float getDescCP()
    {
        return this.descCP;
    }

    public void setDescCP(float descCP)
    {
        this.descCP = descCP;
    }

    public float getPrice()
    {
        return this.price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public float getAgentComm()
    {
        return this.agentComm;
    }

    public void setAgentComm(float agentComm)
    {
        this.agentComm = agentComm;
    }

    public float getFinalPrice()
    {
        return this.finalPrice;
    }

    public void setFinalPrice(float finalPrice)
    {
        this.finalPrice = finalPrice;
    }

    public int getCampNumb()
    {
        return this.campNumb;
    }

    public void setCampNumb(int campNumb)
    {
        this.campNumb = campNumb;
    }

    public String getObs()
    {
        return this.obs;
    }

    public void setObs(String obs)
    {
        this.obs = obs;
    }

    /**
     * Transform the money value in the detail file to
     * a float value.
     * Take in consideration that this method works supposing 
     * a particular format for the string
     * @param moneyValue
     * @return The float representative value of moneyValue
     */
    private float customParseFloat(String moneyValue)
    {
        //Removing the money sign
        String aux = moneyValue.charAt(0)=='$'? moneyValue.substring(1).trim():moneyValue;
        String[] splitedNumber = aux.split("\\.");
        aux = "";
        for(int i = 0; i < splitedNumber.length; i++)
        {
            aux = aux + splitedNumber[i];
        }
        return Float.parseFloat(aux.replace(',','.'));
    }
}