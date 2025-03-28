package Main;

import java.util.ArrayList;

public class Conversa {
    private ArrayList<Usuario> participantes;
    private ArrayList<Mensagem> mensagens;

    public Conversa(ArrayList<Usuario> conversantes){
        if(conversantes.size() < 2){
            System.out.println("A conversa deve ter pelo menos 2 participantes");
        }
        this.participantes = new ArrayList<>();
        this.mensagens = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "*** Conversa ***" +
                "\nparticipantes = " + participantes +
                "\nmensagens = " + mensagens;
    }
}
