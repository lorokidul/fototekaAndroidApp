package com.sna.fototeka;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<DocumentWithFiles> docsList;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.rv_item, parent, false);
        Item item = new Item(row);
        return item;
    }

    public void removeItem(int position) {
        docsList.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final int p = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayPages.class);
                intent.putExtra("doc",docsList.get(position).doc.name);
                context.startActivity(intent);
            }
        });





        ((Item)holder).textViewName.setText(docsList.get(position).doc.name);
        String key = docsList.get(position).doc.name;
        int nPages = docsList.get(position).pages.size();
        String suffix =  nPages == 1? " страница" :" страниц(ы)";
        ((Item)holder).textViewPages.setText( nPages+suffix );
        ((Item)holder).deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doc doc = docsList.get(position).doc;
                DeleteDocFromDatabase deleteDocFromDatabase = new DeleteDocFromDatabase();
                deleteDocFromDatabase.execute(doc);
                removeItem(position);

            }
        });
    };

    @Override
    public int getItemCount() {
        return docsList.size();
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewPages;
        ImageView deleteIcon;
        public Item(View itemView){
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.docNameItem);
            textViewPages = (TextView) itemView.findViewById(R.id.numberOfPages);
            deleteIcon = (ImageView) itemView.findViewById(R.id.deleteIcon);
        }
    }

    public Adapter(Context context,  List<DocumentWithFiles> docsList){
        this.context = context;
        this.docsList = docsList;

    }
}
