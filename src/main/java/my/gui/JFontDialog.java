/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.gui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ADMIN
 */
public class JFontDialog extends JDialog{
     private JList<String> lstFontName, lstStyle;
    private JList<Integer> lstSize;
    private JLabel lbPreview;
    private JButton btOk, btCancel;
    
    private Font selectedFont;
    private static final int[] FONT_STYLES = { Font.PLAIN, Font.ITALIC, Font.BOLD, Font.ITALIC + Font.BOLD };
    private static final String[] STYLE_NAMES = {"Plain", "Italic", "Bold", "Italic Bold"};
    
    private JNotepad parent;
    
    public JFontDialog(Frame owner, boolean modal) {
        super(owner, modal);
        
        // Ensure owner is of type JNotepad before casting
        if (owner instanceof JNotepad) {
            parent = (JNotepad) owner;
        } else {
            throw new IllegalArgumentException("Owner must be an instance of JNotepad");
        }
        
        createGUI();
        setFontPreview();
        processEvent();
        setSize(600, 450);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner);
    }

    private void createGUI() {
        JPanel p = new JPanel(null);

        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        lstFontName = new JList<>(fontNames);
        JScrollPane scrollFontName = new JScrollPane(lstFontName);
        lstFontName.setSelectedIndex(0);
        p.add(scrollFontName);
        scrollFontName.setBounds(5, 50, 200, 200);
        
        lstStyle = new JList<>(STYLE_NAMES);
        JScrollPane scrollStyle = new JScrollPane(lstStyle);
        lstStyle.setSelectedIndex(0);
        p.add(scrollStyle);
        scrollStyle.setBounds(210, 50, 200, 200);
        
        Integer[] sizes = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};
        lstSize = new JList<>(sizes);
        JScrollPane scrollSize = new JScrollPane(lstSize);
        lstSize.setSelectedIndex(4); // Select 12 by default
        p.add(scrollSize);
        scrollSize.setBounds(420, 50, 100, 200);
        
        p.add(lbPreview = new JLabel("AaBbYyZz"));
        lbPreview.setBounds(200, 270, 300, 80);
        
        p.add(btOk = new JButton("OK"));
        p.add(btCancel = new JButton("Cancel"));
        btOk.setBounds(250, 350, 100, 30);
        btCancel.setBounds(380, 350, 100, 30);
        
       add(p);
    }

    private void setFontPreview() {
        // Handle null values safely
        String name = lstFontName.getSelectedValue();
        if (name == null) {
            name = Font.SANS_SERIF; // Default font if none selected
        }
        
        int style = FONT_STYLES[lstStyle.getSelectedIndex()];
        
        Integer size = lstSize.getSelectedValue();
        if (size == null) {
            size = 12; // Default size if none selected
        }
        
        selectedFont = new Font(name, style, size);
        lbPreview.setFont(selectedFont);
    }

    private void processEvent() {
        ListSelectionListener updatePreview = e -> setFontPreview();
        lstFontName.addListSelectionListener(updatePreview);
        lstStyle.addListSelectionListener(updatePreview);
        lstSize.addListSelectionListener(updatePreview);
        
        btOk.addActionListener(e -> {
           // parent.getEditor().setFont(selectedFont);
            dispose();
        });
        
        btCancel.addActionListener(e -> dispose());
    }
   public Font getSelectedFont() {
        return selectedFont;
    }
}
