package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Task;

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
        void Delete(ActionEvent event) {

        }

        @FXML
        void Exit(ActionEvent event) {

        }

        @FXML
        void Insert(ActionEvent event) {

        }

        @FXML
        void Update(ActionEvent event) {

        }

        @FXML
        void initialize() {

        }
    }

