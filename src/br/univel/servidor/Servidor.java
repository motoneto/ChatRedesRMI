package br.univel.servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import br.univel.comum.ICliente;
import br.univel.comum.ServicoRMI;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Servidor extends JFrame implements ServicoRMI, Runnable{
	
	private Thread thread;
	private Servidor server;
	
	public Servidor() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread = new Thread(server);
				thread.start();
				btnStop.setEnabled(true);
				btnStart.setEnabled(false);
				textArea.append("servidor startado\n");
			}
		});
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 0;
		getContentPane().add(btnStart, gbc_btnStart);
		
		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				thread.stop();
				btnStop.setEnabled(false);
				btnStart.setEnabled(true);
				textArea.append("servidor stopado\n");
			}
		});
		btnStop.setEnabled(false);
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 5, 5);
		gbc_btnStop.gridx = 1;
		gbc_btnStop.gridy = 0;
		getContentPane().add(btnStop, gbc_btnStop);
		
		textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 3;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 1;
		getContentPane().add(textArea, gbc_textArea);
		setSize(300, 700);
		setVisible(true);
		server = this;
	}

	Map<String, ICliente> listaOnline = new HashMap<String, ICliente>();
	private JTextArea textArea;
	private JButton btnStart;
	private JButton btnStop;
	
	@Override
	public void registrar(String nome, ICliente cliente) throws RemoteException {
		Date data = new Date();
		
		List<String> lista = new ArrayList<>();
		if(!listaOnline.containsKey(nome)){
			listaOnline.put(nome, cliente);			
			listaOnline.values().forEach(e->{
				try {
					listaOnline.keySet().forEach(h ->{lista.add(h);});
					e.atualizaLista(lista);
					e.receberMensagem("servidor", nome+" se conectou.");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			});
			textArea.append(data+"-"+nome+" se conectou;\n");
		}else{
			cliente.reportError("Nome de usuário já cadastrado");
		}
	}

	@Override
	public void enviarMensagem(String remetente, String mensagem) throws RemoteException {
		Date data = new Date();
		listaOnline.values().forEach(e->{
			try {
				e.receberMensagem(remetente, mensagem);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		});
		textArea.append(data+"-"+remetente+" diz: "+mensagem+"\n");
	}

	@Override
	public void sair(String nome, ICliente cliente) throws RemoteException {
		Date data = new Date();
		List<String> lista = new ArrayList<>();
		
		if(listaOnline.containsKey(nome)){
			listaOnline.remove(nome);			
			listaOnline.values().forEach(e->{
				try {
					listaOnline.keySet().forEach(h ->{lista.add(h);});
					e.atualizaLista(lista);
					e.receberMensagem("servidor", nome+" se desconectou.");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				textArea.append(data+"-"+nome+" se desconectou;\n");
			});
		}else{
			cliente.reportError("Nome de usuário já cadastrado");
		}
	}

	@Override
	public void run() {
		final int PORTA_TCPIP = 1818;
		ServicoRMI servico;

			try {
			    textArea.append("Iniciando servidor\n");
				servico = (ServicoRMI) UnicastRemoteObject.exportObject(Servidor.this, 0);
				Registry registry = LocateRegistry.createRegistry(PORTA_TCPIP);
				registry.rebind(ServicoRMI.NOME, servico);
				textArea.append("Aguardando Conexôes\n");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		new Servidor();
	}
}
