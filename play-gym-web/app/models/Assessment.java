package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

@Entity
public class Assessment extends Model {
    public float weight;
    public float chest;
    public float thigh;
    public float upperArm;
    public float waist;
    public float hips;

    public String comment;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    public Assessment(float weight, float chest, float thigh, float upperArm, float waist,
                      float hips) {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
    }

    public float getWeight() {
        return this.weight;
    }

}
