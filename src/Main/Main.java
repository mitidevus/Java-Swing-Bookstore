package Main;

import presentation.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                // Initialize frame
//                UserListFrame userListFrame = new UserListFrame();
//
//                // Make the frame visible
//                userListFrame.setVisible(true);
                // Initialize frame
                OrderListFrame publisherListFrame = new OrderListFrame();

                // Make the frame visible
                publisherListFrame.setVisible(true);
            }
        });
    }
}