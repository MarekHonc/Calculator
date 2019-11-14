package Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        Calculator calculator = new Calculator();
        double result = calculator.Calculate(expression);

        System.out.println("Vysledek je:" + result);
    }
}
