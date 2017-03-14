package br.univel.comum;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JOptionPane;
/**
 * Interface para comunica��o RMI do Cliente
 * @author matt_
 *
 */
public interface ICliente extends Remote{
	/**
	 * M�todo para recebimento de Mensagens do servidor.
	 * @param remetente
	 * @param Mensagem
	 * @throws RemoteException
	 */
	public void receberMensagem(String remetente, String Mensagem) throws RemoteException;
	/**
	 * M�todo para atualiza��o da lista de pessoas Online na tela do Cliente.
	 * @param lista
	 * @throws RemoteException
	 */
	public void atualizaLista(List<String> lista) throws RemoteException;
	/**
	 * M�todo para reportar erros poss�veis proveniente de usu�rios. Ser� exibido em {@link JOptionPane};
	 * @param mensagem
	 * @throws RemoteException
	 */
	public void reportError(String mensagem) throws RemoteException;
	
}
