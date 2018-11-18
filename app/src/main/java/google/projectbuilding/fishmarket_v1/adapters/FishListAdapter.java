package google.projectbuilding.fishmarket_v1.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import google.projectbuilding.fishmarket_v1.DeskripsiActivity;
import google.projectbuilding.fishmarket_v1.MainActivity;
import google.projectbuilding.fishmarket_v1.R;
import google.projectbuilding.fishmarket_v1.custom.SquareImageView;
import google.projectbuilding.fishmarket_v1.models.FishModel;

public class FishListAdapter extends
        RecyclerView.Adapter<FishListAdapter.ViewHolder> {

    Context context;
    ArrayList<FishModel> fishList;

    public FishListAdapter(ArrayList<FishModel> fishList){
        this.fishList = fishList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.fish_search_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.fishName.setText(fishList.get(position).getName());
        holder.fishPrice.setText(fishList.get(position).getPrice());
        holder.fishermanName.setText(fishList.get(position).getFisherman());
        Glide.with(context)
                .load(fishList.get(position).getImage())
                .crossFade()
                .into(holder.fishImage);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DeskripsiActivity.class);
                intent.putExtra("nama", fishList.get(position).getName());
                intent.putExtra("deskripsi", fishList.get(position).getDescription());
                intent.putExtra("image", fishList.get(position).getDescription());
                intent.putExtra("price", fishList.get(position).getPrice());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SquareImageView fishImage;
        public TextView fishName;
        public TextView fishPrice;
        public TextView fishermanName;
        public View container;

        public ViewHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            fishImage = itemView.findViewById(R.id.fishImage);
            fishName = itemView.findViewById(R.id.fishName);
            fishPrice = itemView.findViewById(R.id.fishPrice);
            fishermanName = itemView.findViewById(R.id.fishermanName);
        }
    }
}