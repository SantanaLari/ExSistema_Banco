package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.threadBanco;

public class Main {

	public static void main(String[] args) {
		
		Random aleatorio = new Random();
		
		int permissoesDep = 1;
		int permissoesSaq = 1;
		
		Semaphore semaforo1 = new Semaphore(permissoesDep);
		Semaphore semaforo0 = new Semaphore(permissoesSaq);
		
		for(int conta = 0; conta < 20; conta++) {
			//saq = 0 | dep = 1
			int SaqDep = aleatorio.nextInt(2); 
			threadBanco banco = new threadBanco(conta, SaqDep, semaforo0, semaforo1);
			banco.start();
		}

	}

}
