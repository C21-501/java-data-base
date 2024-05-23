package database.api;

import lombok.Data;

import java.io.*;
import java.util.List;

@Data
public class DatabaseEditor {
    private static String DATABASE_DIR_PATH = "src/main/resources/db";

    public DatabaseEditor() {
    }

    public void setDataEntries(List<String> dataEntries) throws IOException {
        saveToFile();
    }

    public void createDataEntry(String newData) throws IOException {

        saveToFile();
    }

    public void readDataEntries() throws IOException {

    }

    public void updateDataEntry(int entryIndex, String newData) throws IOException {

    }

    public void deleteDataEntry(int entryIndex) throws IOException {

    }

    public void getEntry(int entryIndex) throws IOException {

    }

    private void saveToFile() throws IOException {

    }
}
