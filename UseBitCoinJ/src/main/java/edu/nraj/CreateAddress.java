package edu.nraj;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;

public class CreateAddress {

	public static Address generateAddress() {
		String net = "test";
				
		ECKey key = new ECKey();
		
		final NetworkParameters netParams;
		
		if(net.equals("prod")){ 
			netParams = MainNetParams.get();
		} else {
			netParams = TestNet3Params.get();
		}
		
		//get valid Bitcoin address from public key
		Address addressFromKey = key.toAddress(netParams);
		
//		System.out.println("On the " + net + " network, we can use this address:\n" + addressFromKey);
		
		return addressFromKey;
	}

}
