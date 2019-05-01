package project.suzieqcraft.View;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

import project.suzieqcraft.Adapters.FullscreenAdapter;
import project.suzieqcraft.Model.Image;
import project.suzieqcraft.R;

public class Fullscreen extends FragmentActivity {

    FullscreenAdapter fullscreenAdapter;
    RecyclerView fullscreenViewer;
    ImageView fullsizeImage;
    private ArrayList<Image> fullscreenArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fullscreen );

    fullscreenViewer = findViewById( R.id.fullscreenViewer );
    fullsizeImage = findViewById( R.id.fullsizeImage );

    fullscreenAdapter = new FullscreenAdapter( fullscreenArrayList );
    fullscreenViewer.setAdapter( fullscreenAdapter );

    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false );
    fullscreenViewer.setLayoutManager( linearLayoutManager );
    new BackgroundImages().execute();
    }

    class BackgroundImages extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            String galleryURL = "https://mayar.abertay.ac.uk/~1605460/Android/Model/getGallery.php";
            // String galleryProductURL = "https://mayar.abertay.ac.uk/~1605460/Android/Model/getGalleryByType.php";
            // if (type.equals( "gallery" )) {
            URL url;
            try {
                url = new URL(galleryURL);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
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
//        } else if(type.equals("product"))
//
//        {
//            URL url;
//            try {
//                url = new URL( galleryProductURL );
//                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
//                InputStream inputStream = httpsURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );
//                String result = "";
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    result += line;
//                }
//                return result;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONArray images = new JSONArray(result);
                ArrayList<HashMap<String, String>> jsonObjectArrayList;

                jsonObjectArrayList = new ObjectMapper().readValue(images.toString(), ArrayList.class);
                fullscreenArrayList.clear();
                for (HashMap<String, String> imageToBeAdded : jsonObjectArrayList) {
                    fullscreenArrayList.add(new Image(Integer.parseInt(imageToBeAdded.get("0")), imageToBeAdded.get("Gallery_Image"), imageToBeAdded.get("Product_Type")));
                }
                FullscreenAdapter fullscreenAdapter= new FullscreenAdapter( fullscreenArrayList);
                fullscreenViewer.setAdapter( fullscreenAdapter );
               fullscreenAdapter.notifyDataSetChanged();

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
