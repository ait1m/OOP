import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ClockGUI implements Runnable , ActionListener{

    private JFrame fr;
    private JTextField txt;
    private JButton save;
    private JPanel savepanel;
    private LogGUI log;
    private JLabel datelabel;
    private JLabel timelabel;

    public ClockGUI(LogGUI log) {
        fr = new JFrame("Clock");
        txt = new JTextField(25);
        save = new JButton("Save");
        savepanel = new JPanel();
        datelabel = new JLabel();
        timelabel = new JLabel();
        save.addActionListener(this);
        this.log = log;

        // input
        savepanel.setLayout(new FlowLayout());
        savepanel.add(txt);
        savepanel.add(save);
        
        Font f1 = new Font("Monospaced",Font.BOLD,50);
        Font f2 = new Font("Monospaced",Font.BOLD,20);
        datelabel.setFont(f2);
        timelabel.setFont(f1);
        
        Thread t = new Thread(this);
        t.start();
        
        savepanel.setBackground(Color.WHITE);
        fr.getContentPane().setBackground(Color.WHITE);
        
        fr.setLayout(new BorderLayout());
        fr.add(datelabel,BorderLayout.NORTH);
        fr.add(timelabel,BorderLayout.CENTER);
        fr.add(savepanel,BorderLayout.SOUTH);
        

        datelabel.setHorizontalAlignment(JLabel.CENTER);
        timelabel.setHorizontalAlignment(JLabel.CENTER);
        fr.setSize(350,200);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        while (true) {
            LocalDateTime now = LocalDateTime.now();
            String date = now.format(dateFormatter);
            String time = now.format(timeFormatter);
            
            datelabel.setText(date);
            timelabel.setText(time);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save){
            String msg = txt.getText();
            if(!msg.isEmpty()){
                String logtxt = datelabel.getText() + " " + timelabel.getText() + ": " + msg ;
                txt.setText("");
                log.addLog(logtxt);
            }
        }
    }
}
