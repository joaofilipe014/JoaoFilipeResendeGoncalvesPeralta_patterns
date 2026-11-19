// ===== Componente =====
interface Pizza {
    String getDescricao();
    double getPreco();
}

// ===== Componente concreto =====
class PizzaMussarela implements Pizza {
    public String getDescricao() { return "Pizza de Mussarela"; }
    public double getPreco() { return 40.0; }
}

// ===== Decorator abstrato =====
abstract class AdicionalDecorator implements Pizza {
    protected Pizza pizza;

    public AdicionalDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

// ===== Decorators concretos =====
class BordaRecheada extends AdicionalDecorator {
    public BordaRecheada(Pizza pizza) { super(pizza); }

    public String getDescricao() { return pizza.getDescricao() + ", Borda Recheada"; }
    public double getPreco() { return pizza.getPreco() + 8.0; }
}

class Bacon extends AdicionalDecorator {
    public Bacon(Pizza pizza) { super(pizza); }

    public String getDescricao() { return pizza.getDescricao() + ", Bacon"; }
    public double getPreco() { return pizza.getPreco() + 6.0; }
}

class QueijoExtra extends AdicionalDecorator {
    public QueijoExtra(Pizza pizza) { super(pizza); }

    public String getDescricao() { return pizza.getDescricao() + ", Queijo Extra"; }
    public double getPreco() { return pizza.getPreco() + 5.0; }
}

public class Decorator {
    public static void main(String[] args) {
        Pizza pedido1 = new PizzaMussarela();
        System.out.println(pedido1.getDescricao() + " -> R$ " + pedido1.getPreco());

        Pizza pedido2 = new Bacon(new BordaRecheada(new PizzaMussarela()));
        System.out.println(pedido2.getDescricao() + " -> R$ " + pedido2.getPreco());

        Pizza pedido3 = new QueijoExtra(new Bacon(new BordaRecheada(new PizzaMussarela())));
        System.out.println(pedido3.getDescricao() + " -> R$ " + pedido3.getPreco());
    }
}