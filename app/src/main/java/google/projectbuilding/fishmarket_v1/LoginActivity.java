package google.projectbuilding.fishmarket_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText editUsername;

    SharedPreferences sharedLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.edt_username);

        sharedLogin = getSharedPreferences("login", MODE_PRIVATE);
    }

    public void click_login(View view) {
        SharedPreferences.Editor editor = sharedLogin.edit();
        editor.putString("parse_username",editUsername.getText().toString());
        editor.apply();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void click_signup(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
