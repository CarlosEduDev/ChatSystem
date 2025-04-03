package Main;

import java.util.Scanner;

public class Login {
    private ChatSystem chatSystem;

    public Login(ChatSystem chatSystem){
        this.chatSystem = chatSystem;
    }


    public Usuario logar(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Coloque seu nome de usuário: ");
        String nome = sc.nextLine();

        System.out.print("Coloque sua senha: ");
        String senha = sc.nextLine();

        for(Usuario user : chatSystem.getUsuarios().values()){
            if(user.getNome().equalsIgnoreCase(nome) && user.getSenha().equalsIgnoreCase(senha)){
                System.out.println("Login realizado com sucesso! Boas vindas " + user.getNome());
                return user; // conseguiu logar;
            }
        }

        System.out.println("Nome de usuário ou senha incorretos, tente novamente.");

        return null; // não logou
    }
}
