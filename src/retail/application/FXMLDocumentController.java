/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail.application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 *
 * @author Shubhadeep
 */

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private MenuBar menu_bar;
    
  @FXML
    private TableView<retail> table_view;

    @FXML
    private TableColumn<retail, String> display_fn;

    @FXML
    private TableColumn<retail, String> display_ln;

    @FXML
    private TableColumn<retail, String> display_sa;

    @FXML
    private TableColumn<retail, String> display_city;

    @FXML
    private TableColumn<retail, String> display_state;

    @FXML
    private TableColumn<retail, String> display_zip;
    
    @FXML
    private TableColumn<retail, String> display_gender;
    
    
    
    
    
    
    
    @FXML
    private void handle_cust_list(ActionEvent event) throws SQLException {
      
        ObservableList<retail> data;
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String dbName = "RetailAppDB";
        String connectionURL = "jdbc:derby:" + dbName + ";create=true";
        Connection conn = DriverManager.getConnection(connectionURL);
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt2.executeQuery("select * from CUSTOMER");
        int num = 0;
        table_view.getItems().clear();
        while (rs.next()) {
            System.out.println(++num + ": FIRST_NAME: " + rs.getString(1)
                    + "\n LAST_NAME: " + rs.getString(2)
                    + "\n STREET_ADDRESS: " + rs.getString(3)
                    + "\n CITY: " + rs.getString(4)
                    + "\n STATE: " + rs.getString(5)
                    + "\n ZIP: " + rs.getString(6)
                    + "\n GENDER: " + rs.getString(7));
            
            data = FXCollections.observableArrayList(
                    new retail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)
                    , rs.getString(7))
            );
            table_view.getItems().addAll(data);
        }
        rs.close();

    }
    
    
    
    
     @FXML
    private void handle_emp_list(ActionEvent event) throws SQLException {
        ObservableList<retail> data;
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String dbName = "RetailAppDB";
        String connectionURL = "jdbc:derby:" + dbName + ";create=true";
        Connection conn = DriverManager.getConnection(connectionURL);
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt2.executeQuery("select * from EMPLOYEE");
        int num = 0;
        table_view.getItems().clear();
        while (rs.next()) {
            System.out.println(++num + ": EMPLOYEE_FIRST_NAME: " + rs.getString(1)
                    + "\n EMPLOYEE_LAST_NAME: " + rs.getString(2)
                    + "\n STREET_ADDRESS: " + rs.getString(3)
                    + "\n CITY: " + rs.getString(4)
                    + "\n STATE: " + rs.getString(5)
                    + "\n ZIP: " + rs.getString(6)
                    + "\n GENDER: " + rs.getString(7));
             data = FXCollections.observableArrayList(
                    new retail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)
                    , rs.getString(7))
            );
            table_view.getItems().addAll(data);
        }
        rs.close();

    }
    
    
       
     @FXML
    private void handle_merc_list(ActionEvent event) throws SQLException {
       
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String dbName = "RetailAppDB";
        String connectionURL = "jdbc:derby:" + dbName + ";create=true";
        Connection conn = DriverManager.getConnection(connectionURL);
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
    
    
       
      
    @FXML
    private void handleCustomerAction(ActionEvent event) throws IOException {
        
        
   FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("FXML_Customer.fxml"));
        Parent root1 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Add New Customer");
        stage.show();


    }

    @FXML
    private void handleEmployeeAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("FXML_Employee.fxml"));
        Parent root1 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Add New Employee");
        stage.show();

    }

    @FXML
    private void handleMerchandiseAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("FXML_Merchandise.fxml"));
        Parent root1 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Add New Merchandise");
        stage.show();

    }

  
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        
          ObservableList<retail> data;
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String dbName = "RetailAppDB";
        String connectionURL = "jdbc:derby:" + dbName + ";create=true";
        Connection conn = DriverManager.getConnection(connectionURL);
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt2.executeQuery("select * from CUSTOMER");
        int num = 0;
        table_view.getItems().clear();
        while (rs.next()) {
            System.out.println(++num + ": FIRST_NAME: " + rs.getString(1)
                    + "\n LAST_NAME: " + rs.getString(2)
                    + "\n STREET_ADDRESS: " + rs.getString(3)
                    + "\n CITY: " + rs.getString(4)
                    + "\n STATE: " + rs.getString(5)
                    + "\n ZIP: " + rs.getString(6)
                    + "\n GENDER: " + rs.getString(7));
            
            data = FXCollections.observableArrayList(
                    new retail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)
                    , rs.getString(7))
            );
            table_view.getItems().addAll(data);
        }
        rs.close();

    }
    
    
    
     @FXML
    private void close_app(ActionEvent event)  {
        Platform.exit();
        System.exit(0);  
        
    }
    
    
    
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       display_fn.setCellValueFactory(new PropertyValueFactory<retail, String>("display_fn"));
       display_ln.setCellValueFactory(new PropertyValueFactory<retail, String>("display_ln"));
       display_sa.setCellValueFactory(new PropertyValueFactory<retail, String>("display_sa"));
       display_city.setCellValueFactory(new PropertyValueFactory<retail, String>("display_city"));
       display_state.setCellValueFactory(new PropertyValueFactory<retail, String>("display_state"));
       display_zip.setCellValueFactory(new PropertyValueFactory<retail, String>("display_zip"));
       display_gender.setCellValueFactory(new PropertyValueFactory<retail, String>("display_gender"));

    }

}

   