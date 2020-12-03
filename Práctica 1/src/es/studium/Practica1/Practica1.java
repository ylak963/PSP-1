package es.studium.Practica1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;

public class Practica1 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCmd;
	private JTextArea txtAreaResultado;
	private JButton btnBlocDeNotas;
	private JButton btnPaint;
	private JButton btnGestion;
	private JButton btnJuego;
	private JButton btnEjecutarCMD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Practica1 frame = new Practica1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Practica1() {
		setTitle("Pr\u00E1ctica PSP Tema 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtCmd = new JTextField();
		txtCmd.setText("cmd");
		txtCmd.setBounds(22, 23, 86, 20);
		contentPane.add(txtCmd);
		txtCmd.setColumns(10);

		btnEjecutarCMD = new JButton("Ejecutar");
		btnEjecutarCMD.setBounds(118, 22, 89, 23);
		contentPane.add(btnEjecutarCMD);

		txtAreaResultado = new JTextArea();
		JScrollPane scroll = new JScrollPane(txtAreaResultado);
		scroll.setBounds(20, 50, 190, 200);
		;
		contentPane.add(scroll);

		// Indicamos que no se pueda escribir en el textArea
		txtAreaResultado.setEditable(false);

		btnBlocDeNotas = new JButton("Bloc de Notas");
		btnBlocDeNotas.setBounds(242, 22, 130, 23);
		contentPane.add(btnBlocDeNotas);

		btnPaint = new JButton("Paint");
		btnPaint.setBounds(382, 22, 89, 23);
		contentPane.add(btnPaint);

		btnGestion = new JButton("Gesti\u00F3n");
		btnGestion.setBounds(481, 22, 89, 23);
		contentPane.add(btnGestion);

		btnJuego = new JButton("Juego");
		btnJuego.setBounds(585, 22, 89, 23);
		contentPane.add(btnJuego);

		JLabel lblProcesosActivos = new JLabel("Procesos activos");
		lblProcesosActivos.setBounds(425, 59, 100, 14);
		contentPane.add(lblProcesosActivos);

		JButton btnTerminar = new JButton("Terminar");
		btnTerminar.setBounds(422, 254, 89, 23);
		contentPane.add(btnTerminar);

		JList list = new JList();
		list.setBounds(277, 89, 374, 154);
		contentPane.add(list);
		setLocationRelativeTo(null);

		// Listener de los botones
		btnEjecutarCMD.addActionListener(this);
		btnBlocDeNotas.addActionListener(this);
		btnPaint.addActionListener(this);
		btnGestion.addActionListener(this);
		btnJuego.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEjecutarCMD)) {
			try {
				// Guardamos el texto capturado del textField
				String instruccion = txtCmd.getText();
				// Ejecutamos la instrucción guardada con el cmd
				Process process = Runtime.getRuntime().exec("cmd /c " + instruccion);
				// Flujo de entrada para la lectura del objeto process
				InputStreamReader inputSr = new InputStreamReader(process.getInputStream());
				BufferedReader br = new BufferedReader(inputSr);
				String salida = null;
				// Mientras haya algo en la salida
				while ((salida = br.readLine()) != null) {
					// Añadimos en el textarea el resultado
					txtAreaResultado.append(salida + "\n");
				}

			} catch (IOException ex) {
				System.out.println(ex);
			}
		}

		if (e.getSource().equals(btnBlocDeNotas)) {
			try {
				Process process = Runtime.getRuntime().exec("notepad.exe");
				btnBlocDeNotas.setEnabled(false);

			} catch (IOException ex) {
				System.out.println("Error");
			}
		}
		if (e.getSource().equals(btnPaint)) {
			try {
				Process process = Runtime.getRuntime().exec("mspaint.exe");
				// Anular el botón una vez pulsado
				btnPaint.setEnabled(false);

			} catch (IOException ex) {
				System.out.println("Error");
			}
		}

		if (e.getSource().equals(btnGestion)) {
			try {
				String arg1 = "java";
				String arg2 = "-jar";
				String arg3 = "C:\\Users\\leyen\\Desktop\\DAMSEGUNDO\\PSP\\Prácticas\\Práctica 1\\Ejecutables\\Gestion.jar";
				String[] instruccion = { arg1, arg2, arg3 };
				Process process = Runtime.getRuntime().exec(instruccion);
				// Anular el botón una vez pulsado
				btnGestion.setEnabled(false);
			} catch (IOException ex) {
				System.out.println("Error");
			}
		}
		if (e.getSource().equals(btnJuego)) {
			try {
				// La ruta del ejecutable damas en formato cmd
				String arg1 = "java";
				String arg2 = "-jar";
				String arg3 = "C:\\Users\\leyen\\Desktop\\DAMSEGUNDO\\PSP\\Prácticas\\Práctica 1\\Ejecutables\\Damas.jar";
				String[] instruccion = { arg1, arg2, arg3 };

				Process process = Runtime.getRuntime().exec(instruccion);
				// Anular el botón una vez pulsado
				btnJuego.setEnabled(false);

			} catch (IOException ex) {
				System.out.println("Error");
			}
		}

	}
}
