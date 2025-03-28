package Main;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Usuario> participantes = new ArrayList<>();

        Usuario usuario1 = new Usuario("Carlos", "carlos@gmail.com", "carlos123");
        Usuario usuario2 = new Usuario("Irving", "irving@gmail.com", "irving123");

//        Conversa chat = new Conversa(participantes);
        ChatSystem chatSystem = new ChatSystem();

        chatSystem.cadastrarUsuario(usuario1);
        chatSystem.cadastrarUsuario(usuario2);

        participantes.add(usuario1);
        participantes.add(usuario2);


        chatSystem.criarConversa(participantes);
        Conversa conversa = new Conversa(participantes);
        Mensagem msg = new Mensagem("EPA", usuario1);
        Mensagem msg2 = new Mensagem("OPA", usuario2);
//        chatSystem.enviarMensagem(usuario1, conversa, "OPA");
        chatSystem.exibirConversa(msg, usuario2);
        chatSystem.exibirConversa(msg2, usuario1);
//        chatSystem.deletarUsuario(1);

//        System.out.println(chatSystem);

//        System.out.println(chat);
    }
}
