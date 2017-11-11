
package willybrodus.binar_test.menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class IsiMenuActivity extends AppCompatActivity {
    private EditText edID;
    private String sID;
    private EditText edNAMA_MENU;
    private String sNAMA_MENU;
    private EditText edID_RESTORAN;
    private String sID_RESTORAN;
    private EditText edHARGA;
    private String sHARGA;
    private EditText edFOTO_MENU;
    private String sFOTO_MENU;
    private EditText edKETERANGAN;
    private String sKETERANGAN;
    private Button btnsimpanIsiMenuActivity;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuisi);
        progress = new ProgressDialog(this);
        progress.setMessage("Tunggu Sedang Memproses ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        edNAMA_MENU = (EditText) findViewById(R.id.edNAMA_MENU);
        edID_RESTORAN = (EditText) findViewById(R.id.edID_RESTORAN);
        edHARGA = (EditText) findViewById(R.id.edHARGA);
        edFOTO_MENU = (EditText) findViewById(R.id.edFOTO_MENU);
        edKETERANGAN = (EditText) findViewById(R.id.edKETERANGAN);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            String jsonIsiMenuActivity = b.getString("jsonIsiMenuActivity");
            Log.d(" jsonIsiMenuActivity", jsonIsiMenuActivity);
            Gson gson = new Gson();
            MenuCLASS IsiMenuActivity = gson.fromJson(jsonIsiMenuActivity, MenuCLASS.class);
            sID = IsiMenuActivity.getID();
            edNAMA_MENU.setText(IsiMenuActivity.getNAMA_MENU());
            edID_RESTORAN.setText(IsiMenuActivity.getID_RESTORAN());
            edHARGA.setText(IsiMenuActivity.getHARGA());
            edFOTO_MENU.setText(IsiMenuActivity.getFOTO_MENU());
            edKETERANGAN.setText(IsiMenuActivity.getKETERANGAN());
        }
        btnsimpanIsiMenuActivity = (Button) findViewById(R.id.btnsimpan);
        btnsimpanIsiMenuActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sNAMA_MENU = edNAMA_MENU.getText().toString();
                sID_RESTORAN = edID_RESTORAN.getText().toString();
                sHARGA = edHARGA.getText().toString();
                sFOTO_MENU = edFOTO_MENU.getText().toString();
                sKETERANGAN = edKETERANGAN.getText().toString();
                new SimpanIsiMenuActivity(null).execute();
            }
        });
    }//TODO AsyncTask SimpanIsiMenuActivity

    public class SimpanIsiMenuActivity extends AsyncTask<Void, Void, ArrayList<MenuCLASS>> {

        private ArrayList<MenuCLASS> listdataCombo;

        SimpanIsiMenuActivity(ArrayList<MenuCLASS> xListIsiMenuActivity) {
            listdataCombo = xListIsiMenuActivity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progress.show();
        }

        @Override
        protected ArrayList<MenuCLASS> doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            Gson gson = new Gson();

            BufferedReader bufreader = null;
            URL url = null;
            try {
                url = new URL(Config.SERVER_PHP + "Menu/simpanupdatemenuAndroid/");
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
                            .appendQueryParameter("edNAMA_MENU", sNAMA_MENU + "")
                            .appendQueryParameter("edID_RESTORAN", sID_RESTORAN + "")
                            .appendQueryParameter("edHARGA", sHARGA + "")
                            .appendQueryParameter("edFOTO_MENU", sFOTO_MENU + "")
                            .appendQueryParameter("edKETERANGAN", sKETERANGAN + "");
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

                    listdataCombo = gson.fromJson(bufreader, new TypeToken<List<MenuCLASS>>() {
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
        protected void onPostExecute(final ArrayList<MenuCLASS> xlistDataCombo) {
            progress.dismiss();
            if (xlistDataCombo != null) {
                Toast.makeText(getApplication(), "Input menu Sukses", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(IsiMenuActivity.this, ListMenuActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplication(), "PENYIMPANAN menu GAGAL", Toast.LENGTH_LONG).show();
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