package edu.nraj;

import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		final CreateWallet2 wallet2 = new CreateWallet2();
		
		Thread thread1 = new Thread(new Runnable() {			
			public void run() {
				try {
					wallet2.myCreateWallet();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		});
		thread1.setName("my thread 1");
		
		Thread thread2 = new Thread(new Runnable() {			
			public void run() {
				try {
					wallet2.myCreateWallet();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		});
		thread2.setName("my thread 2");
		
		thread1.start();
		thread2.start();
	}
}
