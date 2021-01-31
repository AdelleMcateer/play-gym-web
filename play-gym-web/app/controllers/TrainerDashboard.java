package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

/**
 * This controller renders the dashboard and provides the details for
 * the currently logged on trainer
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */


public class TrainerDashboard extends Controller {
    public static void index() {
        List<Member> members = Member.findAll();
        Logger.info("Rendering Trainer Dashboard");
        render("trainerdashboard.html", members);
    }

    public static void trainerAssessment(Long id) {
        Member member = Member.findById(id);
        List<Assessment> assessments = member.assessments;
        render("trainerassessment.html", member.assessments);
    }

    public static void addComment(Long id, String comment) {

        Logger.info("Comment " + comment);
        Assessment assessment = Assessment.findById(id);
        assessment.comment = comment;
        assessment.save();
        redirect("trainerassessment");
    }

    public static void deleteAssessment(Long id, Long assessmentId) {
        Member member = Accounts.getLoggedInMember();
        Assessment assessment = Assessment.findById(id);
        member.assessments.remove(assessmentId);
        member.save();
        assessment.delete();
        Logger.info("Deleting Assessment");
        redirect("/dashboard");
    }

    public static void deleteMember(Long id) {
        Member member = Member.findById(id);
        if (member != null) {
            member.delete();
        }
        redirect("/trainerdashboard");
    }
}
