package google.projectbuilding.fishmarket_v1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import google.projectbuilding.fishmarket_v1.adapters.FishListAdapter;
import google.projectbuilding.fishmarket_v1.models.FishModel;

public class FishListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<FishModel> fishList = new ArrayList<>();
    FishListAdapter adapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_user) {
                    Intent intent = new Intent(FishListActivity.this, AccountActivity.class);
                    startActivity(intent);
                }
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new FishListAdapter(fishList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        getData();

    }

    private void getData(){
        Ion.with(this)
                .load("http://192.168.1.7/HF/DataIkan/getIkanAll")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String data) {
                        processData(data);
                    }
                });
    }

    private void getData(String category){
        Ion.with(this)
                .load("http://192.168.1.7/HF/DataIkan/getIkanBy")
                .setBodyParameter("kategori", category)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String data) {
                        processData(data);
                    }
                });
    }

    private void processData(String data){

        if (fishList.size() > 0){
            int count = fishList.size();
            fishList.clear();
            adapter.notifyItemRangeRemoved(0, count);
        }

        try {
            JSONObject json = new JSONObject(data);
            final JSONArray fish =  json.getJSONArray("data");

            int i = 0;

            while(i < fish.length()) {
                JSONObject getFish = fish.getJSONObject(i);
                String imageUrl = getFish.getString("FOTO");
                String fishName = getFish.getString("NAMA_IKAN");
                String fishPrice = getFish.getString("HARGA");
                String fisherName = "BUDI";
                String description = getFish.getString("DESKRIPSI");

                FishModel model = new FishModel(imageUrl, fishName, fishPrice, fisherName);
                model.setDescription(description);

                this.fishList.add(model);

                i++;
            }

            adapter.notifyItemRangeInserted(0, fishList.size());
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //https://stackoverflow.com/questions/27378981/how-to-use-searchview-in-toolbar-android
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Cari ikan");
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        getData(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return true;
    }
}
