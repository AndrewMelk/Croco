package croco.com.croco.adapters;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import croco.com.croco.R;
import croco.com.croco.objects.Player;

/**
 * Created by Andrew on 30.03.2017.
 */

public class PlayersListAdapter extends BaseAdapter {
    private static ArrayList<Player> players;
    private LayoutInflater mInflater;

    public PlayersListAdapter(Context context, ArrayList<Player> players) {
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
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.player_row,null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.name);
            holder.txtTeam = (TextView) convertView.findViewById(R.id.team);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtName.setText(players.get(position).getName());
        holder.txtTeam.setText(players.get(position).getTeam());

        return convertView;
    }

    private static class ViewHolder {
        TextView txtName;
        TextView txtTeam;
    }


}
