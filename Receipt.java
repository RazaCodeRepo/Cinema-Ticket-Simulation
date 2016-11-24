/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematicketsim;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Kontrol
 */
public class Receipt {
    
    static Stage stage;
    static Label movieName;
    static Label dateTime;
    static Label typeTicket;
    static Label ticketQuantity;
    static Label totalAmount;
    
    
    public static void show(String movieTitle, String dateSelected, String timeSelected, String ticketType, String numOfTickets){
       
        
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("");
        stage.setMinWidth(250);
        
        movieName = new Label();
        movieName.setText("Movie Title: "+movieTitle);
        
        dateTime = new Label();
        dateTime.setText("Date: " + dateSelected + "     " + "Time: " + timeSelected);
        
        typeTicket = new Label();
        typeTicket.setText("Type of Ticket: " + ticketType);
        
        ticketQuantity = new Label();
        ticketQuantity.setText("Number Of Tickets: " + numOfTickets);
        
        int amount = calculateAmount(ticketType,numOfTickets);
        
        totalAmount = new Label();
        totalAmount.setText("Total Amount: $" + amount);
        
        Button printBtn = new Button();
        printBtn.setText("Print");
        printBtn.setOnAction(e -> onClickPrint() );
        
        Button cancelBtn = new Button();
        cancelBtn.setText("Cancel");
        cancelBtn.setOnAction(e -> onClickCancel() );
        
        HBox paneBtn = new HBox(20);
        paneBtn.getChildren().addAll(printBtn, cancelBtn);
        
        VBox pane = new VBox(20);
        pane.getChildren().addAll(movieName,dateTime,typeTicket,ticketQuantity,totalAmount,printBtn,cancelBtn ,paneBtn);
        pane.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
        
    }
    
    private static int calculateAmount(String ticketType,String numOfTickets){
        int totalAmt = 0;
        int value = 0;
        int quantityTickets = Integer.parseInt(numOfTickets);
        if(ticketType.equals("Adult")){
            value = 15;
        }
        if(ticketType.equals("Senior")){
            value = 12;
        }
        if(ticketType.equals("Student")){
            value = 10;
        }
        
        totalAmt = quantityTickets * value;
        return totalAmt;
    }
    
    private static void onClickPrint(){
              
        try (BufferedWriter bw = new BufferedWriter(new PrintWriter("printreceipt.txt"))){
            bw.write(movieName.getText());
            bw.newLine();
            bw.newLine();
            bw.write(dateTime.getText());
            bw.newLine();
            bw.newLine();
            bw.write(typeTicket.getText());
            bw.newLine();
            bw.newLine();
            bw.write(ticketQuantity.getText());
            bw.newLine();
            bw.newLine();
            bw.write(totalAmount.getText());
            bw.newLine();
            bw.newLine();
            
        }catch(IOException e){
            e.printStackTrace();
        }
            System.out.println("Ticket printed");
            stage.close();
    
    }
    
    
    private static void onClickCancel(){
        stage.close();
        
    }
    
}
