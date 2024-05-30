import controller.FileController;
import model.FileModel;
import model.UserModel;
import view.FileView;

public class Main {
    public static void main(String[] args) {
        UserModel userModel = new UserModel();
        FileModel fileModel = new FileModel();
        FileView view = new FileView();
        FileController controller = new FileController(userModel, fileModel, view);
        controller.run();
    }
}
