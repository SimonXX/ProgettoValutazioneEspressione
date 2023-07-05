package poo.valutazioneEspressione;

import java.util.Comparator;
import java.util.StringTokenizer;

import poo.util.Stack;
import poo.util.StackConcatenato;

public final class EspressioneUtil {


	private static boolean apertura = false;
	
	public static boolean espressioneValida(String espressione) {
		
		//condizione necessaria ma non sufficiente
			
		String EXPR = "(\\d+|[\\+\\-\\*/%\\^\\(\\)])+";
		//con questa regex si stabilisce che l'espressione potrà contenere unicamente numeri interi oppure gli operatori indicati
		//non si gestisce l'eventuale inserimento di parentesi non chiuse oppure la costruzione errata dell'espressione
		//questi particolari verranno trattati successivamente
		
		if(!espressione.matches(EXPR)) return false;
		
		return true;
		
		
	}//espressioneValida
	
	public static int valuta(String espressione) {
		
		StringTokenizer st = new StringTokenizer(espressione, "+-*/%^()", true);
		
		int ris = costruzioneEspressione(st);
		
		return ris;
		
	}//valuta
	
	public static int costruzioneEspressione (StringTokenizer st) {
		
		Stack<Integer> operandi = new StackConcatenato<>();
		Stack<Character> operatori = new StackConcatenato<>();
		
		boolean parentesiChiuse = false;
		
		boolean parentesiAperte;
		
		if(!apertura)//se si è verificata un'apertura nell'area dati precedente, abbiamo una parentesi aperta 
			parentesiAperte = false;
		else {
			parentesiAperte = true;
			apertura = false;
		}
		
		while(st.hasMoreTokens()) {
			
			Integer operando = gestioneOperando(st);//avremo un operando oppure una sottoespressione che produrrà un operando
			operandi.push(operando);
			
			if(!st.hasMoreTokens())
				break;
			
			
			char operatoreCorrente = st.nextToken().charAt(0);
			
			if(operatoreCorrente == ')') {
			
				parentesiChiuse = true;
				
				if(!parentesiAperte)
					throw new RuntimeException("Parentesi non Aperte");
	
				break;//fine della sottoespressione
			}
			
			

			if(operatori.size() == 0 || new Gerarchia().compare(operatoreCorrente, operatori.top())>0) {//essere maggiore significa avere maggiore priorità
				operatori.push(operatoreCorrente);
				continue;
			}
			
			
				
			while(operatori.size()!=0 && new Gerarchia().compare(operatoreCorrente, operatori.top())<=0) {
				
				char opCima = operatori.pop();//preleviamo l'operatore in cima allo stack
				gestioneOperatore(operandi, operatori, opCima);//applichiamo l'operatore estratto ai due operandi ai quali è riferito
				
			
			}
			operatori.push(operatoreCorrente);//aggiungiamo l'operatore corrente
			
			
		}
		
		while(operatori.size()!=0) {
			
			char opCima = operatori.pop();
			gestioneOperatore(operandi, operatori, opCima );
			
		}
		
		if(parentesiAperte != parentesiChiuse)
			throw new RuntimeException("Parentesi non Chiuse");
		
		int risultatoEspressione = operandi.pop();
	
		return risultatoEspressione;
	
	}//valuta
	
	private static int eseguiOperazione(int o1, int o2, char op) {
		
		//Abbiamo già controllato se l'operatore è ammesso 
		
		switch(op) {
		case '+' : return o1 + o2;
		case '-' : return o1 - o2;
		case '*' : return o1 * o2;
		case '/' : return o1 / o2;
		case '^' : return (int)Math.pow(o1, o2);
		case '%' : {
			System.out.println(o1%2);
			return o1 % o2;}
		default  : throw new RuntimeException("" + op + "Operatore Inatteso");
		
		}
		
	}//eseguiOperazione
	
	private static Integer gestioneOperando(StringTokenizer st) {
		
		String token = st.nextToken();
		
		if(token.equals("(")) {
			apertura = true;
			return costruzioneEspressione(st);
			
		}

		if(!token.matches("\\d+"))
			throw new RuntimeException("Espressione non valida");
		

		
		return  Integer.valueOf(token);
		
	}//gestioneOperandi
	
	private static void gestioneOperatore(Stack<Integer> operandi, Stack<Character> operatori, char opCima) {
		
	
		int o2 = operandi.pop();
		int o1 = operandi.pop();
		
		int ris = eseguiOperazione(o1, o2, opCima);
		operandi.push(ris);
		
	}//gestioneOperatore
	
	
	//creazione di un oggetto comparatore
	//si definisce una innerClass al fine di delinare la gerarchia esistente tra i vari operatori
	private static class Gerarchia implements Comparator<Character>{
		
		public int compare(Character cor, Character opCima){//cor-> operatore corrente estratto, affiornate->operatore in cima allo stack
			
			if(cor=='+' || cor=='-'){
				switch(opCima){
				 case '^':return -1;
				 case '+':case '-':return 0;
				 case '*':case '/':case '%':return -1;
				 }
			}
			
			if(cor=='*' || cor=='/' || cor == '%'){
				switch(opCima){
				 case '^':return -1;
				 case '+':case '-':return 1;
				 case '*':case '/':case '%':return 0;
				 }
			}
			
			if(cor=='^'){
				 switch(opCima){
				 case '^':return 0;
				 case '+':case '-':case '*':case '/':case '%':return 1;
				 }
				}
		
			
			throw new RuntimeException("Operatore non valido");
			
		}//compare
		
	}//Gerarchia

}//EspressioneUtil
