// Aim: Create an application to calculate interest for FDs, RDs based on certain conditions using inheritance.

import java.util.Scanner;

// Abstract class for Bank Deposit
abstract class BankDeposit {
    protected double principal;
    protected double rate;
    protected int time;

    // Constructor
    public BankDeposit(double principal, double rate, int time) {
        this.principal = principal;
        this.rate = rate;
        this.time = time;
    }

    // Abstract method to be implemented in subclasses
    public abstract double calculateInterest();
}

// Class for Fixed Deposit (FD)
class FixedDeposit extends BankDeposit {

    // Constructor
    public FixedDeposit(double principal, double rate, int time) {
        super(principal, rate, time);
    }

    // Implementing interest calculation for FD
    @Override
    public double calculateInterest() {
        return principal * Math.pow((1 + rate / 100), time) - principal;
    }
}

// Class for Recurring Deposit (RD)
class RecurringDeposit extends BankDeposit {
    private int months;

    // Constructor
    public RecurringDeposit(double principal, double rate, int months) {
        super(principal, rate, months / 12); // Convert months to years
        this.months = months;
    }

    // Implementing interest calculation for RD
    @Override
    public double calculateInterest() {
        double monthlyRate = rate / 100 / 12;
        return (principal * months) * (monthlyRate * (Math.pow(1 + monthlyRate, months) - 1) / (1 - Math.pow(1 + monthlyRate, -1)));
    }
}

// Main class
public class InterestCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Select Deposit Type: 1. Fixed Deposit (FD)  2. Recurring Deposit (RD)");
            int choice = scanner.nextInt();

            System.out.print("Enter Principal Amount: ");
            double principal = scanner.nextDouble();

            System.out.print("Enter Interest Rate (% per annum): ");
            double rate = scanner.nextDouble();

            if (choice == 1) {
                System.out.print("Enter Time (in years): ");
                int time = scanner.nextInt();
                
                FixedDeposit fd = new FixedDeposit(principal, rate, time);
                System.out.println("FD Interest Earned: " + fd.calculateInterest());

            } else if (choice == 2) {
                System.out.print("Enter Time (in months): ");
                int months = scanner.nextInt();
                
                RecurringDeposit rd = new RecurringDeposit(principal, rate, months);
                System.out.println("RD Interest Earned: " + rd.calculateInterest());

            } else {
                System.out.println("Invalid Choice!");
            }

        } catch (Exception e) {
            System.out.println("Invalid Input! Please enter valid numbers.");
        } finally {
            scanner.close();
        }
    }
}


