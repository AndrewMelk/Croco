package croco.com.croco.db;

import java.util.ArrayList;

import croco.com.croco.objects.Game;
import croco.com.croco.objects.Player;

/**
 * Created by Andrew on 11.04.2017.
 */

public interface CrocoHandler {
    public void addGame(ArrayList<Player> players);
    public ArrayList<Player> getLastGamePlayers();
    public ArrayList<Game> getAllGames();
    public int getLastGameId();
    public ArrayList<Player> getLastGame();
    public void deleteAll();
}
