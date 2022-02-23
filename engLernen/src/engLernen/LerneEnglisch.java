package engLernen;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;

/**
 * I created this simple application not even to save new english words I have
 * heard or read but also to use it for practice to remember the words
 * 
 * @author Julius Neimantas
 */
public class LerneEnglisch {

	private JFrame frame;
	private JTextField deutsch;
	private JTextField englisch;
	private JTextArea englischTextArea;
	private JButton speichern;
	private JButton wortAuslesen;
	/**
	 * this list includes all the Woerter-objects
	 */
	private static List<Woerter> liste = new CopyOnWriteArrayList<Woerter>();
	private JButton naechster;
	/**
	 * for counting the words
	 */
	private static int count;
	private JButton btnNewButton_1;
	/**
	 * the file includes arraylist with Woerter-class-objects
	 */
	private static File f = new File("C:\\Users\\Cezar\\Documents\\ECLIPSE-workspace\\ProjectNotizBuch\\object.txt");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					liste = getWoerter(f);
					LerneEnglisch window = new LerneEnglisch();
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
	public LerneEnglisch() {
		initialize();
	}

	/**
	 * This Method adds List<Woerter> Object to the local file
	 */

	public static void createWordsFile(List<Woerter> wort, File dataFile) throws IOException {

		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(dataFile)))) {

			out.writeObject(liste);
		}
	}

	/** this method reads out the List<Woerter> object from the local file */

	public static List<Woerter> getWoerter(File dataFile) throws IOException, ClassNotFoundException {
		List<Woerter> listeWoerter = new ArrayList<Woerter>();
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
			while (true) {
				Object object = in.readObject();
				if (object instanceof List)

					listeWoerter.addAll((ArrayList<Woerter>) object);

			}

		} catch (EOFException e) {

		}
		return listeWoerter;
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 453, 257);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Lerne Englisch mit Julius v2.0 :D");
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 443, 224);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Deutsch:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(12, 11, 86, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Englisch:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(12, 36, 86, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Beispielsatz in Englisch:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(12, 67, 136, 14);
		panel.add(lblNewLabel_2);

		deutsch = new JTextField();
		deutsch.setBounds(154, 8, 279, 20);
		panel.add(deutsch);
		deutsch.setColumns(10);

		englisch = new JTextField();
		englisch.setBounds(154, 33, 279, 20);
		panel.add(englisch);
		englisch.setColumns(10);

		englischTextArea = new JTextArea();
		englischTextArea.setBounds(154, 62, 279, 80);
		englischTextArea.setBorder(BorderFactory.createEtchedBorder());
		panel.add(englischTextArea);

//		save the the word
		speichern = new JButton("Speichern");
		speichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String d = deutsch.getText();
				String en = englisch.getText();
				String area = englischTextArea.getText();

				liste.add(new Woerter(d, en, area));
				deutsch.setText("");
				englisch.setText("");
				englischTextArea.setText("");
				try {
					createWordsFile(liste, f);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		speichern.setBounds(12, 92, 126, 20);
		panel.add(speichern);

//		show random german wort
		wortAuslesen = new JButton("DE Wort anzeigen");
		wortAuslesen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (liste.size() == 0)
						JOptionPane.showMessageDialog(null, "Die Liste enthält keine Elemente mehr!");
					int i = (int) (Math.random() * liste.size());
					count = i;

					deutsch.setText(liste.get(i).getDe());
					englisch.setText("");
					englischTextArea.setText("");
				} catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		wortAuslesen.setBounds(154, 153, 142, 20);
		panel.add(wortAuslesen);

//		delete the selected object from the List
		JButton alleLoeschen = new JButton("Loeschen");
		alleLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (liste.size() == 0)
					JOptionPane.showMessageDialog(null, "Die Liste enthält keine Elemente mehr!");

				JOptionPane.showMessageDialog(null, "Wort: " + liste.get(count).getDe() + " wurde gelöscht");
				liste.remove(count);

				deutsch.setText("");
				englisch.setText("");
				englischTextArea.setText("");

				try {
					createWordsFile(liste, f);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		alleLoeschen.setBounds(12, 112, 126, 20);
		panel.add(alleLoeschen);

//		select next element on the list
		naechster = new JButton("-->");
		naechster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (count < liste.size() - 1) {

					count++;
				}
				deutsch.setText(liste.get(count).getDe());
				englisch.setText(liste.get(count).getEn());
				englischTextArea.setText(liste.get(count).getDetB());

			}
		});
		naechster.setBounds(76, 132, 62, 20);
		panel.add(naechster);

//		go back to the previuos object
		btnNewButton_1 = new JButton("<--");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (count > 0) {
					count--;
				}
				deutsch.setText(liste.get(count).getDe());
				englisch.setText(liste.get(count).getEn());
				englischTextArea.setText(liste.get(count).getDetB());

			}
		});
		btnNewButton_1.setBounds(12, 132, 64, 20);
		panel.add(btnNewButton_1);

//		show translation 
		JButton btnNewButton = new JButton("Uebersetzung");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				englisch.setText(liste.get(count).getEn());
				englischTextArea.setText(liste.get(count).getDetB());
			}
		});
		btnNewButton.setBounds(296, 153, 132, 20);
		panel.add(btnNewButton);

	}
}
