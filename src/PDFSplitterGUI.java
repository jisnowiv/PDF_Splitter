import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.File;
import java.io.IOException;


public class PDFSplitterGUI {

    JFrame frame;
    JButton btnChooseFile;
    JFileChooser fc;
    File in_file;
    String out_file;
    Boolean in_selected;
    SplitPDF splitter;
    JLabel label;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			PDFSplitterGUI window = new PDFSplitterGUI();
			window.frame.setVisible(true);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
    }

    /**
     * Create the application.
     */
    public PDFSplitterGUI() {
	in_selected = false;
	initialize();
    }
	
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame("PDF Splitter");
	frame.setBounds(100, 100, 350, 150);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
		
	fc = new JFileChooser();
	//	fc.setMultiSelectionEnabled(true);
		
	btnChooseFile = new JButton("Choose File");
	btnChooseFile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    System.out.println("blah");
		    int returnval = fc.showOpenDialog(btnChooseFile);
		 
		    if(returnval == JFileChooser.APPROVE_OPTION){
			in_file = fc.getSelectedFile();
			in_selected = true;
		    }
		    else{
			System.out.println("Cancel was selected");
		    }
		}
	    });
	btnChooseFile.setBounds(6, 6, 120, 40);
	frame.getContentPane().add(btnChooseFile);
		
	JButton btnBegin = new JButton("Begin");
	btnBegin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if(in_selected){
			//perform the split
			splitter = new SplitPDF(in_file, out_file);
			try {
			    splitter.Split();
			    /*in_selected = false;
			      out_selected = false;
			      in_file = null;
			      out_file = null;*/
			} catch (IOException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    }
		    else{
			System.out.println("Need to select at least one more file");
		    }
		}
	    });
	btnBegin.setBounds(224, 82, 120, 40);
	frame.getContentPane().add(btnBegin);
	
	label = new JLabel();
	label.setBounds(138, 17, 206, 16);
	frame.getContentPane().add(label);
		
	JButton btnReset = new JButton("Reset");
	btnReset.setBounds(6, 82, 120, 40);
	frame.getContentPane().add(btnReset);
    }
}
