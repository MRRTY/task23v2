import gui.MainForm;

import java.awt.*;

public class Main {
    public static void main(String[] args){

        EventQueue.invokeLater(() -> {
            MainForm mf = new MainForm();
            mf.setVisible(true);
        });


    }
}
