package edu.nraj;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerAddress;    //Instances of this class are not safe for use by multiple threads.
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.core.Transaction;	//Instances of this class are not safe for use by multiple threads.
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.MemoryBlockStore;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;

public class CreateWallet2 {	 
	private final NetworkParameters walletNetParams = TestNet3Params.get(); 
	private BlockStore blockStore = new MemoryBlockStore(walletNetParams); 
	private Wallet wallet = null;
	private File testFile = new File("newTest.wallet");
	private File testFile2 = new File("test.wallet");	

	public synchronized void myCreateWallet() throws UnknownHostException {		

		try {
			wallet = Wallet.loadFromFile(testFile);
		} catch (UnreadableWalletException e1) {
			e1.printStackTrace();
		}

		try {
			BlockChain chain = new BlockChain(walletNetParams, wallet, blockStore);
			//				chain.addWallet(wallet);
			PeerGroup peerGroup = new PeerGroup(walletNetParams, chain);
			peerGroup.addAddress(new PeerAddress(InetAddress.getByName("127.0.0.1"), 18333));
			peerGroup.addWallet(wallet);
			peerGroup.setFastCatchupTimeSecs(wallet.getEarliestKeyCreationTime());
			peerGroup.startAsync();
			peerGroup.downloadBlockChain();
			String addressString = wallet.currentReceiveAddress().toString();
			//						wallet.currentReceiveAddress().toString();
			System.out.println("Current wallet addreass is: " + wallet.currentReceiveAddress().toString());

			@SuppressWarnings("deprecation")
			Address targetAddress = new Address(walletNetParams, addressString);
			Wallet.SendResult result = null;
			try {
				result = wallet.sendCoins(peerGroup, targetAddress, Coin.CENT);
				Transaction tx = new Transaction(walletNetParams);
				System.out.println(tx.toString());
			} catch (InsufficientMoneyException e) {
				System.out.println("balance in the wallet: " + wallet.getBalance() + " at address: " + wallet.currentReceiveAddress().toString());
				e.printStackTrace();
			}
			try {
				wallet.saveToFile(testFile2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				result.broadcastComplete.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println(wallet);
		} catch (BlockStoreException e) {
			e.printStackTrace();
		} 
	};
}
