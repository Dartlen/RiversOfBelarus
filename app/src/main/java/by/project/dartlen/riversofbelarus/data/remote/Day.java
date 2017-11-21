package by.project.dartlen.riversofbelarus.data.remote;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * Created by Dartlen on 13.11.2017.
 */

public class Day {
    public Date from;
    public String ice;
    public Integer level;
    public String river;
    public Double temperature;
    public Date to;

    public Date getFrom() {
        return from;
    }

    public void setFrom(String from) {

        this.from = strDateToDate(from);
    }

    public Date getTo() {
        return to;
    }

    public void setTo(String to) {

        this.to = strDateToDate(to);
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = Double.parseDouble(temperature);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = Integer.parseInt(level);
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public Date strDateToDate(String dateStr){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = dateFormat.parse(dateStr);
            return date;
        }catch(Exception e){
            return new Date();
        }
    }
}
