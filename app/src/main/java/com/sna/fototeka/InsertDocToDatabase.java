package com.sna.fototeka;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


class InsertDocToDatabase extends AsyncTask<String, Void, Doc>  {

    private final Context context;
    public InsertDocToDatabase(final Context mContext){
        context = mContext;
    }
    @Override
    protected Doc doInBackground(String... params) {
        String docName = params[0];
        DocDao docDao  = App.getInstance().getDatabase().docDao();
        Doc doc = createDoc(docName);
        //doc.id = docDao.getAll().size();
        docDao.insert(doc);
        return doc;
    }

    private Doc createDoc(String docName){
        Doc doc = new Doc();
        doc.name = docName;
        doc.category = "";
        doc.numberOfPages = 1;
        Locale locale = new Locale("ru");
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy",locale);
        Date date = new Date();
        doc.creationDate = df.format(date);
        return doc;
    }

    @Override
    protected void onPostExecute(Doc doc) {
        super.onPostExecute(doc);
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra("docName", doc.name);
        intent.putExtra("filename", doc.name + ".png");
        intent.putExtra("docId", doc.id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //context.finish();
    }
}
