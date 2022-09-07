import java.util.*;


public class Main {
    static Scanner in = new Scanner(System.in);
    static int[] numeros = new int[5];
    static int[] estrelas = new int[2];
    static Chave chaveVencedora;
    static int nrChaves = 0;
    static Random rnd = new Random();
    static List<Chave> listaChave;


    public static void main(String[] args) {

        /*O Euromilhões é um jogo de sorte ou azar no qual o jogador deve preencher uma chave composta por 5 números de 1 a
        50 e 2 estrelas (números) de 1 a 11. Crie uma aplicação que permita ao utilizador:
        1. Simular um sorteio (sortear uma chave vencedora que deve ser apresentada ao utilizador de forma ordenada).
        2. Criar um boletim com 1 a 5 chaves (inseridas pelo utilizador) e comparar com a chave vencedora desse sorteio.
        3. Criar um boletim com 1 a 5 chaves (criadas aleatoriamente) e comparar com a chave vencedora desse sorteio.
        4. Simular quantas vezes seria necessário jogar (sempre com a mesma chave) de forma a conseguir ganhar o 1º
        prémio (5 números e 2 estrelas)
         */

        listaChave = new ArrayList<>();

        int opcao = 1;

        while (opcao != 0) {
            System.out.println("Bem-vindo ao Euromilhoes. \nEscolha 5 numeros de 1 a 50 e 2 estrelas (numeros) de 1 a 12. \nBoa sorte.");
            System.out.println("1. Jogar");
            System.out.println("2. Ver chave(s) jogada(s)");
            System.out.println("3. Ver sorteio");
            System.out.println("4. Consultar os resultados e premios");
            System.out.println("5: Simular a probabilidade de ganhar o Euromilhoes");
            System.out.println("0: sair");
            opcao = in.nextInt();

            switch (opcao) {

                case 0:
                    System.out.println("sair");
                    return;
                case 1:
                    Jogar();
                    break;
                case 2:
                    VerChave();
                    break;
                case 3:
                    Sorteio();
                    break;
                case 4:
                    VerResultadosPremios();
                    break;
                case 5:
                    Simulador();
                    break;
                default:
                    return;
            }
        }
    }

    private static void Jogar() {
        in = new Scanner(System.in);

        System.out.println("O Euromilhoes e composto por uma chave que tem 5 numeros (1 a 50) e 2 estrelas (1 a 12). Pode fazer ate 5 chaves por boletim de aposta");
        System.out.println("");
        System.out.println("Quantas chaves que jogar");
        nrChaves = in.nextInt();


        if (!(nrChaves >= 1 && nrChaves < 6)) {
            System.out.println("Opcao invalida. Escolha entre 1 a 5 chaves.");
            return;
        }

        System.out.println("Pretende escolher os numeros ou gerar uma aposta aleatoria: \n Escolha: \n 1: Preencher manualmente \n 2: Gerar aposta aleatoria");
        int opcao = in.nextInt();

        switch (opcao) {
            case 1:
                JogarManualmente(nrChaves);
                break;
            case 2:
                JogarAleatoriamente(nrChaves);
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }
    }

    private static void JogarAleatoriamente(int nrChaves) {

        if(!listaChave.isEmpty()){ //Permite limpar a lista após o sorteio.
            listaChave.clear();
        }

        for (int a = 0; a < nrChaves; a++) {
            listaChave.add(new Chave());
            for (int i = 0; i < listaChave.get(listaChave.size() - 1).getNumeros().length; i++) {
                int palpite = rnd.nextInt(1, 51);
                boolean valorExistente = false;
                for (int j = 0; j < listaChave.get(listaChave.size() - 1).getNumeros().length; j++) {
                    if (palpite == listaChave.get(listaChave.size() - 1).getNumeros()[j]) {
                        valorExistente = true;
                        i--;
                        break;
                    }
                }
                if (!valorExistente) {
                    listaChave.get(listaChave.size() - 1).getNumeros()[i] = palpite;
                }
            }

            for (int i = 0; i < listaChave.get(listaChave.size() - 1).getEstrelas().length; i++) {
                int palpiteEst = rnd.nextInt(1, 13);
                boolean valorExistente = false;
                for (int j = 0; j < listaChave.get(listaChave.size() - 1).getEstrelas().length; j++) {
                    if (palpiteEst == listaChave.get(listaChave.size() - 1).getEstrelas()[j]) {
                        valorExistente = true;
                        i--;
                        break;
                    }
                }
                if (!valorExistente) {
                    listaChave.get(listaChave.size() - 1).getEstrelas()[i] = palpiteEst;
                }
            }
        }
        for (int i = 0; i < listaChave.size(); i++) {

            Arrays.sort(listaChave.get(i).getNumeros());
            Arrays.sort(listaChave.get(i).getEstrelas());
        }
        CriarChavePremiada();
    }

