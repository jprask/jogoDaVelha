package pkgJogoDaVelha;

import java.util.Scanner;

/**
 * Jogador controlado pelo usuario
 * Created by carmen on 15/06/2017.
 */
public class JogadorHumano extends JogadorAbstrato {

    /**
     * Define o lado de um jogador qualquer
     * do jogo da velha
     *
     * @param entrada
     */
    public JogadorHumano(char[] entrada) {
        super(entrada);
    }

    @Override
    /**
     * Jogador deve realizar uma jogada em uma posição válida
     * */
    public int jogar(Tabuleiro tabuleiro) {
        Scanner sc = new Scanner(System.in);
        int posicao = -1;
        while(posicao < 0 || posicao > 8) {
            System.out.println("Escolha uma posição para jogar:");
            posicao = sc.nextInt();
            int[] coord = tabuleiro.acharPosicao(posicao);
            if(tabuleiro.tabuleiro[coord[0]][coord[1]] != '?')
                posicao = -1;
        }
        return posicao;
    }
}
