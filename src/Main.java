import AirLine.Admin;
import java.util.Scanner;

public class Main {
    static String email;
    static String password;
    static String name;

    public static void main(String[] args) {

        System.out.println("1- already have an account? Login");
        System.out.println("2- Don't have an account? Register");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Choice : ");
        int ch = input.nextInt();

        switch (ch) {
            case 1:
                do {
                    System.out.print("Enter your email : ");
                    email = input.next();
                    System.out.print("Enter your password : ");
                    password = input.next();

                    Admin.login(email, password);
                    if (Admin.isLoggedIn()) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Login failed. Invalid email or password.");
                    }
                } while (!Admin.isLoggedIn());
                break;

            case 2:
                System.out.print("Enter your name : ");
                name = input.next();
                System.out.print("Enter your email : ");
                email = input.next();
                System.out.print("Enter your password : ");
                password = input.next();

                Admin.register(name, email, password);
                break;
        }

    }
}