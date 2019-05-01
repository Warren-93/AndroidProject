//package project.suzieqcraft.Controller;
//
//import android.os.AsyncTask;
//import android.support.v7.widget.RecyclerView;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import javax.net.ssl.HttpsURLConnection;
//
//import project.suzieqcraft.Model.Image;
//
//public class BackgroundImages extends AsyncTask<String, Void, String> {
//
//    private String imagejson_url;
//
//
//    public RecyclerView recyclerViewer;
//    public ImageAdapter adapter;
//    private ArrayList<Image> imageList = new ArrayList();
//
//
//    @Override
//    protected void onPreExecute() {
//        imagejson_url = "https://mayar.abertay.ac.uk/~1605460/Android/Model/getProducts.php";
//    }
//
//    @Override
//    protected String doInBackground(String... strings) {
//        try {
//            URL url = new URL( imagejson_url );
//            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
//            InputStream inputStream = httpsURLConnection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );
//            String result = "";
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                result += line;
//            }
//            return result;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate( values );
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        super.onPostExecute( result );
//
//        try {
//            JSONArray products = new JSONArray( result );
//            ArrayList<HashMap<String, String>> jsonObjectArrayList;
//
//            jsonObjectArrayList = new ObjectMapper().readValue( products.toString(), ArrayList.class );
//
//            for (HashMap<String, String> imageToBeAdded : jsonObjectArrayList) {
//                imageList.add( new Image( Integer.parseInt( imageToBeAdded.get( "0" ) ), imageToBeAdded.get( "Product_Type" ), imageToBeAdded.get( "Product_Image" ) ) );
//            }
//
//
//            adapter = new ImageAdapter(imageList);
//            recyclerViewer.setAdapter( adapter );
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
