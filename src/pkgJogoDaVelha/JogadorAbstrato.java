package pkgJogoDaVelha;

/**
 * Classe abstrata para um jogador aarbitrario de jogo da velha
 * Created by carmen on 12/06/2017.
 */

public abstract class JogadorAbstrato {
    char lado;

    /**
     * Define o lado de um jogador qualquer
     * do jogo da velha
     * */
    public JogadorAbstrato(char[] entrada) {
        if(entrada[0] == 'x' || entrada[0] == 'o')
            lado = entrada[0];
        else
            entrada[0] = entrada[1] =  'e';
    }

    /**
     * @return a representação numéria de 1 á 8 da posição para marcar com o lado do jogador
     * */
    public abstract int jogar();
}
