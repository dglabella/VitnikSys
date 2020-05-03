package vitniksys.backend.util;

import java.util.regex.*;

public class ExpressionChecker 
{
    private static ExpressionChecker _expressionChecker;

    private Pattern _pattern;

    private ExpressionChecker() 
    {
        
    }

    public static ExpressionChecker getExpressionChecker()
    {
        return ExpressionChecker._expressionChecker;
    }

    public boolean onlyNumbers(String string, boolean allowEmpty) 
    {
        boolean ret;

        if(allowEmpty)
            _pattern = Pattern.compile("[\\d]*");
        else
            _pattern = Pattern.compile("[\\d]+");

        if (_pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }

    public boolean onlyNumbers(String string, int digitLimit, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            _pattern = Pattern.compile("[\\d]{0,"+digitLimit+"}");
        else
            _pattern = Pattern.compile("[\\d]{1,"+digitLimit+"}");

        if (_pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }

    public boolean moneyValue(String string, int leftDigits, int rightDigits, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            _pattern = Pattern.compile("\\d{0,"+leftDigits+"}(\\.\\d{1,"+rightDigits+"})?");
        else
            _pattern = Pattern.compile("\\d{1,"+leftDigits+"}(\\.\\d{1,"+rightDigits+"})?");

        if (_pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }


    public boolean moneyValueWithNegative(String string, int leftDigits, int rightDigits, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            _pattern = Pattern.compile("(-)?\\d{0,"+leftDigits+"}(\\.\\d{1,"+rightDigits+"})?");
        else
            _pattern = Pattern.compile("(-)?\\d{1,"+leftDigits+"}(\\.\\d{1,"+rightDigits+"})?");

        if (_pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }

    public boolean onlyChars(String string, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            _pattern = Pattern.compile("[a-zA-Z]*");
        else
            _pattern = Pattern.compile("[a-zA-Z]+");

        if (_pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }

    public boolean onlyCharsWithWhiteSpace(String string, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            _pattern = Pattern.compile("[a-zA-Z]*[\\s[a-zA-Z]]*");
        else
            _pattern = Pattern.compile("[a-zA-Z]+[\\s[a-zA-Z]]*");

        if (_pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }

    public boolean isEmail(String string) 
    {
        boolean ret;

        _pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$|^$");

        if (_pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }

    public boolean isCodCat(String string, boolean allowEmpty)
    {
        boolean ret;

        if(allowEmpty)
            _pattern = Pattern.compile("([123456]\\d\\d\\d\\d)?");
        else
            _pattern = Pattern.compile("[123456]\\d\\d\\d\\d");

        if (_pattern.matcher(string).matches())
            ret = true;
        else
            ret = false;

        return ret;
    }

    public boolean composedName(String string)
    {
        boolean ret;

        _pattern = Pattern.compile("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$");

        if (_pattern.matcher(string).matches())
            ret = true; 
        else
            ret = false;
        
        return ret;
    }
}