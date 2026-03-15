import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class LogGUI implements ActionListener , WindowListener{
    private JTextArea txtarea;
    private JFrame fr;
    private JLabel total;
    private JButton clear;
    private JPanel panel;
    private int totalLog = 0;
    
    public LogGUI(){
        fr = new JFrame("Logs Window");
        txtarea = new JTextArea();
        txtarea.setEditable(false);
        clear = new JButton("Clear Logs");
        total = new JLabel("Total Logs: 0");
        panel = new JPanel();
        clear.addActionListener(this);
        fr.addWindowListener(this);
        
        txtarea.setBackground(Color.white);
        panel.setLayout(new BorderLayout());
        panel.add(total , BorderLayout.WEST);
        panel.add(clear , BorderLayout.EAST);
        
        fr.setLayout(new BorderLayout());
        fr.add(txtarea , BorderLayout.CENTER);
        fr.add(panel , BorderLayout.SOUTH);
        
        fr.setSize(400,200);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadLogs();
    }
    
    public void addLog(String msg){
        txtarea.append(msg + "\n");
        totalLog++;
        total.setText("Total Logs: " + totalLog);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clear){
            txtarea.setText("");
            totalLog = 0;
            total.setText("Total Logs: " + totalLog);
        }
    }
    
    public void loadLogs() {
        File file = new File("logs.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    txtarea.append(line + "\n");
                    totalLog++;
                }
                total.setText("Total Logs: " + totalLog);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logs.txt"))) {
            writer.write(txtarea.getText());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}

