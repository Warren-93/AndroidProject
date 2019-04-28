package project.suzieqcraft.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

//import project.suzieqcraft.View.AppMenu;
import project.suzieqcraft.Model.User;
import project.suzieqcraft.View.AppMenu;


public class Connection extends AsyncTask<String, Void, String> {

    private Context connContext;
    public ArrayList<User> userList = new ArrayList();


    public Connection(Context ctx) {
        connContext = ctx;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText( connContext.getApplicationContext(), "Waiting...", Toast.LENGTH_LONG ).show();
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://mayar.abertay.ac.uk/~1605460/Android/Model/login.php";
        String register_url = "https://mayar.abertay.ac.uk/~1605460/Android/Model/register.php";
        if (type.equals( "register" )) {
            String firstname = params[1];
            String surname = params[2];
            String email = params[3];
            String password = params[4];
            URL url;
            try {
                url = new URL( register_url );
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setDoOutput( true );
                httpURLConnection.setDoInput( true );
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                String post_data =
                        URLEncoder.encode( "firstname", "UTF-8" ) + "=" + URLEncoder.encode( firstname, "UTF-8" ) + "&"
                                + URLEncoder.encode( "surname", "UTF-8" ) + "=" + URLEncoder.encode( surname, "UTF-8" ) + "&"
                                + URLEncoder.encode( "email", "UTF-8" ) + "=" + URLEncoder.encode( email, "UTF-8" ) + "&"
                                + URLEncoder.encode( "password", "UTF-8" ) + "=" + URLEncoder.encode( password, "UTF-8" );
                bufferedWriter.write( post_data );
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream, "iso-8859-1" ) );
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals( "login" )) {
            String email = params[1];
            String password = params[2];
            URL url;
            try {
                url = new URL( login_url );
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setDoOutput( true );
                httpURLConnection.setDoInput( true );
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );

                String post_data = URLEncoder.encode( "email", "UTF-8" ) + "=" + URLEncoder.encode( email, "UTF-8" ) + "&"
                        + URLEncoder.encode( "password", "UTF-8" ) + "=" + URLEncoder.encode( password, "UTF-8" );
                bufferedWriter.write( post_data );
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream, "iso-8859-1" ) );
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
    protected void onPostExecute(String result) {
        super.onPostExecute( result );
        try {

            JSONArray userJSONArray = new JSONArray( result );
            ArrayList<HashMap<String, String>> jsonUserArrayList;
            jsonUserArrayList = new ObjectMapper().readValue( userJSONArray.toString(), ArrayList.class );
            for (HashMap<String, String> userToBeAdded : jsonUserArrayList) {
                userList.clear();
                if (userToBeAdded.containsKey( "User_ID" )) {
                    userList.add( new User( Integer.parseInt( userToBeAdded.get( "User_ID" ) != null ? userToBeAdded.get( "User_ID" ) : "0" ), userToBeAdded.get( "First_Name" ), userToBeAdded.get( "Email" ) ) );
                } else {
                    userList.add( new User() );
                }
                if (userList.contains( "User_ID" )) {
                    Toast.makeText( connContext.getApplicationContext(), "Login Success", Toast.LENGTH_SHORT ).show();
                    connContext.startActivity( new Intent( connContext.getApplicationContext(), AppMenu.class ) );
                } else {
                    Toast.makeText( connContext.getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT ).show();
                }

            }


        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate( values );
    }
}

