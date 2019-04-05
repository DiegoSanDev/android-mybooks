package br.com.ps.appmybooks.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private long id;
    private long idServidor;
    private String login;
    private String senha;
    private String senhaApp;
    private boolean ativo;

    public Usuario() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(long idServidor) {
        this.idServidor = idServidor;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaApp() {
        return senhaApp;
    }

    public void setSenhaApp(String senhaApp) {
        this.senhaApp = senhaApp;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
