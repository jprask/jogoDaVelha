package pkgJogoDaVelha;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String entradaHumano = null;
        int jogada;
        char retorno;
        boolean jogoTerminou = false;

        while((entradaHumano = menuPrincipal()) != "-1") {
            Tabuleiro tabuleiro = new Tabuleiro();
            JogadorHumano jogador1 = new JogadorHumano(entradaHumano.toCharArray());
            char[] entrada = new char[2];
            entrada[0] = (entradaHumano.toCharArray()[0] == 'x') ? 'o' : 'x';
            entrada[1] = (entradaHumano.toCharArray()[1] == '0') ? '1' : '0';
            JogadorAI jogador2 = new JogadorAI(entrada);
            System.out.println("Jogando como " + jogador1.lado);
            System.out.println("Jogando contra " + jogador2.lado);
        }
    }

    private static String menuPrincipal() {
        System.out.println(ASCII.JOGO_DA_VELHA.texto);
        System.out.println(ASCII.MENU_PRINCIPAL.texto);
        Scanner scanner = new Scanner(System.in);
        return scanner.next().toLowerCase();
    }

    private static void exibirVencedor(char lado) {
        switch(lado) {
            case 'x' :
                System.out.println(ASCII.VITORIAX.texto);
                break;
            case 'o' :
                System.out.println(ASCII.VITORIAO.texto);
                break;
        }
    }

}
