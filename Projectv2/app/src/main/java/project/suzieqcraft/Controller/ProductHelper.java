package project.suzieqcraft.Controller;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ProductHelper extends AsyncTask<String, Void, String> {

    String JSON_STRING;
    private Context pContext;
    private String productjson_url;

    ProductHelper(Context ctx){
        pContext = ctx;
    }

    @Override
    protected void onPreExecute(){
        productjson_url = "https://mayar.abertay.ac.uk/~1605460/Android/Model/getProducts.php";
    }

    @Override
    protected String doInBackground(String... Strings) {
        try {
            URL url = new URL(productjson_url);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );
            StringBuilder stringBuilder = new StringBuilder();
            while(JSON_STRING == bufferedReader.readLine()){
                stringBuilder.append( JSON_STRING+"\n" );

            }
            bufferedReader.close();
            inputStream.close();
            httpsURLConnection.disconnect();
            return stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onProgressUpdate(Void...values){
        super.onProgressUpdate( values );

    }


    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);

    }
}
