package software.ulpgc.imageviewer;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File folder = selectFolder();
        if (folder == null || !folder.isDirectory()) {
            System.out.println("No se seleccionó un directorio válido. Saliendo del programa.");
            return;
        }

        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(folder).load();
        frame.imageDisplay().show(image);
        frame.add("<", new PreviousImageCommand(frame.imageDisplay()));
        frame.add(">", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);
    }

    private static File selectFolder() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Selecciona el directorio que contiene las imágenes");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}