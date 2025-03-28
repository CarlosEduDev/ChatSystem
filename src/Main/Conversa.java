package Main;

import java.util.ArrayList;

public class Conversa {
    private ArrayList<Usuario> participantes;
    private ArrayList<Mensagem> mensagens;

    public Conversa(ArrayList<Usuario> conversantes){
        if(conversantes.size() < 2){
            System.out.println("A conversa deve ter pelo menos 2 participantes");
        }
        this.participantes = new ArrayList<>(conversantes);
        this.mensagens = new ArrayList<>();
    }

    public void adicionarMensagem(Mensagem mensagem){
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
        return "\nmensagens = " + mensagens;
    }
}
