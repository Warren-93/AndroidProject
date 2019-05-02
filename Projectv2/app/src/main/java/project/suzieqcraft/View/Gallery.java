package project.suzieqcraft.View;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import project.suzieqcraft.Adapters.ImageAdapter;

import project.suzieqcraft.Fragments.FullScreen_Fragment;
import project.suzieqcraft.Interfaces.IGallery;
import project.suzieqcraft.Model.Image;
import project.suzieqcraft.R;

public class Gallery extends AppCompatActivity implements IGallery {

    TextView productName;
    RecyclerView recyclerGalleryView;
    static ImageAdapter imageAdapter;
    public static ArrayList<Image> imageList = new ArrayList();
    ImageView galleryImage;
    CardView galleryCardView;
    FullScreen_Fragment fullScreen_fragment;
    private FrameLayout fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gallery );

        galleryImage = findViewById( R.id.galleryImage );
        galleryCardView = findViewById( R.id.galleryCardView );

        //Setup Recycler View and get Gallery from database execution
        recyclerGalleryView = findViewById( R.id.recyclerGalleryView );
        productName = findViewById( R.id.productName );

        Intent startingIntent = getIntent();
        String type;
        if (startingIntent.hasExtra( "product_key" )) {
            type = startingIntent.getStringExtra( "product_key" );
            BackgroundImages backgroundImages = new BackgroundImages( Gallery.this );
            backgroundImages.execute( type );
        }
        imageAdapter = new ImageAdapter( imageList, this );
        recyclerGalleryView.setAdapter( imageAdapter );
        recyclerGalleryView.setLayoutManager( new GridLayoutManager( this, 2 ) );

        //FullscreenImage Display
        fragment_container = findViewById( R.id.fragment_container );
        fullScreen_fragment = FullScreen_Fragment.createIntent( imageList );

    }

    private void showFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.fragment_container, FullScreen_Fragment.createIntent( imageList ) )
                .commitAllowingStateLoss();

        fragment_container.setVisibility( View.VISIBLE );
    }

    private void hideFragment() {
        fragment_container.setVisibility( View.GONE );
    }

    @Override
    public void onClick(View view, int position) {
        if (fragment_container.isShown()) {
            showFragment();
        }
    }


    public static class BackgroundImages extends AsyncTask<String, Void, String> {

        private Context imageContext;

        public BackgroundImages(Context ctx) {
            imageContext = ctx;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String galleryURL = "https://mayar.abertay.ac.uk/~1605460/Android/Model/getGallery.php";
            String productTypeURL = "https://mayar.abertay.ac.uk/~1605460/Android/Model/getProductByType.php?=" + type;
            if (type.equals( "Gallery" )) {
                URL url;
                try {
                    url = new URL( galleryURL );
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod( "POST" );
                    httpsURLConnection.setDoOutput( true );
                    httpsURLConnection.setDoInput( true );
                    OutputStream outputStream = httpsURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                    String post_data =
                            URLEncoder.encode( "type", "UTF-8" ) + "=" + URLEncoder.encode( type, "UTF-8" );
                    bufferedWriter.write( post_data );
                    bufferedWriter.flush();
                    bufferedWriter.close();
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
            } else if (type.equals( "Canvas" )) {
                URL url;
                try {
                    url = new URL( productTypeURL );
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod( "POST" );
                    httpsURLConnection.setDoOutput( true );
                    httpsURLConnection.setDoInput( true );
                    OutputStream outputStream = httpsURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                    String post_data =
                            URLEncoder.encode( "type", "UTF-8" ) + "=" + URLEncoder.encode( type, "UTF-8" );
                    bufferedWriter.write( post_data );
                    bufferedWriter.flush();
                    bufferedWriter.close();
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
            } else if (type.equals( "Shadow Box" )) {
                URL url;
                try {
                    url = new URL( productTypeURL );
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod( "POST" );
                    httpsURLConnection.setDoOutput( true );
                    httpsURLConnection.setDoInput( true );
                    OutputStream outputStream = httpsURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                    String post_data =
                            URLEncoder.encode( "type", "UTF-8" ) + "=" + URLEncoder.encode( type, "UTF-8" );
                    bufferedWriter.write( post_data );
                    bufferedWriter.flush();
                    bufferedWriter.close();
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
            } else if (type.equals( "Keyring" )) {
                URL url;
                try {
                    url = new URL( productTypeURL );
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod( "POST" );
                    httpsURLConnection.setDoOutput( true );
                    httpsURLConnection.setDoInput( true );
                    OutputStream outputStream = httpsURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                    String post_data =
                            URLEncoder.encode( "type", "UTF-8" ) + "=" + URLEncoder.encode( type, "UTF-8" );
                    bufferedWriter.write( post_data );
                    bufferedWriter.flush();
                    bufferedWriter.close();
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
            } else if (type.equals( "Cards" )) {
                URL url;
                try {
                    url = new URL( productTypeURL );
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod( "POST" );
                    httpsURLConnection.setDoOutput( true );
                    httpsURLConnection.setDoInput( true );
                    OutputStream outputStream = httpsURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                    String post_data =
                            URLEncoder.encode( "type", "UTF-8" ) + "=" + URLEncoder.encode( type, "UTF-8" );
                    bufferedWriter.write( post_data );
                    bufferedWriter.flush();
                    bufferedWriter.close();
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
            } else if (type.equals( "Jewellery" )) {
                URL url;
                try {
                    url = new URL( productTypeURL );
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod( "POST" );
                    httpsURLConnection.setDoOutput( true );
                    httpsURLConnection.setDoInput( true );
                    OutputStream outputStream = httpsURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                    String post_data =
                            URLEncoder.encode( "type", "UTF-8" ) + "=" + URLEncoder.encode( type, "UTF-8" );
                    bufferedWriter.write( post_data );
                    bufferedWriter.flush();
                    bufferedWriter.close();
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
                imageList.clear();
                for (HashMap<String, String> imageToBeAdded : jsonObjectArrayList) {
                    imageList.add( new Image( Integer.parseInt( imageToBeAdded.get( "0" ) ), imageToBeAdded.get( "Gallery_Image" ), imageToBeAdded.get( "Product_Type" ) ) );
                }
//                recyclerGalleryView.setAdapter( imageAdapter );
                imageAdapter.notifyDataSetChanged();

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