package Main;

import java.util.Date;

public class Mensagem {
    private int id;
    private static int contID = 0;
    private String conteudo;
    private Usuario autor;
    private Date horarioEnvio;

    public Mensagem(String conteudo) {
        this.id = ++contID;
        this.conteudo = conteudo;
        this.horarioEnvio = new Date();
    }

    public String getConteudo() {
        return conteudo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Date getHorarioEnvio() {
        return horarioEnvio;
    }

    @Override
    public String toString() {
        return "--- Mensagem ---" +
                "\nconteudo = " + conteudo +
                ", autor=" + autor +
                ", horarioEnvio = " + horarioEnvio;
    }
}
