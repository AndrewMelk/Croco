package croco.com.croco;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import croco.com.croco.adapters.PlayersListAdapter;
import croco.com.croco.objects.Player;
import croco.com.croco.objects.Words;


/**
 * Created by Andrew on 30.03.2017.
 */

public class GameActivity extends Activity {
    private Button showWordBtn;
    private Button wordGuessed;
    private Button nextWord;
    private Button nextPlayerBtn;
    private Button startGameBtn;

    private TextView playerName;
    private TextView playerScore;
    private TextView word;
    private TextView chronometer;


    private Button showDialog;

    private String clickStartButton;
    private Words gameWords;
    private List<String> typeOfWords = new ArrayList<String>();
    private List<String> testArray = new ArrayList<String>();
    private ArrayList<Player> playerList = new ArrayList<>();
    private int counter = 1;
    private int tempScore;
    private String gameTimeFromMainActivity;
    private long gameTime;
    private String quantityWordsGaveFromMainActivity;
    private int maxWords;
    Player player = new Player();
    Random randomGenerator;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        randomGenerator = new Random();
        clickStartButton = getResources().getString(R.string.clickStartButton);
        System.out.println("clickStartButton -----"+clickStartButton);


        showDialog = (Button) findViewById(R.id.buttonShowCustomDialog);

        //Initialize all elements on Activity
        showWordBtn = (Button) findViewById(R.id.showWordBtn);
        wordGuessed = (Button) findViewById(R.id.wordGuessedBtn);
        nextWord = (Button) findViewById(R.id.nextWordBtn);
        nextPlayerBtn = (Button) findViewById(R.id.nextPlayerBtn);
        startGameBtn = (Button) findViewById(R.id.startGameBtn);


        playerName = (TextView) findViewById(R.id.playerName);
        playerScore = (TextView) findViewById(R.id.playerScore);
        word = (TextView) findViewById(R.id.word);
        chronometer = (TextView) findViewById(R.id.chronometer);

        gameWords = new Words();

        nextWord.setVisibility(View.INVISIBLE);
        wordGuessed.setVisibility(View.INVISIBLE);

        gameTimeFromMainActivity = getIntent().getStringExtra("gameTime");
        System.out.println("gameTimeFromMainActivity ------- " + gameTimeFromMainActivity);
        gameTime = Long.parseLong(gameTimeFromMainActivity) * 1000;
        System.out.println("gameTime ------- " + gameTime);

        quantityWordsGaveFromMainActivity = getIntent().getStringExtra("quantityWordsInGame");
        System.out.println("quantityWordsInGame ------" + quantityWordsGaveFromMainActivity);
        maxWords = Integer.parseInt(quantityWordsGaveFromMainActivity);
        typeOfWords = getIntent().getStringArrayListExtra("spinnerList");
        System.out.println("spinnerList in game Activity -----" + typeOfWords.toString());
        //get List of Players from MainActivity
        playerList = getIntent().getParcelableArrayListExtra(Player.class.getCanonicalName());
        System.out.println("Player List In Game Activity" + playerList.toString());




        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(GameActivity.this);
                dialog.setContentView(R.layout.dialog_with_players);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                ListView  lv2 = (ListView) dialog.findViewById(R.id.ListView02);
                PlayersListAdapter playersListAdapter = new PlayersListAdapter(GameActivity.this,playerList);;
                lv2.setAdapter(playersListAdapter);


                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        for (String s : typeOfWords){
            if(s.equals(getResources().getString(R.string.proffessionsWords))){
                gameWords.setProffessionsWords();
            }else if (s.equals(getResources().getString(R.string.difficultWords))){
                gameWords.setDifficultWords();
            }else if (s.equals(getResources().getString(R.string.animalsWords))){
                gameWords.setAnimalsWords();
            }else if (s.equals(getResources().getString(R.string.apphorismsWords))){
                gameWords.setAphorismsWords();
            }else if (s.equals(getResources().getString(R.string.tvShowsWords))){
                gameWords.setTVShowsWords();
            }else if (s.equals(getResources().getString(R.string.famousBrandsWords))){
                gameWords.setFamousBrandsWords();
            }else if (s.equals(getResources().getString(R.string.hobbiesWords))){
                gameWords.setHobbiesWords();
            }else if (s.equals(getResources().getString(R.string.filmsWords))){
                gameWords.setFilmsWords();
            }
        }
//        testArray = testList();
        testArray = gameWords.getGameWords();
        System.out.println(testArray);
        playerName.setText(playerList.get(0).getName());
        playerScore.setText(playerList.get(0).getGameScore()+"");

        word.setVisibility(View.INVISIBLE);
        word.setText(getRandomWord());

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startGameBtn.setVisibility(View.GONE);
                nextWord.setVisibility(View.VISIBLE);
                wordGuessed.setVisibility(View.VISIBLE);


