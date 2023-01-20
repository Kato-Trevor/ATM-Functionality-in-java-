import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello. Welcome to KTT Banking System\n");

        while (true) {
            
            BankData.verifyUser();

            System.out.println(
                    "Choose the operation you would like:\n1-Withdraw Money\n2-Deposit Money\n3-View Account Details\n");
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Enter number for operation: ");
                int operation = Integer.parseInt(scan.nextLine());
                if (operation == 1) {
                    System.out.println("Money Withdraw Operation\n");
                    BankData.withdrawMoney();
                    break;
                } else if (operation == 2) {
                    System.out.println("Money Deposit Operation\n");
                    BankData.depositMoney();
                    break;
                } else if (operation == 3) {
                    System.out.println("Account Details Operation\n");
                    BankData.getDetails();
                    break;
                } else {
                    System.out.println("Choose operation correctly please!!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter one of the digits above!!");
                continue;
            }
        }

    }
}