package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

/**
 * This controller renders the dashboard and provides the details for
 * the currently logged on member
 * <p>
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering Dasboard");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessments = member.assessments;
        render("dashboard.html", member, assessments);
    }

    /**
     * Allows addition of a new assessment and save the details
     *
     * @param weight   measurements obtained from the addAssessment form
     * @param chest    measurements obtained from the addAssessment form
     * @param thigh    measurements obtained from the addAssessment form
     * @param upperArm measurements obtained from the addAssessment form
     * @param waist    measurements obtained from the addAssessment form
     * @param hips     measurements obtained from the addAssessment form
     */

    public static void addAssessment(float weight, float chest, float thigh, float upperArm,
                                     float waist, float hips) {
        Member member = Accounts.getLoggedInMember();
        Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
        member.assessments.add(assessment);
        member.save();
        Logger.info("Adding an Assessment" + weight + chest + thigh + upperArm + waist + hips);
        redirect("/dashboard");
    }

    /**
     * Allows deletion of assessments and saving the update
     *
     * @param id member id attached to the assessments
     */

    /*public static void deleteAssessment(Long id, Long assessmentId) {
        List<Member> members = Member.findAll();
        Member member = Accounts.getLoggedInMember();
        Assessment assessment = Assessment.findById(id);
        member.assessments.remove(assessmentId);
        member.save();
        assessment.delete();
        Logger.info("Deleting Assessment");
        render("../views/member.html", member);
    }*/

    public static void deleteAssessment(Long id, Long assessmentId) {
        Member member = Accounts.getLoggedInMember();
        Assessment assessment = Assessment.findById(id);
        member.assessments.remove(assessmentId);
        member.save();
        assessment.delete();
        Logger.info("Deleting Assessment");
        redirect("/dashboard");
    }

}
