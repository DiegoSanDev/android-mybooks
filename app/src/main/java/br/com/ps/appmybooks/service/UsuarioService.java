package br.com.ps.appmybooks.service;

import android.content.Context;

import br.com.ps.appmybooks.dao.UsuarioDAO;
import br.com.ps.appmybooks.model.Usuario;

public class UsuarioService implements Service<Usuario> {

    private UsuarioDAO usuarioDAO = null;

    public UsuarioService(Context context) {
        this.usuarioDAO = new UsuarioDAO(context);
    }

    @Override
    public Usuario atualizar(Usuario entity) {
        return null;
    }

    @Override
    public Usuario inserir(Usuario usuario) {

        if(usuario != null){
            long id = usuarioDAO.inserir(usuario);
            if(id > 0){
                Usuario usuarioSalvo = buscarPorId(id);
                if(usuarioSalvo != null){
                    return usuarioSalvo;
                }
            }
        }
        return null;
    }

    @Override
    public Usuario buscarPorId(long id) {
        if(id > 0) {
            return usuarioDAO.buscarPorId(id);
        }
        return null;
    }
}
