package file;

public class TesteDados {

	public static void main(String[] args) {
		String arquivoEntrada = "src/entrada.txt" ;
		String arquivoRejeitado = "src/rejeitados.txt";
		
		ProcessadorDados pd = new ProcessadorDados();
		pd.verificacao(arquivoEntrada, arquivoRejeitado);
		
	}

}
