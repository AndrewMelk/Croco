package croco.com.croco.objects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Andrew on 30.03.2017.
 */

public class Player implements Parcelable {
    private String name;
    private String team;
    private int gameScore;
    private int tempScore;

    final static String LOG_TAG = "myLogs";


    public Player() {
    }

    public Player(String name, String team) {
        this.name = name;
        this.team = team;

    }
    public Player(String name, String team, int gameScore) {
        this.name = name;
        this.team = team;
        this.gameScore = gameScore;
    }

    protected Player(Parcel in) {
        name = in.readString();
        team = in.readString();
        gameScore = in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public void addGameScore(int tempScore){
        System.out.println("In Player class, game score" + this.getGameScore() + tempScore);
        this.setGameScore(this.getGameScore() + tempScore);

    }

    public String getName() { return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getGameScore() {
        return gameScore;
    }

    public void setGameScore(Integer gameScore) {
        this.gameScore = gameScore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d(LOG_TAG, "writeToParcel");
        dest.writeString(name);
        dest.writeString(team);
        dest.writeInt(gameScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (getGameScore() != player.getGameScore()) return false;
        if (!getName().equals(player.getName())) return false;
        return getTeam().equals(player.getTeam());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getTeam().hashCode();
        result = 31 * result + getGameScore();
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", gameScore=" + gameScore +
                '}';
    }
}
