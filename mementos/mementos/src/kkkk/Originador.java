package kkkk;

class Originador {
    private String state;

    public void setState(String state) {
        System.out.println("Originador: Mudando estado para " + state);
        this.state = state;
    }
    public Memento save() {
        System.out.println("Originador: Salvando no Memento.");
        return new Memento(state);
    }
    public void restore(Memento m) {
        state = m.getState();
        System.out.println("Originador: Estado retornado do Memento: " + state);
    }
}
