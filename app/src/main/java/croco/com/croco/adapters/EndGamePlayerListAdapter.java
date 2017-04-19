package croco.com.croco.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import croco.com.croco.R;
import croco.com.croco.objects.Player;

/**
 * Created by Andrew on 05.04.2017.
 */

public class EndGamePlayerListAdapter extends BaseAdapter {

    private static ArrayList<Player> players;
    private LayoutInflater mInflater;


    public EndGamePlayerListAdapter(Context context, ArrayList<Player> players) {
        this.players = players;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EndGameViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.player_row,null);
            holder = new EndGameViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.name);
            holder.txtTeam = (TextView) convertView.findViewById(R.id.team);
            holder.txtScore = (TextView) convertView.findViewById(R.id.score);

            convertView.setTag(holder);
        }else {
            holder = (EndGameViewHolder) convertView.getTag();
        }

        holder.txtName.setText(players.get(position).getName());
        holder.txtTeam.setText(players.get(position).getTeam());
        holder.txtScore.setText(players.get(position).getGameScore().toString());

        return convertView;
    }

    private static class EndGameViewHolder {
        TextView txtName;
        TextView txtTeam;
        TextView txtScore;
    }
}
