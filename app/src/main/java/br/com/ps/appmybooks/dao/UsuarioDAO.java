package br.com.ps.appmybooks.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import br.com.ps.appmybooks.model.Usuario;

public class UsuarioDAO implements DAO<Usuario>{

    private final String TABLE_USUARIO = "usuario";
    private Context context;
    private DBGateway dbGateway;

    public UsuarioDAO(Context context) {
        this.context = context;
        this.dbGateway = DBGateway.getInstance(this.context);
    }

    @Override
    public long inserir(Usuario usuario) {
        ContentValues contentValues = new ContentValues();
        try{
            contentValues.put("id_servidor",usuario.getIdServidor());
            contentValues.put("login",usuario.getLogin());
            contentValues.put("senha",usuario.getSenha());
            contentValues.put("is_ativo",usuario.isAtivo() == true ? 1 : 0);
            return this.dbGateway.openDbWrite().insert(TABLE_USUARIO,null,contentValues);
        }finally {
            contentValues = null;
        }
    }

    @Override
    public long atualizar(Usuario entity) {
        return 0;
    }

    @Override
    public Usuario buscarPorId(long id) {
        Usuario usuario = null;
        Cursor cursor = this.dbGateway.openDbRead().rawQuery("select * from usuario where _id = ?", new String[]{id + ""});
        try{
            if(cursor.moveToFirst()){
                usuario = new Usuario();
                usuario.setId(cursor.getColumnIndex("_id"));
                usuario.setIdServidor(cursor.getColumnIndex("id_servidor"));
                usuario.setLogin(cursor.getString(cursor.getColumnIndex("login")));
                usuario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
                usuario.setAtivo(cursor.getColumnIndex("is_ativo") == 1 ? true : false);
            }
        }finally {
            cursor.close();
            cursor = null;
        }
        return usuario;
    }
}
