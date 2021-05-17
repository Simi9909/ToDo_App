package controller;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mysql.cj.log.Log;
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

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static java.lang.Integer.parseInt;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_insert;

    @FXML
    private Button btn_exit;

    @FXML
    private Button btn_refresh;

    private TaskRepository createNewTask = new TaskRepository();

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

    @FXML
    void Insert(ActionEvent event) {
        try {
            Task newTask = new Task();

            newTask.setTask(tf_task.getText());
            newTask.setPerson(tf_person.getText());
            newTask.setStartdate(dp_startdate.getValue());
            newTask.setFinishdate(dp_finishdate.getValue());


            if (tf_task.getText().trim().isEmpty() || tf_person.getText().isEmpty() ||
                    dp_startdate.getValue() == null || dp_finishdate.getValue() == null) {
                Logger.error("Blank field detected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Blank field");
                alert.setHeaderText(null);
                alert.setContentText("Dont leave blank fields");
                alert.showAndWait();
            } else
            if (dp_startdate.getValue().isAfter(dp_finishdate.getValue())) {
                Logger.info("Wrong dates");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong dates");
                alert.setHeaderText(null);
                alert.setContentText("Start date can't be later then the the finish date");
                alert.showAndWait();
            } else {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/dlOWKKEPJg", "dlOWKKEPJg","S9nooKD9cK");
                    String sql = "select * from Task where (person= ? and startdate= ?) or (person= ? and finishdate=?)";

                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1,tf_person.getText());
                    preparedStatement.setDate(2, Date.valueOf(dp_startdate.getValue()));
                    preparedStatement.setString(3,tf_person.getText());
                    preparedStatement.setDate(4, Date.valueOf(dp_finishdate.getValue()));

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
                        createNewTask.AddNewTask(newTask);
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

    @FXML
    void Update(ActionEvent event) {

        try {
            Task newTask = new Task();

            newTask.setTask(tf_task.getText());
            newTask.setPerson(tf_person.getText());
            newTask.setStartdate(dp_startdate.getValue());
            newTask.setFinishdate(dp_finishdate.getValue());

            if (tf_task.getText().trim().isEmpty() || tf_person.getText().isEmpty() ||
                    dp_startdate.getValue() == null || dp_finishdate.getValue() == null) {
                Logger.info("Blank field detected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Blank field");
                alert.setHeaderText(null);
                alert.setContentText("Dont leave blank fields");
                alert.showAndWait();
            } else
            if (dp_startdate.getValue().isAfter(dp_finishdate.getValue())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong dates");
                alert.setHeaderText(null);
                alert.setContentText("Start date can't be later then the the finish date");
                alert.showAndWait();
            } else {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/dlOWKKEPJg", "dlOWKKEPJg","S9nooKD9cK");
                    String sql = "select * from Task where (person= ? and startdate= ?) or (person= ? and finishdate=?)";

                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1,tf_person.getText());
                    preparedStatement.setDate(2, Date.valueOf(dp_startdate.getValue()));
                    preparedStatement.setString(3,tf_person.getText());
                    preparedStatement.setDate(4, Date.valueOf(dp_finishdate.getValue()));;

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
                        createNewTask.DeleteTask(TransferUtility.task = tv_tasks.getSelectionModel().getSelectedItem());
                        createNewTask.UpdateTask(newTask);
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

    @FXML
    @Transactional
    public void Delete(ActionEvent event) {
        createNewTask.DeleteTask(TransferUtility.task = tv_tasks.getSelectionModel().getSelectedItem());
        Refresh();

    }

    @FXML
    void Exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
        Logger.info("Application closed");
    }

    @FXML
    void Refresh() {
        try {
            ObservableList<Task> data = FXCollections.observableArrayList(
                    createNewTask.Query());
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

