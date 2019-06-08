package kkkk;

public class Teste {
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();
        Originador originador = new Originador();
        originador.setState("Jogada 1");
        originador.setState("Jogada 2");
        caretaker.addMemento( originador.save() );
        originador.setState("Jogada 3");
        caretaker.addMemento( originador.save() );
        originador.setState("Jogada 4");
        originador.restore( caretaker.getMemento() );
    }
}
