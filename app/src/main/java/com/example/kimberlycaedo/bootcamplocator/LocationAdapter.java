package com.example.kimberlycaedo.bootcamplocator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Kimberly Cañedo on 14/08/2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder>{

        private ArrayList<Devslopes> locations;
        public LocationAdapter(ArrayList<Devslopes> locations) {
                this.locations = locations;
        }

        @Override
        public void onBindViewHolder(LocationViewHolder holder, int position) {
                final Devslopes location = locations.get(position);
                holder.updateUI(location);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                //Load Details page
                        }
                });
        }

        @Override
        public int getItemCount() {
                return locations.size();
        }

        @Override
        public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlocation, parent, false);
                return new LocationViewHolder(card);
        }

}
