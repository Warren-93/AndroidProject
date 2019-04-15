package project.suzieqcraft.View;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;
import project.suzieqcraft.Controller.Connection;
import project.suzieqcraft.Controller.CustomAdapter;
import project.suzieqcraft.Model.Product;
import project.suzieqcraft.Model.User;
import project.suzieqcraft.R;

public class AppMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView displayUserEmail, displayUsersName;

    public RecyclerView recyclerViewer;
    public CustomAdapter adapter;
    private ArrayList<Product> productList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        displayUserEmail = findViewById( R.id.displayUserEmail );
        displayUsersName = findViewById( R.id.displayUsersName );

        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );


        //Setup Recycler View and get Products from database execution
        new BackgroundProducts().execute();
        recyclerViewer = findViewById( R.id.recyclerViewer );

        //Layout Manager
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
        recyclerViewer.setLayoutManager( linearLayoutManager );

        //Fill Adapter with array list of products
        adapter = new CustomAdapter(productList);
        recyclerViewer.setAdapter(adapter);

        recyclerViewer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == (productList.size() - 1)){
                    new BackgroundProducts().execute();
                }
            }
        });
    }

    protected class BackgroundProducts extends AsyncTask<String, Void, String> {
        private String productjson_url;

        @Override
        protected void onPreExecute() {
            productjson_url = "https://mayar.abertay.ac.uk/~1605460/Android/Model/getProducts.php";
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL( productjson_url );
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate( values );
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute( result );

            try {
                JSONArray products = new JSONArray(result);
                ArrayList<HashMap<String, String>> jsonObjectArrayList;

                jsonObjectArrayList = new ObjectMapper().readValue(products.toString(), ArrayList.class);

                for (HashMap<String, String> productToBeAdded : jsonObjectArrayList) {
                    productList.add(new Product(Integer.parseInt(productToBeAdded.get("0")), productToBeAdded.get( "Product_Type" ), productToBeAdded.get("Product_Image")));
                }
                adapter = new CustomAdapter(productList);
                recyclerViewer.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

//    public boolean onCreateOptionsMenu(AppMenu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate( R.menu.menu, (android.view.Menu) menu );
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_productlist) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_basket) {

//        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_contactUs) {

        } else if (id == R.id.nav_exit) {

        }
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}

