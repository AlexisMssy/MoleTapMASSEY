package org.diiage.massey.moletapmassey;

/**
 * Created by Alexis on 15/03/2018.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Représentation d'un score
 */
public class Score implements Parcelable {
    private int nbPoints;
    private int nbTaupeManquees;
    private int tempsReactionMax;
    private int tempsReactionMin;
    private int tempsReactionMoyen;

    public Score() {

    }

    public Score(int nbPoints, int nbTaupeManquees, int tempsReactionMax, int tempsReactionMin, int tempsReactionMoyen) {
        this.nbPoints = nbPoints;
        this.nbTaupeManquees = nbTaupeManquees;
        this.tempsReactionMax = tempsReactionMax;
        this.tempsReactionMin = tempsReactionMin;
        this.tempsReactionMoyen = tempsReactionMoyen;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public int getNbTaupeManquees() {
        return nbTaupeManquees;
    }

    public void setNbTaupeManquees(int nbTaupeManquees) {
        this.nbTaupeManquees = nbTaupeManquees;
    }

    public int getTempsReactionMax() {
        return tempsReactionMax;
    }

    public void setTempsReactionMax(int tempsReactionMax) {
        this.tempsReactionMax = tempsReactionMax;
    }

    public int getTempsReactionMin() {
        return tempsReactionMin;
    }

    public void setTempsReactionMin(int tempsReactionMin) {
        this.tempsReactionMin = tempsReactionMin;
    }

    public int getTempsReactionMoyen() {
        return tempsReactionMoyen;
    }

    public void setTempsReactionMoyen(int tempsReactionMoyen) {
        this.tempsReactionMoyen = tempsReactionMoyen;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(String.valueOf(nbPoints));
        dest.writeString(String.valueOf(nbTaupeManquees));
        dest.writeString(String.valueOf(tempsReactionMax));
        dest.writeString(String.valueOf(tempsReactionMin));
        dest.writeString(String.valueOf(tempsReactionMoyen));
    }

    public static final Parcelable.Creator<Score> CREATOR = new Parcelable.Creator<Score>()
    {
        @Override
        public Score createFromParcel(Parcel source)
        {
            return new Score(source);
        }

        @Override
        public Score[] newArray(int size)
        {
            return new Score[size];
        }
    };

    public Score(Parcel in) {
        this.nbPoints = Integer.parseInt(in.readString());
        this.nbTaupeManquees = Integer.parseInt(in.readString());
        this.tempsReactionMax = Integer.parseInt(in.readString());
        this.tempsReactionMin = Integer.parseInt(in.readString());
        this.tempsReactionMoyen = Integer.parseInt(in.readString());
    }
}
