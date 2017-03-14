package br.univel.comum;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface para acesso remoto. Toda interface usada com RMI deve estender de
 * {@link java.rmi.Remote}.
 * 
 * @author Fernando D'Agostini
 *
 */
public interface ServicoRMI extends Remote {

	/**
	 * Constante com o nome do servi�o apenas para conveni�ncia, evitar erros de
	 * digita��o.
	 */
	public static final String NOME = "SERVICO_RMI";

	/**
	 * M�todo que recebe o nome da pessoa e sua instancia de Cliente para registro no servidor.
	 * 
	 * Todo m�todo de uma interface usada com RMI deve declarar que pode lan�ar
	 * {@link java.rmi.RemoteException}.
	 * 
	 * @param nome
	 *            Nome da pessoa.
	 * @param cliente
	 * 			  Inst�ncia de ICliente
	 * @throws RemoteException
	 */
	public void registrar(String nome, ICliente cliente) throws RemoteException;
	
	/**
	 * M�todo para envio de Mensagem a todos os conectados com o servi�o de Chat
	 * @param remetente
	 * @param mensagem
	 * @throws RemoteException
	 */
	public void enviarMensagem(String remetente, String mensagem) throws RemoteException;
	
	/**
	 * M�todo para remover um determinado cliente da listaOnline.
	 * @param nome
	 * @param cliente
	 * @throws RemoteException
	 */
	public void sair(String nome, ICliente cliente) throws RemoteException;

}
