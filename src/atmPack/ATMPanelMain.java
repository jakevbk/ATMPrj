package atmPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMPanelMain extends JPanel {

    private JMenuItem quitItem;
    private JMenuItem suspendItem;

    public ATMPanelMain(JMenuItem quitItem, JMenuItem suspendItem) {
        suspendItem = new JCheckBoxMenuItem("Suspend All ATMs");
        add(suspendItem);

        JPanel panel = new JPanel();
        panel.add(new ATMPanel());
        add(panel);

        JPanel panel2 = new JPanel();
        panel.add(new ATMPanel());
        add(panel2);

        JPanel panel3 = new JPanel();
        panel.add(new ATMPanel());
        add(panel3);

        this.quitItem = quitItem;
        this.suspendItem = suspendItem;

        quitItem.addActionListener(new Mylistener());
        suspendItem.addActionListener(new Mylistener());
    }

    private class Mylistener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == quitItem) {
                System.exit(1);
            }
            if (e.getSource() == suspendItem) {
                if(ATM.isSuspend() == true){
                    ATM.suspend(false);
                }
                else {
                    ATM.suspend(true);
                }
            }
        }
    }
}


























