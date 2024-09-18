package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Denne klasse tilbyder en simpel tekstbaseret regnemaskine, der kan udføre grundlæggende aritmetiske operationer.
 */
public class Calculator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Hovedmetoden der driver regnemaskinen. Den håndterer brugerinput og viser menuen.
     */
    public static void main(String[] args) {
        final char ADD = '+';
        final char SUBTRACT = '-';
        final char MULTIPLY = '*';
        final char DIVIDE = '/';
        final char QUIT = 'q';

        double num1, num2, result;
        char operation;

        while (true) {
            visMenu();
            operation = input.next().charAt(0);

            if (operation == QUIT) break;

            num1 = inputDouble("Indtast det første tal: ");
            num2 = inputDouble("Indtast det andet tal: ");

            try {
                result = beregnResultat(num1, num2, operation);
                System.out.println("RESULTAT = " + result);
            } catch (ArithmeticException e) {
                System.out.println("Fejl i beregning: " + e.getMessage());
            }
        }
    }

    /**
     * Viser operationsmenuen for brugeren.
     */
    private static void visMenu() {
        System.out.println("Velkommen til regnemaskinen");
        System.out.println("+ for addition");
        System.out.println("- for subtraktion");
        System.out.println("* for multiplikation");
        System.out.println("/ for division");
        System.out.println("q for at afslutte");
    }

    /**
     * Opfordrer brugeren til at indtaste en double-værdi og håndterer ugyldige inputs.
     * @param prompt Teksten der vises for brugeren.
     * @return Den double-værdi brugeren indtaster.
     */
    static double inputDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return input.nextDouble();
            } catch (InputMismatchException e) {
                input.nextLine(); // ryd det ugyldige input
                System.out.println("Ugyldigt input, prøv venligst igen.");
            }
        }
    }

    /**
     * Beregner resultatet af en binær operation.
     * @param num1 Den første operand.
     * @param num2 Den anden operand.
     * @param operation Operationen der skal udføres.
     * @return Resultatet af operationen.
     */
    static double beregnResultat(double num1, double num2, char operation) {
        switch (operation) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/':
                if (num2 == 0) throw new ArithmeticException("Division ved nul er ikke tilladt.");
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Ugyldig operator (regnetegn).");
        }
    }
}
