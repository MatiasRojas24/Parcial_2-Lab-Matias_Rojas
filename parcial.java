import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parcial {
    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de lectura de ADN");
        System.out.println("En este programa ingresarás la estructura de ADN del sujeto para comprobar si el mismo es un mutante o no");
        System.out.println("Para empezar;");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        int option = 1;
        while (option != 0) {
            ArrayList<String> dna = new ArrayList<>();
            System.out.println("Seleccione una opción: ");
            System.out.println("1. Ingresar y comprobar ADN");
            System.out.println("0. salir");
            option = scanner.nextInt();
            System.out.println();

            if (option == 1) {
                inputDNA(dna, scanner);
                showDNA(dna);
                if (isMutant(dna)) {
                    System.out.println("El sujeto es un mutante");
                    System.out.println();
                } else {
                    System.out.println("El sujeto no es un mutante");
                    System.out.println();
                }
            }
        }

        System.out.println("Hasta luego!");
    }
// Función que permite al usuario rellenar la lista
    public static void inputDNA(List<String> dna, Scanner scanner) {
        System.out.println("--------------------------------");
        System.out.println("Has seleccionado: Ingresar ADN");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("Las estructuras de ADN están compiladas en una matriz de 6x6");
        System.out.println("Para ingresar los datos, ingrese una cadena de 6 letras que representarán cada base nitrogenada del ADN. Al ingresar dicha cadena, presione enter e ingrese la siguiente");
        System.out.println("Las letras permitidas son: A, T, C, G");
        System.out.println();
        System.out.println("Ingrese los datos del ADN del sujeto: ");

        int i = 0;
        while (dna.size() != 6) {
            String dnaStringInput;
            i++;
            while (true) {
                System.out.print(i + "- ");
                dnaStringInput = scanner.next().toUpperCase();
                if (validUserInput(dnaStringInput)) {
                    dna.add(dnaStringInput);
                    System.out.println("Se ha agregado la cadena a la matriz");
                    System.out.println();
                    break;
                }
            }
        }
    }
// Función que valida que el usuario ingrese un string válido
    public static boolean validUserInput(String userStringInput) {
        Pattern comprobarString = Pattern.compile("^[ATCG]{6}$");
        Matcher stringMatcher = comprobarString.matcher(userStringInput);
        if (stringMatcher.matches()){
            return true;
        }
        else {
            System.out.println("La cadena ingresada no es válida, intentelo de nuevo");
            return false;
        }
    }
