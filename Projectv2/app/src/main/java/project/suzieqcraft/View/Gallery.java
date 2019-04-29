package project.suzieqcraft.View;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
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
import project.suzieqcraft.Controller.ImageAdapter;
import project.suzieqcraft.Model.Image;
import project.suzieqcraft.R;

public class Gallery extends AppCompatActivity {

        RecyclerView recyclerGalleryView;
        ImageAdapter adapter;
        ArrayList<Image> imageList = new ArrayList();
        ImageView galleryImage;
        CardView galleryCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gallery );
        galleryImage = findViewById( R.id.galleryImage );
        galleryCardView = findViewById( R.id.galleryCardView );

        //Setup Recycler View and get Gallery from database execution
        recyclerGalleryView = findViewById( R.id.recyclerGalleryView );
        new BackgroundImages().execute();

        //Layout Manager
        final GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 2 );
        recyclerGalleryView.setLayoutManager( gridLayoutManager );

    }

    protected class BackgroundImages extends AsyncTask<String, Void, String> {

        private String imagejson_url;

        @Override
        protected void onPreExecute() {
            imagejson_url = "https://mayar.abertay.ac.uk/~1605460/Android/Model/getGallery.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String galleryURL = "https://mayar.abertay.ac.uk/~1605460/Android/Model/getGallery.php";
            String galleryProductURL = "https://mayar.abertay.ac.uk/~1605460/Android/Model/getGalleryByType.php?="+type;
            if(type.equals("gallery")) {
                URL url;
                try {
                    url = new URL( galleryURL );
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
            }else if (type.equals( "product" )){
                URL url;
                try {
                    url = new URL( galleryProductURL );
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
                JSONArray images = new JSONArray( result );
                ArrayList<HashMap<String, String>> jsonObjectArrayList;

                jsonObjectArrayList = new ObjectMapper().readValue( images.toString(), ArrayList.class );

                for (HashMap<String, String> imageToBeAdded : jsonObjectArrayList) {
                    imageList.add( new Image( Integer.parseInt( imageToBeAdded.get( "0" ) ), imageToBeAdded.get( "Gallery_Image" ), imageToBeAdded.get( "Product_Type" ) ) );
                }

                adapter = new ImageAdapter( imageList );
                recyclerGalleryView.setAdapter( adapter );
                String test ="";

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
}
