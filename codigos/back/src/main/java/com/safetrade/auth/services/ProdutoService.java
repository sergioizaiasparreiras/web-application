package com.safetrade.auth.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetrade.auth.model.Produto;
import com.safetrade.auth.repository.ProdutoRepository;



/**
 *  Metodos de CRUD
 */

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * @return list da produtos
     */
    public List<Produto> obterTodos(){
        return produtoRepository.obterTodos();
    }

    /**
     * Metodo que retorna um produto encontrado por Id
     * @param id que do produto que sera localizado
     * @return retorna um produto que sera localizado
     */
    public Optional<Produto> obterPorId(Integer id){

        return produtoRepository.obterPorId(id);
    }

    /**
     * Metodo para adiconar produto na lista.
     * @param produto que sera adicionado
     * @return produto que foi adicionado a lista
     */
    public Produto adicionar(Produto produto){
        //Poderia ter algumas regras de negocio para validar o produto.
        return produtoRepository.adicionar(produto); 
    }

    /**
     * Metodo para deletar o produto por id
     * @param id do produto a ser deletado
     */
    public void remover(Integer id){
        // Aqui poderia ter alguma lógica de validação.
        produtoRepository.remover(id);
    }


    /**
     * Metodo para atualizar o produto na lista
     * @param id na requisão de um método para atualizar,
     * o id não vem no corpo do produto, ele vem separado.
     * @param produto que será atualizado
     * @return o produto após a atualização na lista
     */
    public Produto atualizar(Integer id, Produto produto){

        // Aqui poderia ter uma lógica de validação
        produto.setId(id);
        return produtoRepository.adicionar(produto);

    }
}
