package pkgJogoDaVelha;

import java.util.ArrayList;

/**
 * Created by carmen on 16/06/2017.
 */
public class JogadorAI extends JogadorAbstrato {
    private char ladoMin, ladoMax;
    public int escolha;
    /**
     * Define o lado de um jogador qualquer
     * do jogo da velha
     * @param entrada
     */
    public JogadorAI(char[] entrada) {
        super(entrada);
        ladoMax = this.lado;
        ladoMin = (ladoMax == 'x') ? 'o' : 'x';
    }

    public int jogar(Tabuleiro tabuleiro){
        Tabuleiro tabuleiroAnalisar = new Tabuleiro(tabuleiro);
        minimax(tabuleiroAnalisar, ladoMax, 0);
        System.out.println(lado + " escolhe " + escolha);
        return escolha;
    }

    /**
     * Função para analisar todas as possibilidades de jogadas de cada um dos lados, procurando minimizar
     * as perdas
     * */
    private int minimax(Tabuleiro tabuleiro, char ladoAtual, int realizarEscolha) {
        //Criar uma lista com as posições vagas do tableiro
        ArrayList<Integer> posicoesVagas = acharPosicoesVagas(tabuleiro);

        //Se não tiver posições vagas ou o jogo tiver acabado, retornar o resultado
        if(tabuleiro.procurarVencedor() != '?')
            return verificarResultado(tabuleiro);

        //para cada posição vaga, computar os possiveis resultados e
        //guardar os valores em uma lista
        ArrayList<Integer> resultados = new ArrayList<>();
        for (Integer possivelJogada : posicoesVagas) {
            char proximoAJogar = (ladoAtual == ladoMax) ? ladoMin : ladoMax;
            Tabuleiro possivelTabuleiro = new Tabuleiro(tabuleiro);
            possivelTabuleiro.realizarJogada(ladoAtual, possivelJogada);
            resultados.add(minimax(possivelTabuleiro, proximoAJogar, realizarEscolha + 1));
            possivelTabuleiro.realizarJogada('?', possivelJogada);
        }

        //A jogada a ser realisada é a primeira que contém o resultado ideal
        if(realizarEscolha == 0)
            escolha = escolher(posicoesVagas, resultados);
        //retornar o valor do primeiro resultado ideal
        //dependendo do lado
        return minOrMax(resultados, ladoAtual);
    }

    /**
     * Procura pela jogada ideal no turno em questão
     * */
    private int escolher(ArrayList<Integer> posicoesVagas, ArrayList<Integer> resultados) {
        int indexIdeal = resultados.indexOf(1);
        if(indexIdeal != -1)
            return posicoesVagas.get(indexIdeal);
        indexIdeal = resultados.indexOf(0);
        if(indexIdeal != -1)
            return posicoesVagas.get(indexIdeal);
        return posicoesVagas.get(0);
    }

    /***
     * Toma a decisão da melhor jogada sobre a perspectiva do lado atual
     */
    private int minOrMax(ArrayList<Integer> resultados, char ladoAtual) {
        if(ladoAtual == ladoMax) {
            if(resultados.contains(1)) return 1;
            if(resultados.contains(0)) return 0;
            return -1;
        } else {
            if(resultados.contains(-1)) return -1;
            if(resultados.contains(0)) return 0;
            return 1;
        }
    }

    /**
     * Verifica quem ganhou/se houve empate e retorna o escore do ponto de vista da máquina
     * */
    private int verificarResultado(Tabuleiro tabuleiro) {
        char resultado = tabuleiro.procurarVencedor();
        if(resultado == 'e') {
            return 0;
        }
        if(resultado == ladoMax) {
            return 1;
        }
        return -1;
    }

    /**
     * @param tabuleiroAtual uma possivel configuração do tabuleiro de jogo da velha
     * @param ladoAtual lado que vai realizar jogada
     * @param jogada posição a ocupar
     * @return tabuleiro com a jogada realizada
     * */
    private Tabuleiro gerarPossivelTabuleiro(Tabuleiro tabuleiroAtual, int jogada, char ladoAtual) {
        Tabuleiro novoTabuleiro = new Tabuleiro(tabuleiroAtual);
        novoTabuleiro.realizarJogada(ladoAtual, jogada);
        return novoTabuleiro;
    }



    /**
     * @param tabuleiro do jogo da velha
     * @return lista com todos os espaços não preenhidos do tabuleiro
     * */
    private ArrayList<Integer> acharPosicoesVagas(Tabuleiro tabuleiro) {
        ArrayList<Integer> posicoesVagas = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            int[] coord = tabuleiro.acharPosicao(i);
            if(tabuleiro.tabuleiro[coord[0]][coord[1]] == '?')
                posicoesVagas.add(i);
        }
        if (posicoesVagas.size() == 0)
            return null;
        return posicoesVagas;
    }

}
