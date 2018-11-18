package google.projectbuilding.fishmarket_v1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

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
        setSupportActionBar(toolbar);

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
                .load("http://192.168.1.7/HF/DataIkan/getIkanAll")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String data) {
                        processData(data);
                    }
                });
    }

    private void processData(String data){

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

//        fishList.add(new FishModel("http://web.com/image.jpg", "Nama Ikan 1", "Rp. 20.000/kg", "Budi Utama"));
//        fishList.add(new FishModel("http://web.com/image.jpg", "Nama Ikan 1", "Rp. 20.000/kg", "Budi Utama"));
//        fishList.add(new FishModel("http://web.com/image.jpg", "Nama Ikan 1", "Rp. 20.000/kg", "Budi Utama"));
//        fishList.add(new FishModel("http://web.com/image.jpg", "Nama Ikan 1", "Rp. 20.000/kg", "Budi Utama"));
//        fishList.add(new FishModel("http://web.com/image.jpg", "Nama Ikan 1", "Rp. 20.000/kg", "Budi Utama"));
//        fishList.add(new FishModel("http://web.com/image.jpg", "Nama Ikan 1", "Rp. 20.000/kg", "Budi Utama"));
//        fishList.add(new FishModel("http://web.com/image.jpg", "Nama Ikan 1", "Rp. 20.000/kg", "Budi Utama"));
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
        Toast.makeText(this, "Query : " + s, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return true;
    }
}
