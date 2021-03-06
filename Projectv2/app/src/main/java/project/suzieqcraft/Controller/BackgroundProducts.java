//package project.suzieqcraft.Controller;
//
//import android.content.Context;
//import android.os.AsyncTask;
//import android.support.v7.widget.RecyclerView;
//import android.widget.TextView;
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
//import project.suzieqcraft.Model.Product;
//
//public class BackgroundProducts extends AsyncTask<String, Void, String> {
//    private String productjson_url;
//
//
//    public RecyclerView recyclerViewer;
//    public CustomAdapter adapter;
//    private ArrayList<Product> productList = new ArrayList();
//
//    private Context productContext;
//
//
//    public BackgroundProducts(Context ctx) {
//        productContext = ctx;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        productjson_url = "https://mayar.abertay.ac.uk/~1605460/Android/Model/getProducts.php";
//    }
//
//    @Override
//    protected String doInBackground(String... strings) {
//        try {
//            URL url = new URL( productjson_url );
//            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
//            InputStream inputStream = httpsURLConnection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );
//            String result = "";
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                result += line;
//            }
//            bufferedReader.close();
//            inputStream.close();
//            httpsURLConnection.disconnect();
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
//            for (HashMap<String, String> productToBeAdded : jsonObjectArrayList) {
//                productList.add( new Product( Integer.parseInt( productToBeAdded.get( "0" ) ), productToBeAdded.get( "Product_Type" ), productToBeAdded.get( "Product_Image" ) ) );
//            }
//            adapter = new CustomAdapter( productList );
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
//