                countDownTimer = new CountDownTimer(gameTime, 1000) {

                    public void onTick(long millisUntilFinished) {
                        chronometer.setText(millisUntilFinished / 1000 + "");
                        chronometer.setTextSize(TypedValue.COMPLEX_UNIT_SP,60);
                        //here you can have your logic to set text to edittext
                    }

                    public void onFinish() {
                        nextWord.setVisibility(View.GONE);
                        wordGuessed.setVisibility(View.GONE);
                        chronometer.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
                        chronometer.setText("Time end!");

                    }



                }.start();
            }
        });

        //Touch Listener on circle button to show word
        showWordBtn.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {

                    word.setVisibility(View.VISIBLE);
                    showWordBtn.setBackground(getDrawable(R.drawable.button_bg_round_pressed));
                    return true;
                }else if(event.getAction() == MotionEvent.ACTION_UP ) {
                    word.setVisibility(View.GONE);
                    showWordBtn.setBackground(getDrawable(R.drawable.button_bg_round));
                    return true;
                }

                return false;
            }
        });

        nextPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
                chronometer.setText(clickStartButton);
                System.out.println("Counter = " + counter);
//                playerList.get(counter-1).setGameScore(tempScore);
//                playerList.get(counter-1).addGameScore(tempScore);
                nextWord.setVisibility(View.INVISIBLE);
                wordGuessed.setVisibility(View.INVISIBLE);
                startGameBtn.setVisibility(View.VISIBLE);
                if (countDownTimer!=null) {
                    countDownTimer.cancel();
                }
