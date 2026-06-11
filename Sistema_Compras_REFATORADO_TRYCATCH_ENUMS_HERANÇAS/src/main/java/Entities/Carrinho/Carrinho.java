package Entities.Carrinho;

import Entities.Cliente.Cliente;
import Entities.Estoque.Estoque;
import Entities.Produto.Produto;

import java.util.ArrayList;

public class Carrinho extends Cliente {
    private ArrayList<Integer> itensEscolhidos = new ArrayList<>();
    private double precoCarrinho = 0;
    private double valorFinal = 0;

    public Carrinho(String nome, String cpf, String cidade, String estado, String bairro, String rua, int numero, ArrayList<Integer> itensEscolhidos, double precoCarrinho, double valorFinal) {
        super(nome, cpf, cidade, estado, bairro, rua, numero);
        this.itensEscolhidos = itensEscolhidos;
        this.precoCarrinho = precoCarrinho;
        this.valorFinal = valorFinal;
    }

    public Carrinho() {

    }

    public ArrayList<Integer> getItensEscolhidos() {
        return itensEscolhidos;
    }

    public double getPrecoCarrinho() {
        return precoCarrinho;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void adicionarAoCarrinho(Estoque estoque) {
        itensEscolhidos.add(estoque.getProdutoAtual());
        precoCarrinho += estoque.getProduto()[estoque.getProdutoAtual()].getPrecoProduto();
    }

    public boolean verificarFreteGratis() {
        if (precoCarrinho > 500) {
            return true;
        }
        else
            return false;
    }

    public double precoTotal(){
        if (!verificarFreteGratis()) {
            valorFinal = precoCarrinho + 25;
        }
        else
            valorFinal = precoCarrinho;

        return valorFinal;
    }

    public String exibirCarrinho(Estoque estoque){
        String carrinho = "";
        Produto[] produtosAcervo = estoque.getProduto();

        for (int i = 0; i < itensEscolhidos.size(); i++) {
            int indiceProduto = itensEscolhidos.get(i);
            String stringCarrinho = "";
            stringCarrinho += "Nome: " + produtosAcervo[indiceProduto].getNomeProduto() + " | Preço: " + String.format("R$ %.2f%n", produtosAcervo[indiceProduto].getPrecoProduto());
            carrinho += stringCarrinho;
        }

        return carrinho;
    }
    @Override
    public String toString () {
        String msg = "";

        msg = "\nNome do comprador: " +getNome() +
                "\nCPF na nota: " + getCpf() +
                "\n ========= Endereço de entrega =========" +
                "\nEstado: " +getEstado() +
                "\nBairro: " +getBairro() +
                "\nRua: " +getRua() +
                "\nNúmero da casa: " + getNumero();
        return msg;
    }
}