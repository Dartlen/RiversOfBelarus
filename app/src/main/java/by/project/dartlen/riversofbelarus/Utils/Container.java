package by.project.dartlen.riversofbelarus.Utils;

import by.project.dartlen.riversofbelarus.data.remote.Day;

/***
 * Created by Dartlen on 24.12.2017.
 */

public class Container {
    private Day day;
    private String namePost;

    public Container(Day day, String namePost){
        setDay(day);
        setNamePost(namePost);
    }
    public void setDay(Day day) {
        this.day = day;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    public Day getDay() {
        return day;
    }

    public String getNamePost() {
        return namePost;
    }
}
