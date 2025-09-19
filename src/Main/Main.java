package Main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import entidades.Dependente;
import entidades.FolhaPagamento;
import entidades.Funcionario;
import enuns.Parentesco;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Funcionario> funcionarios = new ArrayList<>();
    private static final Set<String> cpfsUtilizados = new HashSet<>();
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    adicionarDependente();
                    break;
                case 3:
                    calcularFolhaDePagamento();
                    break;
                case 4:
                    listarFuncionarios();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        scanner.close();
    }
    
    private static void exibirMenu() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Adicionar Dependente");
        System.out.println("3. Calcular Folha de Pagamento");
        System.out.println("4. Listar Funcionários");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarFuncionario() {
        System.out.println("\n--- Cadastro de Funcionário ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (cpfsUtilizados.contains(cpf)) {
            System.out.println("ERRO: CPF já cadastrado!");
            return;
        }

        LocalDate dataNascimento = lerData("Data de Nascimento (dd/mm/yyyy): ");

        System.out.print("Salário Bruto: ");
        double salario = Double.parseDouble(scanner.nextLine());

        Funcionario f = new Funcionario(nome, cpf, dataNascimento, salario, 0.0, 0.0);
        funcionarios.add(f);
        cpfsUtilizados.add(cpf);
        System.out.println("Funcionário cadastrado com sucesso!");
    }
    
    private static void adicionarDependente() {
        System.out.println("\n--- Adicionar Dependente ---");
        System.out.print("Digite o CPF do funcionário: ");
        String cpfFuncionario = scanner.nextLine();
        
        Funcionario funcionario = null;
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpfFuncionario)) {
                funcionario = f;
                break;
            }
        }
        
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        
        System.out.print("Nome do dependente: ");
        String nomeDependente = scanner.nextLine();
        System.out.print("CPF do dependente: ");
        String cpfDependente = scanner.nextLine();
       
        LocalDate dataNascDependente = lerData("Data de Nascimento do dependente: "
        		+ "");
        
        System.out.print("Parentesco (FILHO(A), SOBRINHO(A), OUTROS): ");
        Parentesco parentesco = Parentesco.valueOf(scanner.nextLine().toUpperCase());
        
        try {
            Dependente dependente = new Dependente(nomeDependente, cpfDependente, dataNascDependente, parentesco);
            funcionario.adicionarDependente(dependente);
            System.out.println("Dependente adicionado com sucesso!");
        } catch (Exception e) { 
            System.out.println("Erro ao adicionar dependente: " + e.getMessage());
        }
    }

    private static void calcularFolhaDePagamento() {
        System.out.println("\n--- Calcular Folha de Pagamento ---");
        System.out.print("Digite o CPF do funcionário: ");
        String cpfFuncionario = scanner.nextLine();
        
        Funcionario funcionario = null;
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpfFuncionario)) {
                funcionario = f;
                break;
            }
        }
        
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        
        FolhaPagamento folha = new FolhaPagamento(1, funcionario, LocalDate.now());
        System.out.println(folha);
    }
    
    private static void listarFuncionarios() {
        System.out.println("\n--- Lista de Funcionários ---");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome() + ", CPF: " + f.getCpf() + ", Dependentes: " + f.getDependentes().size());
        }
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return LocalDate.parse(scanner.nextLine(), dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use o formato dd/mm/yyyy.");
            }
        }
    }
}