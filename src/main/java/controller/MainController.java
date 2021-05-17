package controller;

import java.sql.*;
import java.time.LocalDate;

import database.Check;
import database.DBConnection;
import database.TaskRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Task;
import model.TransferUtility;
import org.tinylog.Logger;

import javax.transaction.Transactional;

/**
 * Main controller class
 */
public class MainController {

    @FXML
    private TextField tf_task;

    @FXML
    private TextField tf_person;

    @FXML
    private DatePicker dp_startdate;

    @FXML
    private DatePicker dp_finishdate;

    @FXML
    private TableView<Task> tv_tasks;

    @FXML
    private TableColumn<Task, String> col_task;

    @FXML
    private TableColumn<Task, String> col_person;

    @FXML
    private TableColumn<Task, LocalDate> col_startdate;

    @FXML
    private TableColumn<Task, LocalDate> col_finishdate;

    private TaskRepository taskRepository = new TaskRepository();

    private Check check = new Check();


    /**
     * Method for initializing the connection and setting the clicked row data into the text field and data pickers
     */
    @FXML
    void initialize() {
        DBConnection.openEmf();
        Refresh();
        tv_tasks.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                tf_task.setText(newVal.getTask());
                tf_person.setText(newVal.getPerson());
                dp_startdate.setValue(newVal.getStartdate());
                dp_finishdate.setValue(newVal.getFinishdate());
            }
        });

    }

    private void initColumn() {
        col_task.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        col_person.setCellValueFactory(new PropertyValueFactory<Task, String>("person"));
        col_startdate.setCellValueFactory(new PropertyValueFactory<Task, LocalDate>("startdate"));
        col_finishdate.setCellValueFactory(new PropertyValueFactory<Task, LocalDate>("finishdate"));

    }

    /**
     * Method to insert data into database
     *
     * @param event
     */
    @FXML
    void Insert(ActionEvent event) {

        try {
            /*Date d1 = Date.valueOf(dp_startdate.getValue());
            System.out.println("d1 dtae "+dp_startdate.getValue());
            Date d2 = Date.valueOf(dp_finishdate.getValue());
            System.out.println("d2 date "+dp_finishdate.getValue());
            */
            /**
             * Creating new task and setting up the walues
             */
            Task newTask = new Task();

            newTask.setTask(tf_task.getText());
            newTask.setPerson(tf_person.getText());
            newTask.setStartdate(dp_startdate.getValue());
            newTask.setFinishdate(dp_finishdate.getValue());

            /**
             * Checking if the entered data's are correct, if not making an alert with error massage
             */
            if (check.checkIfEmpty(tf_task.getText(), tf_person.getText(), dp_startdate.getValue(), dp_finishdate.getValue())) {
                Logger.error("Blank field detected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Blank field");
                alert.setHeaderText(null);
                alert.setContentText("Dont leave blank fields");
                alert.showAndWait();
            }
            /**
             * Checking if the entered data's are correct, if not making an alert with error massage
             */
            else if (check.checkDates(dp_startdate.getValue(), dp_finishdate.getValue())) {
                Logger.info("Wrong dates");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong dates");
                alert.setHeaderText(null);
                alert.setContentText("Start date can't be later then the the finish date");
                alert.showAndWait();
            } else {

                /**
                 * Checking if the entered dates are already in the database for the person
                 */
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/dlOWKKEPJg", "dlOWKKEPJg", "S9nooKD9cK");
                    String sql = "select * from Task where (person= ? and startdate= ?) or (person= ? and finishdate=?)";

                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, tf_person.getText());
                    preparedStatement.setDate(2, Date.valueOf(dp_startdate.getValue()));
                    preparedStatement.setString(3, tf_person.getText());
                    preparedStatement.setDate(4, Date.valueOf(dp_finishdate.getValue()));

                    ResultSet resultSet = preparedStatement.executeQuery();

                    /*String sql = "select * from Task where person = ? and date=? between startdate=? and finishdate=?";

                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1,tf_person.getText());
                    preparedStatement.setDate(2, Date.valueOf(d1));
                    preparedStatement.setDate(3, Date.valueOf(dp_startdate.getValue()));
                    preparedStatement.setDate(4, Date.valueOf(dp_finishdate.getValue()));

                    ResultSet resultSet = preparedStatement.executeQuery();*/

                    if (resultSet.next()) {
                        Logger.info("Person busy");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Person already busy at this date");
                        alert.showAndWait();

                    } else {
                        Logger.info("Adding new data to table");
                        taskRepository.AddNewTask(newTask);
                    }
                } catch (Exception e) {
                    Logger.error("Something went wrong");
                }
            }

        } catch (Exception e) {
            Logger.error("Invalid data detected");
        }
        Refresh();
    }

    /**
     * Method for updating an existing task, same as the insert
     *
     * @param event
     */
    @FXML
    void Update(ActionEvent event) {

        try {
            Task newTask = new Task();

            newTask.setTask(tf_task.getText());
            newTask.setPerson(tf_person.getText());
            newTask.setStartdate(dp_startdate.getValue());
            newTask.setFinishdate(dp_finishdate.getValue());

            if (check.checkIfEmpty(tf_task.getText(), tf_person.getText(), dp_startdate.getValue(), dp_finishdate.getValue())) {
                Logger.info("Blank field detected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Blank field");
                alert.setHeaderText(null);
                alert.setContentText("Dont leave blank fields");
                alert.showAndWait();
            } else if (check.checkDates(dp_startdate.getValue(), dp_finishdate.getValue())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong dates");
                alert.setHeaderText(null);
                alert.setContentText("Start date can't be later then the the finish date");
                alert.showAndWait();
            } else {
                try {
                    //taskRepository.DeleteTask(TransferUtility.task = tv_tasks.getSelectionModel().getSelectedItem());
                    //taskRepository.UpdateTask(newTask);
                    Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/dlOWKKEPJg", "dlOWKKEPJg", "S9nooKD9cK");
                    String sql = "select * from Task where (person= ? and startdate= ?) or (person= ? and finishdate=?)";

                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, tf_person.getText());
                    preparedStatement.setDate(2, Date.valueOf(dp_startdate.getValue()));
                    preparedStatement.setString(3, tf_person.getText());
                    preparedStatement.setDate(4, Date.valueOf(dp_finishdate.getValue()));
                    ;

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        Logger.info("Person busy");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Person already busy at this date");
                        alert.showAndWait();

                    } else {
                        Logger.info("Adding new data to table");
                        taskRepository.DeleteTask(TransferUtility.task = tv_tasks.getSelectionModel().getSelectedItem());
                        taskRepository.UpdateTask(newTask);
                    }

                } catch (Exception e) {
                    Logger.error("Something went wrong");
                }

            }

        } catch (Exception e) {
            Logger.error("Something went wrong in update");
        }

        Refresh();

    }

    /**
     * Method for deleting a task from the database
     *
     * @param event
     */
    @FXML
    @Transactional
    public void Delete(ActionEvent event) {
        taskRepository.DeleteTask(TransferUtility.task = tv_tasks.getSelectionModel().getSelectedItem());
        Refresh();

    }

    /**
     * Method for closing the application
     *
     * @param event
     */
    @FXML
    void Exit(ActionEvent event) {
        Logger.info("Application closed");
        Platform.exit();
        System.exit(0);

    }

    /**
     * Method for refreshing the tableview
     */
    @FXML
    void Refresh() {
        try {
            ObservableList<Task> data = FXCollections.observableArrayList(
                    taskRepository.Query());
            tv_tasks.setItems(data);
            initColumn();
            Logger.info("Table refreshed");

        } catch (Exception e) {
            System.out.println("Something went wrong in table refresh");
        }


        tf_task.clear();
        tf_person.clear();
        dp_startdate.setValue(null);
        dp_finishdate.setValue(null);
    }

}

