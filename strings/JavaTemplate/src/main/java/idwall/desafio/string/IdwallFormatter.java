package idwall.desafio.string;

import java.lang.StringBuilder;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */

public class IdwallFormatter extends StringFormatter {
    private boolean justificar = true;

    private int limiteLinha = 40;

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override

    public String format(String text) {

        StringBuilder sb = new StringBuilder();

        String[] novoTexto = text.split("\n", -1);


        for (String paragrafo : novoTexto) {

            String[] texto = paragrafo.split(" ");

            int index = 0;

            if (texto.length == 1 && texto[0].length() == 0)
                sb.append('\n');

            while (index < texto.length - 1) {

                int primeiraPalavra = index;
                int caracter = texto[index++].length();

                do {
                    int palavraAtual = texto[index++].length();

                    if (caracter + palavraAtual + 1 > limiteLinha) {
                        index--;
                        break;
                    }
                    caracter += palavraAtual + 1;

                } while (index < texto.length);

                int espacoExtra = limiteLinha - caracter;
                int quantidadeEspaco = 1;
                int espacoRestante = 0;

                int contagemPalavra = index - primeiraPalavra;

                if (justificar && contagemPalavra != 1) {
                    quantidadeEspaco = espacoExtra / (contagemPalavra - 1) + 1;
                    espacoRestante = espacoExtra % (contagemPalavra - 1);
                }

                int espacoPar = (contagemPalavra - 1) / 2 + (contagemPalavra - 1) % 2;

                int par = Math.min(espacoRestante, espacoPar);
                int impar = espacoRestante - par;

                for (int novoCaracter = primeiraPalavra; novoCaracter < index; novoCaracter++) {
                    sb.append(texto[novoCaracter]);

                    if (novoCaracter == index - 1)
                        break;

                    for (int espaco = 0; espaco < quantidadeEspaco; espaco++)
                        sb.append(' ');

                    if ((novoCaracter - primeiraPalavra) / 2 < ((novoCaracter - primeiraPalavra) % 2 == 0 ? par : impar))
                        sb.append(' ');
                }
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}