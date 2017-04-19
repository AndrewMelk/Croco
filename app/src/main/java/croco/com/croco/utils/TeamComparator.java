package croco.com.croco.utils;

import java.util.Comparator;

import croco.com.croco.objects.Player;

/**
 * Created by Andrew on 10.04.2017.
 */

public class TeamComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o2.getTeam().compareTo(o1.getTeam());
    }
}
