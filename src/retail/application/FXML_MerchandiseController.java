/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail.application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Shubhadeep
 */
public class FXML_MerchandiseController implements Initializable {
    
        @FXML
    private Button merc_cancel;
        
    @FXML
    private TextField merc_name;
    
    @FXML
    private TextField merc_price;
    
      @FXML
    private TextArea merc_desc;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
        
           @FXML
    private void handle_merc_cancel(ActionEvent event) {
        
    ((Node)(event.getSource())).getScene().getWindow().hide();

    }  
    
               @FXML
    private void handle_merc_ok(ActionEvent event) throws ClassNotFoundException, SQLException {
         
     
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
                    
        String createString = "CREATE TABLE MERCHANDISE (NAME VARCHAR (32) NOT NULL, PRICE VARCHAR(50) NOT NULL, DESCRIPTION VARCHAR(50) NOT NULL)";
        
        Class.forName(driver);
         
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(createString); 
        }
        
        String Merc_fn = merc_name.getText();
        String Merc_price = merc_price.getText();
         String Merc_desc = merc_desc.getText();
       
        
       
        PreparedStatement psInsert = conn
        .prepareStatement("insert into MERCHANDISE values (?,?,?)");

        psInsert.setString(1, Merc_fn);
        psInsert.setString(2, Merc_price);
        psInsert.setString(3, Merc_desc);
        psInsert.executeUpdate();

        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt2.executeQuery("select * from MERCHANDISE");
        int num = 0;
        while (rs.next()) {
        System.out.println(++num + ": NAME: " + rs.getString(1) 
              + "\n PRICE: "+ rs.getString(2)
               + "\n DESCRIPTION: "+ rs.getString(3));
            }
        rs.close();
        }
    
  public static boolean productsTableExists(Connection connection)
 {
     
 String selectProductsString =
 "SELECT COUNT(NAME) FROM MERCHANDISE";
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
    }    
    
}
