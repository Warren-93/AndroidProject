package project.suzieqcraft.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.MenuItem;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.navigation.NavigationView;

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


import project.suzieqcraft.Adapters.CustomAdapter;
import project.suzieqcraft.Interfaces.IProduct;
import project.suzieqcraft.Model.Product;
import project.suzieqcraft.R;

public class AppMenu extends AppCompatActivity implements IProduct,
         NavigationView.OnNavigationItemSelectedListener {

    TextView displayUserEmail, displayUsersName;

    public RecyclerView recyclerViewer;
    public CustomAdapter adapter;
    private ArrayList<Product> productList = new ArrayList();
    public CardView cardView;
    public ImageView imageView;
    public TextView productName;
    private IProduct pListener;
    String YOUR_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        displayUserEmail = findViewById( R.id.displayUserEmail );
        displayUsersName = findViewById( R.id.displayUsersName );
        cardView = findViewById( R.id.cardView );
        imageView = findViewById( R.id.imageView );
        productName = findViewById( R.id.productName );

        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        //Setup Recycler View and get Products from database execution
        adapter = new CustomAdapter(productList, this);
        recyclerViewer = findViewById( R.id.recyclerViewer );
        new BackgroundProducts().execute();

        //Layout Manager
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
        recyclerViewer.setLayoutManager( linearLayoutManager );

        YOUR_KEY = "product_key";
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, Gallery.class);
        intent.putExtra(YOUR_KEY, ((TextView) view).getText().toString());
        startActivity(intent);
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
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
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
                JSONArray products = new JSONArray( result );
                ArrayList<HashMap<String, String>> jsonObjectArrayList;

                jsonObjectArrayList = new ObjectMapper().readValue( products.toString(), ArrayList.class );

                for (HashMap<String, String> productToBeAdded : jsonObjectArrayList) {
                    productList.add( new Product( Integer.parseInt( productToBeAdded.get( "0" ) ), productToBeAdded.get( "Product_Type" ), productToBeAdded.get( "Product_Image" ) ) );
                }
                recyclerViewer.setAdapter( adapter );
                adapter.notifyDataSetChanged();

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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_productlist) {

            startActivity( new Intent( this, AppMenu.class ) );

        } else if (id == R.id.nav_gallery) {

            Intent intent = new Intent(this, Gallery.class);
            intent.putExtra(YOUR_KEY, item.getTitle().toString());
            startActivity(intent);

        } else if (id == R.id.nav_camera) {

            startActivity( new Intent( this, Camera.class ) );

        } else if (id == R.id.nav_location) {

            startActivity( new Intent( this, Location.class ) );

        } else if (id == R.id.nav_exit) {

            startActivity( new Intent( this, Home.class ) );

        }

        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}

