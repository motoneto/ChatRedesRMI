package br.univel.comum;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICliente extends Remote{

	public void receberMensagem(String remetente, String Mensagem) throws RemoteException;
	
	public void atualizaLista(List<String> lista) throws RemoteException;
	
	public void reportError(String mensagem) throws RemoteException;
	
}
