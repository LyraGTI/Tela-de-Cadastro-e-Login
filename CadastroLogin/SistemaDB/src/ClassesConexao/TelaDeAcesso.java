package ClassesConexao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class TelaDeAcesso extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeAcesso frame = new TelaDeAcesso();
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
	public TelaDeAcesso() {
		setResizable(false);
		setTitle("Tela de Acesso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seu Usuario:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 46, 92, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Sua senha:");
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSenha.setBounds(10, 92, 92, 36);
		contentPane.add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUsuario.setBounds(126, 56, 155, 19);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setForeground(Color.RED);
		pfSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		pfSenha.setBounds(126, 102, 155, 19);
		contentPane.add(pfSenha);
		//AQUI MOSTRA A AÇÃO DO BOTÃO ENTRAR//
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// BOTÃO DE ENTRAR E SUAS CONFIGS//
			
					
					try {
						Connection con = Conexao.faz_conexao();
						
						String sql = "select * from dados_senhas where usuario=? and senha=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setString(1, tfUsuario.getText());
						stmt.setString(2, new String (pfSenha.getPassword()));
						
						ResultSet rs = stmt.executeQuery();
						
						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "Acesso Permitido");
							InfoCliente exibir = new InfoCliente();
							exibir.setVisible(true);
							setVisible(false);
						}else {
							JOptionPane.showMessageDialog(null, "Usuário/Senha não identificados, crie sua conta a seguir.");
							TelaCadastro exibir = new TelaCadastro();
							exibir.setVisible(true);
							setVisible(false);
							
						}
						
						stmt.close();
						con.close(); 
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				
				
			}
		});
		btnNewButton.setForeground(new Color(128, 128, 255));
		btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		btnNewButton.setBounds(126, 149, 155, 50);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "N\u00E3o possui conta?", TitledBorder.LEFT, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(10, 209, 361, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCadastre = new JButton("Cadastre-se");
		btnCadastre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastro exibir = new TelaCadastro();
				exibir.setVisible(true);
				setVisible(false);
				
			}
		});
		btnCadastre.setBounds(116, 10, 157, 48);
		panel.add(btnCadastre);
	}
}
