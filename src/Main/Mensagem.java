package Main;

import java.text.SimpleDateFormat; // só pra formatar o horário da classe Date
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

    public String getAutor() {
        return autor.getNome();
    }

    @Override
    public String toString() {
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
        String horarioFormatado = formatador.format(horarioEnvio);
        return "[" + horarioFormatado + "] " +
                autor.getNome()+ ": " + conteudo + " - Id(" + id + ')';
    }
}