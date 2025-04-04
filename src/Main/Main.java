package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Usuario> participantes = new ArrayList<>();
        ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();
        ChatSystem chatSystem = new ChatSystem();
        Login login = new Login(chatSystem);

        // Antes de tudo, primeiro precisa criar 2 ou mais usuários
        // Vai ser tipo whatsapp, mas vai ter a parte que o usuário vai logar(se já foi cadastrado) e adiciona contatos(usuários)
        while (true){

            System.out.println("--- ChatSystem ---");
            System.out.println("O que deseja fazer?");
            System.out.println("1 - Criar/Cadastrar Usuário");
            System.out.println("2 - Fazer login");
            System.out.println("3 - Deletar um usuário");
            System.out.println("4 - Criar conversa");
            System.out.println("5 - Iniciar conversa");
            System.out.println("6 - Excluir mensagem");
            System.out.println("7 - Exibir conversas");
            System.out.println("8 - Exibir participantes da conversa");
            System.out.println("9 - Exibir informações dos participantes da conversa");
            System.out.println("10 - Exibir informações dos usuários cadastrados no sistema");
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
                    chatSystem.deletarUsuario(idUser);
                    break;
                case 4: chatSystem.criarConversa(participantes); break;
                case 5:
                    System.out.printf("Digite o ID do primeiro participante: ");
                    int user1Id = sc.nextInt();

                    System.out.printf("Digite o ID do primeiro participante: ");
                    int user2Id = sc.nextInt();
                    sc.nextLine(); // Limpa o buffer sla

                    Usuario user1 = null, user2 = null;
                    for (Usuario user : participantes) {
                        if (user.getId() == user1Id) user1 = user;
                        if (user.getId() == user2Id) user2 = user;
                    }

                    if (user1 == null || user2 == null) {
                        System.out.println("Um dos usuários não foi encontrado!");
                        break;
                    }

                    if (chatSystem.getConversas().isEmpty()) {
                        System.out.println("Nenhuma conversa disponível! Por favor, crie uma conversa.");
                        break;
                    }

                    Conversa conversaAtual = chatSystem.getConversas().get(0);

                    System.out.println("Digite suas mensagens. Digite 'sair' para encerrar a conversa.");

                    Usuario remetente = user1;
                    while (true) {
                        System.out.printf(remetente.getNome() + ": ");
                        String msg = sc.nextLine();

                        if (msg.equalsIgnoreCase("sair")) {
                            System.out.println("Conversa encerrada.");
                            break;
                        }

                        chatSystem.enviarMensagem(remetente, conversaAtual, new Mensagem(msg, remetente));

                        // alterna entre usuários
                        remetente = (remetente == user1) ? user2 : user1;
                    }
                    break;
                case 6:
                    System.out.println("Digite o nome do autor da mensagem:");
                    String autor = sc.nextLine();

                    System.out.println("Digite o ID da mensagem para deletar:");
                    int idMsg = sc.nextInt();

                    chatSystem.excluirMensagem(autor, chatSystem.getConversas().get(0), idMsg);
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
                    System.out.println("Informações dos participantes da conversa:");
                    if(participantes.isEmpty()){
                        System.out.println("Nenhum participante na conversa.");
                    }
                    for(int i = 0; i < participantes.size(); i++){
                        System.out.printf("Nome: " + participantes.get(i).getNome());
                        System.out.print(" | Email:" + participantes.get(i).getEmail());
                        System.out.println(" | ID:" + participantes.get(i).getId());
                    }
                    break;
                case 10:
                    System.out.println("Informações dos usuários cadastrados");
                    if(usuariosCadastrados.isEmpty())
                        System.out.println("Nenhum usuário cadastrado!");
                    for(int i = 0; i < usuariosCadastrados.size(); i++){
                        System.out.print("Nome: " + usuariosCadastrados.get(i).getNome());
                        System.out.print(" | Email: " + usuariosCadastrados.get(i).getEmail());
                        System.out.println(" | ID: " + usuariosCadastrados.get(i).getId());
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
