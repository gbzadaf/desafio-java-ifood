import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        try(ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (String arquivo : arquivos) {
                BuscaNomeRunnable busca = new BuscaNomeRunnable(arquivo, procurado);
                executor.execute(busca);
            }

        }

        long fim =  System.currentTimeMillis();
        System.out.println();
        System.out.println("Todas as buscas foram finalizadas");
        System.out.println("Tempo total: "+(fim - inicio)+" ms");

    }
}