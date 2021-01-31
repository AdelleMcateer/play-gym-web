package controllers;

import models.Member;
import play.Logger;
import models.Trainer;

import play.mvc.Controller;

/**
 * This manages the Member and trainer accounts within the app.
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */


public class Accounts extends Controller

        //methods to render the singup & login page
{
    public static void signup() {
        render("signup.html");
    }

    public static void login() {
        render("login.html");
    }

    /**
     * Create and store Member details
     *
     * @param firstname      member's first name
     * @param lastname       member's last name
     * @param email          member's email address
     * @param password       member's password
     * @param address        member's address
     * @param gender         member's gender
     * @param height         member's height
     * @param startingWeight member's starting weight
     */

    public static void register(String firstname, String lastname, String email, String password,
                                String address, String gender, float height, float startingWeight) {
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, email, password, address, gender,
                height, startingWeight);
        member.save();
        redirect("/");
    }

    //method to authenticate previously stored member login details.

    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Member member = Member.findByEmail(email);
        Trainer trainer = Trainer.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        } else if ((trainer != null) && (trainer.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Trainerid", trainer.id);
            redirect("/trainermenu");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    //methods to allow the user to logout of their active session

    public static void logout() {
        session.clear();
        redirect("/");
    }

    //methods to identify logged in member & trainers.

    public static Member getLoggedInMember() {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    public static Trainer getLoggedInTrainer() {
        Trainer trainer = null;
        if (session.contains("logged_in_Trainerid")) {
            String trainerId = session.get("logged_in_Trainerid");
            trainer = Trainer.findById(Long.parseLong(trainerId));
        } else {
            login();
        }
        return trainer;
    }
}

