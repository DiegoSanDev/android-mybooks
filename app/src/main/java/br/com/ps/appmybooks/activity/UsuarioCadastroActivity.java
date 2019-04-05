package br.com.ps.appmybooks.activity;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ps.appmybooks.R;
import br.com.ps.appmybooks.model.Usuario;
import br.com.ps.appmybooks.service.UsuarioService;
import br.com.ps.appmybooks.servidor.UsuarioServidor;
import br.com.ps.appmybooks.utils.Utils;

public class UsuarioCadastroActivity extends AbstractAsyncActivity {

    private Button mButtonInscreverse;
    private EditText mEditLogin;
    private EditText mEditSenha;
    private EditText mEditConfirmarSenha;

    private Usuario mUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_cadastro);

        getSupportActionBar().setSubtitle("Cadastre-se");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initView();

        mUsuario = new Usuario();
        mButtonInscreverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsuario.setLogin(mEditLogin.getText().toString());
                mUsuario.setSenha(mEditSenha.getText().toString());
                if(validarCampos() && validarConfirmarSenhas()) {
                    new UsuarioCadastroTask(mUsuario).execute();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initView(){
        mEditLogin = findViewById(R.id.edit_login);
        mEditSenha = findViewById(R.id.edit_senha);
        mButtonInscreverse = findViewById(R.id.button_inscrevase);
        mEditConfirmarSenha = findViewById(R.id.edit_senha_confirma);
    }

    private boolean validarCampos(){
        String msg = "Campo obrigátorio.";
        if(Utils.isEmpty(mEditLogin)){
            mEditLogin.setError(msg);
            return false;
        }else if(Utils.isEmpty(mEditSenha)){
            mEditSenha.setError(msg);
            return false;
        }else if(Utils.isEmpty(mEditConfirmarSenha)){
            mEditConfirmarSenha.setError(msg);
            return false;
        }
        return true;
    }

    private boolean validarConfirmarSenhas(){
        String senha = mEditSenha.getText().toString();
        String senhaConfirma = mEditConfirmarSenha.getText().toString();
        if(senha.equals(senhaConfirma)){
            return true;
        }
        Toast.makeText(this, "ATENÇÃO! As senhas não conferem.", Toast.LENGTH_LONG).show();
        return false;
    }

    private class UsuarioCadastroTask extends AsyncTask<Void,Void,Integer>{

        UsuarioServidor servidor = null;
        Usuario usuario;

        public UsuarioCadastroTask(Usuario usuario){
            this.usuario = usuario;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            servidor = new UsuarioServidor(UsuarioCadastroActivity.this);
            if(Utils.isNetworkConnected(UsuarioCadastroActivity.this)) {
                Usuario usuarioResponse = servidor.cadastrar(this.usuario);
                if (usuarioResponse != null) {
                    UsuarioService usuarioService = new UsuarioService(UsuarioCadastroActivity.this);
                    if (usuarioService.inserir(usuarioResponse) != null) {
                        return SUCESS;
                    }
                }
            }else{
                return NETWORK_NOT_CONNECTED;
            }
            return ERROR;
        }

        @Override
        protected void onPostExecute(Integer result) {
            dismissProgressDialog();
            servidor = null;
            switch (result){
                case SUCESS:
                    Toast.makeText(UsuarioCadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                    break;
                case NETWORK_NOT_CONNECTED:
                    Toast.makeText(UsuarioCadastroActivity.this, "ATENÇÃO! Verifique sua conexão com a internet.", Toast.LENGTH_LONG).show();
                    break;
                case ERROR:
                    Toast.makeText(UsuarioCadastroActivity.this, "Usuário não cadastrado!", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
