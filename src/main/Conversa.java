package main;

import java.util.ArrayList;

public class Conversa {
    private ArrayList<Usuario> participantes;
    private ArrayList<Mensagem> mensagens;

    public Conversa(ArrayList<Usuario> conversantes){
        this.participantes = new ArrayList<>(conversantes);
        this.mensagens = new ArrayList<>();
    }

    protected void adicionarMensagem(Mensagem mensagem){
        mensagens.add(mensagem);
    }

    public ArrayList<Usuario> getParticipantes() {
        return participantes;
    }

    public ArrayList<Mensagem> getMensagens() {
        return mensagens;
    }

    @Override
    public String toString() {
        return "conversa: " + mensagens;
    }
}