    private static void JogarManualmente(int nrChaves) {

        if(!listaChave.isEmpty()){
            listaChave.clear();
        }

        for (int a = 0; a < nrChaves; a++) {
            int[] numeros = new int[5];
            int[] estrelas = new int[2];

            System.out.println("Como preencher a chave: \n Escolha 5 numeros de 1 a 50 e 2 estrelas (numeros) de 1 a 12.");
            System.out.println("");
            System.out.println("Chave " + (a + 1) + ":");
            System.out.println("Primeiro os numeros");

            for (int i = 0; i < numeros.length; i++) {
                System.out.println("Selecione o " + (i + 1) + " Numero");
                int palpite = in.nextInt();
                boolean valorExistente = false;
                if (palpite >= 1 && palpite <= 50) {
                    for (int j = 0; j < numeros.length; j++) {
                        if (palpite == numeros[j]) {
                            System.out.println("Ja selecionou este numero. Escolha um numero diferente.");
                            valorExistente = true;
                            i--;
                            break;
                        }
                    }
                    if (!valorExistente) {
                        numeros[i] = palpite;
                    }
                } else {
                    i--;
                    System.out.println("Numero invalido. Escolha um valor entre 1 e 50.");
                }
            }

            for (int i = 0; i < estrelas.length; i++) {
                System.out.println("Escolha as estrelas. \n " + (i + 1) + " Estrela");
                int palpiteEst = in.nextInt();
                boolean valorExistente = false;
                if (palpiteEst >= 1 && palpiteEst <= 12) {
                    for (int j = 0; j < estrelas.length; j++) {
                        if (palpiteEst == estrelas[j]) {
                            System.out.println("Ja selecionou este numero. Escolha um numero diferente.");
                            valorExistente = true;
                            i--;
                            break;
                        }
                    }
                    if (!valorExistente) {
                        estrelas[i] = palpiteEst;
                    }
                } else {
                    i--;
                    System.out.println("Numero invalido. Escolha um valor entre 1 e 12.");
                }
            }
            Arrays.sort(numeros);
            Arrays.sort(estrelas);
            listaChave.add(new Chave(numeros, estrelas));
        }
        CriarChavePremiada();
    }

    private static void VerChave() {
        if (listaChave.size() == 0) {
            System.out.println("Jogue primeiro.");
            return;
        }

        for (int i = 0; i < listaChave.size(); i++) {
            System.out.print(listaChave.get(i));
            System.out.println("");
        }
    }

    private static void Sorteio() {

        if (listaChave.size() == 0) {
            System.out.println("Jogue primeiro.");
            return;
        }

        System.out.println("--------------------------");
        System.out.println("A chave premiada e: \n" + chaveVencedora);
        System.out.println("--------------------------");
    }

