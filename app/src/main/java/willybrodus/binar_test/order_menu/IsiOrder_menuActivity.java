
package willybrodus.binar_test.order_menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import willybrodus.binar_test.R;
import willybrodus.binar_test.helper.Config;

public class IsiOrder_menuActivity extends AppCompatActivity {
    private EditText edID;
    private String sID;
    private EditText edID_RESTO;
    private String sID_RESTO;
    private EditText edID_MENU;
    private String sID_MENU;
    private EditText edID_USERAPP;
    private String sID_USERAPP;
    private EditText edJUMLAH;
    private String sJUMLAH;
    private EditText edORDER_DATE;
    private String sORDER_DATE;
    private EditText edSTATUS;
    private String sSTATUS;
    private EditText edALAMAT_PENGIRIMAN;
    private String sALAMAT_PENGIRIMAN;
    private Button btnsimpanIsiOrder_menuActivity;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_menuisi);
        progress = new ProgressDialog(this);
        progress.setMessage("Tunggu Sedang Memproses ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        edID_RESTO = (EditText) findViewById(R.id.edID_RESTO);
        edID_MENU = (EditText) findViewById(R.id.edID_MENU);
        edID_USERAPP = (EditText) findViewById(R.id.edID_USERAPP);
        edJUMLAH = (EditText) findViewById(R.id.edJUMLAH);
        edORDER_DATE = (EditText) findViewById(R.id.edORDER_DATE);
        edSTATUS = (EditText) findViewById(R.id.edSTATUS);
        edALAMAT_PENGIRIMAN = (EditText) findViewById(R.id.edALAMAT_PENGIRIMAN);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            String jsonIsiOrder_menuActivity = b.getString("jsonIsiOrder_menuActivity");
//            Log.d(" jsonIsiOrder_menuActivity", jsonIsiOrder_menuActivity );
            Gson gson = new Gson();
            Order_menuCLASS IsiOrder_menuActivity = gson.fromJson(jsonIsiOrder_menuActivity, Order_menuCLASS.class);
            sID = IsiOrder_menuActivity.getID();
            edID_RESTO.setText(IsiOrder_menuActivity.getID_RESTO());
            edID_MENU.setText(IsiOrder_menuActivity.getID_MENU());
            edID_USERAPP.setText(IsiOrder_menuActivity.getID_USERAPP());
            edJUMLAH.setText(IsiOrder_menuActivity.getJUMLAH());
            edORDER_DATE.setText(IsiOrder_menuActivity.getORDER_DATE());
            edSTATUS.setText(IsiOrder_menuActivity.getSTATUS());
            edALAMAT_PENGIRIMAN.setText(IsiOrder_menuActivity.getALAMAT_PENGIRIMAN());
        }
        btnsimpanIsiOrder_menuActivity = (Button) findViewById(R.id.btnsimpan);
        btnsimpanIsiOrder_menuActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sID_RESTO = edID_RESTO.getText().toString();
                sID_MENU = edID_MENU.getText().toString();
                sID_USERAPP = edID_USERAPP.getText().toString();
                sJUMLAH = edJUMLAH.getText().toString();
                sORDER_DATE = edORDER_DATE.getText().toString();
                sSTATUS = edSTATUS.getText().toString();
                sALAMAT_PENGIRIMAN = edALAMAT_PENGIRIMAN.getText().toString();
                new SimpanIsiOrder_menuActivity(null).execute();
            }
        });
    }//TODO AsyncTask SimpanIsiOrder_menuActivity

    public class SimpanIsiOrder_menuActivity extends AsyncTask<Void, Void, ArrayList<Order_menuCLASS>> {

        private ArrayList<Order_menuCLASS> listdataCombo;

        SimpanIsiOrder_menuActivity(ArrayList<Order_menuCLASS> xListIsiOrder_menuActivity) {
            listdataCombo = xListIsiOrder_menuActivity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progress.show();
        }

        @Override
        protected ArrayList<Order_menuCLASS> doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            Gson gson = new Gson();

            BufferedReader bufreader = null;
            URL url = null;
            try {
                url = new URL(Config.SERVER_PHP + "Order_menu/simpanupdateorder_menuAndroid/");
                HttpURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setReadTimeout(30000);
//                    urlConnection.setConnectTimeout(35000);
//                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);

                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("edID", sID + "")
                            .appendQueryParameter("edID_RESTO", sID_RESTO + "")
                            .appendQueryParameter("edID_MENU", sID_MENU + "")
                            .appendQueryParameter("edID_USERAPP", sID_USERAPP + "")
                            .appendQueryParameter("edJUMLAH", sJUMLAH + "")
                            .appendQueryParameter("edORDER_DATE", sORDER_DATE + "")
                            .appendQueryParameter("edSTATUS", sSTATUS + "")
                            .appendQueryParameter("edALAMAT_PENGIRIMAN", sALAMAT_PENGIRIMAN + "");
                    String query = builder.build().getEncodedQuery();

                    OutputStream os = urlConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();

                    //urlConnection.connect();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bufreader = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));
//                    String  line;
//                    String Sresponse="";
//
//                    while ((line=bufreader.readLine()) != null) {
//                        Sresponse+=line;
//                    }
//                    Log.d("Data hasil Search ", "> " + Sresponse+"");

                    listdataCombo = gson.fromJson(bufreader, new TypeToken<List<Order_menuCLASS>>() {
                    }.getType());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            return listdataCombo;
        }

        @Override
        protected void onPostExecute(final ArrayList<Order_menuCLASS> xlistDataCombo) {
            progress.dismiss();
            if (xlistDataCombo != null) {
                Toast.makeText(getApplication(), "Input order_menu Sukses", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(IsiOrder_menuActivity.this, ListOrder_menuActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplication(), "PENYIMPANAN order_menu GAGAL", Toast.LENGTH_LONG).show();
            }

        }


        @Override
        protected void onCancelled() {

            progress.dismiss();
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}