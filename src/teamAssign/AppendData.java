package teamAssign;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import javafx.stage.Stage;

public class AppendData {

    public void openBrowseBtn( ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();

        final FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
        stage.show();
        stage.close();
    }

    // todo: What happens to file goes here
    private void openFile(File file) {

    }
}
