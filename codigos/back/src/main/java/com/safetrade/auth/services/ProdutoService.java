package com.safetrade.auth.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.safetrade.auth.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.safetrade.auth.shared.ProdutoDTO;
import com.safetrade.auth.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * @return Lista de produtos
     */
    public List<ProdutoDTO> obterTodos(){

        //Retorna uma lista de produto model
        List<Produto> produtos = produtoRepository.findAll();


        //Transforma Produto em ProdutoDTO e o retorna
        return produtos.stream()
                .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu Id.
     * @param id do produto que será localizado
     * @return Retorna um produto caso tenha encontrado
     */
    public Optional<ProdutoDTO> obterPorId(Integer id){
        //Obetendo optional de produto por Id.
        Optional<Produto> produto = produtoRepository.findById(id);

        //Se nao encontrar lança exception
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: " + id + "não foi encontrado");
        }
        //Convertendo optional de produto em um produto dto
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        //criando e retornando um optonal de ProdutoDTO
        return Optional.of(dto);
    }

    /**
     * Metodo para adcionar produto na lista.
     * @param produtoDto que será adicionado na lista
     * @return retorna o produto que foi adiiconado na lista
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){
        //remover o id para fazer o cadastro
        produtoDto.setId(null);

        //Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        //Converter produtoDTO para produto Model
        Produto produto = mapper.map(produtoDto, Produto.class);

        //armazenar o produto model convertido no banco de dados (produto ja vem com Id)
        produto = produtoRepository.save(produto);
        //Retornar produtoDTO atualizado
        produtoDto.setId(produto.getId());


        return produtoDto;
    }


    /**
     * Metodo para remover produto por id da lista.
     * @param id do produto a ser removido.
     */
    public void deletar(Integer id){
        //Verificar se produto existe para ser deletado
        Optional<Produto> produto = produtoRepository.findById(id);

        //Se nao existir, lança uma exception
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Nao foi possivel encontrar o produto com o id: " + id + ". ");
        }
        //Deleta o produto por id.
        produtoRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param id que será atualizado
     * @return Retorna o produto após a atualização na lista.
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto){
        //Passar o id para o ProdutoDTO
        produtoDto.setId(id);
        //Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
        //Converter o ProdutoDTO em um produto model
        Produto produto =  mapper.map(produtoDto, Produto.class);
        //Atualizar o produto no banco
        /*
            Como o banco sabe oque ele tem que cadastrar, e oque tem que atualizar?
            Reposta: Se tiver id, ele sabe que tem atualizar, se nao tiver ele cadastra
         */
        produtoRepository.save(produto);

        //Retornar o produtoDTO atualizado
        return produtoDto;
    }
}
