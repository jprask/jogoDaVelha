package pkgJogoDaVelha;

/**
 * Tabuleiro de jogo da velha
 * Created by carmen on 09/06/2017.
 */
public class Tabuleiro {
    char[][] tabuleiro;
    char ultimoAJogar = '-';

    public Tabuleiro() {
        tabuleiro = new char[][] { {'?', '?', '?'}, {'?', '?', '?'}, {'?', '?', '?'} };
    }

    public Tabuleiro(Tabuleiro original) {
        this.tabuleiro = new char[3][3];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tabuleiro[i][j] = original.tabuleiro[i][j];
            }
        }
    }

    /**
     * Exibe o Tabuleiro no console
     */
    public void exibir() {
        int idBloco = 0;

        for(int i = 0; i < 5; i++) {
            if(i%2 != 0)
                System.out.println(ASCII.TABULEIRO_DIVISORIA.texto);
            else {
                System.out.println(ASCII.TABULEIRO_LINHA.construirLinha(tabuleiro[i/2], idBloco));
                idBloco += 3;
            }
        }
    }
    /**
     * @param lado que está realizando a jogada
     * @param posicao a ser preenchida
     * @return 'lado' caso houver vencedor, 'e' para empate e '?' caso contrário,
     * caso a jogada seja inválida retorna '!'
     * */
    public void realizarJogada(char lado, int posicao) {
        int[] coord = acharPosicao(posicao);

        if(tabuleiro[coord[0]][coord[1]] == '?') {
            tabuleiro[coord[0]][coord[1]] = lado;
            ultimoAJogar = lado;
        } else return;
    }

    /**
     * verifica se o lado que realizou a ultima jogada ganhou o jogo,
     * se houve empate ou se o jogo não acabou
     * */
    public char procurarVencedor() {
        if(venceuLinha() || venceuColuna() || venceuDiagonal()) return ultimoAJogar;
        if(!contemVazio(tabuleiro[0]) && !contemVazio(tabuleiro[1]) && !contemVazio(tabuleiro[2])) return 'e';
        return '?';
    }

    /**
     * Verifica se há tres caracteres iguais em uma das linhas do tabuleiro
     */
    private boolean venceuLinha() {
        for (char[] chars : tabuleiro)
            if((chars[0] != '?') && (chars[0] == chars[1]) && (chars[0] == chars[2]))
                return true;
        return false;
    }

    /**
     * Verifica se há tres caracteres iguais em uma das colunas do tabuleiro
     */
    private boolean venceuColuna() {
        for (int i = 0; i < 3; i++)
            if((tabuleiro[0][i] != '?') && (tabuleiro[0][i] == tabuleiro[1][i]) && (tabuleiro[0][i] == tabuleiro[2][i]))
                return true;
        return false;
    }

    /**
     * Verifica se há tres caracteres iguais nas diagonais principal ou secundária
     * */
    private boolean venceuDiagonal() {
        return (tabuleiro[0][0] != '?') && (tabuleiro[0][0] == tabuleiro[1][1]) && (tabuleiro[0][0] == tabuleiro[2][2]) ||
                (tabuleiro[0][2] != '?') && (tabuleiro[0][2] == tabuleiro[1][1]) && (tabuleiro[0][2] == tabuleiro[2][0]);
    }

    /**
     * Verifica se uma sequencia do tabuleiro contém espaços não preenchidos
     * */
    private boolean contemVazio(char[] sequencia) {
        for (char c : sequencia) {
            if(c == '?') return true;
        }
        return false;
    }

    /**
     * @param posicao indicador numerico da posição escolhida pelo jogador
     *                de zero a oito
     * @return um vetor indicando as coordenadas da posição no tabuleiro
     * */
    protected int[] acharPosicao(int posicao) {
        switch(posicao) {
            case 0:
                return new int[]{0, 0};
            case 1:
                return new int[]{0, 1};
            case 2:
                return new int[]{0, 2};
            case 3:
                return new int[]{1, 0};
            case 4:
                return new int[]{1, 1};
            case 5:
                return new int[]{1, 2};
            case 6:
                return new int[]{2, 0};
            case 7:
                return new int[]{2, 1};
            case 8:
                return new int[]{2, 2};
            default: return new int[]{};
        }
    }

}