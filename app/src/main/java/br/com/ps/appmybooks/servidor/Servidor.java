package br.com.ps.appmybooks.servidor;

import android.content.Context;
import android.content.res.Resources;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.ps.appmybooks.R;

public abstract class Servidor {

    private Context context;
    private RestTemplate restTemplate = null;
    private String urlBase = "";

    public Servidor(Context context) {
        this.context = context;
    }

    public RestTemplate getInstanceRestTemplate() {
        if(restTemplate == null){
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }

    public HttpHeaders getHeadersSemAutenticacao() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    public HttpHeaders getHeadersComAutenticacao(String login, String senha) {
        HttpAuthentication authentication = new HttpBasicAuthentication(login,senha);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAuthorization(authentication);
        return httpHeaders;
    }

    public String getUrlBase(boolean urlServidor) {
        try {
            Resources resources = this.context.getResources();
            InputStream rawResource = resources.openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(rawResource);
            if(urlServidor){
                urlBase = properties.getProperty("url_base_servidor");
            }else{
                urlBase = properties.getProperty("url_base_local");
            }
        }catch (IOException e){e.printStackTrace();}
        return urlBase;
    }
}
