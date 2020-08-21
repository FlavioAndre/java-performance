package com.teste;

import java.time.Duration;
import java.time.Instant;
import java.util.InputMismatchException;

public class ValidaCPFv2 {

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

    private static String removeCharacter(String cpf) {
        StringBuilder cpfBuilder = new StringBuilder(cpf.length());
        for (int i = 0; i < cpf.length(); i++) {
            if (Character.isDigit(cpf.charAt(i))) {
               cpfBuilder.append(cpf.charAt(i));
            }
        }
        return cpfBuilder.toString();
    }

    public static boolean isCPF(String cpf) {
        if (cpf == null || cpf.isEmpty())
            return false;

        cpf = removeCharacter(cpf);

        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
                || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
                || cpf.equals("99999999999") || (cpf.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

}
