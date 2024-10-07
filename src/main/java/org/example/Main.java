package org.example;

import org.example.entities.Train;
import org.example.entities.User;
import org.example.services.UserBookingService;
import org.example.utils.UserServiceUtil;

import java.io.IOException;
import java.util.*;



public class Main {
    public static void main(String[] args) {
       System.out.println("Running Train Booking System");
       Scanner scanner = new Scanner(System.in);
       int option = 0;
        UserBookingService userBookingService;

        try{
            userBookingService = new UserBookingService();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }


        while(option != 3) {
            try {
                System.out.println("1 - Signup");
                System.out.println("2 - Login");
                System.out.println("3 - Exit");

                System.out.print("Please enter a valid option: -> ");

                option = scanner.nextInt();
                scanner.nextLine();

                Train trainSelectedForBooking = new Train();

                switch (option) {
                    case 1:
                        System.out.print("Enter the username to signup: ");
                        String nameToSignUp = scanner.nextLine();

                        System.out.print("Enter the password to signup: ");
                        String passwordToSignUp = scanner.next();
                        User userToSignup = new User(nameToSignUp, passwordToSignUp, UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                        boolean status = userBookingService.signUp(userToSignup);
                        if(status == true) {
                            System.out.println("Signup successful");
                        }else{
                            System.out.println("Signup is not Successful");
                        }
                        break;
                    case 2:
                        System.out.print("Enter the username to login: ");
                        String nameToLogin = scanner.nextLine();
                        System.out.print("Enter the password to login: ");
                        String passwordToLogin = scanner.next();
                        User userToLogin = new User(nameToLogin, passwordToLogin, UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(), UUID.randomUUID().toString());
                        try{
                            userBookingService = new UserBookingService(userToLogin);
                            boolean statusToLoging = userBookingService.loginUser();
                            if(statusToLoging == true) {
                                System.out.println("Login successful");
                                System.out.println("Choose Option: ");
                                System.out.println("1. Fetch Bookings");
                                System.out.println("2. Search Trains");
                                System.out.println("3. Book a Seat");
                                System.out.println("4. Cancel my Booking");
                                System.out.println("5. Exit");
                                System.out.print("Please enter a valid option: -> ");
                                int menu = scanner.nextInt();
                                scanner.nextLine();
                                switch (menu) {
                                    case 1:
                                        System.out.println("Fetching Bookings");
                                        userBookingService.fetchBooking();
                                        break;
                                    case 2:
                                        System.out.println("Searching Trains");
                                        break;
                                    case 3:
                                        System.out.println("Book a Seat");
                                        break;
                                    case 4:
                                        System.out.println("Cancel my Booking");
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("Invalid Option");
                                        break;
                                }
                            } else {
                                System.out.println("Login is not Successful");
                            }
//
//

                        }catch (IOException ex){
                            return;
                        }
                        break;

                    case 3:
                        System.out.println("Exit successfully");
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
                break;
            }
        }
        scanner.close();
    }
}