package GUI.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeText implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            System.out.println(str+":"+str.length());
        }
}
