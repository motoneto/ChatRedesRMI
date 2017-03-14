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
	 * Constante com o nome do serviço apenas para conveniência, evitar erros de
	 * digitação.
	 */
	public static final String NOME = "SERVICO_RMI";

	/**
	 * Método que recebe o nome da pessoa e sua instancia de Cliente para registro no servidor.
	 * 
	 * Todo método de uma interface usada com RMI deve declarar que pode lançar
	 * {@link java.rmi.RemoteException}.
	 * 
	 * @param nome
	 *            Nome da pessoa.
	 * @param cliente
	 * 			  Instância de ICliente
	 * @throws RemoteException
	 */
	public void registrar(String nome, ICliente cliente) throws RemoteException;
	
	/**
	 * Método para envio de Mensagem a todos os conectados com o serviço de Chat
	 * @param remetente
	 * @param mensagem
	 * @throws RemoteException
	 */
	public void enviarMensagem(String remetente, String mensagem) throws RemoteException;
	
	/**
	 * Método para remover um determinado cliente da listaOnline.
	 * @param nome
	 * @param cliente
	 * @throws RemoteException
	 */
	public void sair(String nome, ICliente cliente) throws RemoteException;

}
