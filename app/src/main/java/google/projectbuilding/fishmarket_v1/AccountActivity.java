package google.projectbuilding.fishmarket_v1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {

    TextView nama,kota, noTelp, email, gender, tglLahir, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        nama = findViewById(R.id.tv_nama_profile);
        kota = findViewById(R.id.tv_kota_profile);
        noTelp = findViewById(R.id.tv_noTelp_profile);
        email = findViewById(R.id.tv_email_profile);
        tglLahir = findViewById(R.id.tv_tglLahir_profile);
        alamat = findViewById(R.id.tv_Alamat_profile);

        SharedPreferences getDataUser = getSharedPreferences("dataUser",MODE_PRIVATE);

        nama.setText( getDataUser.getString("parse_nama_user","-"));
        kota.setText( getDataUser.getString("parse_kota_user","-"));
        noTelp.setText( getDataUser.getString("parse_noTelp_user","-"));
        email.setText( getDataUser.getString("parse_email_user","-"));
        tglLahir.setText( getDataUser.getString("parse_tglLahir_user","-"));
        alamat.setText( getDataUser.getString("parse_alamatr_user","-"));
    }
}
