/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail.application;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author Shubhadeep
 */
public class retail{
    private final SimpleStringProperty display_fn ;
    private final SimpleStringProperty display_ln;
    private final SimpleStringProperty display_sa;
    private final SimpleStringProperty display_city ;
    private final SimpleStringProperty display_state;
    private final SimpleStringProperty display_zip;
    private final SimpleStringProperty display_gender;
    
     public retail(String display_fn, String display_ln, String display_sa, String display_city, String  display_state, String display_zip, String display_gender) {
        this.display_fn = new SimpleStringProperty(display_fn);
        this.display_ln = new SimpleStringProperty(display_ln);
        this.display_sa = new SimpleStringProperty(display_sa);
        this.display_city = new SimpleStringProperty(display_city);
        this.display_state = new SimpleStringProperty(display_state);
        this.display_zip = new SimpleStringProperty(display_zip);
        this.display_gender = new SimpleStringProperty(display_gender);
        
    }   

   
    
    public String getDisplay_fn() {
        return display_fn.get();
    }

    public String getDisplay_ln() {
        return display_ln.get();
    }

    public String getDisplay_sa() {
        return display_sa.get();
    }

    public String getDisplay_city() {
        return display_city.get();
    }

    public String getDisplay_state() {
        return display_state.get();
    }

    public String getDisplay_zip() {
        return display_zip.get();
    }
    
    public String getDisplay_gender() {
        return display_gender.get();
    }   
}

