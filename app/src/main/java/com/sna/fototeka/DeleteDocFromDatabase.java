package com.sna.fototeka;

import android.os.AsyncTask;


public class DeleteDocFromDatabase extends AsyncTask<Doc,Void, Void> {

    @Override
    protected Void doInBackground(Doc... params) {
        DocDao docDao = App.getInstance().getDatabase().docDao();
        docDao.delete(params[0]);
        PageDao pageDao = App.getInstance().getDatabase().pageDao();
        pageDao.deletePagesOfDoc(params[0].name);
        return null;

    }
}