//                chronometer.setText(gameTime/1000+"");

                System.out.println("Player counter" + playerList.get(counter-1).toString());
                System.out.println("Player counter" + playerList.toString());
                Player toastPlayer = new Player();
                tempScore = 0;
                toastPlayer = getNextPlayer();
                playerName.setText(toastPlayer.getName());
                playerScore.setText(toastPlayer.getGameScore()+"");
                System.out.println("Player next" + toastPlayer.toString());
            }
        });

        nextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word.setText(getRandomWord());
            }
        });

        wordGuessed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (playerList.get(counter - 1).getGameScore() < maxWords - 1 && testArray.size()>1) {
                        tempScore++;
                        playerList.get(counter - 1).addGameScore(tempScore);
                        tempScore = 0;
                        playerScore.setText(playerList.get(counter - 1).getGameScore() + "");
                        testArray.remove(word.getText().toString());
                        System.out.println("test array after guessed word" + testArray.size());
                        word.setText(getRandomWord());
                    } else {
                        if(testArray.size()>0){
                            Toast.makeText(GameActivity.this, R.string.endOfArray, Toast.LENGTH_SHORT).show();
                        }
                        tempScore++;
                        playerList.get(counter - 1).addGameScore(tempScore);
                        System.out.println("Winner " + playerList.toString());
                        Intent intent = new Intent(GameActivity.this, EndGameActivity.class);
                        intent.putParcelableArrayListExtra("endGamePlayersList", playerList);
                        startActivity(intent);
                    }


            }
        });
    }

    public String getRandomWord(){
        Log.d("TestArray",testArray.size() + "");
        int index = randomGenerator.nextInt(testArray.size());
        String item = testArray.get(index);
//        System.out.println("Managers choice this week" + item + "our recommendation to you");
        return item;

    }

    public static List<String> testList(){
        List<String> testArray = new ArrayList<>();
        testArray.add("стриптизер");
        testArray.add("сантехник");
        testArray.add("сурдопереводчик");
        testArray.add("крупье");
        testArray.add("пожарный");
        testArray.add("дальнобойщик");
        testArray.add("психиатр");
        testArray.add("лифтер");
        testArray.add("прокурор");
        testArray.add("акушерка");
        testArray.add("скульптор");
        testArray.add("режиссер");
        testArray.add("кинолог");
        testArray.add("космонавт");
        testArray.add("инкассатор");
        testArray.add("дипломат");
        testArray.add("крановщик");
        testArray.add("химик");
        testArray.add("стюардесса");
        testArray.add("мерчандайзер");
        testArray.add("шахтер");
        testArray.add("гинеколог");
        testArray.add("пчеловод");
        testArray.add("дизайнер");
        testArray.add("электрик");
        testArray.add("дрессировщик");
        testArray.add("промоутер");
        testArray.add("археолог");
        testArray.add("ветеринар");
        testArray.add("дровосек");

        //Animals
        testArray.add("хамелеон");
        testArray.add("ротвейлер");
        testArray.add("краб");
        testArray.add("жук-навозник");
        testArray.add("выхухоль");
        testArray.add("морская звезда");
        testArray.add("удав");
        testArray.add("скунс");
        testArray.add("саранча");
        testArray.add("страус");
        testArray.add("ленивец");
        testArray.add("чихуахуа");
        testArray.add("блоха");
        testArray.add("енот-полоскун");
        testArray.add("креветка");
        testArray.add("божья коровка");
        testArray.add("муравьед");
        testArray.add("утконос");
        testArray.add("колибри");
        testArray.add("бобр");
        testArray.add("пеликан");
        testArray.add("павлин");
        testArray.add("гусеница");
        testArray.add("паук");
        testArray.add("динозавр");
        testArray.add("медуза");
        testArray.add("улитка");
        testArray.add("индюк");
        testArray.add("дикобраз");
        testArray.add("шиншилла");

        //Aphorisms
        testArray.add("подложить свинью");
        testArray.add("как с гуся вода");
        testArray.add("коту под хвост");
        testArray.add("медвежья услуга");
        testArray.add("заморить червячка");
        testArray.add("когда рак на горе свистнет");
        testArray.add("курам на смех");
        testArray.add("волк в овечьей шкуре");
        testArray.add("наступить на те же грабли");
        testArray.add("кот в мешке");
        testArray.add("не в своей тарелке");
        testArray.add("скатертью дорога");
        testArray.add("божий одуванчик");
        testArray.add("красота требует жертв");
        testArray.add("не разлей вода");
        testArray.add("выносить сор из избы");
        testArray.add("медведь на ухо наступил");

        //Famous people
        testArray.add("Владимир Жириновский");
        testArray.add("Мэрилин Мэнсон");
        testArray.add("Никита Михалков");
        testArray.add("Стас Михайлов");
        testArray.add("Наполеон Бонапарт");
        testArray.add("Леди Гага");
        testArray.add("Элтон Джон");
        testArray.add("Юрий Куклачев");
        testArray.add("Майкл Джексон");
        testArray.add("Юрий Гагарин");
        testArray.add("Исаак Ньютон");
        testArray.add("Ксения Собчак");
        testArray.add("Джеки Чан");
        testArray.add("Андрей Малахов");
        testArray.add("Альберт Эйнштейн");
        testArray.add("Алина Кабаева");
        testArray.add("Иван Грозный");
        testArray.add("Павел Воля");
        testArray.add("Максим Галкин");
        testArray.add("Верка Сердючка");

        //World Brands
        testArray.add("McDonald’s");
        testArray.add("Apple");
        testArray.add("Google");
        testArray.add("Pampers");
        testArray.add("Barbie");
        testArray.add("Windows");
        testArray.add("LEGO");
        testArray.add("Ferrari");
        testArray.add("Durex");
        testArray.add("Bounty");
        testArray.add("Twitter");
        testArray.add("Gallina Blanca");
        testArray.add("M&M’s");
        testArray.add("Whiskas");
        testArray.add("Playboy");
        testArray.add("Chanel №5");
        testArray.add("ВКонтакте");
        testArray.add("Oriflame");
        testArray.add("Ikea");
        testArray.add("Duracell");
        testArray.add("Jack Daniel’s");
        testArray.add("Burn");
        testArray.add(" Sprite");
        testArray.add("Pedigree");
        testArray.add("Nokia");
        testArray.add("Raffaello");
        testArray.add("Love Is");
        testArray.add("Harley-Davidson");
        testArray.add("Chupa Chups");
        testArray.add("YouTube");

        //Hobbies
        testArray.add("твистер");
        testArray.add("дайвинг");
        testArray.add("буккроссинг");
        testArray.add("бодиарт");
        testArray.add("паркур");
        testArray.add("шоппинг");
        testArray.add("пейнтбол");
        testArray.add("серфинг");
        testArray.add("настольный хоккей");
        testArray.add("зорбинг");
        testArray.add("поход");
        testArray.add("брейк-данс");
        testArray.add("оригами");
        testArray.add("кроссворд");
        testArray.add("покер");
        testArray.add("танец живота");
        testArray.add("экскурсия");
        testArray.add("граффити");
        testArray.add("цветоводство");
        testArray.add("бумеранг");
        testArray.add("кладоискательство");
        testArray.add("роупджампинг");
        testArray.add("КВН");
        testArray.add("театр");
        testArray.add("автостоп");
        testArray.add("скалолазание");
        testArray.add("Мафия");
        testArray.add("бильярд");
        testArray.add("опера");
        testArray.add("Фотошоп");

        return testArray;
    }

    public Player getNextPlayer(){
        word.setText(getRandomWord());
        int size = playerList.size();

        System.out.println("Counter" + counter);
        if(counter==size){
            counter = 0;
            player = playerList.get(counter);
            counter++;
            return player;
        }else {
            player = playerList.get(counter);
            counter++;
            return player;

        }

    }
}
