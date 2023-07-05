## Progetto Valutazione Espressione

Il progetto "Valutazione Espressione" è composto da due package principali: `poo.util` e `poo.valutazioneEspressione`. Il primo package contiene 3 classi che sono necessarie per l'utilizzo della struttura dati "Stack Concatenato". Il secondo package, invece, include le classi `ValutazioneEspressioneGUI.java` e `EspressioneUtil.java`. La classe `ValutazioneEspressioneGUI.java` implementa l'interfaccia grafica dell'applicazione, mentre la classe `EspressioneUtil.java` rappresenta una classe di utilità che contiene i metodi per il controllo della validità e la valutazione dell'espressione.

### Premessa

La valutazione dell'espressione avviene rispettando le usuali precedenze matematiche, con le operazioni ordinate in base alla loro priorità decrescente: `^` (elevamento a potenza) > `*`, `/`, `%` (moltiplicazione, divisione, modulo) > `+`, `-` (somma, sottrazione). In caso di parità di priorità, viene considerata l'associatività a sinistra. È possibile modificare le priorità utilizzando le parentesi.

Per gestire le priorità degli operatori, la classe `EspressioneUtil` include una inner class chiamata `Gerarchia`. Questa classe implementa l'interfaccia `Comparator<Character>` e definisce un comparatore per stabilire la priorità tra gli operatori.

### Algoritmo di valutazione

L'algoritmo di valutazione dell'espressione utilizza due stack: uno per gli operandi e uno per gli operatori. Il metodo principale per la valutazione è `valuta(String espressione)`, che prende in input una stringa espressione e restituisce il risultato finale dell'espressione.

Durante l'esecuzione del metodo `costruzioneEspressione(StringTokenizer st)`, vengono utilizzati due cicli esterni. Il primo ciclo si ripete fintanto che il tokenizer contiene elementi. Durante ogni iterazione, viene prelevato un operando e un operatore corrente. Se lo stack degli operatori è vuoto o se l'operatore corrente ha una maggiore priorità rispetto a quello in cima allo stack, l'operatore corrente viene aggiunto allo stack degli operatori. Altrimenti, se lo stack degli operatori non è vuoto e l'operatore corrente non ha una maggiore priorità, viene eseguito un ciclo annidato per gestire gli operatori.

Il metodo `gestioneOperatore(Stack<Integer> operandi, Stack<Character> operatori, char opCima)` viene chiamato all'interno del ciclo annidato e si occupa di applicare l'operatore corrente agli ultimi due operandi presenti nello stack degli operandi.

Una volta terminato il primo ciclo e non vi sono più elementi nell'espressione (o sottoespressione, nel caso di parentesi tonde), viene eseguito un secondo ciclo per applicare gli operatori rimanenti nello stack degli operatori.

### Gestione delle parentesi tonde

Durante l'esecuzione del metodo `costruzioneEspressione`, si gestisconoanche le parentesi tonde. Il metodo `gestioneOperando(StringTokenizer st)` viene chiamato per gestire gli operandi e le parentesi tonde. Se viene trovato un numero intero, il metodo restituisce un valore di tipo `Integer`. Se viene trovata una parentesi tonde, viene invocato ricorsivamente il metodo `costruzioneEspressione` per valutare la sottoespressione racchiusa tra le parentesi.

Se durante l'esecuzione del metodo `costruzioneEspressione` viene trovata una parentesi chiusa, il ciclo termina e si ritorna all'area dati precedente.

### Malformazioni

Il metodo `espressioneValida(String espressione)` verifica se l'espressione è valida. Questo metodo restituisce un valore booleano e controlla se ci sono caratteri diversi da numeri interi o dagli operatori. Tuttavia, la corretta apertura e chiusura delle parentesi e altri casi specifici vengono valutati durante la costruzione dell'espressione.

### GUI di interazione

Il progetto include anche un'interfaccia grafica (GUI) implementata nella classe `ValutazioneEspressioneGUI.java`. La classe `ValutazioneEspressioneGUI` contiene un frame principale chiamato `FrameGUI`, che presenta un'area di inserimento e una console con barre di scorrimento, insieme a diverse `JLabel` indicative.

Inoltre, è possibile utilizzare una tastiera virtuale implementata nella classe `TastieraVirtuale`. La tastiera virtuale consente l'inserimento dell'espressione da valutare tramite il clic del mouse.

