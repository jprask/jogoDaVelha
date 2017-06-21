package pkgJogoDaVelha;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String entradaHumano = null;
        char vencedor = '?';

        while((entradaHumano = menuPrincipal()) != "0") {
            Tabuleiro tabuleiro = new Tabuleiro();
            JogadorHumano jogador1 = new JogadorHumano(entradaHumano.toCharArray());
            JogadorAI jogador2 = construirJogadorAI(entradaHumano);
            System.out.println("Jogando como " + jogador1.lado);
            System.out.println("Jogando contra " + jogador2.lado);

            while(vencedor == '?') {
                tabuleiro.exibir();
                vencedor = realizarTurno(tabuleiro, jogador1, jogador2);
            }
            tabuleiro.exibir();
            exibirVencedor(vencedor);
            vencedor = '?';
            TimeUnit.SECONDS.sleep(5);
        }
    }

    private static String menuPrincipal() {
        System.out.println(ASCII.JOGO_DA_VELHA.texto);
        System.out.println(ASCII.MENU_PRINCIPAL.texto);
        Scanner scanner = new Scanner(System.in);
        return scanner.next().toLowerCase();
    }

    private static JogadorAI construirJogadorAI(String entradaHumano) {
        char[] entrada = new char[2];
        entrada[0] = (entradaHumano.toCharArray()[0] == 'x') ? 'o' : 'x';
        entrada[1] = (entradaHumano.toCharArray()[1] == '0') ? '1' : '0';
        return new JogadorAI(entrada);
    }

    private static char realizarTurno(Tabuleiro tabuleiro, JogadorHumano jogador1, JogadorAI jogador2) {
        if(jogador1.turno) {
            tabuleiro.realizarJogada(jogador1.lado, jogador1.jogar(tabuleiro));
        } else {
            tabuleiro.realizarJogada(jogador2.lado, jogador2.jogar(tabuleiro));
        }
        jogador1.turno = !jogador1.turno;
        jogador2.turno = !jogador2.turno;

        return tabuleiro.procurarVencedor();
    }

    private static void exibirVencedor(char lado) {
        if (lado == 'x') {
            System.out.println(ASCII.VITORIAX.texto);

        } else if (lado == 'o') {
            System.out.println(ASCII.VITORIAO.texto);

        } else System.out.println(ASCII.EMPATE.texto);
    }

}
