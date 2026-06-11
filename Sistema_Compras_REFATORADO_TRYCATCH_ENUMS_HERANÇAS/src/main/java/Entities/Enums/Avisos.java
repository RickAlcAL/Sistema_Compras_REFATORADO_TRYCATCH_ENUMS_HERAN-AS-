package Entities.Enums;

public enum Avisos {
    CONFIRMADO ("Situação confirmada"),
    ERRO("Erro... tente novamente"),
    AGUARDE ("Aguarde..."),
    SAIR ("Saindo...");

    private final String mensagemUsuario ;

    Avisos (String mensagemUsuario) {
        this.mensagemUsuario = mensagemUsuario;
    }

    public String getMensagemUsuario() {
        return mensagemUsuario;
    }
}
