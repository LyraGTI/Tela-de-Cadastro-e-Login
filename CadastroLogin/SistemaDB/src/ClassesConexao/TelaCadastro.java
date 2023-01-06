package ClassesConexao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaCadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfUsuario;
	private JPasswordField tfSenha;
	private JTextField tfCPF;
	private JTextField tfEndereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
		setResizable(false);
		setTitle("Novo Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 27, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 69, 65, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 102, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(107, 27, 96, 19);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfUsuario = new JTextField();
		tfUsuario.setColumns(10);
		tfUsuario.setBounds(106, 68, 123, 19);
		contentPane.add(tfUsuario);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ac\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 357, 443, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Arg0) {
			
				try {
					
					
					Connection con = Conexao.faz_conexao();
					String sql = "insert into dados_senhas(usuario,senha,cpf,endereco,Linguagem) values (?, ?, ?, ?,?)";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, tfSenha.getText());
					stmt.setString(3, tfCPF.getText());
					stmt.setString(4, tfEndereco.getText());
					
					
					
					
					stmt.execute();
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Usuário cadastrado! Agora você pode fazer o login de novo.");
					TelaDeAcesso exibir = new TelaDeAcesso();
					exibir.setVisible(true);
					setVisible(false);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			}
		});
		btnNewButton.setBounds(124, 10, 178, 66);
		panel.add(btnNewButton);
		
		tfSenha = new JPasswordField();
		tfSenha.setBackground(Color.WHITE);
		tfSenha.setBounds(107, 101, 122, 19);
		contentPane.add(tfSenha);
		
		JLabel lblNewLabel_2_1 = new JLabel("CPF");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 136, 45, 13);
		contentPane.add(lblNewLabel_2_1);
		
		tfCPF = new JTextField();
		tfCPF.setBounds(107, 135, 123, 19);
		contentPane.add(tfCPF);
		tfCPF.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaDeAcesso exibir = new TelaDeAcesso();
				exibir.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setBackground(Color.GRAY);
		btnVoltar.setForeground(Color.BLUE);
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVoltar.setBounds(353, 10, 65, 13);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel_3 = new JLabel("* Campo Obrigatório");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(247, 104, 122, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Endereço");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 166, 65, 13);
		contentPane.add(lblNewLabel_4);
		
		tfEndereco = new JTextField();
		tfEndereco.setBounds(107, 164, 346, 19);
		contentPane.add(tfEndereco);
		tfEndereco.setColumns(10);
	}
}
