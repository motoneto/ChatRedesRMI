package br.univel.comum;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JOptionPane;
/**
 * Interface para comunicação RMI do Cliente
 * @author matt_
 *
 */
public interface ICliente extends Remote{
	/**
	 * Método para recebimento de Mensagens do servidor.
	 * @param remetente
	 * @param Mensagem
	 * @throws RemoteException
	 */
	public void receberMensagem(String remetente, String Mensagem) throws RemoteException;
	/**
	 * Método para atualização da lista de pessoas Online na tela do Cliente.
	 * @param lista
	 * @throws RemoteException
	 */
	public void atualizaLista(List<String> lista) throws RemoteException;
	/**
	 * Método para reportar erros possíveis proveniente de usuários. Será exibido em {@link JOptionPane};
	 * @param mensagem
	 * @throws RemoteException
	 */
	public void reportError(String mensagem) throws RemoteException;
	
}
