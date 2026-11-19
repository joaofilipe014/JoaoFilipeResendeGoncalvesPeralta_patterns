// === Produtos abstratos ===
interface Carro {
    void dirigir();
}

interface Moto {
    void pilotar();
}

// === Produtos concretos: família ELÉTRICA ===
class CarroEletrico implements Carro {
    public void dirigir() {
        System.out.println("Dirigindo carro elétrico (silencioso e sem emissões).");
    }
}

class MotoEletrica implements Moto {
    public void pilotar() {
        System.out.println("Pilotando moto elétrica (recarregada na tomada).");
    }
}

// === Produtos concretos: família COMBUSTÃO ===
class CarroCombustao implements Carro {
    public void dirigir() {
        System.out.println("Dirigindo carro a combustão (abastecido com gasolina).");
    }
}

class MotoCombustao implements Moto {
    public void pilotar() {
        System.out.println("Pilotando moto a combustão (abastecida com gasolina).");
    }
}

// === Fábrica abstrata ===
interface FabricaVeiculos {
    Carro criarCarro();
    Moto criarMoto();
}

// === Fábricas concretas ===
class FabricaEletrica implements FabricaVeiculos {
    public Carro criarCarro() { return new CarroEletrico(); }
    public Moto criarMoto() { return new MotoEletrica(); }
}

class FabricaCombustao implements FabricaVeiculos {
    public Carro criarCarro() { return new CarroCombustao(); }
    public Moto criarMoto() { return new MotoCombustao(); }
}

// === Cliente: só conhece as interfaces ===
class Concessionaria {
    private Carro carro;
    private Moto moto;

    public Concessionaria(FabricaVeiculos fabrica) {
        this.carro = fabrica.criarCarro();
        this.moto = fabrica.criarMoto();
    }

    public void testarVeiculos() {
        carro.dirigir();
        moto.pilotar();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        System.out.println("--- Concessionária Elétrica ---");
        Concessionaria eletrica = new Concessionaria(new FabricaEletrica());
        eletrica.testarVeiculos();

        System.out.println("\n--- Concessionária Combustão ---");
        Concessionaria combustao = new Concessionaria(new FabricaCombustao());
        combustao.testarVeiculos();
    }
}