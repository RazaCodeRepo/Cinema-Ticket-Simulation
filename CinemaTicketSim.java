/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematicketsim;

import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Kontrol
 */
public class CinemaTicketSim extends Application {
    
    private Button listMoviesBtn;
    private Button showTimeBtn;
    private Button purchaseBtn;
    private Button showReceiptBtn;
    private Button searchBtn;
    
    private TableView movieTable;
    
    private TextField textField;
    private TextField numOfTickets;
    private TextField movieSearch;
    
    private ComboBox comboDates;
    private ComboBox comboTimes;
    private ComboBox comboGenre;
    private ComboBox comboYear;
    
    private RadioButton rdoAdult;
    private RadioButton rdoSenior;
    private RadioButton rdoStudent;
    
    
    private ToggleGroup radioBtnGroup;
    
    
    private String selectedMovieID;
    private String selectedMovieTitle;
    private String selectedDate;
    private String selectedTime;
    private String ticketType;
    private String numTicket;
    private String searchName;
    
    private Label lb1;
    
    MovieClassParser movieData;
    
    
    
    GetDatesTimes dateTimes;
    
    @Override
    public void start(Stage primaryStage) {
        
        movieData = new MovieClassParser();
        
        dateTimes = new GetDatesTimes();
        dateTimes.mapMovieToDatesTimes(); 
        
        listMoviesBtn = new Button();
        listMoviesBtn.setText("List Movies");
        listMoviesBtn.setOnAction(e -> updateMoviesList());
       
       
        showTimeBtn = new Button();
        showTimeBtn.setText("Show Date And Time");
        showTimeBtn.setOnAction(e -> showDateTime());
        
        movieTable = new TableView();
        setupMovieTable();
        
        textField = new TextField();
        textField.setPromptText("Enter Movie ID");
        
        ObservableList<String> genreOptions = FXCollections.observableArrayList("Comedy","Drama","Romance");
        
        comboGenre = new ComboBox(genreOptions);
        comboGenre.setPromptText("Select Genre");
        comboGenre.setOnAction(e -> comboGenreSelected());
        
        ObservableList<String> yearOptions = FXCollections.observableArrayList("2016","2015","2014","2013");
        
        comboYear = new ComboBox(yearOptions);
        comboYear.setPromptText("Select Year");
        comboYear.setOnAction(e -> comboYearSelected());
        
        HBox filterCombos = new HBox();
        filterCombos.setSpacing(10);
        filterCombos.getChildren().addAll(comboGenre,comboYear);
        
        Label labelForFilters = new Label();
        labelForFilters.setText("Sort Movies By Filter");
        
        movieSearch = new TextField();
        movieSearch.setPromptText("Movie Name");
        
        searchBtn = new Button("Search");
        searchBtn.setOnAction(e->onClickSearchBtn());
        
        Label forMovieSearch = new Label();
        forMovieSearch.setText("Search Movie By Name");
        
        HBox searchTxtBtn = new HBox();
        searchTxtBtn.setSpacing(10);
        searchTxtBtn.getChildren().addAll(movieSearch,searchBtn);
        
        
        
        VBox tableAndUpdateBtn = new VBox();
        tableAndUpdateBtn.setSpacing(10);
        tableAndUpdateBtn.setPadding(new Insets(0, 20, 10, 20));
        tableAndUpdateBtn.getChildren().addAll(movieTable,listMoviesBtn);
        
        
        HBox inputAndDateBtn = new HBox();
        inputAndDateBtn.setSpacing(10);
        inputAndDateBtn.getChildren().addAll(textField,showTimeBtn);
        
        VBox filterControls = new VBox();
        filterControls.setSpacing(10);
        filterControls.getChildren().addAll(forMovieSearch,searchTxtBtn,labelForFilters,filterCombos);
        
        VBox setFiltersAndInput = new VBox();
        setFiltersAndInput.setSpacing(50);
        setFiltersAndInput.getChildren().addAll(filterControls,inputAndDateBtn);
        
        
        
        BorderPane tableVboxAndTextHbox = new BorderPane();
        tableVboxAndTextHbox.setPadding(new Insets(20, 20, 20, 20));
        tableVboxAndTextHbox.setCenter(setFiltersAndInput);
        tableVboxAndTextHbox.setLeft(tableAndUpdateBtn);
        
        
        Scene scene = new Scene(tableVboxAndTextHbox, 800, 500);
        
        
        
        primaryStage.setTitle("Cinema Ticket Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void setupMovieTable(){
        
        
        ArrayList<Movie> listOfMovies = movieData.loadMovies("ratedmovies_short.csv");
        
        ObservableList<Movie> options = FXCollections.observableArrayList(listOfMovies);
        
        
        movieTable.setItems(options);
        
        TableColumn<Movie,String> colID = new TableColumn("Movie ID");
        colID.setMinWidth(100);
        colID.setCellValueFactory(new PropertyValueFactory<Movie,String>("ID"));
        
        TableColumn<Movie,String> colTitle = new TableColumn("Movie Title");
        colTitle.setMinWidth(150);
        colTitle.setCellValueFactory(new PropertyValueFactory<Movie,String>("Title"));
        
        TableColumn<Movie,String> colGenre = new TableColumn("Genre");
        colGenre.setMinWidth(150);
        colGenre.setCellValueFactory(new PropertyValueFactory<Movie,String>("Genres"));
        
        TableColumn<Movie,String> colYear = new TableColumn("Year");
        colYear.setMinWidth(100);
        colYear.setCellValueFactory(new PropertyValueFactory<Movie,String>("year"));
        
        movieTable.getColumns().addAll(colID,colTitle,colGenre,colYear);
    }
    
    public void updateMoviesList(){
        
        ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
        listOfMovies = movieData.loadMovies("ratedmovies_short.csv");
        
        ObservableList<Movie> options= FXCollections.observableArrayList(listOfMovies);
        movieTable.setItems(options);
    }
    
    public void showDateTime(){
        
        
        selectedMovieID = textField.getText();
        if (selectedMovieID.matches("[0-9]+") && selectedMovieID.length() > 2 && MovieDatabase.containsID(selectedMovieID)) {
        
            selectedMovieTitle = movieData.getMovieTitle(selectedMovieID);
        
            ArrayList<String> viewDates = dateTimes.getDates(selectedMovieID);
        
            ObservableList<String> dateList = FXCollections.observableArrayList(viewDates);

            comboDates = new ComboBox(dateList);
            comboDates.setPromptText("Select A Date");
            comboDates.setOnAction(e -> comboDateSelected());
            comboDates.setMaxWidth(Double.MAX_VALUE);

            comboTimes = new ComboBox();
            comboTimes.setPromptText("Select Time");
            comboTimes.setMaxWidth(Double.MAX_VALUE);

            purchaseBtn = new Button("Purchase");
            purchaseBtn.setOnAction(e -> onClickPurchase());


            VBox vBox =  new VBox();
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(0, 20, 10, 20)); 
            vBox.getChildren().addAll(comboDates,comboTimes,purchaseBtn);

            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(vBox);

            Stage stage = new Stage();

            Scene scene = new Scene(borderPane,300,350);

            stage.setTitle("Choose Movie Date And Time");
            stage.setScene(scene);
            stage.show();
        }
        
        else{
            MessageBox.show("Invalid Movie Id Entered","Invalid Input");
        }
        
        
    }
    
