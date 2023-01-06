package ClassesConexao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

public class InfoCliente extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JTextField tfBusca;
	private JTextField tfCPF;
	private JTextField tfEndereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoCliente frame = new InfoCliente();
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
	public InfoCliente() {
		setTitle("Dados dos Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelUsuario = new JLabel("Usuario");
		LabelUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelUsuario.setBounds(48, 186, 59, 45);
		contentPane.add(LabelUsuario);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(141, 201, 96, 19);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Buscar Informa\u00E7\u00F5es de Clientes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(48, 44, 451, 79);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Arg0) {
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "select * from dados_senhas where usuario like ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, "%"+tfBusca.getText());
					ResultSet rs = stmt.executeQuery();
					
					if (rs.next()) {
						
						tfUsuario.setText(rs.getString("usuario"));
						tfCPF.setText(rs.getString("CPF"));
						tfEndereco.setText(rs.getString("Endereco"));
						JOptionPane.showMessageDialog(null, "ID Localizado!");
						tfUsuario.setText("");
						tfCPF.setText("");
						tfEndereco.setText("");
					}else {
						
						JOptionPane.showMessageDialog(null, "ID não localizado.");
						
					}
					
					
					rs.close();
					con.close();
					
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ID Não existente.");
					e.printStackTrace();
					
				}

				
			}
		});
		btnProcurar.setBounds(176, 48, 101, 21);
		panel.add(btnProcurar);
		
		tfBusca = new JTextField();
		tfBusca.setBounds(176, 19, 96, 19);
		panel.add(tfBusca);
		tfBusca.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(48, 241, 45, 13);
		contentPane.add(lblNewLabel);
		
		tfCPF = new JTextField();
		tfCPF.setBounds(141, 240, 96, 19);
		contentPane.add(tfCPF);
		tfCPF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Endereço");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(48, 280, 73, 13);
		contentPane.add(lblNewLabel_1);
		
		tfEndereco = new JTextField();
		tfEndereco.setBounds(141, 279, 337, 19);
		contentPane.add(tfEndereco);
		tfEndereco.setColumns(10);
		
		JButton btnVoltarAoLogin = new JButton("Voltar ao Login");
		btnVoltarAoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaDeAcesso exibir = new TelaDeAcesso();
				exibir.setVisible(true);
				setVisible(false);
				
			}
		});
		btnVoltarAoLogin.setBounds(438, 375, 148, 41);
		contentPane.add(btnVoltarAoLogin);
		
	}

}
