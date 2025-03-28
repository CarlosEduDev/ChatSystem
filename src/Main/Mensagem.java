package Main;

import java.util.Date;

public class Mensagem {
    private int id;
    private static int contID = 0;
    private String conteudo;
    private Usuario autor;
    private Date horarioEnvio;

    public Mensagem(String conteudo, Usuario autor) {
        this.id = ++contID;
        this.conteudo = conteudo;
        this.autor = autor;
        this.horarioEnvio = new Date();
    }

    public int getId() {
        return id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
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
                "\nautor = " + autor;
    }
}
