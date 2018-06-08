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
	double limitA,limitB,valorProbableAnterior,errorAbsoluto;
	JLabel lblValue1,lblValue2,lblHelp,lblWarning;
	private JTable tbBiseccion;
	public ReglaFalsa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 466);
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
		
		JLabel lblHeader = new JLabel("Biseccion");
		lblHeader.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(44, 11, 448, 24);
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
						
					}else if(cbTolerancia.getSelectedIndex() == 1) {
						lblHelp.setText("Resultado de funcion");
					}else if(cbTolerancia.getSelectedIndex() == 2) {
						lblHelp.setText("Error esperado");
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
		
		TextPrompt tpVal1 = new TextPrompt("---",txtValue1);
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
						"Iteracion","Limite A","Limite B", "Raiz","Error Relativo","Error Porcentual","Error Absoluto"
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
			s.btnPointer(btnInicio);
			s.btnHover(btnInicio, Color.decode("#00E676"), Color.WHITE, Color.decode("#00E676"));
			
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
	
	public double getValorProbable() {
		return getLimitB()-((getFunctionExp(getLimitB())*(getLimitA()-getLimitB()))/(getFunctionExp(getLimitA())-getFunctionExp(getLimitB())));	
	}
	
	public double getErrorRelativo() {
		return getErrorAbsoluto() / getValorProbable();
	}
	
	public double getFuncion() {
		return (getValorProbable() * Math.sin(getValorProbable())) -1;
	}
	
	public void setValorProbableAnterior(double valorProbableAnterior) {
		this.valorProbableAnterior = valorProbableAnterior;
	}
	
	public double getValorProbableAnterior() {
		return this.valorProbableAnterior;
	}
	
	public double getErrorAbsoluto() {
		return this.errorAbsoluto;
	}
	public void setErrorAbsoluto(double errorAbsoluto) {
		this.errorAbsoluto = errorAbsoluto;
	}
	
	public double getErrorPorcentual() {
		return getErrorRelativo() * 100;
	}
	
	public double getFunctionExp(double x) {
		return Math.exp(-x)-Math.log(x);
	}
	
	public void fillTable() {
		setValorProbableAnterior(0);
		
		setLimitA(Integer.parseInt(txtValue1.getText()));
		setLimitB(Integer.parseInt(txtValue2.getText()));
		DefaultTableModel model = (DefaultTableModel) tbBiseccion.getModel();
		model.setRowCount(0);
		System.out.println(cbTolerancia.getSelectedIndex()+" seleccionado");
		
		switch(cbTolerancia.getSelectedIndex()) {
		
			case 0:
				if(isInt(txtHelp.getText())) {
					if(isInt(txtValue1.getText())&&isInt(txtValue2.getText())) {
						orderLimits();
						if(!isNotSqrt()) {
							int iteraciones = Integer.parseInt(txtHelp.getText());
							for(int i =0 ; i < iteraciones;i++) {
								setErrorAbsoluto(Math.abs(getValorProbable() - getValorProbableAnterior()));
								
								model.addRow(new Object[] { 
										i+1,getLimitA(),getLimitB(),getValorProbable(),getErrorRelativo(),getErrorPorcentual(),getErrorPorcentual()
								});
								setValorProbableAnterior(getValorProbable());
								if(getFunctionExp(getLimitA()) *getFunctionExp(getLimitB())<0) {
									setLimitB(getValorProbable());
								}else if(getFunctionExp(getLimitA()) *getFunctionExp(getLimitB())==0) {
									setErrorAbsoluto(0);
								}else {
									setLimitA(getValorProbable());
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
				if(isInt(txtHelp.getText())||isDouble(txtHelp.getText())) {
					double errorEsp = Double.parseDouble(txtHelp.getText());
					
					if(isInt(txtValue1.getText())&&isInt(txtValue2.getText())) {
						orderLimits();
						
						do{
							i++;
							setErrorAbsoluto(Math.abs(getValorProbable() - getValorProbableAnterior()));
							
							model.addRow(new Object[] { 
									i+1,getLimitA(),getLimitB(),getValorProbable(),getErrorRelativo(),getErrorPorcentual(),getErrorPorcentual()
							});
							
							setValorProbableAnterior(getValorProbable());
							if(getFunctionExp(getLimitA()) *getFunctionExp(getLimitB())<0) {
								setLimitB(getValorProbable());
							}else if(getFunctionExp(getLimitA()) *getFunctionExp(getLimitB())==0) {
								setErrorAbsoluto(0);
							}else {
								setLimitA(getValorProbable());
							}
							lblWarning.setText("");
						}while(errorEsp < getErrorRelativo());
					}else{
						lblWarning.setText("Formato de limites invalido");
						lblWarning.setForeground(Color.red);
					}
				}else {
					lblWarning.setText("Fortmato de Error invalido");
					lblWarning.setForeground(Color.red);
				}
				tbBiseccion.setModel(model);
				break;
			case 1:
				
				int j = 0;
				if(isDouble(txtHelp.getText())||isInt(txtHelp.getText())) {
					double funcionEsp = Double.parseDouble(txtHelp.getText());
					if(isInt(txtValue1.getText())&&isInt(txtValue2.getText())) {
						do{
							j++;
							model.addRow(new Object[] { 
									j+1,getLimitA(),getLimitB(),getValorProbable(),getFuncion(),getErrorRelativo(),getErrorPorcentual(),getErrorPorcentual()
							});
							
							setValorProbableAnterior(getValorProbable());
							if(getLimitA()*getValorProbable() < 0) {
								setLimitB(getValorProbable());
							}else if(getLimitA()*getValorProbable()>0){
								setLimitA(getValorProbable());
							}else {
								setErrorAbsoluto(0);
							}
							lblWarning.setText("");
							
						}while(funcionEsp < getErrorRelativo());
					}else {
						lblWarning.setText("Formato de limites invalido");
						lblWarning.setForeground(Color.red);
					}
				}else {
					lblWarning.setText("Formato de Funcion invalido");
					lblWarning.setForeground(Color.red);
				}
				tbBiseccion.setModel(model);
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
	
	
	
	public boolean isNotSqrt() {
		return getFunctionExp(getLimitA())*getFunctionExp(getLimitB())>0;
	}
}
