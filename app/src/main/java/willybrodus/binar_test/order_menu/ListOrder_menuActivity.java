
package willybrodus.binar_test.order_menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import willybrodus.binar_test.helper.ClickListener;
import willybrodus.binar_test.helper.Config;
import willybrodus.binar_test.helper.RecyclerTouchListener;


public class ListOrder_menuActivity extends AppCompatActivity {
    private ArrayList<Order_menuCLASS> xLsorder_menu = null;
    private Adapterorder_menu order_menu;
    private RecyclerView rv;
    private LinearLayoutManager llm;
    private GridLayoutManager llmG;
    public ProgressDialog progress;
    public int posisiOnLOngClick = -1;
    private Button btnInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_menulist);
        progress = new ProgressDialog(this);
        progress.setMessage("Tunggu Sedang Memproses ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            String jsonListOrder_menuActivity = b.getString("jsonListOrder_menuActivity");
            Gson gson = new Gson();
            Order_menuCLASS ListOrder_menuActivity = gson.fromJson(jsonListOrder_menuActivity, Order_menuCLASS.class);
        }


        rv = (RecyclerView) findViewById(R.id.rv_recycler);
        rv.setHasFixedSize(true);
        xLsorder_menu = new ArrayList<Order_menuCLASS>();
        llm = new LinearLayoutManager(this);
        new listListOrder_menuActivity("").execute();
        rv.addOnItemTouchListener(new RecyclerTouchListener(this, rv, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Order_menuCLASS order_menuclass = xLsorder_menu.get(position);
                if (!order_menuclass.getID().trim().equals("")) {
                    Intent intent = new Intent(ListOrder_menuActivity.this, IsiOrder_menuActivity.class);
                    Gson gson = new Gson();
                    String jsonIsiOrder_menuActivity = gson.toJson(order_menuclass);
                    intent.putExtra("jsonIsiOrder_menuActivity", jsonIsiOrder_menuActivity);
                    Toast.makeText(getApplication(), "OnKlik", Toast.LENGTH_LONG).show();

//                    Log.d("jsonIsiOrder_menuActivity", jsonIsiOrder_menuActivity.toString());
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplication(), "Data Not Found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        btnInput = (Button) findViewById(R.id.btnInput);
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListOrder_menuActivity.this, IsiOrder_menuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onPause() {
        super.onPause();
        if ((progress != null) && progress.isShowing())
            progress.dismiss();
        progress = null;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }//TODO AsyncTask GET listListOrder_menuActivity

    public class listListOrder_menuActivity extends AsyncTask<Void, Void, ArrayList<Order_menuCLASS>> {

        private ArrayList<Order_menuCLASS> listdataCombo;
        private String isearch;

        listListOrder_menuActivity(String search) {
            this.isearch = search;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progress.show();
        }

        @Override
        protected ArrayList<Order_menuCLASS> doInBackground(Void... params) {

            Gson gson = new Gson();

            BufferedReader bufreader = null;
            URL url = null;
            try {
                url = new URL(Config.SERVER_PHP + "Order_menu/readorder_menuAndroid/");
                HttpURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setReadTimeout(30000);
//                    urlConnection.setConnectTimeout(35000);
//                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);

                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("search", isearch + "");

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
            if ((progress != null) && progress.isShowing())
                progress.dismiss();
            posisiOnLOngClick = -1;

            if (xlistDataCombo != null) {
                if (xlistDataCombo.size() > 0) {
                    xLsorder_menu.clear();
                    xLsorder_menu.addAll(xlistDataCombo);

                    SetListListOrder_menuActivity();

                }
            }

        }


        @Override
        protected void onCancelled() {

            progress.dismiss();
        }
    }

    public void SetListListOrder_menuActivity() {
        order_menu = new Adapterorder_menu(xLsorder_menu);
        rv.setAdapter(order_menu);
        rv.setLayoutManager(llm);

    }//TODO AsyncTask DeletListOrder_menuActivity

    public class DeleteListOrder_menuActivity extends AsyncTask<Void, Void, ArrayList<Order_menuCLASS>> {
        private ArrayList<Order_menuCLASS> Listproduk;
        private String iID;

        DeleteListOrder_menuActivity(String xID) {
            iID = xID;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progress.show();
        }

        @Override
        protected ArrayList<Order_menuCLASS> doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.


            URL url = null;


            try {

                url = new URL(Config.SERVER_PHP + "Order_menu/deletorder_menuAndroid/");
                HttpURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setReadTimeout(30000);
//                    urlConnection.setConnectTimeout(35000);
//                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);


                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("edidx", iID);
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
                    BufferedReader bufreader = null;
                    //List<ItemHasilSearch> buflistitemSearch= new ArrayList<>();
                    bufreader = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));

                    @SuppressWarnings("serial")
                    Gson gson = new Gson();

//
//                    String  line;
//                    String Sresponse="";
//
//
//                    while ((line=bufreader.readLine()) != null) {
//                        Sresponse+=line;
//                    }
//                    Log.d("Data hasil Search ", "> " + Sresponse+"\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(final ArrayList<Order_menuCLASS> listSize) {
            if ((progress != null) && progress.isShowing())
                progress.dismiss();

            new listListOrder_menuActivity("").execute();

        }


        @Override
        protected void onCancelled() {
            if ((progress != null) && progress.isShowing())
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
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
