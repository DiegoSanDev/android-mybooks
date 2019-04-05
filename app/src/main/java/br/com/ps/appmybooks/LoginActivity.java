package br.com.ps.appmybooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.ps.appmybooks.activity.AbstractAsyncActivity;
import br.com.ps.appmybooks.activity.LivroActivity;
import br.com.ps.appmybooks.activity.UsuarioCadastroActivity;

public class LoginActivity extends AbstractAsyncActivity {

    private Button mButtonEntrar;
    private TextView mTextNaoCadastrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setSubtitle("Login");

        mButtonEntrar = findViewById(R.id.button_entrar);
        mTextNaoCadastrado = findViewById(R.id.text_nao_cadastrado);

        mTextNaoCadastrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,UsuarioCadastroActivity.class);
                startActivity(intent);
            }
        });

        mButtonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,LivroActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
