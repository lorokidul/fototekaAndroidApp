package com.sna.fototeka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    String[] items;
    String[] pages;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.rv_item, parent, false);
        Item item = new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Item)holder).textViewName.setText(items[position]);
        ((Item)holder).textViewPages.setText(MainActivity.documents.get(items[position]).getNumberOfPages() +" страниц");
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewPages;
        public Item(View itemView){
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.docNameItem);
            textViewPages = (TextView) itemView.findViewById(R.id.numberOfPages);

        }
    }

    public Adapter(Context context, String[] items){
        this.context = context;
        this.items = items;
        //this.docs = docs;

    }
}
