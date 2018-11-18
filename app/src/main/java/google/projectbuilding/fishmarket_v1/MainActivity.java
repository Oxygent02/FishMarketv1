package google.projectbuilding.fishmarket_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView welcome;
    ListView lvList;
    ArrayList<FishModel> arrFish = new ArrayList<>();
    int i = 0;

    // Reinhard JS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.tv_welcome);
        SharedPreferences getLogin = getSharedPreferences("login",MODE_PRIVATE);
        welcome.setText( "Welcome, " + getLogin.getString("parse_username","-"));

        lvList = findViewById(R.id.lv_list);
        getData();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.account_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        startActivity(intent);
        return true;
    }

    private void processData(final String data) {
        try {
            JSONObject json = new JSONObject(data);
            final JSONArray fish =  json.getJSONArray("teams");

            while(i < fish.length()) {
            JSONObject getFish = fish.getJSONObject(i);
            final String teamName = getFish.getString("strTeam");
            String imgBadge = getFish.getString("strTeamBadge");

            this.arrFish.add(new FishModel(teamName, imgBadge));

            CustomAdapter adapter = new CustomAdapter(this.arrFish, getApplicationContext());
            lvList.setAdapter(adapter);

            lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    JSONObject json = null;
                    try {
                        json = new JSONObject(data);
                        JSONArray fishOnDetail =  json.getJSONArray("teams");
                        JSONObject getFishOnDetail = fishOnDetail.getJSONObject(position);
                        final String desc_detail = getFishOnDetail.getString("strWebsite");
                        final String name_detail = getFishOnDetail.getString("strTeam");
                        String image_detail = getFishOnDetail.getString("strTeamBadge");


                        Intent intent = new Intent(MainActivity.this, DeskripsiActivity.class);

                        intent.putExtra("nama", name_detail);
                        intent.putExtra("deskripsi", desc_detail);

                        intent.putExtra("image", image_detail);

                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


//                    Log.d("klik", "" + position);
                }
            });

            i++;
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getData() {
        Ion.with(this)
                .load("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String data) {
                        processData(data);
                    }
                });
    }

}
