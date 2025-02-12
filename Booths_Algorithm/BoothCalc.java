package Course;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
public class BoothCalc {
    private Main booth = new Main();
    private JFrame frame;
    private JTextField inputN1;
    private JTextField inputN2;
    private JLabel resultLabel;
    private JLabel binaryResultLabel;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BoothCalc().createAndShowGUI());
    }
    private void createAndShowGUI() {
        frame = new JFrame("Booth Algorithm GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(9, 12, 18, 18));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        JLabel labelN1 = new JLabel("Enter The Multiplicand(M):");
        inputN1 = new JTextField();
        JLabel labelN2 = new JLabel("Enter The Multiplier(Q):");
        inputN2 = new JTextField();
        JButton multiplyButton = new JButton("Show Result");
        resultLabel = new JLabel("Result:");
        binaryResultLabel = new JLabel("Binary Result:");
        JButton showStepsButton = new JButton("Show Steps");
        JButton flowchartButton = new JButton("Show Flowchart");
        labelN1.setBounds(10, 10, 150, 30);
        inputN1.setBounds(170, 10, 100, 30);
        labelN2.setBounds(10, 50, 150, 30);
        inputN2.setBounds(170, 50, 100, 30);
        multiplyButton.setBounds(10, 90, 150, 30);
        resultLabel.setBounds(170, 90, 200, 30);
        flowchartButton.setBounds(10, 120, 150, 30);
        showStepsButton.setBounds(220, 120, 150, 30);
        binaryResultLabel.setBounds(240, 140, 200, 30);
        panel.add(labelN1);
        panel.add(inputN1);
        panel.add(labelN2);
        panel.add(inputN2);
        panel.add(multiplyButton);
        panel.add(resultLabel);
        panel.add(binaryResultLabel);
        panel.add(flowchartButton);
        panel.add(showStepsButton); 
        multiplyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performMultiplication();
                displayCalculation();
            }
			private void displayCalculation() {
			}
        });
        showStepsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performMultiplication();
                displayCalculationInPopup();
            }
        });
        flowchartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayFlowchart();
            }
        });
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    private void performMultiplication() {
        try {
            int n1 = Integer.parseInt(inputN1.getText());
            int n2 = Integer.parseInt(inputN2.getText());
            int result = booth.multiply(n1, n2);
            resultLabel.setText("Result: " + result);
            displayCalculation(n1, n2);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter integers.");
            binaryResultLabel.setText("Binary Result:");
        }
    }
    private void displayCalculationInPopup() {
        StringBuilder steps = new StringBuilder();
        steps.append("Booth Algorithm Calculation:\n\n");
        steps.append(String.format("%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s\n", "Step", "A", "Q", "Qn-1", "Multiplier", "Multiplier", "Result", "Right", "Binary", "Decimal"));
        steps.append(String.format("%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s\n", "", "", "", "", "M", "M1", "", "Shift", "Result", "Equivalent"));
        int[] m = booth.binary(Integer.parseInt(inputN1.getText()));
        int[] m1 = booth.binary(-Integer.parseInt(inputN1.getText()));
        int[] r = booth.binary(Integer.parseInt(inputN2.getText()));
        int[] A = new int[9];
        int[] S = new int[9];
        int[] P = new int[9];
        for (int i = 0; i < 4; i++) {
            A[i] = m[i];
            S[i] = m1[i];
            P[i + 4] = r[i];
        }
        for (int step = 1; step <= 4; step++) {
            steps.append(String.format("%-12d%-12s%-12s%-12s%-12s%-12s%-12s%-12d%-12s%-12d\n", step, arrayToString(A), arrayToString(S), arrayToString(P),
                    arrayToString(m), arrayToString(m1), "", step, arrayToString(booth.binaryResult()), booth.getDecimal(booth.binaryResult())));
            if (P[7] == 0 && P[8] == 0)
                ; 
            else if (P[7] == 1 && P[8] == 0)
                booth.add(P, S);
            else if (P[7] == 0 && P[8] == 1)
                booth.add(P, A);
            else if (P[7] == 1 && P[8] == 1)
                ; 
            booth.rightShift(P);
        }
        JOptionPane.showMessageDialog(frame, steps.toString(), "Booth Algorithm Steps", JOptionPane.PLAIN_MESSAGE);
    }
    private void displayCalculation(int n1, int n2) {
        System.out.println("Booth Algorithm Calculation:");
        System.out.println("Input 1: " + n1);
        System.out.println("Input 2: " + n2);
        int result = booth.multiply(n1, n2);
        System.out.println("Result: " + result);
        int[] binaryResult = booth.binaryResult();
        binaryResultLabel.setText("Binary Result: " + arrayToString(binaryResult));
    }
    private String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num);
        }
        return sb.toString();
    }
    private void displayFlowchart() {
        String imagePath = "D:\\JAVA\\Flow.png";
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            JLabel label = new JLabel(icon);
            JOptionPane.showMessageDialog(frame, label, "Booth Algorithm Flowchart", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error opening flowchart image.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
}
class Main {
	private int[] binaryResult;
    public int multiply(int n1, int n2) {
        int[] m = binary(n1);
        int[] m1 = binary(-n1);
        int[] r = binary(n2);
        int[] A = new int[9];
        int[] S = new int[9];
        int[] P = new int[9];
        for (int i = 0; i < 4; i++) {
            A[i] = m[i];
            S[i] = m1[i];
            P[i + 4] = r[i];
        }
        for (int i = 0; i < 4; i++) {
            if (P[7] == 0 && P[8] == 0)
                ; 
            else if (P[7] == 1 && P[8] == 0)
                add(P, S);
            else if (P[7] == 0 && P[8] == 1)
                add(P, A);
            else if (P[7] == 1 && P[8] == 1)
                ; 
            rightShift(P);
        }
        binaryResult = new int[8];
        System.arraycopy(P, 0, binaryResult, 0, 8);

        return getDecimal(P);
    }
    public int getDecimal(int[] B) {
        int p = 0;
        int t = 1;
        for (int i = 7; i >= 0; i--, t *= 2)
            p += (B[i] * t);

        if (p > 64)
            p = -(256 - p);

        return p;
    }
    public void rightShift(int[] A) {
        for (int i = 8; i >= 1; i--)
            A[i] = A[i - 1];
    }
    public void add(int[] A, int[] B) {
        int carry = 0;
        for (int i = 8; i >= 0; i--) {
            int temp = A[i] + B[i] + carry;
            A[i] = temp % 2;
            carry = temp / 2;
        }
    }
    public int[] binary(int n) {
        int[] bin = new int[4];
        int ctr = 3;
        int num = Math.abs(n);

        while (num != 0) {
            bin[ctr--] = num % 2;
            num /= 2;
        }
        if (n < 0) {
            for (int i = 0; i < 4; i++) {
                bin[i] = (bin[i] == 0) ? 1 : 0;
            }
            int carry = 1;
            for (int i = 3; i >= 0; i--) {
                bin[i] += carry;
                carry = bin[i] / 2;
                bin[i] %= 2;
            }
        }
        return bin;
    }
    public int[] binaryResult() {
        return binaryResult;
    }
}