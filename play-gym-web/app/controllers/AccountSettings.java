package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

/**
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

public class AccountSettings extends Controller {

    // Renders the account settings option for the logged in member.
    public static void index() {
        Logger.info("Rendering accountsettings");
        Member member = Accounts.getLoggedInMember();
        render("accountsettings.html", member);
    }

    /**
     * Updates the existing member details
     *
     * @param firstname      member's firstname
     * @param lastname       member's last name
     * @param email          member's email
     * @param password       member's password
     * @param address        member's address
     * @param gender         member's gender
     * @param height         member's height
     * @param startingWeight member's starting weight
     */

    //Allows the Member to update their details within the app.
    public static void update(String firstname, String lastname, String email, String password,
                              String address, String gender, float height, float startingWeight) {
        Member member = Accounts.getLoggedInMember();
        member.setFirstname(firstname);
        member.setLastname(lastname);
        member.setEmail(email);
        member.setPassword(password);
        member.setAddress(address);
        member.setGender(gender);
        member.setHeight(height);
        member.setWeight(startingWeight);
        member.save();

        Logger.info(member.firstname + "'s Details Updated");
        redirect("/dashboard");
    }
}
