import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -3880026026104218593L;
	private Primes m_Primes;
	private JTextField tfPrimeFileName;
	private JTextField tfCrossFileName;
	private JLabel lblPrimeCount;
	private JLabel lblCrossCount;
	private JLabel lblLargestPrime;
	private JLabel lblLargestCross;
	private JLabel lblStatus;
	
	MainWindow(String name, Primes p)
	{
		/* Requirements
		* 1000 x 400 pixels mainWindow
		* Background Color = Texas A&M Maroon
		* Four divisions:
		* 1. Primes File textbox with Load and Save buttons
		* 2. Hexagon Cross File textbox with Load and Save buttons
		* 3. Generate Primes and Generate Crosses with different popouts
		* 4. Status bar with the status of the app
		*/


		JFrame mainWindow = new JFrame(name);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		// Reposition left corner to (200, 200) and resize to 1000 x 400
		mainWindow.setBounds(200, 200, 1000, 400);

		// Recolor to Texas A&M Maroon (80, 0, 0)
		mainWindow.getContentPane().setBackground(new Color(80, 0, 0));

		// =============== Main Window Layout ========================
		mainWindow.setLayout(new GridBagLayout());
		

		// ============== Primes Panel ========================

		// Adding the primes panel component
		JPanel primesPanel = new JPanel();
		GridBagConstraints gbcMainWindow = new GridBagConstraints();
		gbcMainWindow.fill = GridBagConstraints.HORIZONTAL;
		gbcMainWindow.anchor = GridBagConstraints.WEST;
		gbcMainWindow.ipady = 10;
		gbcMainWindow.weightx = .5;
		gbcMainWindow.gridx = 0;
		gbcMainWindow.gridy = 0;
		gbcMainWindow.insets = new Insets(1,2,0,0);

		GridBagConstraints gbcPrimePanel = new GridBagConstraints();
		primesPanel.setLayout(new GridBagLayout());

		//Adding the text field
		tfPrimeFileName = new JTextField(Config.PRIMEFILENAME);
		tfPrimeFileName.setColumns(50);	
		gbcPrimePanel.anchor = GridBagConstraints.NORTHWEST;
		gbcPrimePanel.fill = GridBagConstraints.HORIZONTAL;
		gbcPrimePanel.weightx = .5;
		gbcPrimePanel.insets = new Insets(1,2,0,0);
		gbcPrimePanel.gridx = 0;
		gbcPrimePanel.gridy = 0;
		gbcPrimePanel.gridwidth = 2;
		primesPanel.add(tfPrimeFileName, gbcPrimePanel);

		//Adding the primes count
		lblPrimeCount = new JLabel("0");
		gbcPrimePanel.gridx = 2;
		lblPrimeCount.setLabelFor(tfPrimeFileName);
		gbcPrimePanel.fill = GridBagConstraints.NONE;
		gbcPrimePanel.anchor = GridBagConstraints.CENTER;
		gbcPrimePanel.gridwidth = 1;
		primesPanel.add(lblPrimeCount, gbcPrimePanel);

		//Adding the primes file text
		JLabel primesFile = new JLabel("Primes File");
		primesFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gbcPrimePanel.gridy = 1;
		gbcPrimePanel.gridx = 0;
		gbcPrimePanel.fill = GridBagConstraints.HORIZONTAL;
		gbcPrimePanel.anchor = GridBagConstraints.SOUTHWEST;
		primesPanel.add(primesFile, gbcPrimePanel);

		//Adding the Load Button
		JButton loadPrimeButton = new JButton("Load");
		loadPrimeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileAccess.loadPrimes(p, tfPrimeFileName.getText());
				}
				catch (FileNotFoundException ex) {
					lblStatus.setText("Millions of years of evolution and we have a human here who cannot enter a file name that exists.");
					mainWindow.dispose();
				}
			}
		});
		gbcPrimePanel.gridx = 1;
		gbcPrimePanel.fill = GridBagConstraints.NONE;
		gbcPrimePanel.anchor = GridBagConstraints.EAST;
		primesPanel.add(loadPrimeButton, gbcPrimePanel);

		//Adding the Save Button
		JButton savePrimeButton = new JButton("Save");
		savePrimeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileAccess.savePrimes(p, tfPrimeFileName.getText());
				}
				catch (IOException ex) {
					lblStatus.setText("Something went wrong, probably from your end.");
					System.out.println(ex.getMessage());
					System.out.println(ex.getStackTrace());
					mainWindow.dispose();
				}
			}
		});
		gbcPrimePanel.gridx = 2;
		primesPanel.add(savePrimeButton, gbcPrimePanel);


		mainWindow.add(primesPanel, gbcMainWindow);

		//====================================================



		//================ Hexagon Cross Panel ===============

		// Adding the hexagon cross panel component
		JPanel crossPanel = new JPanel();

		GridBagConstraints gbcCrossPanel = new GridBagConstraints();
		crossPanel.setLayout(new GridBagLayout());

		//Adding the text field
		tfCrossFileName = new JTextField(Config.CROSSFILENAME);
		tfCrossFileName.setColumns(50);
		gbcCrossPanel.anchor = GridBagConstraints.NORTHWEST;
		gbcCrossPanel.fill = GridBagConstraints.HORIZONTAL;
		gbcCrossPanel.weightx = .5;
		gbcCrossPanel.insets = new Insets(1,2,0,0);
		gbcCrossPanel.gridx = 0;
		gbcCrossPanel.gridy = 0;
		gbcCrossPanel.gridwidth = 2;
		crossPanel.add(tfCrossFileName, gbcCrossPanel);

		//Adding the cross count
		lblCrossCount = new JLabel("0");
		gbcCrossPanel.gridx = 2;
		lblCrossCount.setLabelFor(tfCrossFileName);
		gbcCrossPanel.fill = GridBagConstraints.NONE;
		gbcCrossPanel.anchor = GridBagConstraints.CENTER;
		gbcCrossPanel.gridwidth = 1;
		crossPanel.add(lblCrossCount, gbcCrossPanel);

		//Adding the cross file text
		JLabel crossFile = new JLabel("Hexagon Cross File");
		crossFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gbcCrossPanel.gridy = 1;
		gbcCrossPanel.gridx = 0;
		gbcCrossPanel.fill = GridBagConstraints.HORIZONTAL;
		gbcCrossPanel.anchor = GridBagConstraints.SOUTHWEST;
		crossPanel.add(crossFile, gbcCrossPanel);

		//Adding the Load Button
		JButton loadCrossButton = new JButton("Load");
		loadCrossButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileAccess.loadCrosses(p, tfCrossFileName.getText());
				}
				catch (FileNotFoundException ex) {
					lblStatus.setText("Dawg that file dont exist, go commit sudoku.");
					mainWindow.dispose();
				}
			}
		});
		gbcCrossPanel.gridx = 1;
		gbcCrossPanel.fill = GridBagConstraints.NONE;
		gbcCrossPanel.anchor = GridBagConstraints.EAST;
		crossPanel.add(loadCrossButton, gbcCrossPanel);

		//Adding the Save Button
		JButton saveCrossButton = new JButton("Save");
		saveCrossButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileAccess.saveCrosses(p, tfCrossFileName.getText());
				}
				catch (IOException ex) {
					lblStatus.setText("Something went wrong, probably from your end.");
					System.out.println(ex.getMessage());
					System.out.println(ex.getStackTrace());
					mainWindow.dispose();
				}
			}
		});
		gbcCrossPanel.gridx = 2;
		crossPanel.add(saveCrossButton, gbcCrossPanel);

		gbcMainWindow.gridy = 1;
		mainWindow.add(crossPanel, gbcMainWindow);
		//==============================================


		// =================== Button Panel ================
		JPanel buttonsPanel = new JPanel();

		GridBagConstraints gbcButtonPanel = new GridBagConstraints();
		buttonsPanel.setLayout(new GridBagLayout());

		// Add Generate Primes Button
		JButton generatePrimesButton = new JButton("Generate Primes");
		gbcButtonPanel.anchor = GridBagConstraints.CENTER;
		gbcButtonPanel.fill = GridBagConstraints.NONE;
		gbcButtonPanel.weightx = .5;
		gbcButtonPanel.insets = new Insets(1,2,0,0);
		gbcButtonPanel.gridx = 0;
		gbcButtonPanel.gridy = 0;
		gbcButtonPanel.gridheight = 2;
		buttonsPanel.add(generatePrimesButton, gbcButtonPanel);

		// Add the two labels
		lblLargestPrime = new JLabel("The largest prime has 0 digits.");
		lblLargestPrime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcButtonPanel.gridheight = 1;
		gbcButtonPanel.gridx = 1;
		buttonsPanel.add(lblLargestPrime, gbcButtonPanel);

		lblLargestCross = new JLabel("The largest cross has 0 and 0 digits.");
		lblLargestCross.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcButtonPanel.gridy = 1;
		buttonsPanel.add(lblLargestCross, gbcButtonPanel);

		// Add Generate Cross Button
		JButton generateCrossesButton = new JButton("Generate Crosses");
		gbcButtonPanel.gridx = 2;
		gbcButtonPanel.gridy = 0;
		gbcButtonPanel.gridheight = 2;
		buttonsPanel.add(generateCrossesButton, gbcButtonPanel); 

		gbcMainWindow.gridy = 2;
		mainWindow.add(buttonsPanel, gbcMainWindow);
		// =============================================================



		// ================= Status Bar Panel ===============
		JPanel statusPanel = new JPanel();

		GridBagConstraints gcbStatusPanel = new GridBagConstraints();
		statusPanel.setLayout(new GridBagLayout());

		// Adding the Status Label
		lblStatus = new JLabel("Status: Bored");
		lblStatus.setHorizontalAlignment(JLabel.LEFT);
		lblStatus.setFont(new Font("Tahome", Font.PLAIN, 12));
		statusPanel.add(lblStatus, gcbStatusPanel);

		gbcMainWindow.gridy = 3;
		mainWindow.add(statusPanel, gbcMainWindow);
		// =============================================================


		//mainWindow.setMinimumSize(new Dimension(1000,400));
		mainWindow.pack();
		mainWindow.setVisible(true);
	}

	protected void popupGeneratePrimes()
	{
		JDialog dPrimes = new JDialog(this, "Prime Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(new Color(52, 0, 0));
		dPrimes.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("Number of Primes to Generate");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);
		
		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);
		
		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);
		
		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);
		
		dPrimes.add(pnlGenerate, gbcDialog);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		BigInteger start = new BigInteger(tfStart.getText());
      		int count = Integer.parseInt(tfCount.getText());
       		m_Primes.generatePrimes(start, count);
       		lblStatus.setText("Status: Excited. Primes have been generated.");
       		updateStats();
      		dPrimes.dispose();
      	}
      	catch(NumberFormatException ex)
      	{
      		lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
      		dPrimes.dispose();
      	}
      	
      }
    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	dPrimes.dispose();
      }
    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);		
		
		gbcDialog.gridy = 1;
		dPrimes.add(pnlButtons, gbcDialog);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);
		
		gbcDialog.gridy = 2;
		dPrimes.add(pnlStatus, gbcDialog);
		
		dPrimes.setSize(200,200);
		dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dPrimes.setVisible(true);		
	}

	// This function updates all the GUI statistics. (# of primes, # of crosses, etc)
	private void updateStats()
	{
 	}

}
