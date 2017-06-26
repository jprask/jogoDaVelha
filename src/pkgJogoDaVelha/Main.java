package pkgJogoDaVelha;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        char[] entradaHumano = null;
        char vencedor = '?';

        while((entradaHumano = menuPrincipal())[0] != '-') {
            Tabuleiro tabuleiro = new Tabuleiro();
            JogadorHumano jogador1 = new JogadorHumano(entradaHumano);
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

    /**
     * Exibe o menu principal ao usuário,
     * @return array com os caracteres informados
     * */
    private static char[] menuPrincipal() {
        System.out.println(ASCII.JOGO_DA_VELHA.texto);
        System.out.println(ASCII.MENU_PRINCIPAL.texto);
        Scanner scanner = new Scanner(System.in);
        return scanner.next().toLowerCase().toCharArray();
    }

    /**
     * Constroi os argumentos que devem ser passados ao construtor do jogador AI,
     * Baseado na entrada do usuário. Constroi o jogador AI.
     * @return novo jogador AI
     * */
    private static JogadorAI construirJogadorAI(char[] entradaHumano) {
        char[] entradaAI = new char[2];
        entradaAI[0] = (entradaHumano[0] == 'x') ? 'o' : 'x';
        entradaAI[1] = (entradaHumano[1] == '0') ? '1' : '0';
        return new JogadorAI(entradaAI);
    }

    /**
     * Realiza uma jogada, do jogador ao qual o turno pertence.
     * @return o resultado (vitória de um dos lados, empate ou jogo não terminado)
     * */
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

    /**
     * Exibe o vencedor de uma partida
     * */
    private static void exibirVencedor(char lado) {
        if (lado == 'x') {
            System.out.println(ASCII.VITORIAX.texto);

        } else if (lado == 'o') {
            System.out.println(ASCII.VITORIAO.texto);

        } else System.out.println(ASCII.EMPATE.texto);
    }

}