    private static void VerResultadosPremios() {
        if (listaChave.size() == 0) {
            System.out.println("Jogue primeiro.");
            return;
        }

        System.out.print("A(s) sua(s) chave(s) e(sao): " + "\n" + listaChave + "\n");
        System.out.println("");
        System.out.println("A chave vencedora e " + chaveVencedora);

// Comparações entre variaveis
        int[] numIguais = new int[listaChave.size()];
        int[] estrelasIguais = new int[listaChave.size()];

        for (int a = 0; a < nrChaves; a++) {

            for (int i = 0; i < listaChave.get(listaChave.size() - 1).getNumeros().length; i++) {
                for (int j = 0; j < chaveVencedora.getNumeros().length; j++) {
                    if (listaChave.get(a).getNumeros()[i] == chaveVencedora.getNumeros()[j]) {
                        numIguais[a]++;
                    }
                }
            }
            for (int i = 0; i < listaChave.get(listaChave.size() - 1).getEstrelas().length; i++) {
                for (int j = 0; j < chaveVencedora.getEstrelas().length; j++) {
                    if (listaChave.get(a).getEstrelas()[i] == chaveVencedora.getEstrelas()[j]) {
                        estrelasIguais[a]++;
                    }
                }
            }
        }
        System.out.println("PREMIOS:");
        System.out.println("");
        for (int i = 0; i < nrChaves; i++) {
            if (numIguais[i] == 5 && estrelasIguais[i] == 2) { // 1ºpremio
                System.out.println("Chave #" + (i + 1) + " Ganhou o primeiro premio. PARABENS!! E o novo euroMilionario!");
            } else if (numIguais[i] == 5 && estrelasIguais[i] == 1) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o segundo premio. Parabens!!");
            } else if (numIguais[i] == 5 && estrelasIguais[i] == 0) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o terceiro premio. Parabens!!");
            } else if (numIguais[i] == 4 && estrelasIguais[i] == 2) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o quarto premio. Parabens!!");
            } else if (numIguais[i] == 4 && estrelasIguais[i] == 1) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o quinto premio. Parabens!!");
            } else if (numIguais[i] == 3 && estrelasIguais[i] == 2) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o sexto premio. Parabens!!");
            } else if (numIguais[i] == 4 && estrelasIguais[i] == 0) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o setimo premio. Parabens!!");
            } else if (numIguais[i] == 2 && estrelasIguais[i] == 2) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o oitava premio. Parabens!!");
            } else if (numIguais[i] == 3 && estrelasIguais[i] == 1) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o nono premio. Parabens!!");
            } else if (numIguais[i] == 3 && estrelasIguais[i] == 0) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o decimo premio. Parabens!!");
            } else if (numIguais[i] == 1 && estrelasIguais[i] == 2) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o decimo primeiro premio.Parabens!!");
            } else if (numIguais[i] == 2 && estrelasIguais[i] == 1) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o decimo segundo premio. Parabens!!");
            } else if (numIguais[i] == 2 && estrelasIguais[i] == 0) {
                System.out.println("Chave #" + (i + 1) + " Ganhou o decimo terceiro premio. Parabens!!");
            } else {
                System.out.println("Chave #" + (i + 1) + " nao premiada.");
                System.out.println("");
            }
        }
    }

    private static void CriarChavePremiada() {
        Random rnd = new Random();

        for (int i = 0; i < numeros.length; i++) {
            int numSort = rnd.nextInt(1, 51);
            boolean valorExistente = false;
            for (int j = 0; j < numeros.length; j++) {
                if (numSort == numeros[j]) {
                    valorExistente = true;
                    i--;
                    break;
                }
            }
            if (!valorExistente) {
                numeros[i] = numSort;
            }
        }

        for (int i = 0; i < estrelas.length; i++) {
            int numSort = rnd.nextInt(1, 13);
            boolean valorExistente = false;
            for (int j = 0; j < estrelas.length; j++) {
                if (numSort == estrelas[j]) {
                    valorExistente = true;
                    i--;
                    break;
                }
            }
            if (!valorExistente) {
                estrelas[i] = numSort;
            }
        }
        Arrays.sort(numeros);
        Arrays.sort(estrelas);
        chaveVencedora = new Chave(numeros, estrelas);
    }

    private static void Simulador() {

        int[] numsFixos = {3, 11, 19, 33, 45};
        int[] estrelasFixas = {2, 7};
        Chave chaveFixa = new Chave(numsFixos, estrelasFixas);

        int numsIguais = 0;

        int numTentativas = 0;

        do {
            CriarChavePremiada();
            for (int i = 0; i < chaveFixa.getNumeros().length; i++) {
                for (int j = 0; j < chaveVencedora.getNumeros().length; j++) {
                    if (chaveFixa.getNumeros()[i] == chaveVencedora.getNumeros()[j]) {
                        numsIguais++;
                    }
                }
            }

            for (int i = 0; i < chaveFixa.getEstrelas().length; i++) {
                for (int j = 0; j < chaveVencedora.getEstrelas().length; j++) {
                    if (chaveFixa.getEstrelas()[i] == chaveVencedora.getEstrelas()[j]) {
                        numsIguais++;
                    }
                }
            }
            if (numsIguais < 7) {
               numsIguais = 0;
                }

            numTentativas++;
            System.out.println("Chave nao premiada.");
        }while (numsIguais != 7);

        double valorGasto = numTentativas*2.5;
        int tempoGasto = numTentativas/104; // 2 apostas por semanas * 52 semanas

            System.out.println("A sua aposta foi: " + chaveFixa + " \n A chave vencedora foi: " + chaveVencedora + " \nPARABENS! E o novo Euromilionario!");
            System.out.println("Foram necessarias " + numTentativas + " apostas para ganhar.");
            System.out.println("Levaria " + tempoGasto + " anos para ganhar.");
            System.out.println("Gastaria " + valorGasto + " euros.");
            System.out.println("-----------------------------");
        }
    }



