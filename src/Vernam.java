import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vernam extends JFrame implements ActionListener{
    private JButton cipher;
    private JTextField word, key;
    private String text, code;
    private JLabel labelDecimal1, labelDecimal2, labelBinary1, labelBinary2;
    private JLabel answer, label;
    private JPanel first;
    private String xorString;
    public Vernam (){
        super("Vernam Cipher");
        setLayout(new GridLayout(7, 1));

        Font font  = new Font("Arial", Font.BOLD, 14);

        first = new JPanel();
        add(first);
        first.setLayout(new FlowLayout(FlowLayout.LEFT));
        word = new JTextField(10);
        word.setFont(font);
        first.add(word);
        key = new JTextField(10);
        key.setFont(font);
        first.add(key);
        cipher = new JButton("Cipher the text");
        cipher.setFont(font);
        first.add(cipher);
        cipher.addActionListener(this);

        labelDecimal1 = new JLabel("");
        add(labelDecimal1);
        labelDecimal1.setFont(font);

        labelDecimal2 = new JLabel("");
        add(labelDecimal2);
        labelDecimal2.setFont(font);

        labelBinary1 = new JLabel("");
        add(labelBinary1);
        labelBinary1.setFont(font);

        labelBinary2 = new JLabel("");
        add(labelBinary2);
        labelBinary2.setFont(font);

        label = new JLabel("");
        add(label);
        label.setFont(font);

        answer = new JLabel("");
        add(answer);
        answer.setFont(font);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        check(word.getText(), key.getText());
        text = word.getText();
        text = toLower(text);
        labelDecimal1.setText("Decimal representation of text: "+decimalShow(text));
        labelBinary1.setText("Binary representation of text: "+binaryShow(text));

        code = key.getText();
        code = toLower(code);
        labelDecimal2.setText("Decimal representation of key: "+decimalShow(code));
        labelBinary2.setText("Binary representation of key: "+binaryShow(code));
        label.setText("After XOR ing: "+vernamCipher());
        answer.setText("Ciphered text is: "+xorString);
    }
    private void check(String strWord, String strKey){
        strWord = toLower(strWord);
        strKey = toLower(strKey);
        int k = 0;
        if (strWord.length() != strKey.length()){
            while(strWord.length() != strKey.length()){
                strKey += strKey.charAt(k);
                k++;
            }
        }
        word.setText(strWord);
        key.setText(strKey);
    }
    private String vernamCipher(){
        String javob = ""; xorString = "";
        String word1 = word.getText();
        String key1 = key.getText(); int k = 0;
        word1 = toLower(word1);
        key1 = toLower(key1);

        while(key1.length() < word1.length()){
            key1 += key1.charAt(k);
            k++;
        }

        int []arrWord = new int[word1.length()];
        for (int i = 0; i < arrWord.length; i++) {
            arrWord[i] = (word1.charAt(i)-97);
        }
        int []arrKey = new int[key1.length()];
        for (int i = 0; i < arrKey.length; i++) {
            arrKey[i] = (key1.charAt(i)-97);
        }
        int []ans = new int[arrWord.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = arrWord[i]^arrKey[i];
            System.out.println(ans[i]);
            javob +=  (""+arrWord[i]+"^"+""+arrKey[i]+" ="+ans[i]);
            javob += " ";
            xorString += (""+(char)(ans[i]+97));
        }
        return javob;
    }
    private String toLower(String text1){
//        for (int i = 0; i < text1.length(); i++){
//            if (Character.isUpperCase(text1.charAt(i)))
//                Character.toLowerCase(text1.charAt(i));
//        }
        text1.toLowerCase();
        return text1;
    }
    private String decimalShow(String str){
        String temp = "";
        for (int i = 0; i < str.length(); i++){
            temp += (str.charAt(i)+"->" + ""+(str.charAt(i)-97)+" ");
            }
        System.out.println(temp);
        return temp;
    }

    private String binaryShow(String str){
        String temp = "", ans = "", string = "";
        int val;
        for (int i = 0; i < str.length(); i++) {
            val = (str.charAt(i) - 97);
            ans += str.charAt(i)+"->";
            temp = ""; string = "";
            while(val > 1){
               temp += (""+(val%2));
                val /= 2;
            } temp += ("" + val);
            for (int j = temp.length()-1; j >= 0; j--){
                string += temp.charAt(j);
            }
            ans += string + " ";
        }
        return ans;
    }

    public static void main(String[] args) {
        Vernam v = new Vernam();
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        v.setSize(700, 600);
        v.setVisible(true);
    }
}
