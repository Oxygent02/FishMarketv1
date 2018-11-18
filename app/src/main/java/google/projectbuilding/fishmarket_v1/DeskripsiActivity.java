package google.projectbuilding.fishmarket_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DeskripsiActivity extends AppCompatActivity {

    TextView deskripsi,nama;

    ImageView image;

    public static String getNama = "NAMA";
    public static String getDeskripsi = "DESKRIPSI";
    public static String getImage = "IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);

        nama = findViewById(R.id.tv_fish_name_detail);
        deskripsi = findViewById(R.id.tv_deskripsi);
        image = findViewById(R.id.img_fish_detail);

        Intent intent = getIntent();
        getNama = intent.getStringExtra("nama");
        getDeskripsi = intent.getStringExtra("deskripsi");
        getImage = intent.getStringExtra("image");

        if (getImage.isEmpty()) {
            image.setVisibility(View.GONE);
        } else {
            Glide.with(this)
                    .load(getImage)
                    .into(image);
        }

        nama.setText(getNama);
        deskripsi.setText(getDeskripsi);
    }

    public void click_beli(View view) {
    }
}
