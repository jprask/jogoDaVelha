package pkgJogoDaVelha;

import java.util.ArrayList;

/**
 * Jogador AI de jogo da velha
 * Created by jprask on 16/06/2017.
 */
public class JogadorAI extends JogadorAbstrato {
    private char ladoMin, ladoMax;
    public int escolha;

    /**
     * Define o lado e o status do turno do jogador ai
     * de jogo da velha
     * @param entrada
     */
    public JogadorAI(char[] entrada) {
        super(entrada);
        ladoMax = this.lado;
        ladoMin = (ladoMax == 'x') ? 'o' : 'x';
    }

    /**
     * Chama a função minimax no tabuleiro recebido e escolhe
     * a jogada a ser relizada
     * */
    public int jogar(Tabuleiro tabuleiro){
        Tabuleiro tabuleiroAnalisar = new Tabuleiro(tabuleiro);
        minimax(tabuleiroAnalisar, ladoMax, 0);
        System.out.println(lado + " escolhe " + escolha);
        return escolha;
    }

    /**
     * Função que analisa as possibilidades de jogada, alternando entre os dois lados. para cada estado do tabuleiro,
     * cria uma lista com os espaços vazios e estima resultados possiveis para cada possivbilidade, levando em
     * consideração que cada um dos lados procura realizar a melhor jogada possivel. no momento de @realizarEscolha = 0,
     * o jogador AI faz a escolha de jogada, de acordo com a lista de resultados possiveis, escolhendo o resultado ideal
     *
     * @param tabuleiro Tabuleiro de jogo da velha
     * @param ladoAtual o lado que deve análisar o tabuleiro, procurando a melhor opção de jogada
     * @param realizarEscolha deve ser zero na primeira iteração da função, marca o momento no qual
     *                        o jogador AI deve realizar a escolha de jogada
     * @return se o tabuleiro se encontra em um estado final, retorna uma representação do resultado, do ponto de vista
     *          do jogador AI.
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
     * @return posição de jogada ideal
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

    /**
     * Avalia a lista de resultados possiveis
     * @return resultado ideal que poderia ser escolhido pelo lado em questão,
     * do ponto de vista do jogador AI
     */
    private int minOrMax(ArrayList<Integer> resultados, char ladoAtual) {
        if(ladoAtual == ladoMax) {
            if(resultados.contains(1)) return 1;
            if(!resultados.contains(0))
            return -1;
        } else {
            if(resultados.contains(-1)) return -1;
            if(!resultados.contains(0))
            return 1;
        }
        return 0;
    }

    /**
     * Verifica quem ganhou/se houve empate e retorna o escore do ponto de vista da máquina
     * @return representação numérica do resultado do jogo, do ponto de vista do jogador AI
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
