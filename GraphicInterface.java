import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MarosR on 2014-03-26.
 */
public class GraphicInterface implements ActionListener {
    public JButton next;
    JFrame f = new JFrame();
    JButton[][] buttons;
    JButton[] bText;
    String text1;
    String text2;
    JLabel compareText, v3Value, v2Value, v1Value;

    int widthText1;
    int widthText2;
    int i = 1;
    int j = 0;

    public GraphicInterface() {
        f.setSize(700, 700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void createStrings(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;

        //ustalamy długość tekstów 1 i 2
        widthText1 = this.text1.length() + 1;
        widthText2 = this.text2.length() + 1;
        System.out.println(text1 + " :" + widthText1);
        System.out.println(text2 + " :" + widthText2);


        bText = new JButton[widthText1 + widthText2];
        System.out.println(bText.length);

        for (int i = 0; i < widthText2 + 1; i++) {
            for (int j = 0; j < widthText1 + 1; j++) {
                //creating text1 row
                if (i == 0 && j > 1) {
                    bText[i] = new JButton(String.valueOf(text1.charAt(j - 2)));
                    bText[i].setSize(45, 45);
                    bText[i].setLocation(50 * j, 50 * i);
                    bText[i].setBackground(Color.green);
                    bText[i].setBorder(BorderFactory.createLineBorder(Color.black));
                    f.add(bText[i]);
                }

                //creating text2 in first column
                if (i > 1 && j == 0) {
                    bText[i] = new JButton(String.valueOf(text2.charAt(i - 2)));
                    bText[i].setSize(45, 45);
                    bText[i].setLocation(50 * j, 50 * i);
                    bText[i].setBackground(Color.green);
                    bText[i].setBorder(BorderFactory.createLineBorder(Color.black));
                    f.add(bText[i]);
                }
            }
        }
    }

    public void createButtons() {
        //inicjalizacja macierzy o rozmiarze widthText1 + 2 x widthText2 + 2
        buttons = new JButton[widthText2][widthText1];

        int startX = 50;
        int startY = 50;
        for (int i = 0; i < widthText2; i++) {
            for (int j = 0; j < widthText1; j++) {

                buttons[i][j] = new JButton();
                buttons[i][j].setSize(45, 45);
                buttons[i][j].setLocation(startX + 50 * j, startY + 50 * i);
                buttons[i][j].setBackground(Color.white);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                f.add(buttons[i][j]);

                //inicjalizacja pierwszego wiersza wartościami od 0 do widthText1
                if (i == 0) {
                    buttons[i][j].setText(String.valueOf(j));
                }


                //inicjalizacja pierwszej kolumny wartościami od 0 do widthText2
                if (j == 0) {
                    buttons[i][j].setText(String.valueOf(i));
                }
            }
            f.repaint();

        }


    }

    public void addLabelsAndNextButton() {
        compareText = new JLabel();
        compareText.setSize(400, 50);
        compareText.setLocation(20, f.getHeight() - 140);
        f.add(compareText);

        v1Value = new JLabel();
        v1Value.setSize(400, 50);
        v1Value.setLocation(20, f.getHeight() - 120);
        f.add(v1Value);

        v2Value = new JLabel();
        v2Value.setSize(400, 50);
        v2Value.setLocation(20, f.getHeight() - 100);
        f.add(v2Value);

        v3Value = new JLabel();
        v3Value.setSize(400, 50);
        v3Value.setLocation(20, f.getHeight() - 80);
        f.add(v3Value);

        next = new JButton("NEXT STEP");
        next.setSize(150, 45);
        next.setLocation(f.getWidth() - 180, f.getHeight() - 100);
        f.add(next);
        next.addActionListener(this);
        f.repaint();
    }

    public void checkStrings() {
        int cost = 0;
        char charText1, charText2;
        for (int i = 1; i < text2.length() + 1; i++) {
            for (int j = 1; j < text1.length() + 1; j++) {
                //pobranie znaków z pozycji i, j
                charText1 = text1.charAt(j - 1);
                charText2 = text2.charAt(i - 1);
                System.out.print(" || " + charText1 + " -- " + charText2);

                if (charText1 != charText2) {
                    cost = 1;
                } else {
                    cost = 0;
                }

                int deletion = Integer.parseInt(buttons[i - 1][j].getText()) + 1;
                int insertion = Integer.parseInt(buttons[i][j - 1].getText()) + 1;
                int substitution = Integer.parseInt(buttons[i - 1][j - 1].getText()) + cost;

                String min = String.valueOf(Math.min(Math.min(deletion, insertion), substitution));
                System.out.println(" - - - Min: " + min);
                compareText.setText(charText1 + " -- " + charText2 + "   Koszt: " + cost + " - - - Min: " + min);
                buttons[i][j].setText(min);
            }
        }
    }

    public void checkLetters(int i, int j) {
        resetAll();
        int cost = 0;
        char charText1, charText2;
        charText1 = text1.charAt(j - 1);
        charText2 = text2.charAt(i - 1);
        System.out.print(" || " + charText1 + " -- " + charText2);

        if (charText1 != charText2) {
            cost = 1;
        } else {
            cost = 0;
        }

        int deletion = Integer.parseInt(buttons[i - 1][j].getText()) + 1;
        buttons[i - 1][j].setBackground(Color.cyan);
        int insertion = Integer.parseInt(buttons[i][j - 1].getText()) + 1;
        buttons[i][j - 1].setBackground(Color.cyan);
        int substitution = Integer.parseInt(buttons[i - 1][j - 1].getText()) + cost;
        buttons[i - 1][j - 1].setBackground(Color.cyan);

        String min = String.valueOf(Math.min(Math.min(deletion, insertion), substitution));


        System.out.println(" - - - Min: " + min);
        compareText.setText("Porównanie: " + charText1 + " == " + charText2 + "   Koszt: " + cost + "  Minmalna z liczb: " + min);
        v1Value.setText("Zamiana([i-1][j-1] + cost(" + cost + "):     \t" + String.valueOf(substitution));
        v2Value.setText("Wstawianie([i][j-1] + 1:              \t" + String.valueOf(insertion));
        v3Value.setText("Usuwanie([i-1][j] + 1:                  \t" + String.valueOf(deletion));

        buttons[i][j].setText(min);
        buttons[i][j].setBackground(Color.red);
    }

    private void resetAll() {
        for (int i = 0; i < text2.length() + 1; i++) {
            for (int j = 0; j < text1.length() + 1; j++) {
                buttons[i][j].setBackground(Color.white);
            }
        }
    }

    public void countIndexes() {
        if (j == (text1.length())) {
            i++;
            j = 0;
        }
        if (i == (text2.length() + 1)) {
            next.setEnabled(false);
        }
        j++;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            countIndexes();
            checkLetters(i, j);
        }
    }
}
