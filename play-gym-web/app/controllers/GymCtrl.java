package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

/**
 * The GymCtrl allows access to  account settings and updates the current member details
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

public class GymCtrl extends Controller {
    /*
     *Renders the Member account for the currently logged in member
     */
    public static void index() {
        Member member = Accounts.getLoggedInMember();
        Logger.info("Rendering  Member Account");
        render("../views/member.html", member);
    }

    public static void addMember() {
    }

    public static void deleteMember() {
    }

    public static void deleteMemberAssessment(Long id) {
        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(member);
        Logger.info("Removing" + assessment);
        member.assessments.remove(assessment);
        member.save();
        assessment.delete();
        render("../views/member.html", member);
    }

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
        Logger.info(member + "'s details updated");
        redirect("/dashbaord");
    }
}