    public void comboGenreSelected(){
        String genreSelected = (String)comboGenre.getValue();
        
        CreateFilters createFilters = new CreateFilters();
        ArrayList<Movie> filterList =createFilters.byGenre(genreSelected);
        
        
        ObservableList<Movie> options= FXCollections.observableArrayList(filterList);
        movieTable.setItems(options);
        
        
    }
    
    public void comboYearSelected(){
        String valueSelected = (String)comboYear.getValue();
        System.out.println(valueSelected);
        int yearSelected = Integer.parseInt(valueSelected);
        CreateFilters createFilters = new CreateFilters();
        ArrayList<Movie> filterList =createFilters.byYear(yearSelected);
        
        
        ObservableList<Movie> options= FXCollections.observableArrayList(filterList);
        movieTable.setItems(options);
    }
    
    public void onClickSearchBtn(){
        searchName = movieSearch.getText();
       
        ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
        
        listOfMovies = movieData.getMovie("ratedmovies_short.csv",searchName);
        if(listOfMovies.size() != 0){
        
            ObservableList<Movie> options= FXCollections.observableArrayList(listOfMovies);
            movieTable.setItems(options);
        }
        else{
            MessageBox.show("Movie Not Found", "Not Found");
        }
        
    }
    
    public void comboDateSelected(){
        selectedDate = (String)comboDates.getValue();
        
        ArrayList<String> timeList = dateTimes.getTime(selectedDate);
        ObservableList<String> timeOptions = FXCollections.observableArrayList(timeList);
        comboTimes.setItems(timeOptions);
        comboTimes.setOnAction(e -> comboTimeSelected());
    }
    
    public void comboTimeSelected(){
        selectedTime = (String)comboTimes.getValue();
    }
    
    public void onClickPurchase(){
        
        showReceiptBtn = new Button("Show Receipt");
        showReceiptBtn.setOnAction(e -> showReceipt());
        
        rdoAdult = new RadioButton("Adult");
        rdoSenior = new RadioButton("Senior");
        rdoStudent = new RadioButton("Student");
        
        radioBtnGroup = new ToggleGroup();
        rdoAdult.setToggleGroup(radioBtnGroup);
        rdoSenior.setToggleGroup(radioBtnGroup);
        rdoStudent.setToggleGroup(radioBtnGroup);
        
        numOfTickets = new TextField();
        numOfTickets.setPromptText("Enter Quantity Of Tickets");
        
        lb1 = new Label("Enter Quantity Of Tickets");
        
        VBox labelAndTxtField = new VBox();
        labelAndTxtField.getChildren().addAll(lb1,numOfTickets);
        
        HBox hBox = new HBox(rdoAdult,rdoSenior,rdoStudent);
        hBox.setSpacing(10);
        
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBox,labelAndTxtField,showReceiptBtn);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vBox);
        
        Stage stage = new Stage();
        
        Scene scene = new Scene(borderPane,300,300);
        
        stage.setTitle("Purchase Ticket");
        stage.setScene(scene);
        stage.show();
    }
    
    public void showReceipt(){
        String typeOfTicket = "";
        if(rdoAdult.isSelected()){
            typeOfTicket = "Adult";
        }
        if(rdoSenior.isSelected()){
            typeOfTicket = "Senior";
        }
        if(rdoStudent.isSelected()){
            typeOfTicket = "Student";
        }
        numTicket = numOfTickets.getText();
        if (numTicket.matches("[0-9]+") && numTicket.length() > 0){
            Receipt.show(selectedMovieTitle, selectedDate, selectedTime, typeOfTicket, numTicket);
        }
        else{
            MessageBox.show("Invalid Quantity Added","Invalid Input");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
