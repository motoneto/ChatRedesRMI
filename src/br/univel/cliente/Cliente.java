package br.univel.cliente;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.univel.comum.ICliente;
import br.univel.comum.ServicoRMI;

public class Cliente extends JFrame implements ICliente {
	public Cliente() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 40, 216, 77, 86, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 23, 208, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblServidor = new JLabel("Servidor");
		GridBagConstraints gbc_lblServidor = new GridBagConstraints();
		gbc_lblServidor.anchor = GridBagConstraints.EAST;
		gbc_lblServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblServidor.gridx = 0;
		gbc_lblServidor.gridy = 0;
		getContentPane().add(lblServidor, gbc_lblServidor);

		textFieldServer = new JTextField();
		textFieldServer.setText("localhost");
		GridBagConstraints gbc_textFieldServer = new GridBagConstraints();
		gbc_textFieldServer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldServer.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldServer.gridx = 1;
		gbc_textFieldServer.gridy = 0;
		getContentPane().add(textFieldServer, gbc_textFieldServer);
		textFieldServer.setColumns(10);

		JLabel lblPorta = new JLabel("Porta");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 2;
		gbc_lblPorta.gridy = 0;
		getContentPane().add(lblPorta, gbc_lblPorta);

		textFieldPorta = new JTextField();
		textFieldPorta.setText("1818");
		GridBagConstraints gbc_textFieldPorta = new GridBagConstraints();
		gbc_textFieldPorta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPorta.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPorta.gridx = 3;
		gbc_textFieldPorta.gridy = 0;
		getContentPane().add(textFieldPorta, gbc_textFieldPorta);
		textFieldPorta.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		getContentPane().add(lblNome, gbc_lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setText("Teste ");
		GridBagConstraints gbc_textFieldNome = new GridBagConstraints();
		gbc_textFieldNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNome.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNome.gridx = 1;
		gbc_textFieldNome.gridy = 1;
		getContentPane().add(textFieldNome, gbc_textFieldNome);
		textFieldNome.setColumns(10);

		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				conectar();
			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConectar.gridx = 2;
		gbc_btnConectar.gridy = 1;
		getContentPane().add(btnConectar, gbc_btnConectar);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desconectar();
			}
		});
		btnSair.setEnabled(false);
		GridBagConstraints gbc_btnSair = new GridBagConstraints();
		gbc_btnSair.insets = new Insets(0, 0, 5, 0);
		gbc_btnSair.gridx = 3;
		gbc_btnSair.gridy = 1;
		getContentPane().add(btnSair, gbc_btnSair);

		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridwidth = 4;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 2;
		getContentPane().add(splitPane, gbc_splitPane);

		JScrollPane sPLista = new JScrollPane();
		splitPane.setLeftComponent(sPLista);

		textAreaLista = new JTextArea();
		textAreaLista.setEditable(false);
		sPLista.setViewportView(textAreaLista);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);

		JScrollPane sPChat = new JScrollPane();
		splitPane_1.setLeftComponent(sPChat);

		textAreaChat = new JTextArea();
		textAreaChat.setEditable(false);
		textAreaChat.setWrapStyleWord(true);
		sPChat.setViewportView(textAreaChat);

		JPanel panel = new JPanel();
		splitPane_1.setRightComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		textFieldMensagem = new JTextField();
		GridBagConstraints gbc_textFieldMensagem = new GridBagConstraints();
		gbc_textFieldMensagem.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldMensagem.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMensagem.gridx = 0;
		gbc_textFieldMensagem.gridy = 0;
		panel.add(textFieldMensagem, gbc_textFieldMensagem);
		textFieldMensagem.setColumns(10);

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldMensagem.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nada foi digitado");
				} else {
					enviarMensagem();
					textFieldMensagem.setText("");
				}

			}
		});
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.gridx = 1;
		gbc_btnEnviar.gridy = 0;
		panel.add(btnEnviar, gbc_btnEnviar);
		splitPane_1.setDividerLocation(170);
		splitPane.setDividerLocation(120);

		setSize(490, 310);
		setVisible(true);
	}

	private ServicoRMI startRMI(int porta, String servidor) {
		Registry registry;
		ServicoRMI servico = null;
		try {
			registry = LocateRegistry.getRegistry(servidor, porta);
			servico = (ServicoRMI) registry.lookup(ServicoRMI.NOME);
		} catch (Exception e) {
			System.err.println("\n\n-------------------------------------------------------\n"
					+ "ERRO: VERIFIQUE SE O SERVIDOR ESTÃ� RODANDO, SE O IP E PORTA ESTÃƒO"
					+ " CORRETOS, SE NÃƒO HÃ� BLOQUEIO DE FIREWALL OU ANTIVIRUS.\n"
					+ "-------------------------------------------------------------------\n\n");
			e.printStackTrace();
		}
		return servico;
	}

	private void conectar() {

		remetente = textFieldNome.getText();
		server = textFieldServer.getText();
		porta = Integer.parseInt(textFieldPorta.getText());

		servico = startRMI(porta, server);
		try {
			servico.registrar(remetente, this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		textFieldNome.setEnabled(false);
		textFieldPorta.setEnabled(false);
		textFieldServer.setEnabled(false);
		btnConectar.setEnabled(false);
		btnSair.setEnabled(true);
	}

	private void desconectar() {
		try {
			servico.sair(remetente, this);
			textFieldNome.setEnabled(true);
			textFieldPorta.setEnabled(true);
			textFieldServer.setEnabled(true);
			btnConectar.setEnabled(true);
			btnSair.setEnabled(false);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void enviarMensagem() {
		try {
			String mensagem = textFieldMensagem.getText();
			servico.enviarMensagem(remetente, mensagem);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -9023378386367829218L;
	private JTextField textFieldServer;
	private JTextField textFieldPorta;
	private JTextField textFieldNome;
	private JTextField textFieldMensagem;
	private JButton btnSair;
	private JButton btnConectar;
	private JButton btnEnviar;
	private JTextArea textAreaChat;
	private JTextArea textAreaLista;
	private String remetente = null;
	private int porta = 0;
	private String server = null;

	private ServicoRMI servico = null;

	@Override
	public void receberMensagem(String remetente, String mensagem) throws RemoteException {

		Date data = new Date();

		String mensagemFinal = "(" + data + ")" + remetente + " diz: " + mensagem;
		textAreaChat.append(mensagemFinal);

		System.out.println("CLIENTE ->> (" + data + ")" + remetente + " diz: " + mensagem);
	}

	public void atualizaLista(List<String> lista) throws RemoteException {

		lista.forEach(e -> {
			 textAreaLista.append(e);
			System.out.println("LISTA ->> "+e);
		});

	}

	@Override
	public void reportError(String mensagem) throws RemoteException {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public static void main(String[] args) {
		new Cliente();
	}

}
