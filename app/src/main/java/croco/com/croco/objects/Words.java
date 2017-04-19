package croco.com.croco.objects;

import java.util.ArrayList;

/**
 * Created by Andrew on 10.04.2017.
 */

public class Words {
    private int id;
    private String word;
    private ArrayList<String> gameWords = new ArrayList<>();

    public Words( String word) {
        this.word = word;
    }

    public Words() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setProffessionsWords(){
        gameWords.add("стриптизер");
        gameWords.add("сантехник");
        gameWords.add("сурдопереводчик");
        gameWords.add("крупье");
        gameWords.add("пожарный");
        gameWords.add("дальнобойщик");
        gameWords.add("психиатр");
        gameWords.add("лифтер");
        gameWords.add("прокурор");
        gameWords.add("акушерка");
        gameWords.add("скульптор");
        gameWords.add("режиссер");
        gameWords.add("кинолог");
        gameWords.add("космонавт");
        gameWords.add("инкассатор");
        gameWords.add("дипломат");
        gameWords.add("крановщик");
        gameWords.add("химик");
        gameWords.add("стюардесса");
        gameWords.add("мерчандайзер");
        gameWords.add("шахтер");
        gameWords.add("гинеколог");
        gameWords.add("пчеловод");
        gameWords.add("дизайнер");
        gameWords.add("электрик");
        gameWords.add("дрессировщик");
        gameWords.add("промоутер");
        gameWords.add("археолог");
        gameWords.add("ветеринар");
        gameWords.add("дровосек");
    }

    public void setDifficultWords(){
        gameWords.add("априори");
        gameWords.add("парадигма");
        gameWords.add("менталитет");
        gameWords.add("конъюнктура");
        gameWords.add("цивилизация");
        gameWords.add("перспектива");
        gameWords.add("резонанс");
        gameWords.add("империя");
        gameWords.add("эклектика");
        gameWords.add("аллегория");
        gameWords.add("концепция");
        gameWords.add("постоянство");
        gameWords.add("ресурс");
        gameWords.add("антураж");
        gameWords.add("латентность");
        gameWords.add("индустриализация");
        gameWords.add("кредо");
        gameWords.add("плюрализм");
        gameWords.add("судьба");
        gameWords.add("контекст");
        gameWords.add("конфигурация");
        gameWords.add("инфраструктура");
        gameWords.add("ассимиляция");
        gameWords.add("протеже");
        gameWords.add("коммунизм");
        gameWords.add("дежавю");
        gameWords.add("консерватизм");
        gameWords.add("волюнтаризм");

    }

    public void setAnimalsWords(){
        gameWords.add("хамелеон");
        gameWords.add("ротвейлер");
        gameWords.add("краб");
        gameWords.add("жук-навозник");
        gameWords.add("выхухоль");
        gameWords.add("морская звезда");
        gameWords.add("удав");
        gameWords.add("скунс");
        gameWords.add("саранча");
        gameWords.add("страус");
        gameWords.add("ленивец");
        gameWords.add("чихуахуа");
        gameWords.add("блоха");
        gameWords.add("енот-полоскун");
        gameWords.add("креветка");
        gameWords.add("божья коровка");
        gameWords.add("муравьед");
        gameWords.add("утконос");
        gameWords.add("колибри");
        gameWords.add("бобр");
        gameWords.add("пеликан");
        gameWords.add("павлин");
        gameWords.add("гусеница");
        gameWords.add("паук");
        gameWords.add("динозавр");
        gameWords.add("медуза");
        gameWords.add("улитка");
        gameWords.add("индюк");
        gameWords.add("дикобраз");
        gameWords.add("шиншилла");

    }

    public void setAphorismsWords(){
        gameWords.add("подложить свинью");
        gameWords.add("как с гуся вода");
        gameWords.add("коту под хвост");
        gameWords.add("медвежья услуга");
        gameWords.add("заморить червячка");
        gameWords.add("когда рак на горе свистнет");
        gameWords.add("курам на смех");
        gameWords.add("волк в овечьей шкуре");
        gameWords.add("наступить на те же грабли");
        gameWords.add("кот в мешке");
        gameWords.add("не в своей тарелке");
        gameWords.add("скатертью дорога");
        gameWords.add("божий одуванчик");
        gameWords.add("красота требует жертв");
        gameWords.add("не разлей вода");
        gameWords.add("выносить сор из избы");
        gameWords.add("медведь на ухо наступил");

    }

