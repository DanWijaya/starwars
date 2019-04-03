package starwars.coding.com.ParkLah.Search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.AppCompatTextView;

import java.util.List;

import starwars.coding.com.ParkLah.CarparkDetail.CarparkDetailActivity;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;
import starwars.coding.com.ParkLah.R;

public class CarparkSummaryAdapter extends RecyclerView.Adapter<CarparkSummaryAdapter.ViewHolder> {

    private List<CarparkInfoRecord > records;
    class ViewHolder extends RecyclerView.ViewHolder{

        public AppCompatTextView carparkNumber;
        public AppCompatTextView address;
        public AppCompatTextView slots;
        public AppCompatTextView distance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carparkNumber = (AppCompatTextView)itemView.findViewById(R.id.carpark_summary_number);
            address = address = (AppCompatTextView)itemView.findViewById(R.id.carpark_summary_address);
            slots = (AppCompatTextView)itemView.findViewById(R.id.carpark_summary_available_slots);
            distance = (AppCompatTextView)itemView.findViewById(R.id.carpark_summary_distance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Context context = v.getContext();
                    CarparkInfoRecord record = records.get(position);
                    Intent intent = new Intent(context, CarparkDetailActivity.class);
                    intent.putExtra("carpark", record);
                    context.startActivity(intent);
                }
            });


        }
    }

    @NonNull
    @Override
    public CarparkSummaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carpark_summary_card, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.carparkNumber.setText(records.get(i).getCarParkNo());
        viewHolder.address.setText(records.get(i).getAddress());
        viewHolder.distance.setText(String.valueOf(records.get(i).getDistance()));
        viewHolder.slots.setText(String.valueOf(records.get(i).getLotsAvailable()));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void setRecords(List<CarparkInfoRecord> records) {
        this.records = records;
    }
}
