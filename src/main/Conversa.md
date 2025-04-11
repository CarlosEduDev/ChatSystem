Explicação do código da Classe Conversa
private ArrayList<Usuario> participantes;
private ArrayList<Mensagem> mensagens;

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

O motivo de incluir `public Conversa(ArrayList<Usuario> conversantes)` no construtor é garantir 
que a conversa seja criada com os participantes já definidos.

Problema se não tivermos um construtor que recebe a lista de conversantes, 
a conversa será sempre criada sem participantes e teria que adicioná-los manualmente depois.

`Conversa conversa = new Conversa(); // Criada vazia
conversa.getParticipantes().add(usuario1); // Adicionando manualmente
conversa.getParticipantes().add(usuario2);`

Com o ArrayList conversantes criado no construtor, o código ficaria assim:

`ArrayList<Usuario> listaUsuarios = new ArrayList<>();
listaUsuarios.add(usuario1);
listaUsuarios.add(usuario2);
Conversa conversa = new Conversa(listaUsuarios); // Criada com os participantes`