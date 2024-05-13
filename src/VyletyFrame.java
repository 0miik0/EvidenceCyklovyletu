import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VyletyFrame extends JFrame {
    private JTextField txtField;
    private JButton btnSmaz;
    private JTextArea txtArea;
    private JPanel panelMain;
    File selectedFile;
    List<Cyklovylet> seznamVyletu = new ArrayList<>();
    private VyletyFrame(){
        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Cyklovýlety");
        setSize(500,400);

        //JScrollPane scrollPane = new JScrollPane(txtArea);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        seznamVyletu.add(new Cyklovylet("A", 7, LocalDate.of(2024,10,12)));
        seznamVyletu.add(new Cyklovylet("B", 8, LocalDate.of(2024,10,12)));
        display();
        btnSmaz.addActionListener(e -> removeVylet());
        initMenu();
    }
    public void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu souborMenu = new JMenu("Soubor");
        menuBar.add(souborMenu);

        JMenuItem nactiItem = new JMenuItem("Načti...");
        souborMenu.add(nactiItem);
        nactiItem.addActionListener(e -> openFile());

        JMenuItem refreshItem = new JMenuItem("Refresh");
        souborMenu.add(refreshItem);
        refreshItem.addActionListener(e -> refresh());
    }
    public void display(){
        int i = 1;
        txtArea.setText("");
        for(Cyklovylet vylet : seznamVyletu){
            txtArea.append(i + ". " + vylet.getCil() + " (" + vylet.getDelka() + " km)" + "\n");
            i++;
        }
    }
    public void removeVylet(){
        int num = Integer.parseInt(txtField.getText());
        seznamVyletu.remove(num -1);
        display();
    }
    public void readFile(File selectedFile){
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(selectedFile)))){
            while(sc.hasNextLine()){
                String radek = sc.nextLine();
                String[] bloky = radek.split(",");
                String cil = bloky[0];
                int delka = Integer.parseInt(bloky[1]);
                LocalDate datum = LocalDate.parse(bloky[2]);
                seznamVyletu.add(new Cyklovylet(cil, delka, datum));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void openFile(){
        JFileChooser fc = new JFileChooser(".");
        int result = fc.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();
            readFile(selectedFile);
            display();
        }
    }
    public void refresh(){
        if (selectedFile != null) {
            readFile(selectedFile);
            System.out.println("Data byla aktualizována.");
        } else {
            JOptionPane.showMessageDialog(null, "Není vybrán žádný soubor pro obnovení.", "Upozornění", JOptionPane.WARNING_MESSAGE);
        }
        display();
    }
    public static void main(String[] args) {
        VyletyFrame frame = new VyletyFrame();
        frame.setVisible(true);
    }
}
