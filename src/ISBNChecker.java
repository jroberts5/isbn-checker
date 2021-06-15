import java.util.Scanner;

public class ISBNChecker {

    public static void main (String[] args) {

        //Variable declarations
        Scanner keyboard = new Scanner(System.in);
        String isbn;
        String convertedISBN;
        String answer;
        boolean isValid;
        int numISBN = 0;


        System.out.println("Welcome to the ISBN Checker");
        do {
            //Prompts to enter ISBN - Removes back spaces and dashes
            System.out.print("Please enter an ISBN: ");
            isbn = keyboard.nextLine();
            convertedISBN = isbn.replace(" ","").replace("-", "");

            if(convertedISBN.length() == 10) {
                isValid = CheckISBN10(convertedISBN);
            } else if (convertedISBN.length() == 13){
                isValid = CheckISBN13(convertedISBN);
            } else {
                isValid = false;
            }

            //Calls method that prompts if isbn is valid or not
            displayResult(isValid, isbn);

            //Prompts for another ISBN
            System.out.print("\n\nWould you like to enter information for another ISBN (Y/N)? ");
            answer = keyboard.nextLine().toUpperCase();

            numISBN++;

        } while (answer.equals("Y"));

        //Display total of ISBNs processed
        System.out.println("\nThe total number of ISBNs processed: " + numISBN);

    }
    //Method checks a 10-digit ISBN

    private static boolean CheckISBN10 (String isbn) {
        int sum = 0;
        String nStr;

        for (int n=0; n < 10; n++) {
            nStr= isbn.substring(n, n+1);
            if (n < 9 || nStr != "X") {
                sum += Integer.parseInt(nStr) * (10-n);
            } else {
                sum += 10;
            }
        }
        return (sum % 11 == 0);
    }

    //Method checks 13-digit ISBN

    private static boolean CheckISBN13 (String isbn) {
        int sum = 0;
        int nValue;

        for (int n = 0; n < 13; n++) {
            nValue = Integer.parseInt(isbn.substring(n, n+1));
            if (n % 2 == 0) {
                sum += nValue;
            } else {
                sum += nValue *3;
            }
        }
        return (sum % 10 == 0);
    }

    public static void displayResult(boolean isValid, String isbn)
    {
        if (isValid) {
            System.out.println(isbn + " is a valid ISBN");
        } else {
            System.out.println(isbn + " is not a valid ISBN");
        }
    }
}