// Función que muestra los valores ingresados
    public static void showDNA(List<String> dna) {
        System.out.println("La estructura de ADN del sujeto es: ");
        for (String dnaString : dna) {
            for (char element : dnaString.toCharArray()) {
                System.out.print(element + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
// Función que informa si el sujeto es mutante o no
    public static boolean isMutant(List<String> dna) {
        int mutantDnaOccurrences = 0;
        mutantDnaOccurrences += validateRows(dna);
        mutantDnaOccurrences += validateColumns(dna);
        mutantDnaOccurrences += validateMainDiagonals(dna);
        mutantDnaOccurrences += validateSecondaryDiagonals(dna);
        return mutantDnaOccurrences > 1;
    }
// Función que cuenta las ocurrencias en las filas
    public static int validateRows(List<String> dna) {
        int repeatedElementInRows = 0;
        for (String dnaString : dna) {
            int sameElementCount = 1;
            char comparativeElement = ' ';

            for (char letter : dnaString.toCharArray()) {
                if (sameElementCount == 4) {
                    repeatedElementInRows++;
                }

                if (letter == comparativeElement) {
                    sameElementCount++;
                } else {
                    sameElementCount = 1;
                    comparativeElement = letter;
                }
            }

            if (sameElementCount == 4) {
                repeatedElementInRows++;
            }
        }

        return repeatedElementInRows;
    }
    // Función que cuenta las ocurrencias en las columnas
    public static int validateColumns(List<String> dna) {
        int repeatedElementInColumns = 0;
        for (int column = 0; column < dna.size(); column++) {
            int sameElementCount = 1;
            char comparativeElement = ' ';

            for (String dnaString : dna) {
                if (sameElementCount == 4) {
                    repeatedElementInColumns++;
                }

                if (dnaString.charAt(column) == comparativeElement) {
                    sameElementCount++;
                } else {
                    sameElementCount = 1;
                    comparativeElement = dnaString.charAt(column);
                }
            }

            if (sameElementCount == 4) {
                repeatedElementInColumns++;
            }
        }
        return repeatedElementInColumns;
    }
    // Función que cuenta las ocurrencias en las diagonales principales
    public static int validateMainDiagonals(List<String> dna) {
        int repeatedElementsInMainDiagonals = 0;

        int sameElementCount = 1;
        char comparativeElement = ' ';

        for (int rowColumn = 0; rowColumn < dna.size(); rowColumn++) {
            if (sameElementCount == 4) {
                repeatedElementsInMainDiagonals++;
            }

            if (dna.get(rowColumn).charAt(rowColumn) == comparativeElement) {
                sameElementCount++;
            } else {
                sameElementCount = 1;
                comparativeElement = dna.get(rowColumn).charAt(rowColumn);
            }
        }

        if (sameElementCount == 4) {
            repeatedElementsInMainDiagonals++;
        }

        for (int row = 1; row < dna.size(); row++) {
            sameElementCount = 1;
            comparativeElement = ' ';

            for (int column = 0; column < dna.size() - 1; column++) {
                if (sameElementCount == 4) {
                    repeatedElementsInMainDiagonals++;
                }

                if (dna.get(row).charAt(column) == comparativeElement) {
                    sameElementCount++;
                } else {
                    sameElementCount = 1;
                    comparativeElement = dna.get(row).charAt(column);
                }
            }

            if (sameElementCount == 4) {
                repeatedElementsInMainDiagonals++;
            }
        }

        for (int column = 1; column < dna.size(); column++) {
            sameElementCount = 1;
            comparativeElement = ' ';

            for (int row = 0; row < dna.size() - 1; row++) {
                if (sameElementCount == 4) {
                    repeatedElementsInMainDiagonals++;
                }

                if (dna.get(row).charAt(column) == comparativeElement) {
                    sameElementCount++;
                } else {
                    sameElementCount = 1;
                    comparativeElement = dna.get(row).charAt(column);
                }
            }

            if (sameElementCount == 4) {
                repeatedElementsInMainDiagonals++;
            }
        }

        sameElementCount = 1;
        comparativeElement = ' ';

        for (int row = 2; row < dna.size(); row++) {
            if (sameElementCount == 4) {
                repeatedElementsInMainDiagonals++;
            }

            if (dna.get(row).charAt(row - 2) == comparativeElement) {
                sameElementCount++;
            } else {
                sameElementCount = 1;
                comparativeElement = dna.get(row).charAt(row - 2);
            }
        }

        if (sameElementCount == 4) {
            repeatedElementsInMainDiagonals++;
        }

        for (int column = 2; column < dna.size(); column++) {
            sameElementCount = 1;
            comparativeElement = ' ';

            for (int row = 0; row < dna.size() - 2; row++) {
                if (sameElementCount == 4) {
                    repeatedElementsInMainDiagonals++;
                }

                if (dna.get(row).charAt(column) == comparativeElement) {
                    sameElementCount++;
                } else {
                    sameElementCount = 1;
                    comparativeElement = dna.get(row).charAt(column);
                }
            }

            if (sameElementCount == 4) {
                repeatedElementsInMainDiagonals++;
            }
        }

        return repeatedElementsInMainDiagonals;
    }
    // Función que cuenta las ocurrencias en las diagonales secundarias
    public static int validateSecondaryDiagonals(List<String> dna) {
        int repeatedElementsInSecondaryDiagonals = 0;

        int sameElementCount = 1;
        char comparativeElement = '\0';
        int column = 0;

        for (int row = 5; row >= 0; row--) {
            if (sameElementCount == 4) {
                repeatedElementsInSecondaryDiagonals++;
            }

            if (dna.get(row).charAt(column) == comparativeElement) {
                sameElementCount++;
            } else {
                sameElementCount = 1;
                comparativeElement = dna.get(row).charAt(column);
            }
            column++;
        }

        if (sameElementCount == 4) {
            repeatedElementsInSecondaryDiagonals++;
        }

        sameElementCount = 1;
        comparativeElement = '\0';
        column = 0;

        for (int row = 4; row >= 0; row--) {
            if (sameElementCount == 4) {
                repeatedElementsInSecondaryDiagonals++;
            }

            if (dna.get(row).charAt(column) == comparativeElement) {
                sameElementCount++;
            } else {
                sameElementCount = 1;
                comparativeElement = dna.get(row).charAt(column);
            }
            column++;
        }

        if (sameElementCount == 4) {
            repeatedElementsInSecondaryDiagonals++;
        }

        sameElementCount = 1;
        comparativeElement = '\0';
        int row = 5;

        for (column = 1; column <= 5; column++) {
            if (sameElementCount == 4) {
                repeatedElementsInSecondaryDiagonals++;
            }

            if (dna.get(row).charAt(column) == comparativeElement) {
                sameElementCount++;
            } else {
                sameElementCount = 1;
                comparativeElement = dna.get(row).charAt(column);
            }
            row--;
        }

        if (sameElementCount == 4) {
            repeatedElementsInSecondaryDiagonals++;
        }

        sameElementCount = 1;
        comparativeElement = '\0';
        column = 0;

        for (row = 3; row >= 0; row--) {
            if (sameElementCount == 4) {
                repeatedElementsInSecondaryDiagonals++;
            }

            if (dna.get(row).charAt(column) == comparativeElement) {
                sameElementCount++;
            } else {
                sameElementCount = 1;
                comparativeElement = dna.get(row).charAt(column);
            }
            column++;
        }

        if (sameElementCount == 4) {
            repeatedElementsInSecondaryDiagonals++;
        }

        sameElementCount = 1;
        comparativeElement = '\0';
        row = 5;

        for (column = 2; column <= 5; column++) {
            if (sameElementCount == 4) {
                repeatedElementsInSecondaryDiagonals++;
            }

            if (dna.get(row).charAt(column) == comparativeElement) {
                sameElementCount++;
            } else {
                sameElementCount = 1;
                comparativeElement = dna.get(row).charAt(column);
            }
            row--;
        }

        if (sameElementCount == 4) {
            repeatedElementsInSecondaryDiagonals++;
        }

        return repeatedElementsInSecondaryDiagonals;
    }
}
