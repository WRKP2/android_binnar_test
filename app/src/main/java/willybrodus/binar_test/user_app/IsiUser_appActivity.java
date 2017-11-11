
package willybrodus.binar_test.user_app;

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

public class IsiUser_appActivity extends AppCompatActivity {
    private EditText edID;
    private String sID;
    private EditText edNAMA;
    private String sNAMA;
    private EditText edALAMAT;
    private String sALAMAT;
    private EditText edEMAIL;
    private String sEMAIL;
    private EditText edPASWORD;
    private String sPASWORD;
    private Button btnsimpanIsiUser_appActivity;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_appisi);
        progress = new ProgressDialog(this);
        progress.setMessage("Tunggu Sedang Memproses ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        edNAMA = (EditText) findViewById(R.id.edNAMA);
        edALAMAT = (EditText) findViewById(R.id.edALAMAT);
        edEMAIL = (EditText) findViewById(R.id.edEMAIL);
        edPASWORD = (EditText) findViewById(R.id.edPASWORD);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            String jsonIsiUser_appActivity = b.getString("jsonIsiUser_appActivity");
            Gson gson = new Gson();
            User_appCLASS IsiUser_appActivity = gson.fromJson(jsonIsiUser_appActivity, User_appCLASS.class);
            sID = IsiUser_appActivity.getID();
            edNAMA.setText(IsiUser_appActivity.getNAMA());
            edALAMAT.setText(IsiUser_appActivity.getALAMAT());
            edEMAIL.setText(IsiUser_appActivity.getEMAIL());
            edPASWORD.setText(IsiUser_appActivity.getPASWORD());
        }
        btnsimpanIsiUser_appActivity = (Button) findViewById(R.id.btnsimpan);
        btnsimpanIsiUser_appActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sNAMA = edNAMA.getText().toString();
                sALAMAT = edALAMAT.getText().toString();
                sEMAIL = edEMAIL.getText().toString();
                sPASWORD = edPASWORD.getText().toString();
                new SimpanIsiUser_appActivity(null).execute();
            }
        });
    }//TODO AsyncTask SimpanIsiUser_appActivity

    public class SimpanIsiUser_appActivity extends AsyncTask<Void, Void, ArrayList<User_appCLASS>> {

        private ArrayList<User_appCLASS> listdataCombo;

        SimpanIsiUser_appActivity(ArrayList<User_appCLASS> xListIsiUser_appActivity) {
            listdataCombo = xListIsiUser_appActivity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progress.show();
        }

        @Override
        protected ArrayList<User_appCLASS> doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            Gson gson = new Gson();

            BufferedReader bufreader = null;
            URL url = null;
            try {
                url = new URL(Config.SERVER_PHP + "User_app/simpanupdateuser_appAndroid/");
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
                            .appendQueryParameter("edNAMA", sNAMA + "")
                            .appendQueryParameter("edALAMAT", sALAMAT + "")
                            .appendQueryParameter("edEMAIL", sEMAIL + "")
                            .appendQueryParameter("edPASWORD", sPASWORD + "");
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

                    listdataCombo = gson.fromJson(bufreader, new TypeToken<List<User_appCLASS>>() {
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
        protected void onPostExecute(final ArrayList<User_appCLASS> xlistDataCombo) {
            progress.dismiss();
            if (xlistDataCombo != null) {
                Toast.makeText(getApplication(), "Input user_app Sukses", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(IsiUser_appActivity.this, ListUser_appActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplication(), "PENYIMPANAN user_app GAGAL", Toast.LENGTH_LONG).show();
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