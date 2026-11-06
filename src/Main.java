//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        String procurado = "Dave";
        System.out.println("Procurando: " + procurado);

        long inicio = System.currentTimeMillis();

        String[] arquivos =  {
                "signatarios-fev-2002.txt",
                "signatarios-fev-2016.txt",
                "signatarios-out-2014.txt"
        };

        Thread[] threads = new Thread[arquivos.length];

        for (int i = 0; i < threads.length; i++) {

            BuscaNomeRunnable busca = new BuscaNomeRunnable(arquivos[i], procurado);
            //threads[i] = new Thread(busca);              <- plataforms threads
            //threads[i].start();

            threads[i] = Thread.ofVirtual()              //<- virtual threads
                    .name("busca-"+(i+1))
                    .start(busca);

        }

        for (Thread thread : threads) {
            thread.join();
        }

        long fim =  System.currentTimeMillis();
        System.out.println();
        System.out.println("Todas as buscas foram finalizadas");
        System.out.println("Tempo total: "+(fim - inicio)+" ms");

    }
}