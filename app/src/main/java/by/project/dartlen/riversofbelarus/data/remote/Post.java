package by.project.dartlen.riversofbelarus.data.remote;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Created by Dartlen on 13.11.2017.
 */
@IgnoreExtraProperties
public class Post {
    public String city;
    public String number;
    public String river;
    public List<Day> days = new ArrayList<>();

    public List<Day> getDays() {
        return days;
    }

    public String getRiver() {
        return river;
    }

    public String getCity() {
        return city;
    }

    public String getNumber() {
        return number;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
