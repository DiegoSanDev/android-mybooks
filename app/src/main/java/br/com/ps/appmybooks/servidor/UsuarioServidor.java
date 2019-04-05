package br.com.ps.appmybooks.servidor;

import android.content.Context;
import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import br.com.ps.appmybooks.model.Usuario;

public class UsuarioServidor extends Servidor {

    private String urlBase = "";

    public UsuarioServidor(Context context) {
        super(context);
    }

    private String getUrlBase() {
        if(urlBase.equals("")){
            urlBase = getUrlBase(false);
        }
        return urlBase;
    }

    public Usuario cadastrar(Usuario usuario){
        try{
           getInstanceRestTemplate().getMessageConverters().add(new MappingJackson2HttpMessageConverter());
           HttpEntity<Usuario> httpEntity = new HttpEntity<>(usuario,getHeadersSemAutenticacao());
           ResponseEntity<Usuario> responseEntity = getInstanceRestTemplate()
                   .exchange(getUrlBase().concat("usuarios/inserir"), HttpMethod.POST,httpEntity,Usuario.class);
           Usuario usuarioResponse = responseEntity.getBody();
           if(usuarioResponse != null){
               return usuarioResponse;
           }
        }catch (Exception e){
            Log.d("POST_USUARIO",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
