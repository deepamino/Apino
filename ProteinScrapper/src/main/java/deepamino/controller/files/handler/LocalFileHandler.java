package deepamino.controller.files.handler;

import deepamino.controller.files.FileHandler;

import java.io.FileWriter;

public class LocalFileHandler implements FileHandler {
    @Override
    public void save(String filename, String content) {
        try {
            FileWriter writer = new FileWriter("src/main/resources/" + filename);
            writer.write(content);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
