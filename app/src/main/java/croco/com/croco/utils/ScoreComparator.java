package croco.com.croco.utils;

import java.util.Comparator;

import croco.com.croco.objects.Player;

/**
 * Created by Andrew on 05.04.2017.
 */

public class ScoreComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o2.getGameScore().compareTo(o1.getGameScore());
    }
}