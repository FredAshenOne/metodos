package practicas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class MainMenu extends JFrame implements ActionListener,MouseListener{

	private JPanel contentPane;
	private final JPanel mainPanel = new JPanel();
	Style s = new Style();
	JPanel pnBiseccion,pnSecante,pnReglaFalsa,pnPuntoFijo;
	JButton btnBiseccion,btnReglaFalsa,btnSecante,btnPuntoFijo;
	
	
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		mainPanel.setBounds(0, 0, 376, 186);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Metodos Numericos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 356, 37);
		mainPanel.add(lblNewLabel);
		s.mdPanel(mainPanel);
		
		pnBiseccion = new JPanel();
		pnBiseccion.setBounds(10, 65, 173, 49);
		mainPanel.add(pnBiseccion);
		pnBiseccion.setLayout(null);
		s.mdPanel(pnBiseccion);
		pnBiseccion.addMouseListener(this);
		
		JLabel lblIconBiseccion = new JLabel("");
		lblIconBiseccion.setBounds(0, 0, 49, 49);
		pnBiseccion.add(lblIconBiseccion);
		s.lblIcon(lblIconBiseccion, "views/biseccion.png");
		
		JLabel lblBiseccion = new JLabel("Biseccion");
		lblBiseccion.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		lblBiseccion.setBounds(59, 0, 114, 49);
		pnBiseccion.add(lblBiseccion);
		btnBiseccion.addActionListener(this);
		
		btnBiseccion = new JButton("");
		btnBiseccion.setBounds(0, 0, 173, 49);
		pnBiseccion.add(btnBiseccion);
		s.btnTransparent(btnBiseccion);
		btnBiseccion.addMouseListener(this);
		
		pnPuntoFijo = new JPanel();
		pnPuntoFijo.setBounds(10, 125, 173, 49);
		mainPanel.add(pnPuntoFijo);
		pnPuntoFijo.setLayout(null);
		s.mdPanel(pnPuntoFijo);
		pnPuntoFijo.addMouseListener(this);
		
		JLabel lblIconPuntoFijo = new JLabel("");
		lblIconPuntoFijo.setBounds(0, 0, 49, 49);
		pnPuntoFijo.add(lblIconPuntoFijo);
		s.lblIcon(lblIconPuntoFijo, "views/puntofijo.png");
		
		JLabel lblPuntoFijo = new JLabel("Punto Fijo");
		lblPuntoFijo.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		lblPuntoFijo.setBounds(59, 0, 114, 49);
		pnPuntoFijo.add(lblPuntoFijo);
		
		btnPuntoFijo = new JButton("");
		btnPuntoFijo.setBounds(0, 0, 173, 49);
		pnPuntoFijo.add(btnPuntoFijo);
		s.btnTransparent(btnPuntoFijo);
		btnPuntoFijo.addMouseListener(this);
		btnPuntoFijo.addActionListener(this);
		
		pnReglaFalsa = new JPanel();
		pnReglaFalsa.setBounds(193, 65, 173, 49);
		mainPanel.add(pnReglaFalsa);
		pnReglaFalsa.setLayout(null);
		s.mdPanel(pnReglaFalsa);
		pnReglaFalsa.addMouseListener(this);
		
		JLabel lblIconReglaFalsa = new JLabel("");
		lblIconReglaFalsa.setBounds(0, 0, 49, 49);
		pnReglaFalsa.add(lblIconReglaFalsa);
		
		JLabel lblReglaFalsa = new JLabel("Regla Falsa");
		lblReglaFalsa.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		lblReglaFalsa.setBounds(59, 0, 114, 49);
		pnReglaFalsa.add(lblReglaFalsa);
		s.lblIcon(lblIconReglaFalsa, "views/reglafalsa.png");
		
		btnReglaFalsa = new JButton("");
		btnReglaFalsa.setBounds(0, 0, 173, 49);
		pnReglaFalsa.add(btnReglaFalsa);
		s.btnTransparent(btnReglaFalsa);
		btnReglaFalsa.addMouseListener(this);
		btnReglaFalsa.addActionListener(this);
		pnSecante = new JPanel();
		pnSecante.setBounds(193, 125, 173, 49);
		mainPanel.add(pnSecante);
		pnSecante.setLayout(null);
		s.mdPanel(pnSecante);
		pnSecante.addMouseListener(this);
		
		JLabel lblIconSecante = new JLabel("");
		lblIconSecante.setBounds(0, 0, 49, 49);
		pnSecante.add(lblIconSecante);
		s.lblIcon(lblIconSecante,"views/secante.png");
		
		JLabel lblSecante = new JLabel("Secante");
		lblSecante.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		lblSecante.setBounds(59, 0, 114, 49);
		pnSecante.add(lblSecante);
		
		btnSecante = new JButton("");
		btnSecante.setBounds(0, 0, 173, 49);
		pnSecante.add(btnSecante);
		s.btnTransparent(btnSecante);
		btnSecante.addMouseListener(this);
		btnSecante.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == pnBiseccion || e.getSource() == btnBiseccion) {
			s.panelPointer(pnBiseccion);
			s.btnPointer(btnBiseccion);
		}else if(e.getSource() == pnReglaFalsa || e.getSource() == btnReglaFalsa) {
			s.panelPointer(pnReglaFalsa);
			s.btnPointer(btnReglaFalsa);
		}else if(e.getSource() == pnSecante || e.getSource() == btnSecante) {
			s.panelPointer(pnSecante);
			s.btnPointer(btnSecante);
		}else if(e.getSource() == pnPuntoFijo || e.getSource() ==  btnPuntoFijo) {
			s.panelPointer(pnPuntoFijo);
			s.btnPointer(btnPuntoFijo);
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == pnBiseccion || e.getSource() == btnBiseccion) {
			pnBiseccion.setBorder(null);
			btnBiseccion.setBorder(null);
		}else if(e.getSource() == pnReglaFalsa || e.getSource() == btnReglaFalsa) {
			pnReglaFalsa.setBorder(null);
			btnReglaFalsa.setBorder(null);
		}else if(e.getSource() == pnSecante || e.getSource() == btnSecante) {
			pnSecante.setBorder(null);
			btnSecante.setBorder(null);
		}else if(e.getSource() == pnPuntoFijo || e.getSource() ==  btnPuntoFijo) {
			pnPuntoFijo.setBorder(null);
			btnPuntoFijo.setBorder(null);
		}
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