    public void setTVShowsWords(){
        gameWords.add("Пока все дома");
        gameWords.add("Угадай мелодию");
        gameWords.add("Форт Боярд");
        gameWords.add("В мире животных");
        gameWords.add("Последний герой");
        gameWords.add("Поле чудес");
        gameWords.add("Дом-2");
        gameWords.add("Человек и закон");
        gameWords.add("Сам себе режиссер");
        gameWords.add("Модный приговор");
        gameWords.add("Comedy Club");
        gameWords.add("Звёзды на льду");
        gameWords.add("Давай поженимся");
        gameWords.add("Минута славы");
        gameWords.add("Ты не поверишь");
        gameWords.add("Пусть говорят");
        gameWords.add("Малахов +");
        gameWords.add("Аншлаг");
        gameWords.add("Наша Russia");
        gameWords.add("Большая разница");
        gameWords.add("Умники и умницы");
        gameWords.add("Что? Где? Когда?");

    }

    public void setFamousBrandsWords(){
        gameWords.add("McDonald’s");
        gameWords.add("Apple");
        gameWords.add("Google");
        gameWords.add("Pampers");
        gameWords.add("Barbie");
        gameWords.add("Windows");
        gameWords.add("LEGO");
        gameWords.add("Ferrari");
        gameWords.add("Durex");
        gameWords.add("Bounty");
        gameWords.add("Twitter");
        gameWords.add("Gallina Blanca");
        gameWords.add("M&M’s");
        gameWords.add("Whiskas");
        gameWords.add("Playboy");
        gameWords.add("Chanel №5");
        gameWords.add("ВКонтакте");
        gameWords.add("Oriflame");
        gameWords.add("Ikea");
        gameWords.add("Duracell");
        gameWords.add("Jack Daniel’s");
        gameWords.add("Burn");
        gameWords.add("Sprite");
        gameWords.add("Pedigree");
        gameWords.add("Nokia");
        gameWords.add("Raffaello");
        gameWords.add("Love Is");
        gameWords.add("Harley-Davidson");
        gameWords.add("Chupa Chups");
        gameWords.add("YouTube");

    }

    public void setHobbiesWords(){
        gameWords.add("твистер");
        gameWords.add("дайвинг");
        gameWords.add("буккроссинг");
        gameWords.add("бодиарт");
        gameWords.add("паркур");
        gameWords.add("шоппинг");
        gameWords.add("пейнтбол");
        gameWords.add("серфинг");
        gameWords.add("настольный хоккей");
        gameWords.add("зорбинг");
        gameWords.add("поход");
        gameWords.add("брейк-данс");
        gameWords.add("оригами");
        gameWords.add("кроссворд");
        gameWords.add("покер");
        gameWords.add("танец живота");
        gameWords.add("экскурсия");
        gameWords.add("граффити");
        gameWords.add("цветоводство");
        gameWords.add("бумеранг");
        gameWords.add("кладоискательство");
        gameWords.add("роупджампинг");
        gameWords.add("КВН");
        gameWords.add("театр");
        gameWords.add("автостоп");
        gameWords.add("скалолазание");
        gameWords.add("Мафия");
        gameWords.add("бильярд");
        gameWords.add("опера");
        gameWords.add("Фотошоп");

    }

    public void setFilmsWords(){
        gameWords.add("Аватар");
        gameWords.add("Человек-паук");
        gameWords.add("Американский пирог");
        gameWords.add("О чем говорят мужчины");
        gameWords.add("Интерны");
        gameWords.add("Один дома");
        gameWords.add("Симпсоны");
        gameWords.add("Кавказская пленница");
        gameWords.add("Стиляги");
        gameWords.add("Секс в большом городе");
        gameWords.add("Мадагаскар");
        gameWords.add("Кин-дза-дза");
        gameWords.add("Хатико");
        gameWords.add("Моя прекрасная няня");
        gameWords.add("Очень страшное кино");
        gameWords.add("Маша и медведь");
        gameWords.add("Мистер и миссис Смит");
        gameWords.add("Матрица");
        gameWords.add("Секс по дружбе");
        gameWords.add("Ну погоди!");
    }

    public ArrayList<String> getGameWords(){
        return gameWords;
    }

    @Override
    public String toString() {
        return "Words{" +
                "gameWords=" + gameWords +
                '}';
    }
}
