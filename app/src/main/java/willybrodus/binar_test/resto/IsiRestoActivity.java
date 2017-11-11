
package willybrodus.binar_test.resto;

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

public class IsiRestoActivity extends AppCompatActivity {
    private EditText edid;
    private String sid;
    private EditText edNama_Resto;
    private String sNama_Resto;
    private EditText edAlamat;
    private String sAlamat;
    private EditText edno_tlp;
    private String sno_tlp;
    private Button btnsimpanIsiRestoActivity;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restoisi);
        progress = new ProgressDialog(this);
        progress.setMessage("Tunggu Sedang Memproses ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        edNama_Resto = (EditText) findViewById(R.id.edNama_Resto);
        edAlamat = (EditText) findViewById(R.id.edAlamat);
        edno_tlp = (EditText) findViewById(R.id.edno_tlp);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            String jsonIsiRestoActivity = b.getString("jsonIsiRestoActivity");
            Log.d(" jsonIsiRestoActivity", jsonIsiRestoActivity);
            Gson gson = new Gson();
            RestoCLASS IsiRestoActivity = gson.fromJson(jsonIsiRestoActivity, RestoCLASS.class);
            sid = IsiRestoActivity.getId();
            edNama_Resto.setText(IsiRestoActivity.getNama_Resto());
            edAlamat.setText(IsiRestoActivity.getAlamat());
            edno_tlp.setText(IsiRestoActivity.getNo_tlp());
        }
        btnsimpanIsiRestoActivity = (Button) findViewById(R.id.btnsimpan);
        btnsimpanIsiRestoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sNama_Resto = edNama_Resto.getText().toString();
                sAlamat = edAlamat.getText().toString();
                sno_tlp = edno_tlp.getText().toString();
                new SimpanIsiRestoActivity(null).execute();
            }
        });
    }//TODO AsyncTask SimpanIsiRestoActivity

    public class SimpanIsiRestoActivity extends AsyncTask<Void, Void, ArrayList<RestoCLASS>> {

        private ArrayList<RestoCLASS> listdataCombo;

        SimpanIsiRestoActivity(ArrayList<RestoCLASS> xListIsiRestoActivity) {
            listdataCombo = xListIsiRestoActivity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progress.show();
        }

        @Override
        protected ArrayList<RestoCLASS> doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            Gson gson = new Gson();

            BufferedReader bufreader = null;
            URL url = null;
            try {
                url = new URL(Config.SERVER_PHP + "Resto/simpanupdaterestoAndroid/");
                HttpURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setReadTimeout(30000);
//                    urlConnection.setConnectTimeout(35000);
//                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);

                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("edid", sid + "")
                            .appendQueryParameter("edNama_Resto", sNama_Resto + "")
                            .appendQueryParameter("edAlamat", sAlamat + "")
                            .appendQueryParameter("edno_tlp", sno_tlp + "");
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

                    listdataCombo = gson.fromJson(bufreader, new TypeToken<List<RestoCLASS>>() {
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
        protected void onPostExecute(final ArrayList<RestoCLASS> xlistDataCombo) {
            progress.dismiss();
            if (xlistDataCombo != null) {
                Toast.makeText(getApplication(), "Input resto Sukses", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(IsiRestoActivity.this, ListRestoActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplication(), "PENYIMPANAN resto GAGAL", Toast.LENGTH_LONG).show();
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