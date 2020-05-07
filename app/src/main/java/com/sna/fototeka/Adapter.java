package com.sna.fototeka;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    String[] items;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayPages.class);
                //intent.putExtra("filename",position);
                context.startActivity(intent);
            }
        });


        ((Item)holder).textViewName.setText(items[position]);
        String key = items[position];
        int nPages = MainActivity.documents.get(key).getNumberOfPages();
        String suffix =  nPages == 1? " страница" :" страниц(ы)";

        ((Item)holder).textViewPages.setText( nPages+suffix);
    };

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

    }
}
