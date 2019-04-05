package br.com.ps.appmybooks.service;

public interface Service<T> {

    T atualizar(T entity);

    T inserir(T entity);

    T buscarPorId(long id);

}
