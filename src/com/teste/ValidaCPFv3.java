package com.teste;

import java.time.Duration;
import java.time.Instant;

public class ValidaCPFv3 {

    public static void main(String[] args) throws Exception {
        Instant start = Instant.now();

        for (int i = 0; i < 10_000_000; i++) {
            if (!isCPF("135.925.160-00")) {
                throw new Exception("Cálculo Inválido!");
            }
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Tempo: " + timeElapsed);

    }

    public static boolean isCPF(String cpf) {
        if (cpf == null)
            return false;

        int posicao = 0;
        int totalPrimeiroDigito = 0;
        int totalSegundoDigito = 0;
        int primeiroDigitoVerificador = 0;
        int segundoDigitoVerificador = 0;

        boolean ehDigitoRepetido = true;
        int ultimoDigito = -1;

        for (int i = 0; i < cpf.length(); i++) {
            char digitoEmCaracter = cpf.charAt(i);
            if (Character.isDigit(digitoEmCaracter)) {
                int digito = digitoEmCaracter - 48;
                if (ehDigitoRepetido && posicao != 0 && ultimoDigito != digito) {
                    ehDigitoRepetido = false;
                }

                ultimoDigito = digito;
                if (posicao < 9) {
                    totalPrimeiroDigito += digito * (10 - posicao);
                    totalSegundoDigito += digito * (11 - posicao);
                } else if (posicao == 9) {
                    primeiroDigitoVerificador = digito;
                } else if (posicao == 10) {
                    segundoDigitoVerificador = digito;
                }

                posicao++;
            }
        }

        if (posicao > 11 || ehDigitoRepetido)
            return false;

        int primeiroDigito = totalPrimeiroDigito % 11;
        primeiroDigito = primeiroDigito < 2
                ? 0
                : 11 - primeiroDigito;

        if (primeiroDigitoVerificador != primeiroDigito)
            return false;

        totalSegundoDigito += primeiroDigito * 2;
        int segundoDigito = totalSegundoDigito % 11;
        segundoDigito = segundoDigito < 2 ? 0 : 11 - segundoDigito;

        return segundoDigitoVerificador == segundoDigito;
    }
}
