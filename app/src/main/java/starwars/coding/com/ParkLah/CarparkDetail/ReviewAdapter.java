package starwars.coding.com.ParkLah.CarparkDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import starwars.coding.com.ParkLah.Entity.Review;
import starwars.coding.com.ParkLah.R;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private Review r1 = new Review("Daniel W.", "Y77", 4.2, "Great Carpark! I love it!");
    private Review r2 = new Review("Chen C.","Y77",4.5,"It's okay");
    private Review r3 = new Review("Pranav S.","Y77",3.0,"Lots of covered parking");
    private Review r4 = new Review("Nathan D.","Y77",4.2,"Quite reasonably priced");
    private Review r5 = new Review("Sam D.","Y77",2.1,"Great value!");
    private Review r6 = new Review("Peter P.","Y77",1.2,"They sell watermelon also!");
    private Review r7 = new Review("Tony S.","Y77",4.3,"Not that great, my car was boiling after a while");
    private Review r8 = new Review("Steve R.","Y77",4.5,"Don't go on Saturday, very crowded");


    private Review[] reviews = {r1, r2, r3, r4, r5, r6, r7, r8};

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public TextView itemDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle =
                    (TextView)itemView.findViewById(R.id.item_title);
            itemDetail =
                    (TextView)itemView.findViewById(R.id.item_detail);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.carparkdetail_card_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        viewHolder.itemTitle.setText(reviews[i].getUserName());
        viewHolder.itemDetail.setText(reviews[i].getText());
    }

    @Override
    public int getItemCount(){
        return reviews.length;
    }


}