import java.util.Scanner;

class Calculator {
    
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

   
    public double subtract(double a, double b) {
        return a - b;
    }

    
    public double multiply(double a, double b) {
        return a * b;
    }

    
    public double divide(double a, double b) {
        if (b == 0) { 
            System.out.println("Error: Division by zero!");
            return 0;
        }
        return a / b;
    }
}


public class ConsoleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator(); 
        boolean running = true;

        System.out.println("===== Java Console Calculator =====");

        
        while (running) {
            System.out.println("\nChoose operation:");
            System.out.println("1. Addition (+)");
            System.out.println("2. Subtraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            
            if (choice == 5) {
                running = false;
                System.out.println("Exiting Calculator. Goodbye!");
                break;
            }

        
            System.out.print("Enter first number: ");
            double x = sc.nextDouble();
            System.out.print("Enter second number: ");
            double y = sc.nextDouble();

            double result = 0;

            switch (choice) {
                case 1:
                    result = calc.add(x, y);
                    break;
                case 2:
                    result = calc.subtract(x, y);
                    break;
                case 3:
                    result = calc.multiply(x, y);
                    break;
                case 4:
                    result = calc.divide(x, y);
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
                    continue;
            }

            
            System.out.println("Result: " + result);
        }

        sc.close();
    }
}
