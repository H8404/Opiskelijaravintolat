package fi.ahonen.emilia.hanna.mobilerestaurants;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Hanna on 26.11.2017.
 */

public class ReadRss extends AsyncTask<Void, Void, Void> {

    Context context;
    ProgressDialog progressDialog;

    public ReadRss(Context context){
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
    }

    @Override
    protected void onPreExecute(){
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... params){
        return null;
    }

}
