package com.sna.fototeka;

import android.content.Context;
import android.os.AsyncTask;



class InsertPageToDatabase extends AsyncTask<Page, Void, Void>  {

    private final Context context;
    public InsertPageToDatabase(final Context mContext){
        context = mContext;
    }
    @Override
    protected Void doInBackground(Page... params) {
        Page page = params[0];
        AppDatabase db = App.getInstance().getDatabase();
        PageDao pageDao = db.pageDao();
        DocDao docDao = db.docDao();

        Doc doc = docDao.getDocsByName(page.document).get(0);
        doc.numberOfPages=page.pageNumber;
        docDao.update(doc);
        pageDao.insert(page);
        return null;
    }


}
