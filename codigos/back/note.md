Objetivo: 

Criar uma API RESTful para controle de produtos

Modelo: OK
    Produto: id, nome, quantidade, valor, observacao

Repositorio de produtos: OK
    obterTodos, obterPorId, adicionar, remover, atualizar

Serviço de produto: OK
    obterTodos, obterPorId, adicionar, remover, atualizar

Controller de produto:
//Classe principal que vai trabalhar com os metodos da api, o end point principal que vai receber as requisições e fazer as manipulações, chamando os serviços.

    obterTodos, obterPorId, adicionar, remover, atualizar