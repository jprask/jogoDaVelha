package pkgJogoDaVelha;

/**
 * Enumeração com o texto á ser exibido na tela no jogo da velha
 * Created by carmen on 09/06/2017.
 */
public enum ASCII {

    MENU_PRINCIPAL ("                     ___________________________________________\n" +
            "                     \\                                         /\n" +
            "                      \\            MENU PRINCIPAL             /\n" +
            "                       \\_____________________________________/ \n" +
            "                                                             \n" +
            "                          X .......................... Lado X\n" +
            "                          O .......................... Lado O\n" +
            "                          1 ................. Começar Jogando\n" +
            "                          2 ................ Jogar em segundo\n" +
            "                          - ............................ Sair\n" + "\n" +
            "                            Uso: X1, X2, O1, O2, -\n"),
    JOGO_DA_VELHA ("    _                                 _                            _   _             \n" +
            "   (_)                               | |                          | | | |            \n" +
            "    _    ___     __ _    ___       __| |   __ _    __   __   ___  | | | |__     __ _ \n" +
            "   | |  / _ \\   / _` |  / _ \\     / _` |  / _` |   \\ \\ / /  / _ \\ | | | '_ \\   / _` |\n" +
            "   | | | (_) | | (_| | | (_) |   | (_| | | (_| |    \\ V /  |  __/ | | | | | | | (_| |\n" +
            "   | |  \\___/   \\__, |  \\___/     \\__,_|  \\__,_|     \\_/    \\___| |_| |_| |_|  \\__,_|\n" +
            "  _/ |           __/ |                                                               \n" +
            " |__/           |___/                                                                "),
    EMPATE ("\n" +
            "                                     _          \n" +
            "                                    | |         \n" +
            "   ___   _ __ ___    _ __     __ _  | |_    ___ \n" +
            "  / _ \\ | '_ ` _ \\  | '_ \\   / _` | | __|  / _ \\\n" +
            " |  __/ | | | | | | | |_) | | (_| | | |_  |  __/\n" +
            "  \\___| |_| |_| |_| | .__/   \\__,_|  \\__|  \\___|\n" +
            "                    | |                         \n" +
            "                    |_|                         \n"),
    VITORIAX ("          _   _       __           _                 _          __   __    _ \n" +
            "         (_) | |     /_/          (_)               | |         \\ \\ / /   | |\n" +
            " __   __  _  | |_    ___    _ __   _    __ _      __| |  ___     \\ V /    | |\n" +
            " \\ \\ / / | | | __|  / _ \\  | '__| | |  / _` |    / _` | / _ \\     > <     | |\n" +
            "  \\ V /  | | | |_  | (_) | | |    | | | (_| |   | (_| ||  __/    / . \\    |_|\n" +
            "   \\_/   |_|  \\__|  \\___/  |_|    |_|  \\__,_|    \\__,_| \\___|   /_/ \\_\\   (_)"),
    VITORIAO ("          _   _       __           _                 _            ___      _ \n" +
            "         (_) | |     /_/          (_)               | |          / _ \\    | |\n" +
            " __   __  _  | |_    ___    _ __   _    __ _      __| |  ___    | | | |   | |\n" +
            " \\ \\ / / | | | __|  / _ \\  | '__| | |  / _` |    / _` | / _ \\   | | | |   | |\n" +
            "  \\ V /  | | | |_  | (_) | | |    | | | (_| |   | (_| ||  __/   | |_| |   |_|\n" +
            "   \\_/   |_|  \\__|  \\___/  |_|    |_|  \\__,_|    \\__,_| \\___|    \\___/    (_)"),
    TABULEIRO_LINHA ("                                    AA : CC : EE             \n" +
            "                                   !BB :@DD :#FF             "),
    TABULEIRO_DIVISORIA ("                                   - - - - - - - -           ");

    public String texto;

    ASCII(String s) {
        this.texto = s;
    }

    /**
     * @param idComponentes componentes ('X', 'O', ' ') da linha
     * @param idInicial identificador inicial (numerico) da linha
     * @return Linha do tabuleiro de jogo da velha para exibição
     */
    public String construirLinha(char[] idComponentes, int idInicial) {
        String linha = this.texto;
        char[] identificadores = {'!', '@', '#'};
        CharSequence[] componentes = {"AA", "BB", "CC", "DD", "EE", "FF"};

        for(int i = 0; i < 3; i++) {
            int aux = i * 2;
            linha = linha.replace(identificadores[i], (char) (48 + idInicial++));
            linha = substituir(idComponentes[i], componentes[aux], componentes[aux+1], linha);
        }
        return linha;
    }

    /**
     * Substitui @upper e @lower pela representação de @id
     * @param id identificador do componente ('x', 'o', ' ')
     * @param upper parte de cima da linha á ser exibida
     * @param lower parte de baixo da linha á ser exibida
     */
    private String substituir(char id, CharSequence upper, CharSequence lower, String linha) {
        switch(id) {
            case 'o':
                linha = linha.replace(upper, "/\\");
                linha = linha.replace(lower, "\\/");
                break;
            case 'x':
                linha = linha.replace(upper, "\\/");
                linha = linha.replace(lower, "/\\");
                break;
            default:
               linha = linha.replace(upper, "  ");
               linha = linha.replace(lower, "  ");
               break;
        }
        return linha;
    }

}
