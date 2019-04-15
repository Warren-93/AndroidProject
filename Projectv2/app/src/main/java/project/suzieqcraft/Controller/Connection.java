package project.suzieqcraft.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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
import project.suzieqcraft.Model.Product;
import project.suzieqcraft.Model.User;
import project.suzieqcraft.View.AppMenu;
import project.suzieqcraft.View.test;


public class Connection extends AsyncTask<String, Void, String>  {

    private Context connContext;
    public ArrayList<User> userList = new ArrayList();


    public Connection(Context ctx){
        connContext = ctx;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(connContext.getApplicationContext(), "Waiting...", Toast.LENGTH_LONG ).show();
    }

    @Override
    protected String doInBackground(String...params){
        String type = params[0];
        String login_url = "https://mayar.abertay.ac.uk/~1605460/Android/Model/login.php";
        String register_url = "https://mayar.abertay.ac.uk/~1605460/Android/Model/register.php";
        if(type.equals("register")){
            String firstname = params[1];
            String surname = params[2];
//            String username = params[3];
            String email = params[4];
            String password = params[5];
            URL url;
            try {
                url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        URLEncoder.encode("firstname", "UTF-8")+"="+URLEncoder.encode(firstname, "UTF-8")+"&"
                                + URLEncoder.encode("surname", "UTF-8")+"="+URLEncoder.encode(surname, "UTF-8")+"&"
//                                + URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                                +URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"
                                +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while((line = bufferedReader.readLine()) != null) {
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
        }else if(type.equals("login")){
            String email = params[1];
            String password = params[2];
            URL url;
            try {
                url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"
                                    +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String resultArray = "";
                String result ="";
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    resultArray += line;
                    result = "Success";
                }

                JSONArray userJSONArray = new JSONArray(resultArray);
                ArrayList<HashMap<String, String>> jsonUserArrayList;

                jsonUserArrayList = new ObjectMapper().readValue(userJSONArray.toString(), ArrayList.class);
                for (HashMap<String, String> userToBeAdded : jsonUserArrayList) {
//                    userList.add( new User(Integer.parseInt( userToBeAdded.get( "0" )), userToBeAdded.get( "" ), userToBeAdded.get(""), userToBeAdded.get( "" )))
                    userList.add(new User (Integer.parseInt(userToBeAdded.get("User_ID")), userToBeAdded.get( "First_Name" ), userToBeAdded.get("Email")));

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
            if(result.equals("Success")){
                Toast.makeText(connContext.getApplicationContext(), result, Toast.LENGTH_SHORT ).show();
                connContext.startActivity( new Intent(connContext.getApplicationContext(), AppMenu.class));
                User user = new User();
                user.getEmail();
                user.getName();
            }
            else{
                    Toast.makeText(connContext.getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT ).show();
                }

            }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

