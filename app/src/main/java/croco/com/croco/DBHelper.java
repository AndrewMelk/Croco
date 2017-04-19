package croco.com.croco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import croco.com.croco.db.CrocoHandler;
import croco.com.croco.objects.Game;
import croco.com.croco.objects.Player;

/**
 * Created by Andrew on 05.04.2017.
 */

public class DBHelper extends SQLiteOpenHelper implements CrocoHandler {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CrocoManager";
    private static final String TABLE_GAMES = "games";
    private static final String TABLE_PLAYERS = "players";
    private static final String GAMES_KEY_ID = "id";
    private static final String GAMES_KEY_NAME = "names";
    private static final String PLAYERS_KEY_ID = "id";
    private static final String PLAYERS_KEY_GAME_ID = "game_id";
    private static final String PLAYERS_KEY_NAME = "player_name";
    private static final String PLAYERS_KEY_TEAM = "player_team";
    private int lastId;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAME_TABLE = "CREATE TABLE " + TABLE_GAMES + "("
                + GAMES_KEY_ID + " INTEGER PRIMARY KEY,"
                + GAMES_KEY_NAME + " TEXT" +")";
        db.execSQL(CREATE_GAME_TABLE);
        String CREATE_PLAYERS_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "("
                + PLAYERS_KEY_ID + " INTEGER PRIMARY KEY,"
                + PLAYERS_KEY_GAME_ID + " INTEGER,"
                + PLAYERS_KEY_NAME + " TEXT,"
                + PLAYERS_KEY_TEAM + " TEXT,"
                + "FOREIGN KEY(" + PLAYERS_KEY_GAME_ID + ") REFERENCES " + TABLE_GAMES + "(" + GAMES_KEY_ID + ")"
                +")";
        db.execSQL(CREATE_PLAYERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);

        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);

        onCreate(db);
    }

    @Override
    public void addGame(ArrayList<Player> players) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues gameContent = new ContentValues();
        String names = "";
        for (Player player : players)
        {
            names += player.getName() + ", ";
        }
        System.out.println("insert names ---- " + names);
        gameContent.put(GAMES_KEY_NAME,names);
        db.insert(TABLE_GAMES,null,gameContent);
        ContentValues playersContent = new ContentValues();
        int tempid = getLastGameId();
        for (Player player : players){
            playersContent.put(PLAYERS_KEY_GAME_ID, tempid);
            playersContent.put(PLAYERS_KEY_NAME, player.getName());
            playersContent.put(PLAYERS_KEY_TEAM, player.getTeam());
            db.insert(TABLE_PLAYERS,null,playersContent);
        }
        db.close();


    }

    @Override
    public ArrayList<Player> getLastGamePlayers() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Player> players = new ArrayList<>();
        int tempId = getLastGameId();
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS + " WHERE " + PLAYERS_KEY_GAME_ID + "=" + tempId;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setName(cursor.getString(2));
                player.setTeam(cursor.getString(3));
                players.add(player);
            } while (cursor.moveToNext());
        }

        return players;
    }

    @Override
    public ArrayList<Game> getAllGames() {
        return null;
    }

    @Override
    public int getLastGameId() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_GAMES;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0){
            cursor.moveToLast();
            lastId = Integer.parseInt(cursor.getString(0));
        }


        System.out.println(" public int getLastGameId() ---- " + lastId);


        return lastId;
    }

    @Override
    public ArrayList<Player> getLastGame() {
        return null;
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GAMES,null,null);
        db.delete(TABLE_PLAYERS,null,null);
        db.close();
    }
}
