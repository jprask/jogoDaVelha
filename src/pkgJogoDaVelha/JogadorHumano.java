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
     * Jogador deve realizar uma jogada em uma posição existente, o controle de posições
     * ocupadas é feito na classe Main
     * */
    public int jogar() {
        Scanner sc = new Scanner(System.in);
        int posicao = -1;
        while(posicao < 0 || posicao > 8) {
            System.out.println("Escolha uma posição para jogar:");
            posicao = sc.nextInt();
        }
        return posicao;
    }
}
