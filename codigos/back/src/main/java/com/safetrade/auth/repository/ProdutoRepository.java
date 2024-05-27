package com.safetrade.auth.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetrade.auth.model.Produto;
@SpringBootApplication


public class ProdutoRepository {
    
    private List<Produto> produtos = new ArrayList<Produto>(); // Simula meu banco de dados para essa aplicação
    private Integer ultimoId = 0;

    /**
     * Metodo para retornar uma lista de produtos
     * @return Lista de produtos
     */
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu Id.
     * @param id do produto que será localizado
     * @return Retorna um produto caso tenha encontrado
     */
    public Optional<Produto> obterPorId(Integer id){
        return produtos.stream()
                      .filter(produto -> produto.getId() == id)
                      .findFirst();
    }

    /**
     * Metodo para adcionar produto na lista.
     * @param produto que será adicionado na lista
     * @return retorna o produto que foi adiiconado na lista
     */
    public Produto adicionar(Produto produto){
        
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    /**
     * Metodo para remover produto por id da lista.
     * @param id do produto a ser removido.
     */
    public void deletar(Integer id){
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param produto que será atualizado
     * @return Retorna o produto após a atualização na lista.
     */
    public Produto atualizar(Produto produto){
        
        //Encontra produto na lista.
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        //Se produto encontrado, for vazio, ele retorna a mensagem.
        if(produtoEncontrado.isEmpty()){
            throw new InputMismatchException("Produto não encontrado");
        }
        //Remove produto antigo da lista.
        deletar(produto.getId());

        //Depois adicionar o produto atualizado na lista.
        produtos.add(produto);

        return produto;
    }
}
