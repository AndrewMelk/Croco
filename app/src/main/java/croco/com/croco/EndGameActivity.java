package croco.com.croco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import croco.com.croco.adapters.EndGamePlayerListAdapter;
import croco.com.croco.adapters.PlayersListAdapter;
import croco.com.croco.objects.Player;
import croco.com.croco.utils.ScoreComparator;

/**
 * Created by Andrew on 05.04.2017.
 */

public class EndGameActivity extends Activity {
    private ListView listView;
    private ArrayList<Player> players;
    private Button startNewGameBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game_layout);

        players = new ArrayList<>();
        players = getIntent().getParcelableArrayListExtra("endGamePlayersList");
        System.out.println("Player List In EndGameActivity " + players.toString());
        Collections.sort(players, new ScoreComparator());
        listView = (ListView) findViewById(R.id.ListView01);
        startNewGameBtn = (Button) findViewById(R.id.startNewGameBtn);
        listView.setAdapter(new EndGamePlayerListAdapter(this,players));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Player player = (Player)o;
                Toast.makeText(EndGameActivity.this, "You select: " + player.getName(), Toast.LENGTH_LONG).show();
            }
        });

        startNewGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGameActivity.this,MainActivity.class);
                intent.putParcelableArrayListExtra("oldGameArrayListOfPlayers",players);
                startActivity(intent);
            }
        });
    }
}
