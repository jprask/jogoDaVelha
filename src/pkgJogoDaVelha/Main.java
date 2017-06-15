package pkgJogoDaVelha;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String entrada = null;
        int jogada;
        char retorno;

        while((entrada = menuPrincipal()) != "-1") {
            Tabuleiro tabuleiro = new Tabuleiro();
            JogadorHumano jogador1 = new JogadorHumano(entrada.toCharArray());
            System.out.println("Jogando como " + jogador1.lado);
            do {
                tabuleiro.exibir();
                jogada = jogador1.jogar();
                retorno = tabuleiro.realizarJogada(jogador1.lado, jogada);
                System.out.println(retorno);
            }while(true);
        }
    }

    private static String menuPrincipal() {
        System.out.println(ASCII.JOGO_DA_VELHA.texto);
        System.out.println(ASCII.MENU_PRINCIPAL.texto);
        Scanner scanner = new Scanner(System.in);
        return scanner.next().toLowerCase();
    }

   /* private static boolean executarJogo(Tabuleiro tabuleiro, JogadorHumano jogador1) {
        tabuleiro.exibir();
        char resultado = tabuleiro.realizarJogada(jogador1.lado, jogador1.jogar());
        switch(resultado) {
            case jogador1.lado
        }
    }*/

}
