package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * Renders the start controller
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */
public class Start extends Controller {
    public static void index() {
        Logger.info("Rendering Start");
        render("start.html");
    }
}
