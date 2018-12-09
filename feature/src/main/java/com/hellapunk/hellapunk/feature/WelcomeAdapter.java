package com.hellapunk.hellapunk.feature;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class WelcomeAdapter extends RecyclerView.Adapter<WelcomeAdapter.WelcomeViewHolder>{

    private Context mCtx;
    private List<WelcomeListings> welcomeList;

    public WelcomeAdapter(Context mCtx, List<WelcomeListings> welcomeList) {
        this.mCtx = mCtx;
        this.welcomeList = welcomeList;
    }

    @NonNull
    @Override
    public WelcomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.welcome_listing, null);

        return new WelcomeViewHolder(view, mCtx, welcomeList);
    }

    @Override
    public void onBindViewHolder(@NonNull WelcomeViewHolder holder, int position) {
        WelcomeListings welcome = welcomeList.get(position);

        holder.textWelcomeTitle.setText(welcome.getWelcomeTitle());
        holder.textWelcomeSummary.setText(welcome.getWelcomeSummary());
        holder.textWelcomeMsg.setText(welcome.getWelcomeMsg());
        Glide.with(mCtx)
            .load(welcome.getWelcomePic())
            .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return welcomeList.size();
    }

    class WelcomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textWelcomeTitle, textWelcomeSummary, textWelcomeMsg;
        Context ctx;
        List<WelcomeListings> welcome;

        public WelcomeViewHolder(View itemView, Context ctx, List<WelcomeListings> welcome) {
            super(itemView);

            itemView.setOnClickListener(this);
            this.ctx = ctx;
            this.welcome = welcome;

            imageView = itemView.findViewById(R.id.filler);
            textWelcomeTitle = itemView.findViewById(R.id.fillertext);
            textWelcomeSummary = itemView.findViewById(R.id.fillersummary);
            textWelcomeMsg = itemView.findViewById(R.id.fillermsg);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            WelcomeListings welcome = this.welcome.get(position);

            Intent i = new Intent(view.getContext(), WelcomeSingleActivity.class);
            i.putExtra("welcomeTitle", welcome.getWelcomeTitle());
            i.putExtra("welcomeMsg", welcome.getWelcomeMsg());
            i.putExtra("welcomePic", welcome.getWelcomePic());
            view.getContext().startActivity(i);
        }
    }
}
