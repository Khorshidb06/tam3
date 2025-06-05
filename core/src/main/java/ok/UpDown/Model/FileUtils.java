package ok.UpDown.Model;

import javax.swing.*;
import java.io.File;

public class FileUtils {
    public static File chooseImageFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Avatar Image");
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }
}

