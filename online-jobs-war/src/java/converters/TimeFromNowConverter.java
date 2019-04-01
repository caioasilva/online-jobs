/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import static com.sun.faces.util.MessageUtils.CONVERSION_ERROR_MESSAGE_ID;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author caio
 */
@FacesConverter("timefromnow")
public class TimeFromNowConverter implements Converter {

    final static long ONE_SECOND = 1000;
    final static long ONE_MINUTE = ONE_SECOND * 60;
    final static long ONE_HOUR = ONE_MINUTE * 60;
    final static long ONE_DAY = ONE_HOUR * 24;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String[] words = value.split(" ");
        Date ret = null;
        if (value.isEmpty()) {
            return null;
        } else {
            if (words[1].substring(0, 3).equals("day")) {
                long now = new Date().getTime();
                long diff = Long.getLong(words[0]) * ONE_DAY;
                ret = new Date(now - diff);
            } else if (words[1].substring(0, 4).equals("hour")) {
                long now = new Date().getTime();
                long diff = Long.getLong(words[0]) * ONE_HOUR;
                ret = new Date(now - diff);
            } else if (words[1].substring(0, 6).equals("minute")) {
                long now = new Date().getTime();
                long diff = Long.getLong(words[0]) * ONE_MINUTE;
                ret = new Date(now - diff);
            } else if (value.equals("now")) {
                ret = new Date();
            }
        }
        return ret;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Date) {
            Date input = (Date) value;
            // Difference between today and date object
            long diff = new Date().getTime() - input.getTime();

            StringBuilder res = new StringBuilder();
            long calc = 0;
            if (diff >= ONE_MINUTE) {
                calc = diff / ONE_DAY;
                if (calc > 0) {
                    return res.append(calc).append(" day").append(calc > 1 ? "s" : "").append(" ago").toString();
                }

                calc = diff / ONE_HOUR;
                if (calc > 0) {
                    return res.append(calc).append(" hour").append(calc > 1 ? "s" : "").append(" ago").toString();
                }

                calc = diff / ONE_MINUTE;
                return res.append(calc).append(" minute").append(calc > 1 ? "s" : "").append(" ago").toString();
            }
            return "now";
        } else {
            FacesMessage errMsg = new FacesMessage(CONVERSION_ERROR_MESSAGE_ID);
            FacesContext.getCurrentInstance().addMessage(null, errMsg);
            throw new ConverterException(errMsg.getSummary());
        }

    }
}
