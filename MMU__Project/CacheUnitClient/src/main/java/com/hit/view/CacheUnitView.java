package com.hit.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class CacheUnitView extends java.lang.Object
{
    private JFrame myJframe;//all the screen
    private CacheUnitPanel panel;//the main paenl
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final String firstLine="\nWelcome to my MMU Project ! \n";//the title

    JTextArea textArea = new JTextArea();
    JLabel textAreaLabel = new JLabel();

    public CacheUnitView()
    {
        myJframe = new JFrame();
        panel = new CacheUnitPanel();
    }
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }
    public void start()
    {
        panel.run();
    }

    public <T> void updateUIData(T ans)
    {
        if (ans.toString().equals("true"))
        {
            textArea.setForeground(Color.green);
            textArea.setFont(new Font("SANS_SERIF", Font.BOLD, 30));
            textArea.setText("\n\n  Succeeded!!!!!!!!!!!!!!!!!!!!!!! ");
            textAreaLabel .setIcon(new ImageIcon("images/suc.png"));
        }
        else if (ans.toString().equals("false") || ans.toString().equals("empty") )
        {
            textArea.setFont(new Font("Ariel", Font.BOLD, 14));
            textArea.setForeground(Color.red);
            textArea.setText("\n\n Failed! /n" + "Not bad, try again ");
        }
        else
        {
            textArea.setFont(new Font("SERIF", Font.BOLD, 14));
            textArea.setForeground(Color.blue);
            String[] res = ((String) ans).split(" ");
            textArea.setText("Algorithm: " + res[0] + "\n\nCapacity: " + res[1] +
                    "\n\nTotal Numbers Of Requests: " + res[3] +
                    "\n\nTotal Number Of DataModel Swaps(From Cache To Disk): " + res[2] +
                    "\n\nTotal Number Of DataModels(GET/DELETE/UPDATE Requests): " + res[4]);
            //taLabel .setIcon(new ImageIcon("images/analytics.png"));
        }

        textArea.validate();
        textAreaLabel.validate();
        panel.revalidate();
        panel.repaint();
    }

    public class CacheUnitPanel extends javax.swing.JPanel implements java.awt.event.ActionListener
    {
        private static final long serialVersionUID = 1L;

        JButton statsButton;
        JButton requestButton;
        JLabel wp;
        JLabel titleLable;

        @Override
        public void actionPerformed(ActionEvent arg0) {}

        public <T> void updateUIData(T ans)
        {
            if (ans.toString().equals("false")) // load failed
            {
                textArea.setText("Failed!");
                textArea.setSelectedTextColor(Color.RED);
            }
            else if (ans.toString().equals("true")) //load succeeded
            {
                textArea.setText("Succeeded!!!!!!!!!!!!!!!!!!!!");
                textArea.setSelectedTextColor(Color.GREEN);
            }
            else textArea.setText(ans.toString());  // print stats
            textArea.invalidate();
        }

        public void run()
        {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//design the frame
            myJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myJframe.setTitle(" My MMU Project");
            myJframe.setBounds(700, 700, 700, 700);
            panel.setBorder(new EmptyBorder(8, 2, 2, 2));
            myJframe.setContentPane(panel);
            panel.setLayout(null);
            textArea.setBounds(60, 100, 550, 500);
            textAreaLabel.setBounds(220,140,650,550);
            textArea.setSelectedTextColor(Color.ORANGE);
            textArea.setForeground(Color.pink);
            textArea.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            textArea.setText(firstLine);
            panel.add(textAreaLabel);
            panel.add(textArea);
            titleLable = new JLabel(" My MMU");
            titleLable.setForeground(Color.YELLOW);
            titleLable.setFont(new Font("Ariel", Font.BOLD, 50));
            titleLable.setBounds(215, 11, 400, 50);
            panel.add(titleLable);
            statsButton = new JButton("Show Statistics");
            statsButton.setFont(new Font("Ariel", Font.PLAIN, 18));
            statsButton.setBackground(Color.ORANGE);
            statsButton.setBounds(470, 11, 200, 50);
            statsButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    PropertyChangeEvent pce;
                    pce = new PropertyChangeEvent(CacheUnitView.this, "stats", null, "{ \"headers\":{\"action\":\"STATS\"},\"body\":[]}");
                    pcs.firePropertyChange(pce);
                }
            });
            panel.add(statsButton);

            requestButton = new JButton("Load a Request");
            requestButton.setFont(new Font("Ariel", Font.PLAIN, 18));
            requestButton.setBackground(Color.MAGENTA);
            requestButton.setBounds(10,11,200, 50);
            requestButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)//the action by click on the button
                {
                    JFileChooser fileChooserc = new JFileChooser();
                    fileChooserc.setCurrentDirectory(new File("./src/main/resources"));//the fileChooser opened by
                    // resources file that contain the request files
                    int choosenFile = fileChooserc.showOpenDialog(new JFrame());
                    if (choosenFile == JFileChooser.APPROVE_OPTION)
                    {
                        File selectedFile = fileChooserc.getSelectedFile();
                        if (selectedFile != null) //if some file chosen
                        {
                            try
                            {
                                PropertyChangeEvent change;
                                change = new PropertyChangeEvent(CacheUnitView.this,"load",null,ParseFileToString(selectedFile.getPath()));
                                pcs.firePropertyChange(change);
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                    }
                }

            });
            panel.add(requestButton);//show the butten on the screen
            wp = new JLabel("");
            wp.setBounds(0, 0, screenSize.width, screenSize.height);
            panel.add(wp);
            myJframe.setLocationRelativeTo(null);
            myJframe.setVisible(true);
        }

        public String ParseFileToString(String filePath) throws IOException
        {
            String thisLine= null;
            BufferedReader bufferedReader = null;
            StringBuilder stringBuilder = new StringBuilder();
            File file = new File(filePath);
            bufferedReader = new BufferedReader(new FileReader(file));//create new bufferReader
            thisLine = bufferedReader.readLine();
            while (thisLine != null)
            {
                stringBuilder.append(thisLine);//write to the buffer
                thisLine = bufferedReader.readLine();
            }
            bufferedReader.close();//close the buffer
            return stringBuilder.toString();
        }
    }
}
