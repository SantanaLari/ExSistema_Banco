package controller;

import java.util.concurrent.Semaphore;

public class threadBanco extends Thread{
	
	private int conta;
	private int SaqDep;
	private Double saldo;
	private Double valorTransacionado;
	private Semaphore semaforo0;
	private Semaphore semaforo1;
	
	public threadBanco(int conta, int SaqDep, Semaphore semaforo0, Semaphore semaforo1) {
		this.conta = conta;
		this.SaqDep = SaqDep;
		this.semaforo0 = semaforo0;
		this.semaforo1 = semaforo1;
	}
	
	public void run() {
		if(SaqDep == 0) {
			try {
				semaforo0.acquire();
				saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaforo0.release();
				saqueConcluido();
			}
			
		}else {
			try {
				semaforo1.acquire();
				deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaforo1.release();
				depositoConcluido();	
			}
			
		}
	}

	private void saque() {
		saldo = (Math.random()*1000);
		valorTransacionado = (Math.random()*100);
	
		System.out.printf("\nSAQUE \nCódigo: %d \nSaldo: %.2f \nValor: %.2f \n-------------------------", 
				conta, saldo, valorTransacionado);
		
		try {
			sleep(75);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	private void deposito() {
		saldo = (Math.random()*1000);
		valorTransacionado = (Math.random()*100);
		
		System.out.printf("\nDEPÓSITO \nCódigo: %d \nSaldo: %.2f \nValor: %.2f \n-------------------------", 
				conta, saldo, valorTransacionado);
		
		try {
			sleep(75);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void saqueConcluido() {
		System.out.println("\nA conta com o código " + conta + " concluiu seu saque.");
	}
	
	private void depositoConcluido() {
		System.out.println("\nA conta com o código " + conta + " concluiu seu depósito.");
	}

}
