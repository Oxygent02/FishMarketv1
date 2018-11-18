package google.projectbuilding.fishmarket_v1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import google.projectbuilding.fishmarket_v1.models.FishModel;

public class CustomAdapter extends ArrayAdapter<FishModel>{

    private ArrayList<FishModel> fishModels;
    Context context;

    private static class ViewHolder{
        TextView outputTeam;
        ImageView teamBadge;
    }

    public CustomAdapter(ArrayList<FishModel> data, Context context){
        super(context,R.layout.item_row,data);
        this.fishModels = data;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FishModel teamModel = getItem(position);
        ViewHolder viewHolder;


        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_row, parent, false);


            viewHolder.teamBadge = convertView.findViewById(R.id.img_fish);
            viewHolder.outputTeam = convertView.findViewById(R.id.tv_fish_name);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.outputTeam.setText(teamModel.getName());

        Glide.with(context)
                .load(teamModel.getImage())
                .crossFade()
                .into(viewHolder.teamBadge);


        return convertView;
    }
}
