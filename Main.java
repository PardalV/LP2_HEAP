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
		HeapMaxima meuHeap = new HeapMaxima(10);
		Scanner sc = new Scanner(System.in);
		int opcao = 0;

		while(opcao != 6){
			System.out.println("\n--- SISTEMA DE GERENCIAMENTO DE CARGAS ---");
			System.out.println("1. Carregar do CSV");
            System.out.println("2. Inserir Nova Carga Manualmente");
            System.out.println("3. Consultar Carga de Maior Prioridade");
            System.out.println("4. Remover Carga de Maior Prioridade");
            System.out.println("5. Exibir Relatório Completo (Ordenado)");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

			try{
				String input = sc.nextLine();
				opcao = Integer.parseInt(input);

			} catch (NumberFormatException e){
				System.out.println("Por favor, digite apenas números válidos");
				continue;
			}
			switch(opcao){
				case 1:
					System.out.println("Digite o nome do arquivo (ex: dados.csv): ");
					String nomeArq = sc.nextLine();
					carregarDadosCSV(nomeArq, meuHeap);
					break;
				case 2:
					System.out.println("--- Inserção Manual ---");
					try{
						System.out.print("ID: ");
						int id = Integer.parseInt(sc.nextLine());
						System.out.print("Tipo : ");
						int tipo = Integer.parseInt(sc.nextLine());
						System.out.print("Urgência: ");
						int urgencia = Integer.parseInt(sc.nextLine());
						System.out.print("Peso: ");
						int peso = Integer.parseInt(sc.nextLine());
						System.out.print("Descrição: ");
						String descricao = sc.nextLine();

						Carga nova = new Carga(id, tipo, urgencia, peso, descricao);
						meuHeap.inserir(nova);
						System.out.println("Carga inserida com prioridade: " + nova.getPrioridade());
					} catch (NumberFormatException e){
						System.out.println("Erro na digitação dos números. Tente novamente");
					}
					break;
				case 3:
					Carga topo = meuHeap.consultarTopo();
					if(topo != null){
						System.out.println("Topo atual: " + topo);
					} else {
						System.out.println("A Heap está vazia");
					}
					break;
				case 4:
					Carga removida = meuHeap.removerMaximo();
					if(removida != null){
						System.out.println("Carga removida e despachada: " + removida);
					}
					break;
				case 5:
					System.out.println("\n--- Relatório de Cargas (Ordenado por Prioridade) ---");
					if (meuHeap.estaVazia()){
						System.out.println("Nenhuma carga cadastrada");
					} else {
						Carga[] dadosCopia = meuHeap.getVetorCopia();
						HeapMaxima auxHeap = new HeapMaxima(dadosCopia.length);
						for(int i = 1; i < dadosCopia.length; i ++){
							if(dadosCopia[i] != null){
								auxHeap.inserir(dadosCopia[i]);
							}
						}
						System.out.println("Prioridade | ID | Descrição");
						System.out.println("-----------------------------------");
						while(!auxHeap.estaVazia()){
							System.out.println(auxHeap.removerMaximo());
						}
					}
					break;
				case 6:
					System.out.println("Encerrando o sistema");
					break;
				default:
					System.out.println("Opção inválida! Escolha um número válido");
			}
		}
		sc.close();
	}
}