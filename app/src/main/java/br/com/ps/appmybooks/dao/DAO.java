package br.com.ps.appmybooks.dao;

public interface DAO<T> {

    long inserir(T entity);

    long atualizar(T entity);

    T buscarPorId(long id);
}
