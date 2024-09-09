package ma.youcode.transport.ui;

import java.sql.SQLException;
import java.util.Scanner;

import ma.youcode.transport.ui.admin.*;

public class Menu {

    private final Scanner sc;
    private PartnerUI partnerUI;
    private ContractUI contractUI;
    private SpecialOfferUI specialOfferUI;
    private TicketUI ticketsUI;
    private int choice;

    public Menu()  {
        this.sc = new Scanner(System.in);
    }

    public Menu start(Menu menu) {
        do {
            System.out.println("\n================== Transport app Menu ==================");
            System.out.println("1. Login as a admin");
            System.out.println("2. Login as a costumer");
            System.out.println("3. Register as a costumer");
            System.out.println("0. Exit");
            System.out.println("================== Transport app Menu ==================\n");
            System.out.println("Choose an option :");
            this.choice = sc.nextInt();
            this.choice = getChoice();

            switch (this.choice) {
                case 1:
                    while (true) {
                       System.out.println("Enter your username : ");
                        String username = sc.next().trim();
                        System.out.println("Enter your password : ");
                        String password = sc.next().trim();
                        if (username.equals("admin") && password.equals("1234")) {
                            AdminMenu.start(menu);
                            break;
                        }else {
                            System.out.println("Invalid username or password , please try again");
                        }
                    }
                break;
                case 2:
                    System.out.println(" Login Costumer: ");
                break;
                case 3:
                    System.out.println("Enter your username : ");
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        } while (this.choice != 0);

        return menu;
    }

    public int getChoice() {

        while (this.choice < 0 || this.choice > 3) {
            System.out.println("Enter Number Between 0 - 3");
            if (sc.hasNextInt()) {
                this.choice = sc.nextInt();
                if (this.choice < 0 || this.choice > 0) {
                    System.out.println("Number out of range. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a correct number.");
                sc.next();
            }
        }
        return this.choice;
    }

}
