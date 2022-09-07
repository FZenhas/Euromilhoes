import java.util.Arrays;

public class Chave {

    private int[] numeros;
    private int[] estrelas;


    public Chave() {
        numeros = new int[5];
        estrelas = new int [2];
        }

    public Chave(int[] numeros, int[] estrelas) {
        this.numeros = numeros;
        this.estrelas = estrelas;
    }


    public int[] getNumeros() {
        return numeros;
    }

    public void setNumeros(int[] numeros) {
        this.numeros = numeros;
    }

    public int[] getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int[] estrelas) {
        this.estrelas = estrelas;
    }

    @Override
    public String toString() {
        return "Chave: " + "numeros = " + Arrays.toString(numeros) + "estrelas = " + Arrays.toString(estrelas) + " \n";
    }

}
