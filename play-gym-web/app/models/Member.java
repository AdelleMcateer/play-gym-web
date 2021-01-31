package models;

import controllers.GymUtility;
import controllers.Accounts;
import play.Logger;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

@Entity
public class Member extends Model {
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String address;
    public String gender;
    public float height;
    public float weight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();
    private double startingweight;
    private Member Member;
    private Assessment Assessment;

    public Member(String firstname, String lastname, String email, String password,
                  String address, String gender, float height, float weight) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public Assessment latestAssessment() {
        if (assessments.size() != 0) {
            return assessments.get(assessments.size() - 1);
        } else {
            return null;
        }
    }

    //Calculation for the Member's BMI
    public float memberBMI() {
        Member member = Accounts.getLoggedInMember();
        if (assessments.size() != 0) {
            Logger.info("Calculating existing member bmi ");
            Assessment assessment = assessments.get(assessments.size() - 1);
            //int bmi = (int) (assessment.weight / (member.height * member.height));
            float bmi = GymUtility.calculateBMI(member, assessment);
            return bmi;
        } else {
            Logger.info("Calculating new member bmi ");
            return (int) (member.startingweight / (member.height * member.height) * 10000);
        }
    }

    /**
     * This method determines the BMI category that the member belongs to.
     *
     * @return The BMI category that the member belongs to.
     * <p>
     * BMI less than     15(exclusive)   is                     "VERY SEVERLY UNDERWEIGHT".
     * BMI between       15(inclusive)   and 16(exclusive)   is "SEVERELY UNDERWEIGHT".
     * BMI between       16(inclusive)   and 18.5(exclusive) is "UNDERWEIGHT".
     * BMI between       18.5(inclusive) and 25(exclusive)   is "NORMAL".
     * BMI between       25(inclusive)   and 30(exclusive)   is "OVERWEIGHT".
     * BMI between       30(inclusive)   and 35(exclusive)   is "MODERATELY OBESE".
     * BMI between       35(inclusive)   and 40(exclusive    is "SEVERELY OBESE".
     * BMI greater than  40(inclusive)   is                     "VERY SEVERELY OBESE".
     */
    public String determineBMICategory() {
        double bmiValue = memberBMI();

        String BMI = "";
        if (bmiValue < 15) {
            BMI = "VERY SEVERELY UNDERWEIGHT";
        } else if ((bmiValue >= 15) && (bmiValue < 16)) {
            BMI = "SEVERELY UNDERWEIGHT";
        } else if ((bmiValue >= 16) && (bmiValue < 18.5)) {
            BMI = "UNDERWEIGHT";
        } else if ((bmiValue >= 18.5) && (bmiValue < 25)) {
            BMI = "NORMAL";
        } else if ((bmiValue >= 25) && (bmiValue < 30)) {
            BMI = "OVERWEIGHT";
        } else if ((bmiValue >= 30) && (bmiValue < 35)) {
            BMI = "MODERATELY OBESE";
        } else if ((bmiValue >= 35) && (bmiValue < 40)) {
            BMI = "SEVERELY OBESE";
        } else if (bmiValue >= 40) {
            BMI = "VERY SEVERELY OBESE";
        }
        return BMI;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}
