package com.sna.fototeka;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

class SelectDocsFromDatabase extends AsyncTask<String,Void, List<Doc>> {
    private final Context context;
    private String docName;
    public SelectDocsFromDatabase(final Context mContext){
        context = mContext;
    }
    @Override
    protected List<Doc> doInBackground(String ...params) {
        AppDatabase db = App.getInstance().getDatabase();
        docName = params[0];
        return  db.docDao().getDocsByName(docName);
    }

    @Override
    protected void onPostExecute(List<Doc> docs) {
        super.onPostExecute(docs);
        if(docs.size()>0){
            Toast.makeText( context, "Документ с таким наименованием уже есть.", Toast.LENGTH_LONG).show();
        } else{
            InsertDocToDatabase insertDoc = new InsertDocToDatabase(context);
            insertDoc.execute(docName);

        }
    }
}
