package Applications.Menu;

import Entities.Carrinho.Carrinho;
import Entities.Cliente.Cliente;
import Entities.Enums.Avisos;
import Entities.Estoque.Estoque;
import Entities.Pagamento.Pagamento;
import Entities.Enums.OpcaoMenuPrincipal; // Import dos Enums
import Entities.Enums.DecisaoCompra;      // Import dos Enums
import Entities.Enums.FormasPagamento;   // Import dos Enums

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private Carrinho carrinho = new Carrinho();
    private Pagamento pagamento = new Pagamento();
    private Estoque estoque = new Estoque();

    // Metodo que exibe o primeiro menu e liga as ações aos outros
    public void exibirMenuPrincipal() {
        boolean confirmacao = true;
        OpcaoMenuPrincipal opcao; // Uso do Enum OpcaoMenuPrincipal

        do {
            try {
                System.out.println(
                        "=================================" +
                                "\nSelecione a opção que você deseja:" +
                                "\n1- Adicionar produtos ao carrinho" +
                                "\n2- Ver carrinho" +
                                "\n3- Sair" +
                                "\n================================="
                );
                char resp = sc.next().charAt(0);

                switch (resp) {
                    case '1':
                        System.out.println(Avisos.CONFIRMADO.getMensagemUsuario());
                        menuCompras();
                        break;
                    case '2':
                        System.out.println(Avisos.CONFIRMADO.getMensagemUsuario());
                        menuCarrinho();
                        break;
                    case '3':
                        System.out.println(Avisos.SAIR.getMensagemUsuario());
                        confirmacao = false;
                        break;
                    default:
                        System.out.println(Avisos.ERRO.getMensagemUsuario());
                        break;
                }
            }catch (Exception e){
                System.out.println(Avisos.ERRO.getMensagemUsuario());
                sc.nextLine();
            }
        } while (confirmacao);

        sc.close();
    }

    // Metodo que exibe os produtos disponíveis para compra e armazena os itens escolhidos na ArrayList da classe Carrinho
    public void menuCompras() {
        int prod = 0;
        boolean confirmacao = true;
        do {
            try {
                System.out.println(
                        "=================================" +
                                "\nSelecione o produto que deseja comprar:"
                );
                System.out.print(estoque.exibirEstoque());

                prod = sc.nextInt();

                if (prod <= estoque.getProduto().length && prod > 0) {
                    estoque.setProdutoAtual(prod - 1);
                    carrinho.adicionarAoCarrinho(estoque);
                    menuCarrinho();
                } else {
                    System.out.println("Produto inválido, tente novamente.");
                    confirmacao = false;
                }

            }catch (InputMismatchException e){
                System.out.println(Avisos.ERRO.getMensagemUsuario());
                sc.nextLine();
            }
        } while (confirmacao);
    }

    // Metodo que exibe os produtos adicionados ao carrinho e realiza a decisão de finalizar ou não a compra
    public void menuCarrinho() {
        try {
            if (carrinho.getItensEscolhidos().isEmpty()) {
                System.out.println("O carrinho está vazio.");
                return;
            }
            else {
                System.out.println(
                        "=================================" +
                                "\nCarrinho:"
                );
                System.out.print(carrinho.exibirCarrinho(estoque));

                carrinho.verificarFreteGratis();
                System.out.printf("Preço total: R$ %.2f%n", carrinho.precoTotal());
                System.out.println("=================================");
            }

            DecisaoCompra finalizar; // Uso do Enum DecisaoCompra
            do {
                System.out.println(
                        "Deseja finalizar a compra?" +
                                "\n1- Sim" +
                                "\n2- Não"
                );
                char respFinalizar = sc.next().charAt(0);
                finalizar = DecisaoCompra.deCodigo(respFinalizar);

                if (finalizar == DecisaoCompra.FINALIZAR) {
                    coletarDadosCliente();
                    processarPagamento();
                }
                else if (finalizar == DecisaoCompra.INVALIDA) {
                    System.out.println("Digite uma opção válida");
                }

            } while (finalizar != DecisaoCompra.FINALIZAR && finalizar != DecisaoCompra.CANCERLAR_VOLTAR);
        }catch (InputMismatchException e){
            System.out.println(Avisos.ERRO.getMensagemUsuario());
            sc.nextLine();
        }
    }

    // Metodo que coleta e armazena os dados do cliente
    public void coletarDadosCliente() {
        String nome = "";
        String cpf = "";
        String cidade = "";
        String estado = "";
        String bairro = "";
        String rua;
        String numero = "";
        boolean confirmacao = false;
        sc.nextLine();
        try{
            do {
                boolean entradaValida = false;
                System.out.println("Digite seus dados para finalizar a compra:");
                System.out.println("Nome completo:");
                try {
                    nome = sc.nextLine();
                    if (nome.isEmpty()){
                        System.out.println(Avisos.ERRO.getMensagemUsuario());
                        confirmacao = true;
                        continue;
                    }
                } catch (IllegalArgumentException e){
                    System.out.println(Avisos.ERRO.getMensagemUsuario());
                    confirmacao = true;
                    continue;
                }
                carrinho.setNome(nome);
                System.out.println("CPF:");
                try {
                    cpf = sc.nextLine();
                    if (cpf.isEmpty()){
                        System.out.println(Avisos.ERRO.getMensagemUsuario());
                        confirmacao = true;
                        continue;
                    }
                }catch (IllegalArgumentException e){
                    System.out.println(Avisos.ERRO.getMensagemUsuario());
                    confirmacao = true;
                    continue;
                }
                carrinho.setCpf(cpf);
                System.out.println("Cidade:");
                try {
                    cidade = sc.nextLine();
                    if (cidade.isEmpty()){
                        System.out.println(Avisos.ERRO.getMensagemUsuario());
                        confirmacao = true;
                        continue;
                    }
                }catch (IllegalArgumentException e){
                    System.out.println(Avisos.ERRO.getMensagemUsuario());
                    confirmacao = true;
                    continue;
                }
                carrinho.setCidade(cidade);
                System.out.println("Estado:");
                try {
                    estado = sc.nextLine();
                    if (estado.isEmpty()){
                        System.out.println(Avisos.ERRO.getMensagemUsuario());
                        confirmacao = true;
                        continue;
                    }
                }catch (IllegalArgumentException e) {
                    System.out.println(Avisos.ERRO.getMensagemUsuario());
                    confirmacao = true;
                    continue;
                }
                carrinho.setEstado(estado);
                System.out.println("Bairro:");
                try {
                    bairro = sc.nextLine();
                    if (bairro.isEmpty()){
                        System.out.println(Avisos.ERRO.getMensagemUsuario());
                        confirmacao = true;
                        continue;
                    }
                }catch (IllegalArgumentException e){
                    System.out.println(Avisos.ERRO.getMensagemUsuario());
                    confirmacao = true;
                    continue;
                }
                carrinho.setBairro(bairro);
                System.out.println("Rua:");
                try {
                    rua = sc.nextLine();
                    if (rua.isEmpty()){
                        System.out.println(Avisos.ERRO.getMensagemUsuario());
                        confirmacao = true;
                        continue;
                    }
                }catch (IllegalArgumentException e) {
                    System.out.println(Avisos.ERRO.getMensagemUsuario());
                    confirmacao = true;
                    continue;
                }
                carrinho.setRua(rua);
                System.out.println("Número:");
                try {
                    numero = sc.nextLine();
                    if (numero.trim().isEmpty()) {
                        System.out.println(Avisos.ERRO.getMensagemUsuario());
                        confirmacao = true;
                        continue;
                    }
                }catch (IllegalArgumentException e){
                    System.out.println(Avisos.ERRO.getMensagemUsuario());
                    confirmacao = true;
                    continue;
                }
                int numeroFinal = 0;
                try {
                    numeroFinal = Integer.parseInt(numero);
                    entradaValida = true;
                } catch (NumberFormatException e) {
                    System.out.println(Avisos.ERRO.getMensagemUsuario());
                    confirmacao = true;
                    continue;
                }
                carrinho.setNumero(numeroFinal);
                if (confirmacao) {
                    System.out.println("\n🔄 Alguns campos estavam inválidos. Por favor, preencha novamente todo o formulário.\n");
                }
            }while (confirmacao);
        }catch (IllegalArgumentException e){
            System.out.println(Avisos.ERRO.getMensagemUsuario());
            sc.nextLine();
        }
    }

    // Metodo que processa a forma de pagamento escolhida
    public void processarPagamento() {
        FormasPagamento formaPagamento; // Uso do Enum FormasPagamento
        try {
            do {
                System.out.println(
                        "=================================" +
                                "\nSelecione a forma de formaPagamento" +
                                "\n1- Cartão de crédito" +
                                "\n2- PIX"
                );
                char respPagamento = sc.next().charAt(0);
                formaPagamento = FormasPagamento.deCodigo(respPagamento);

                if (formaPagamento == FormasPagamento.INVALIDA) {
                    System.out.println("Digite uma opção válida!");
                }

            } while (formaPagamento == FormasPagamento.INVALIDA);

            if (formaPagamento == FormasPagamento.CARTAO_CREDITO) {
                System.out.println("Digite o número do cartão de crédito:");
                sc.nextLine(); // Limpar buffer
                String numeroCartao = sc.nextLine();

                if (pagamento.verificarCartao(numeroCartao)) {
                    exibirCompra(FormasPagamento.CARTAO_CREDITO.getDescricao());
                }
                else
                    System.out.println("Número de cartão inválido. Compra não finalizada.");
            }
            else if (formaPagamento == FormasPagamento.PIX) {
                System.out.println(
                        "Chave de formaPagamento via PIX:" +
                                "\ne8b2a1c4-5d9f-4b7e-a123-bc90d1f56789" +
                                "\nAperte ENTER para confirmar o formaPagamento!"
                );
                sc.nextLine(); // Limpar buffer
                sc.nextLine(); // Espera pressionar ENTER

                exibirCompra(FormasPagamento.PIX.getDescricao());
            }
        }catch (InputMismatchException e){
            System.out.println(Avisos.ERRO.getMensagemUsuario());
            sc.nextLine();
        }
    }

    // Metodo que exibe um resumo da compra finalizada
    public void exibirCompra(String formaPagamento) {
        System.out.println(
                "=================================" +
                        "\nCompra finalizada com sucesso" +
                        "\nNúmero do pedido: " + pagamento.gerarNumeroPedido() +
                        "\nDetalhes da compra:" +
                        "\n" + carrinho.exibirCarrinho(estoque) +
                        "\nPreço total: R$ " + String.format("%.2f%n", carrinho.precoTotal()) +
                        "\nForma de pagamento: " + formaPagamento +
                         carrinho.toString() +
                        "\n================================="
        );
    }
}