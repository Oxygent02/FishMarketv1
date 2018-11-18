package google.projectbuilding.fishmarket_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText nama,kota, noTelp, email, gender, tglLahir, alamat;

    SharedPreferences sharedDataUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nama = findViewById(R.id.edt_nama);
        kota = findViewById(R.id.edt_kota);
        noTelp = findViewById(R.id.edt_email);
        email = findViewById(R.id.edt_gender);
        tglLahir = findViewById(R.id.edt_tglLahir);
        alamat = findViewById(R.id.edt_email);

        sharedDataUser = getSharedPreferences("dataUser", MODE_PRIVATE);
    }

    public void click_signup(View view) {
        SharedPreferences.Editor editor = sharedDataUser.edit();
        editor.putString("parse_username",editUsername.getText().toString());
        editor.apply();

        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
