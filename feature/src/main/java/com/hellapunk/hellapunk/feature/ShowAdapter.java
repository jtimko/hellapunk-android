package com.hellapunk.hellapunk.feature;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder>{

    private Context mCtx;
    private List<ShowListings> showList;

    public ShowAdapter(Context mCtx, List<ShowListings> showList) {
        this.mCtx = mCtx;
        this.showList = showList;
    }

    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.show_listing, null);

        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
        ShowListings show = showList.get(position);

        holder.textShowTitle.setText(show.getShowTitle());
        holder.textShowVenue.setText(show.getShowVenue());
        holder.textShowDate.setText(show.getShowDate());



        if (show.getPunklogo().equals("null")) {
            holder.imageView.setImageDrawable(mCtx.getDrawable(R.drawable.flyer));
        } else {
            Glide.with(mCtx)
                    .load(show.getPunklogo())
                    .into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textShowTitle, textShowVenue, textShowDate;

        public ShowViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.punklogo);
            textShowTitle = itemView.findViewById(R.id.showTitle);
            textShowVenue = itemView.findViewById(R.id.showVenue);
            textShowDate = itemView.findViewById(R.id.showDate);
        }
    }
}
