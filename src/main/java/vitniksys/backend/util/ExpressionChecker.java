package vitniksys.backend.util;

import java.util.regex.*;

/**
 * This class (Singleton) is used to check the most
 * common user inputs.
 */
public class ExpressionChecker 
{
    private static ExpressionChecker expressionChecker;

    private Pattern pattern;

    private ExpressionChecker() 
    {
        //Empty constructor.
    }

    public static ExpressionChecker getExpressionChecker()
    {
        if(ExpressionChecker.expressionChecker == null)
        {
            ExpressionChecker.expressionChecker = new ExpressionChecker();
        }
        return ExpressionChecker.expressionChecker;
    }

    public boolean onlyNumbers(String string, boolean allowEmpty) 
    {
        boolean ret;

        if(allowEmpty)
            pattern = Pattern.compile("[\\d]*");
        else
            pattern = Pattern.compile("[\\d]+");

        if (pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }

    public boolean onlyNumbers(String string, int digitLimit, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            pattern = Pattern.compile("[\\d]{0,"+digitLimit+"}");
        else
            pattern = Pattern.compile("[\\d]{1,"+digitLimit+"}");

        if (pattern.matcher(string).matches())
        {
            System.out.println("true");
            ret = true;
        }
        else
        {
            System.out.println("false");
            ret = false;
        }

        return ret;
    }

    public boolean moneyValue(String string, int leftDigits, int rightDigits, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            pattern = Pattern.compile("\\d{0,"+leftDigits+"}(\\.\\d{1,"+rightDigits+"})?");
        else
            pattern = Pattern.compile("\\d{1,"+leftDigits+"}(\\.\\d{1,"+rightDigits+"})?");

        if (pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }


    public boolean moneyValueWithNegative(String string, int leftDigits, int rightDigits, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            pattern = Pattern.compile("(-)?\\d{0,"+leftDigits+"}(\\.\\d{1,"+rightDigits+"})?");
        else
            pattern = Pattern.compile("(-)?\\d{1,"+leftDigits+"}(\\.\\d{1,"+rightDigits+"})?");

        if (pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }

    public boolean onlyChars(String string, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            pattern = Pattern.compile("[a-zA-Z]*");
        else
            pattern = Pattern.compile("[a-zA-Z]+");

        if (pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }

    public boolean onlyCharsWithWhiteSpace(String string, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            pattern = Pattern.compile("[a-zA-Z]*[\\s[a-zA-Z]]*");
        else
            pattern = Pattern.compile("[a-zA-Z]+[\\s[a-zA-Z]]*");

        if (pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }

    public boolean isEmail(String string, boolean allowEmpty) 
    {
        boolean ret = false;

        if(allowEmpty)
            pattern = Pattern.compile("(^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$|^$)?");
        else
            pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$|^$");

        if(string != null)
        {
            if (pattern.matcher(string).matches())
                ret = true;
            else
                ret = false;
        }
        else
        {
            ret = allowEmpty ? true:false;
        }

        return ret;
    }

    public boolean composedName(String string)
    {
        boolean ret;

        pattern = Pattern.compile("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$");

        if (pattern.matcher(string).matches())
            ret = true; 
        else
            ret = false;
        
        return ret;
    }

    public boolean isCatalogueCode(String string, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            pattern = Pattern.compile("([1-6]\\d\\d\\d\\d)?");
        else
            pattern = Pattern.compile("[1-6]\\d\\d\\d\\d");

        if (pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }
}