package starwars.coding.com.ParkLah.CarparkDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import starwars.coding.com.ParkLah.R;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private String[] titles = {"Daniel W.",
            "Chen C.",
            "Pranav S.",
            "Nathan D.",
            "Sam D.",
            "Peter P.",
            "Tony S.",
            "Steve R."};

    private String[] details = {"Great Carpark! I love it!",
            "It's okay", "Lots of covered parking",
            "Quite reasonably priced", "Great value!",
            "They sell watermelon also!", "Not that great, my car was boiling after a while",
            "Don't go on Saturday, very crowded"};

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
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);
    }

    @Override
    public int getItemCount(){
        return titles.length;
    }


}