package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Usuario> participantes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Digite seu nome:");
//        String nome = sc.nextLine();
//
//        System.out.println("Digite seu email:");
//        String email = sc.nextLine();
//
//        String senha;
//
//        do{
//            System.out.println("Digite sua senha: ");
//            senha = sc.nextLine();
//            if(senha.length() < 6){
//                System.out.println("A senha deve conter no mínimo 6 caracteres!");
//            }
//        }while (senha.length() < 6);
        Usuario user1 = new Usuario("João", "joao@email.com", "joao123");
//
//        System.out.println("Digite o nome do outro usuário:");
//        nome = sc.nextLine();
//
//        System.out.println("Digite o email do outro usuário:");
//        email = sc.nextLine();
//
//        String senha2;
//
//        do{
//            System.out.println("Digite a senha do outro usuário: ");
//            senha2 = sc.nextLine();
//            if(senha2.length() < 6){
//                System.out.println("A senha deve conter no mínimo 6 caracteres!");
//            }
//        }while (senha2.length() < 6);
        Usuario user2 = new Usuario("Carlos", "carlos@email.com", "carlos123");

        ChatSystem chatSystem = new ChatSystem();

        if(chatSystem.cadastrarUsuario(user1))
            participantes.add(user1);

        if(chatSystem.cadastrarUsuario(user2))
            participantes.add(user2);


        Conversa chat = new Conversa(participantes);

        System.out.printf(user1.getNome() + " Digite a mensagem: ");
        String msg1 = sc.next();
        Mensagem msg = new Mensagem(msg1, user1);

        System.out.printf(user2.getNome() + " Digite a mensagem: ");
        String msg02 = sc.next();
        Mensagem msg2 = new Mensagem(msg02, user2);

//        chatSystem.deletarUsuario(1);

        chatSystem.criarConversa(participantes);
        chatSystem.enviarMensagem(user1, chat, msg);
        chatSystem.excluirMensagem(user1, chat, 1);
        chatSystem.enviarMensagem(user2, chat, msg2);

        chatSystem.exibirConversa(chat);


    }
}
