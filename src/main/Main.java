package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Usuario> participantes = new ArrayList<>(); // deletarUsuario == true = removo
        ArrayList<Usuario> usuariosCadastrados = new ArrayList<>(); // deletarUsuario == true = removo
        ArrayList<Usuario> usuariosNaConversa = new ArrayList<>(); // deletarUsuario == true = removo
        ChatSystem chatSystem = new ChatSystem();
        Login login = new Login(chatSystem);

        // Antes de tudo, primeiro precisa criar 2 ou mais usuários
        // Vai ser tipo whatsapp, mas vai ter a parte que o usuário vai logar(se já foi cadastrado) e adiciona contatos(usuários)
        // A conversa não pode ter menos que 2 usuários
        while (true){

            System.out.println("--- ChatSystem ---");
            System.out.println("O que deseja fazer?");
            System.out.println("1 - Criar/Cadastrar Usuário"); // apenas cadastra e adiciona a lista de usuários cadastrados
            System.out.println("2 - Fazer login"); // faz o login do usuário pra poder criar conversas e conversar
            System.out.println("3 - Deletar um usuário");
            System.out.println("4 - Criar conversa"); // pode criar mais de uma conversa com usuarios diferentes
            System.out.println("5 - Iniciar conversa");
            System.out.println("6 - Excluir mensagem");
            System.out.println("7 - Exibir conversas");
            System.out.println("8 - Exibir participantes da conversa");
            System.out.println("9 - Exibir informações dos usuários cadastrados no sistema");
            System.out.println("0 - Sair do ChatSystem");
            System.out.print("Digite uma opção: ");
            int opc = sc.nextInt();

            sc.nextLine();
            switch (opc){
                case 1: // Aqui só cadastra, mas só vai poder participar das conversas se antes fizer login
                    System.out.printf("Digite seu nome: ");
                    String nome = sc.nextLine();
                    String email;
                    do {
                        System.out.print("Digite seu email: ");
                        email = sc.nextLine();
                        if(email.isEmpty()){
                            System.out.println("O campo email deve ser preenchido!");
                        }
                    }while (email.isEmpty());

                    String senha;
                    do{
                        System.out.print("Digite sua senha: ");
                        senha = sc.nextLine();
                        if(senha.length() < 6){
                            System.out.println("A senha deve conter no mínimo 6 caracteres!");
                        }
                    }while (senha.length() < 6);

                    Usuario novoUsuario = new Usuario(nome, email, senha);
                    if(chatSystem.cadastrarUsuario(novoUsuario))
                        usuariosCadastrados.add(novoUsuario);
                    break;
                case 2: // login - só vai poder participar das conversas se fzr o login antes
                    System.out.println("----Login----");
                    Usuario usuarioLogado = login.logar();
                    if(!usuariosCadastrados.contains(usuarioLogado)){
                        System.out.println("Informações incorretas ou não está cadastrado(a)");
                    }
                    if(usuarioLogado != null && !participantes.contains(usuarioLogado)){
                        participantes.add(usuarioLogado);
                    }
                    break;
                case 3:
                    System.out.printf("Digite o ID do usuário: ");
                    int idUser = sc.nextInt();
                    boolean removido = chatSystem.deletarUsuario(idUser);

                    if(removido){
                        usuariosCadastrados.removeIf(usuario -> usuario.getId() == idUser);
                        usuariosNaConversa.removeIf(usuario -> usuario.getId() == idUser);
                        participantes.removeIf(participante -> participante.getId() == idUser);
                    }
                    break;
                case 4: chatSystem.criarConversa(participantes); break;
                case 5:
                    System.out.print("Quantos participantes deseja que tenha na conversa?: ");
                    int qntParticipantes = sc.nextInt();
                    sc.nextLine(); // limpa buffer

                    if (qntParticipantes < 2) {
                        System.out.println("A conversa precisa de pelo menos 2 participantes.");
                        break;
                    }

                    for (int i = 0; i < qntParticipantes; i++) {
                        System.out.printf("Digite o ID do participante %d: ", i + 1);
                        int id = sc.nextInt();
                        sc.nextLine(); // limpa buffer

                        Usuario encontrado = null;
                        for (Usuario user : participantes) {
                            if (user.getId() == id) {
                                encontrado = user;
                                break;
                            }
                        }

                        if (encontrado == null) {
                            System.out.println("Usuário com esse ID não encontrado ou não está logado.");
                            break;
                        } else {
                            usuariosNaConversa.add(encontrado);
                        }
                    }

                    if (chatSystem.getConversas().isEmpty()) {
                        System.out.println("Nenhuma conversa disponível! Por favor, crie uma conversa.");
                        break;
                    }

                    Conversa conversaAtual = chatSystem.getConversas().getFirst();

                    System.out.println("Digite suas mensagens. Digite 'sair' para encerrar a conversa.");

                    int indiceRemetente = 0;
                    while (true) {
                        Usuario remetente = usuariosNaConversa.get(indiceRemetente);
//                        if(remetente == null){
//                            System.out.println("O usuário não existe");
//                            break;
//                        }
                        System.out.print(remetente.getNome() + ": ");
                        String msg = sc.nextLine();

                        if (msg.equalsIgnoreCase("sair")) {
                            System.out.println("Conversa encerrada.");
                            break;
                        }

                        chatSystem.enviarMensagem(remetente, conversaAtual, new Mensagem(msg, remetente));

                        // Alterna entre participantes
                        indiceRemetente = (indiceRemetente + 1) % usuariosNaConversa.size();
                    }
                    break;

                case 6:
                    System.out.println("Digite o nome do autor da mensagem:");
                    String nomeAutor = sc.nextLine();

                    Usuario autorMsg = null;
                    for(Usuario user : usuariosCadastrados){
                        if(user.getNome().equals(nomeAutor)){
                            autorMsg = user;
                            break;
                        }
                    }

                    if(autorMsg == null){
                        System.out.println("Autor da mensagem não encontrado.");
                        break;
                    }

                    System.out.print("Digite o ID da mensagem para deletar: ");
                    int idMsg = sc.nextInt();

                    chatSystem.excluirMensagem(autorMsg, chatSystem.getConversas().get(0), idMsg);
                    break;

                case 7:
                    if(chatSystem.getConversas().isEmpty()){
                        System.out.println("Nenhuma conversa disponível");
                    }
                    else
                        chatSystem.exibirConversa(chatSystem.getConversas().get(0));
                    break;

                case 8:
                    System.out.println("Participantes da conversa:");
                    if(participantes.isEmpty())
                        System.out.println("Não existe nenhum participante da conversa!");
                    for (Usuario user : participantes){
                        System.out.println(user);
                    }
                    break;

                case 9:
                    System.out.println("Informações dos usuários cadastrados");
                    if(chatSystem.getUsuarios().isEmpty())
                        System.out.println("Nenhum usuário cadastrado!");
                    for(Usuario user : chatSystem.getUsuarios().values()){
                        System.out.print("Nome: " + user.getNome());
                        System.out.print(" | Email: " + user.getEmail());
                        System.out.println(" | ID: " + user.getId());
                    }
                    break;
                case 0:
                    System.out.println("Encerrando o ChatSystem...");
                    return;
                default:
                    System.out.println("opção incorreta, tente novamente!");
                    break;
            }
        }
    }
}
