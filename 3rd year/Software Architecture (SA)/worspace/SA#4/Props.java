public class Props {
    public void getTimes(Properties props) throws Exception {
        String valueString;
        int value, checkInterval;

        valueString = propsGetProperty(props, " interval ", " monitor interval ");
        value = valueParsing(valueString, " monitor interval > 0");

        checkInterval = value;
        valueString = propsGetProperty(props, " duration ", " duration ");
        value = valueParsing(valueString, " duration > 0");
        checking(value, checkInterval, " duration % checkInterval ");

        valueString = propsGetProperty(props, " departure ", " departure offset ");
        value = valueParsing(valueString, " departure > 0");
        checking(value, checkInterval, " departure % checkInterval ");

    }

    public String propsGetProperty(Properties props, String property, String exception) throws Exception {
        valueString = props.getProperty(property);
        if (valueString == null) {
            throw new MissingPropertiesException(exception);
        }
        return valueString;
    }

    public int valueParsing(String valueString, String exception) throws Exception {
        value = Integer.parseInt(valueString);
        if (value <= 0) {
            throw new MissingPropertiesException(exception);
        }
        return value;
    }

    public void checking(int value, int checkInterval, String exception) throws Exception {
        if ((value % checkInterval) != 0) {
            throw new MissingPropertiesException(exception);
        }
    }
}
