package org.diiage.massey.moletapmassey;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alexis on 15/03/2018.
 */

/**
 * Représentation d'une session
 */
public class Session implements Parcelable {
    private Date date;
    private String nom;
    private ArrayList<Score> scores;

    public Session() {
        this.scores = new ArrayList<Score>();
    }

    public Session(Date date, String nom, ArrayList<Score> scores) {
        this.date = date;
        this.nom = nom;
        this.scores = scores;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public void addScore(Score score){
        this.scores.add(score);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(date.toString());
        dest.writeString(nom);
        dest.writeArray(scores.toArray());
    }

    public static final Parcelable.Creator<Session> CREATOR = new Parcelable.Creator<Session>()
    {
        @Override
        public Session createFromParcel(Parcel source)
        {
            return new Session(source);
        }

        @Override
        public Session[] newArray(int size)
        {
            return new Session[size];
        }
    };

    public Session(Parcel in) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(in.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.date = convertedDate;
        this.nom = in.readString();
        this.scores = in.readArrayList(Session.class.getClassLoader());
    }
}
