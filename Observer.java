import java.util.ArrayList;
import java.util.List;

// ===== Observador =====
interface Cliente {
    void notificar(String produto);
}

// ===== Observadores concretos =====
class ClienteEmail implements Cliente {
    private String email;

    public ClienteEmail(String email) { this.email = email; }

    public void notificar(String produto) {
        System.out.println("[E-mail para " + email + "] " + produto + " voltou ao estoque!");
    }
}

class ClienteApp implements Cliente {
    private String usuario;

    public ClienteApp(String usuario) { this.usuario = usuario; }

    public void notificar(String produto) {
        System.out.println("[Push no app de " + usuario + "] " + produto + " disponível!");
    }
}

// ===== Sujeito (observado) =====
class Produto {
    private String nome;
    private int estoque;
    private List<Cliente> interessados = new ArrayList<>();

    public Produto(String nome) {
        this.nome = nome;
        this.estoque = 0;
    }

    public void adicionarInteressado(Cliente c) { interessados.add(c); }

    public void removerInteressado(Cliente c) { interessados.remove(c); }

    public void reporEstoque(int quantidade) {
        boolean estavaEsgotado = (estoque == 0);
        estoque += quantidade;
        System.out.println("\nEstoque de " + nome + " atualizado: " + estoque + " unidades.");

        if (estavaEsgotado && estoque > 0) {
            notificarTodos();
        }
    }

    public void vender(int quantidade) {
        estoque -= quantidade;
        System.out.println("\nVendidas " + quantidade + " unidades. Estoque: " + estoque);
    }

    private void notificarTodos() {
        for (Cliente c : interessados) {
            c.notificar(nome);
        }
    }
}

public class Observer {
    public static void main(String[] args) {
        Produto playstation = new Produto("PlayStation 5");

        Cliente maria = new ClienteEmail("maria@email.com");
        Cliente pedro = new ClienteApp("pedro123");

        playstation.adicionarInteressado(maria);
        playstation.adicionarInteressado(pedro);

        playstation.reporEstoque(10);   // estava esgotado -> notifica

        playstation.vender(10);         // esgota de novo
        playstation.removerInteressado(pedro);
        playstation.reporEstoque(5);    // notifica só a Maria
    }
}