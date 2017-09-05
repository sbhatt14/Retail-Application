/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail.application;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * FXML Controller class
 *
 * @author Shubhadeep
 */
public class FXML_EmployeeController implements Initializable {
        
    @FXML
    private Button handle_emp_cancel;
    
    @FXML
    private ComboBox employee_state;
    
    @FXML
    private TextField employee_fn;
    
    @FXML
    private TextField employee_ln;
    
    @FXML
    private TextField employee_sa;
    
    @FXML
    private TextField employee_city;
    
    @FXML
    private TextField employee_zip;
    
     @FXML
    private RadioButton employee_male;
     
    @FXML
    private RadioButton employee_female;


    /**
     * Initializes the controller class.
     */
    
    
    
    
      @FXML
    private void handle_emp_cancel(ActionEvent event) {
        
    ((Node)(event.getSource())).getScene().getWindow().hide();

    }
    
    @FXML
    private void handle_emp_ok(ActionEvent event) throws ClassNotFoundException, Exception {
        
     isValidCredentials();
        
    }
    
    
    
    
        private void isValidCredentials() throws ClassNotFoundException, SQLException
        {
       
    
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String dbName = "RetailAppDB";
        String connectionURL = "jdbc:derby:" + dbName + ";create=true";
        Connection conn = DriverManager.getConnection(connectionURL);
        
        if (productsTableExists(conn) == false)
        {
                    
        String createString = "CREATE TABLE EMPLOYEE (FIRST_NAME VARCHAR (32) NOT NULL, LAST_NAME VARCHAR(50) NOT NULL, STREET_ADDRESS VARCHAR(50) NOT NULL, CITY VARCHAR(50) NOT NULL, STATE VARCHAR(50) NOT NULL, ZIPCODE VARCHAR(50) NOT NULL, GENDER VARCHAR(50) NOT NULL)";
        
        Class.forName(driver);
         
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(createString); 
        }
        
       String gender = "male";
        
        if(employee_male.isSelected()){
             gender = "Male";
            }
        else if(employee_female.isSelected()){
           gender = "Female";
        
        }
        
        String Emp_fn = employee_fn.getText();
        String Emp_ln = employee_ln.getText();
        String Emp_sa = employee_sa.getText();
         
        String Emp_city = employee_city.getText();
        String Emp_zip = employee_zip.getText();
        String emp_loc_state = employee_state.getSelectionModel().getSelectedItem().toString();
        PreparedStatement psInsert = conn
        .prepareStatement("insert into EMPLOYEE values (?,?,?,?,?,?,?)");

        psInsert.setString(1, Emp_fn);
        psInsert.setString(2, Emp_ln);
        psInsert.setString(3, Emp_sa);
        psInsert.setString(4, Emp_city);
        psInsert.setString(5, emp_loc_state);
        psInsert.setString(6, Emp_zip);
        psInsert.setString(7, gender);
        psInsert.executeUpdate();

        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt2.executeQuery("select * from EMPLOYEE");
        int num = 0;
        while (rs.next()) {
        System.out.println(++num + ": FIRST_NAME: " + rs.getString(1) 
              + "\n LAST_NAME: "+ rs.getString(2)
               + "\n STREET_ADDRESS: "+ rs.getString(3)
               + "\n CITY: "+ rs.getString(4)
               + "\n STATE: "+ rs.getString(5)
              + "\n ZIP: "+ rs.getString(6)
               + "\n GENDER: "+ rs.getString(7));
        }
        rs.close();
        }
    
  public static boolean productsTableExists(Connection connection)
 {
     
 String selectProductsString =
 "SELECT COUNT(FIRST_NAME) FROM EMPLOYEE";
 try
 {
 Statement statement =
 connection.createStatement();
 ResultSet rs = statement.executeQuery(
 selectProductsString);
 rs.next();
 statement.close();
 return true;
 }
 catch (SQLException e)
 {
 return false;
 }
 }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        employee_state.getItems().addAll("AK","AL","AR","AZ","CA","CO","CT",
                "DC","DE","FL","GA","GU","HI","IA","ID", "IL","IN","KS","KY",
                "LA","MA","MD","ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH",
                "NJ","NM","NV","NY", "OH","OK","OR","PA","PR","PW","RI","SC","SD","TN","TX",
                "UT","VA","VI","VT","WA","WI","WV","WY");
        employee_state.getSelectionModel().select("NY");
        
        
        ToggleGroup group = new ToggleGroup();
        employee_male.setToggleGroup(group);
        employee_male.setSelected(true);
        employee_female.setToggleGroup(group);
    }    
    
}
