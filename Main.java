import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
	public static void carregarDadosCSV(String caminhoArquivo, HeapMaxima heap){
		try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))){
			String linha;
			while((linha = br.readLine()) != null){
				if (linha.trim().isEmpty()){
					continue;
				}
				try{
					String[] partes = linha.split(",");
					int id = Integer.parseInt(partes[0].trim());
					int tipo = Integer.parseInt(partes[1].trim());
					int urgencia = Integer.parseInt(partes[2].trim());
					int peso = Integer.parseInt(partes[3].trim());
					String descricao = partes[4].trim();
					if(urgencia < 1 || urgencia > 3 || peso <= 0){
						System.out.println("Linha inválida (dados incorretos): " + linha);
						continue;
					}
					Carga nova = new Carga(id, tipo, urgencia, peso, descricao);
					heap.inserir(nova);
				} catch (Exception e){
					System.out.println("Linha ignorada (formato inválido): " + linha);
				}
			}
			System.out.println("Cargas carregadas com Sucesso!");
		}catch (IOException e){
			System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
		}
	}
	public static void main(String[] args) {
		System.out.println("Hello World");
		HeapMaxima meuHeap = new HeapMaxima(20);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Sistema de cargas iniciando");
	}
}