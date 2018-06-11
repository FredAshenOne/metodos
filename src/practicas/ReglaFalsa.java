package practicas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ReglaFalsa extends JFrame implements ActionListener,MouseListener{

	private JPanel contentPane;
	JButton btnBack,btnInicio;
	Style s = new Style();
	JTextField txtValue1,txtValue2,txtHelp;
	JComboBox cbTolerancia;
	double limitA,limitB,valorAnterior,errorAbsoluto,valorActual,condition;
	JLabel lblValue1,lblValue2,lblHelp,lblWarning;
	private JTable tbBiseccion;
	TextPrompt tpVal1;
	public ReglaFalsa() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 820, 427);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		s.mdPanel(mainPanel);
		
		btnBack = new JButton("");
		btnBack.setBounds(10, 11, 24, 24);
		mainPanel.add(btnBack);
		s.btnIcon(btnBack, "views/back1.png");
		btnBack.addMouseListener(this);
		
		JLabel lblHeader = new JLabel("Regla Falsa");
		lblHeader.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(44, 11, 766, 24);
		mainPanel.add(lblHeader);
		
		JPanel setPane = new JPanel();
		setPane.setBounds(10, 47, 156, 369);
		mainPanel.add(setPane);
		setPane.setLayout(null);
		s.mdPanel(setPane);
		
		cbTolerancia = new JComboBox();
		cbTolerancia.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(cbTolerancia.getSelectedIndex() == 0 ) {
						lblHelp.setText("Numero de iteraciones");
						enableFields(true);
					}else if(cbTolerancia.getSelectedIndex() == 1) {
						lblHelp.setText("Resultado de funcion");
						enableFields(false);
					}else if(cbTolerancia.getSelectedIndex() == 2) {
						lblHelp.setText("Error esperado");
						enableFields(true);
					}
				}
			}
		});
		cbTolerancia.setModel(new DefaultComboBoxModel(new String[] {"Iteracion", "Funcion", "Error Relativo"}));
		cbTolerancia.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		cbTolerancia.setBounds(10, 29, 136, 28);
		setPane.add(cbTolerancia);
		s.mdCombo(cbTolerancia);
		
		JLabel lblTolerancia = new JLabel("Tolerancia");
		lblTolerancia.setLabelFor(cbTolerancia);
		lblTolerancia.setHorizontalAlignment(SwingConstants.CENTER);
		lblTolerancia.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		lblTolerancia.setBounds(10, 4, 136, 14);
		setPane.add(lblTolerancia);
		
		txtValue1 = new JTextField();
		txtValue1.setHorizontalAlignment(SwingConstants.CENTER);
		txtValue1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		txtValue1.setBounds(10, 154, 136, 43);
		setPane.add(txtValue1);
		txtValue1.setColumns(10);
		s.comBorder(txtValue1, Color.LIGHT_GRAY);
		s.setPadding(txtValue1);
		
		tpVal1 = new TextPrompt("---",txtValue1);
		tpVal1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		tpVal1.setHorizontalAlignment(SwingConstants.CENTER);
		tpVal1.setForeground(Color.LIGHT_GRAY);
		
		txtValue2 = new JTextField();
		txtValue2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		txtValue2.setHorizontalAlignment(SwingConstants.CENTER);
		txtValue2.setColumns(10);
		txtValue2.setBounds(10, 239, 136, 43);
		setPane.add(txtValue2);
		s.comBorder(txtValue2, Color.LIGHT_GRAY);
		s.setPadding(txtValue2);
		
		TextPrompt tpVal2 = new TextPrompt("---",txtValue2);
		tpVal2.setForeground(Color.LIGHT_GRAY);
		tpVal2.setHorizontalAlignment(SwingConstants.CENTER);
		tpVal2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		
		lblValue1 = new JLabel("Valor 1");
		lblValue1.setHorizontalAlignment(SwingConstants.CENTER);
		lblValue1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		lblValue1.setBounds(10, 129, 136, 14);
		setPane.add(lblValue1);
		
		lblValue2 = new JLabel("Valor 2");
		lblValue2.setHorizontalAlignment(SwingConstants.CENTER);
		lblValue2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		lblValue2.setBounds(10, 214, 136, 14);
		setPane.add(lblValue2);
		
		btnInicio = new JButton("Iniciar");
		btnInicio.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		btnInicio.setBounds(10, 323, 136, 35);
		setPane.add(btnInicio);
		s.mdButton(btnInicio, Color.decode("#00E676"));
		
		txtHelp = new JTextField();
		txtHelp.setHorizontalAlignment(SwingConstants.CENTER);
		txtHelp.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		txtHelp.setBounds(10, 83, 136, 35);
		setPane.add(txtHelp);
		txtHelp.setColumns(10);
		s.comBorder(txtHelp, Color.LIGHT_GRAY);
		
		TextPrompt tpHelp = new TextPrompt("---",txtHelp);
		tpHelp.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		tpHelp.setHorizontalAlignment(SwingConstants.CENTER);
		tpHelp.setForeground(Color.LIGHT_GRAY);
		
		lblHelp = new JLabel("Numero de iteraciones");
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		lblHelp.setBounds(10, 58, 136, 14);
		setPane.add(lblHelp);
		
		lblWarning = new JLabel("");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		lblWarning.setBounds(10, 298, 136, 14);
		setPane.add(lblWarning);
		
		JScrollPane spTablaBiseccion = new JScrollPane();
		spTablaBiseccion.setBounds(177, 46, 633, 370);
		mainPanel.add(spTablaBiseccion);
		
		tbBiseccion = new JTable();
		tbBiseccion.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Iteracion","Limite A","Limite B", "Raiz","Error Relativo"
				}
			));
		spTablaBiseccion.setViewportView(tbBiseccion);
		btnInicio.addMouseListener(this);
		btnInicio.addActionListener(this);
		s.mdScrollPane(spTablaBiseccion, tbBiseccion);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInicio) {
			fillTable();
		}		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == btnBack) {
			s.btnPointer(btnBack);
		}else if(e.getSource() == btnInicio) {
			if(btnInicio.isEnabled()) {
				s.btnPointer(btnInicio);
				s.btnHover(btnInicio, Color.decode("#00E676"), Color.WHITE, Color.decode("#00E676"));
			}
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == btnInicio) {
			s.mdButton(btnInicio, Color.decode("#00E676"));
		}
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	
	
	public void setLimitA(double  limitA) {
		this.limitA = limitA;
	}

	public double getLimitA() {
		return this.limitA;
	}
	
	public void setLimitB(double limitB) {
		this.limitB = limitB;
	}
	
	public double getLimitB() {
		return this.limitB;
	}
	

	public double getErrorAbsoluto() {
		return this.errorAbsoluto;
	}
	public void setErrorAbsoluto(double errorAbsoluto) {
		this.errorAbsoluto = errorAbsoluto;
	}
	

	public double Exp(double x) {
		return Math.exp(-x)-Math.log(x);
	}

	public void fillTable() {
		valorActual= 0;
		
		setLimitA(Integer.parseInt(txtValue1.getText()));
		setLimitB(Integer.parseInt(txtValue2.getText()));
		DefaultTableModel model = (DefaultTableModel) tbBiseccion.getModel();
		model.setRowCount(0);
		
		switch(cbTolerancia.getSelectedIndex()) {
		
			case 0:
				
				if(isInt(txtHelp.getText())) {
					if(isInt(txtValue1.getText())&&isInt(txtValue2.getText())) {
						orderLimits();
						if(!isNotSqrt(getLimitA(),getLimitB())) {							
							int iteraciones = Integer.parseInt(txtHelp.getText());
							for (int i = 0; i < iteraciones;i++) {								
								valorActual = getLimitB()-(Exp(getLimitB()) * (getLimitA() - getLimitB()) / (Exp(getLimitA()) - Exp(getLimitB())));								
								errorAbsoluto = Math.abs((valorActual - valorAnterior)/valorActual)*100;
								model.addRow(new Object[] { 
										i+1,getLimitA(),getLimitB(),Exp(valorActual),errorAbsoluto
								});
								valorAnterior = valorActual;								
								if(Exp(getLimitA()) * Exp(valorActual) < 0) {
									setLimitB(valorActual);
								}else if(Exp(getLimitA()) * Exp(valorActual) > 0) {
									setLimitA(valorActual);
								}else if(Exp(getLimitA()) * Exp(valorActual) == 0){
									errorAbsoluto = 0;
								}
								lblWarning.setText("");							
							}
							tbBiseccion.setModel(model);
						}else {
							lblWarning.setText("El intervalo no tiene Raiz");
						}
					}else{
						lblWarning.setText("Formato de limites invalido");
						lblWarning.setForeground(Color.red);
					}
				}else {
					lblWarning.setText("Formato de iteracion invalido");
					lblWarning.setForeground(Color.red);
				}
				break;
			
			case 2:
				int i = 0;
				if(isDouble(txtHelp.getText())) {
					if(isInt(txtValue1.getText())&&isInt(txtValue2.getText())) {
						orderLimits();
						if(!isNotSqrt(getLimitA(),getLimitB())) {							
							double errorMin= Double.parseDouble(txtHelp.getText());
							do {
								i++;
								valorActual = getLimitB()-(Exp(getLimitB()) * (getLimitA() - getLimitB()) / (Exp(getLimitA()) - Exp(getLimitB())));								
								errorAbsoluto = Math.abs((valorActual - valorAnterior)/valorActual)*100;
								model.addRow(new Object[] { 
										i+1,getLimitA(),getLimitB(),Exp(valorActual),errorAbsoluto
								});
								
								System.out.println(Exp(valorActual));
								valorAnterior = valorActual;								
								if(Exp(getLimitA()) * Exp(valorActual) < 0) {
									setLimitB(valorActual);
								}else if(Exp(getLimitA()) * Exp(valorActual) > 0) {
									setLimitA(valorActual);
								}else if(Exp(getLimitA()) * Exp(valorActual) == 0){
									errorAbsoluto = 0;
								}							
								lblWarning.setText("");
							}while(errorMin < errorAbsoluto);
							tbBiseccion.setModel(model);
						}else {
							lblWarning.setText("El intervalo no tiene Raiz");
						}
					}else{
						lblWarning.setText("Formato de limites invalido");
						lblWarning.setForeground(Color.red);
					}
				}else {
					lblWarning.setText("Formato de error minimo invalido");
					lblWarning.setForeground(Color.red);
				}
				break;
			
		}
		
	}
	

	public boolean isDouble(String txt) {
		Pattern p = Pattern.compile("[+-]?([0-9]*[.])?[0-9]+");
		Matcher m = p.matcher(txt);
		return m.matches();
	}
	
	public boolean isInt(String txt) {
		Pattern p = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
		Matcher m = p.matcher(txt);
		return m.matches();
	}
	
	public void orderLimits() {
		if(getLimitA()>getLimitB()) {
			double a = getLimitA();
			setLimitA(getLimitB());
			setLimitB(a);
		}
	}
	
	
	
	public boolean isNotSqrt(double limita,double limitn) {
		return Exp(limita)* Exp(limitn)>0;
	}
	
	public void enableFields(boolean f) {
		txtHelp.setEnabled(f);
		txtValue1.setEnabled(f);
		txtValue2.setEnabled(f);
		btnInicio.setEnabled(f);
		tpVal1.setEnabled(f);
		
	}
}
