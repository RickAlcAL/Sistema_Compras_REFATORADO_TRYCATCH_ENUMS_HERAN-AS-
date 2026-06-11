package Entities.Cliente;

public class Cliente {
    protected String nome;
    protected String cpf;
    protected String cidade;
    protected String estado;
    protected String bairro;
    protected String rua;
    protected int numero;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String cidade, String estado, String bairro, String rua, int numero) {
        this.nome = nome;
        this.cpf = cpf;
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEstado() {
        return estado;
    }

    public String getRua() {
        return rua;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}