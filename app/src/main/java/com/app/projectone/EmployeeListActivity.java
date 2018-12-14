package com.app.projectone;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.adapter.EmployeeAdapter;
import com.app.model.EmployeeData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {

    RecyclerView lv_employee;
    List<EmployeeData> employeeDataList = new ArrayList<>();
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        new GetAllEmployeeTask().execute();

        lv_employee = (RecyclerView) findViewById(R.id.lv_employee);
        adapter = new EmployeeAdapter(this,employeeDataList);

        lv_employee.setLayoutManager(new LinearLayoutManager(this));
        lv_employee.setAdapter(adapter);


    }


    private class GetAllEmployeeTask extends AsyncTask<Void, Void, String> {

        ProgressDialog progress = new ProgressDialog(EmployeeListActivity.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(">>>","Pre Execute");
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();


        }

        @Override
        protected String doInBackground(Void... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {

                URL url = new URL("http://dummy.restapiexample.com/api/v1/employees");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
                return forecastJsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.i("json", s);
            progress.dismiss();

            try {

                //JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = new JSONArray(s);

                for (int i = 0; i<jsonArray.length();i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    EmployeeData employeeData = new EmployeeData();
                    employeeData.setEmp_id(jsonObject.optString("id"));
                    employeeData.setEmp_name(jsonObject.optString("employee_name"));
                    employeeData.setEmp_age(jsonObject.optString("employee_age"));
                    employeeData.setEmp_salary(jsonObject.optString("employee_salary"));
                    employeeData.setEmp_image(jsonObject.optString("profile_image"));

                    employeeDataList.add(employeeData);
                }


            }catch (Exception e){
                Log.e(">>",e.toString());
            }


            adapter.notifyDataSetChanged();

        }
    }

}
