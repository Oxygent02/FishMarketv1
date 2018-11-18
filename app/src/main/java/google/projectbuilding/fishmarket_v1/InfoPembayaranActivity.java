package google.projectbuilding.fishmarket_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoPembayaranActivity extends AppCompatActivity {

    TextView total;

    String getTotal = "TOTAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pembayaran);

        total = findViewById(R.id.tv_total);

        Intent intent = getIntent();
        getTotal = intent.getStringExtra("total_toPay");

        total.setText("Rp. " + getTotal);

    }
}
