package croco.com.croco;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import croco.com.croco.adapters.PlayersListAdapter;
import croco.com.croco.objects.Player;
import croco.com.croco.utils.MultiSelectionSpinner;

public class MainActivity extends Activity {
    ArrayList<Player> players = new ArrayList<>();
    private ListView lv1;
    private Button startBtn;
    private Button addBtn;
    private Spinner timeSpinner;
    private Spinner quantityWordsSpinner;

    private String gameTime;
    private String quantityWordsInGame;
    private EditText etPlayerName;
    private EditText etPlayerTeam;
    private PlayersListAdapter playersListAdapter;
    private DBHelper database;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final MultiSelectionSpinner spinner =(MultiSelectionSpinner)findViewById(R.id.input1);

        final ArrayList<String> list = new ArrayList<String>();
        list.add(getResources().getString(R.string.proffessionsWords));
        list.add(getResources().getString(R.string.difficultWords));
        list.add(getResources().getString(R.string.animalsWords));
        list.add(getResources().getString(R.string.apphorismsWords));
        list.add(getResources().getString(R.string.tvShowsWords));
        list.add(getResources().getString(R.string.famousBrandsWords));
        list.add(getResources().getString(R.string.hobbiesWords));
        list.add(getResources().getString(R.string.filmsWords));


        spinner.setItems(list);
        database = new DBHelper(this);
        if(database.getLastGamePlayers()!=null){
            players = database.getLastGamePlayers();
        }
//        if (getIntent().getParcelableArrayListExtra("oldGameArrayListOfPlayers")!=null){
//            players = getIntent().getParcelableArrayListExtra("oldGameArrayListOfPlayers");
//        }
//        players = generateData();
        etPlayerName = (EditText) findViewById(R.id.etPlayerName);
        etPlayerTeam = (EditText) findViewById(R.id.etPlayerTeam);

        startBtn = (Button) findViewById(R.id.start);
        addBtn = (Button) findViewById(R.id.addBtn);

        timeSpinner = (Spinner) findViewById(R.id.time_spinner);
        quantityWordsSpinner = (Spinner) findViewById(R.id.quantity_of_words);

        playersListAdapter = new PlayersListAdapter(this,players);
        lv1 = (ListView) findViewById(R.id.ListView01);
        lv1.setAdapter(playersListAdapter);


        final ArrayAdapter<CharSequence> wordsAdapter = ArrayAdapter.createFromResource(this,
                R.array.words_array, android.R.layout.simple_spinner_item);
        wordsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantityWordsSpinner.setAdapter(wordsAdapter);

        quantityWordsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quantityWordsInGame = quantityWordsSpinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                quantityWordsInGame = "20";
            }
        });


        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this,
                R.array.time_array, android.R.layout.simple_spinner_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);
        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gameTime = timeSpinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gameTime = "30";
            }
        });




        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Player player = (Player)o;
                Toast.makeText(MainActivity.this, R.string.deleteItem, Toast.LENGTH_SHORT).show();
            }
        });


        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Object o = lv1.getItemAtPosition(position);
                        Player player = (Player)o;
                        players.remove(player);
                        playersListAdapter.notifyDataSetChanged();


                        return true;
                    }

        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(players.size()==8){
                    Toast.makeText(MainActivity.this,"Max number of players",Toast.LENGTH_LONG).show();
                    clearEditText(etPlayerName);
                    clearEditText(etPlayerTeam);
                } else {

                    if (!etPlayerName.getText().toString().equals("") && !etPlayerTeam.getText().toString().equals("")) {
                        players.add(addPlayer(etPlayerName.getText().toString(), etPlayerTeam.getText().toString()));
                        playersListAdapter.notifyDataSetChanged();
                        clearEditText(etPlayerName);
                        clearEditText(etPlayerTeam);
                        lv1.setSelection(lv1.getCount() - 1);
                    } else if (etPlayerName.getText().toString().equals("") && !etPlayerTeam.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "Please Enter Player Name", Toast.LENGTH_LONG).show();
                    } else if (!etPlayerName.getText().toString().equals("") && etPlayerTeam.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "Please Enter Player Team", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Please Enter Player Name and Team", Toast.LENGTH_LONG).show();

                    }


//                Toast.makeText(MainActivity.this, "List: " + players.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (players.size()!=0) {

                    ArrayList<String> wordsTypeList = new ArrayList<String>();
                    wordsTypeList = spinner.getSelectedStrings();
                    if(wordsTypeList.size()!=0) {
                        database.addGame(players);

                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        intent.putStringArrayListExtra("spinnerList", wordsTypeList);
                        intent.putExtra("quantityWordsInGame", quantityWordsInGame);
                        intent.putExtra("gameTime", gameTime);
                        intent.putParcelableArrayListExtra(Player.class.getCanonicalName(), players);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.selectTypeofWords),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, R.string.pleaseAddPlayers, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private ArrayList<Player> generateData(){
        ArrayList<Player> items = new ArrayList<Player>();
        items.add(new Player("Andrew","Red"));
        items.add(new Player("Helen","Green"));
        items.add(new Player("Tanesha","Blue"));

        return items;
    }

    private Player addPlayer(String name, String team){

            Player player = new Player(name, team);
            return player;

    }

    private void clearEditText(EditText v){
        v.setText("");
    }
}
