package google.projectbuilding.fishmarket_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class CheckOutActivity extends AppCompatActivity {

    ImageView image;
    TextView nama, price, weight, total;

    int counter=1;

    int priceCount=0;
    int totCount=0;

    String getNama = "NAMA";
    String getImage = "IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        nama = findViewById(R.id.tv_fish_name_checkout);
        image = findViewById(R.id.img_fish_checkout);

        Intent intent = getIntent();
        getNama = intent.getStringExtra("nama_toCheckout");
        getImage = intent.getStringExtra("image_toCheckout");

        if (getImage.isEmpty()) {
            image.setVisibility(View.GONE);
        } else {
            Glide.with(this)
                    .load(getImage)
                    .into(image);
        }

        nama.setText(getNama);

//        KALKULASI
        price = findViewById(R.id.tv_fish_price_checkout);
        weight = findViewById(R.id.tv_weight_checkout);
        total = findViewById(R.id.tv_total_checkout);

        priceCount = Integer.parseInt(price.getText().toString());
        total.setText(String.valueOf(priceCount));
    }

    public void click_minus(View view) {
        if(counter > 1){
            counter -= 1;
            weight.setText(String.valueOf(counter) + " Kg");
            totCount = priceCount * counter;
            total.setText(String.valueOf(totCount));
        }
        else{
            Toast.makeText(this, "Minimal pembelian 1 kg",Toast.LENGTH_SHORT).show();
        }
    }

    public void click_tambah(View view) {
        if(counter >= 1){
            counter += 1;
            weight.setText(String.valueOf(counter) + " Kg");
            totCount = priceCount * counter;
            total.setText(String.valueOf(totCount));
        }
    }

    public void click_bayar(View view) {
    }
}
