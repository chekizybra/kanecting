package org.chekizybra.kanecting;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Сontroller extends uhhhpetrunyaController {
    @FXML
    private TextField memeField;
    @FXML
    private TextField originField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField deleteIdField;


    public void onAdd(javafx.event.ActionEvent e) throws IOException {
        String meme = memeField.getText();
        String origin = originField.getText();
        String yearText = yearField.getText();

        if (meme.isBlank() || origin.isBlank() || yearText == null) {
            System.out.println("Все поля должны быть заполнены.");
            return;
        }
        int year;
        try {
            year = Integer.parseInt(yearText);
        } catch (NumberFormatException ex) {
            System.out.println("Год должен быть числом!");
            return;
        }
        insertMeme(meme,origin,year);
    }
    public void onDelete(javafx.event.ActionEvent e) throws IOException {
        String idText = deleteIdField.getText();

        if (idText == null || idText == null) {
            System.out.println("ID не может быть пустым");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            System.out.println("ID должен быть числом");
            return;
        }

        System.out.println("ID удален: " + id);
        deleteMemeById(id);

    }
    @FXML
    private TableView<Map<String, Object>> memeTable;
    @FXML
    private TableColumn<Map<String, Object>, String> idColumn;
    @FXML
    private TableColumn<Map<String, Object>, String> memeColumn;
    @FXML
    private TableColumn<Map<String, Object>, String> originColumn;
    @FXML
    private TableColumn<Map<String, Object>, String> yearColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(param ->
                new SimpleStringProperty(String.valueOf(param.getValue().get("id")))
        );
        memeColumn.setCellValueFactory(param ->
                new SimpleStringProperty(String.valueOf(param.getValue().get("meme")))
        );
        originColumn.setCellValueFactory(param ->
                new SimpleStringProperty(String.valueOf(param.getValue().get("origin")))
        );
        yearColumn.setCellValueFactory(param ->
                new SimpleStringProperty(String.valueOf(param.getValue().get("year")))
        );
    }

    public void onRefresh() throws IOException {
        JSONArray jsonArray = new JSONArray(fetchAllMemes());
        ObservableList<Map<String, Object>> data = FXCollections.observableArrayList();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Map<String, Object> row = new HashMap<>();
            row.put("id", obj.getInt("id"));
            row.put("meme", obj.getString("meme"));
            row.put("origin", obj.getString("origin"));
            row.put("year", obj.getInt("year"));
            data.add(row);
        }
        memeTable.setItems(data);
    }



}
