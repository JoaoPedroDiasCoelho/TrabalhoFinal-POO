package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa {
    private double salarioBruto;
    private double descontoInss;
    private double descontoIr;
    private List<Dependente> dependentes;

    public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto, double descontoInss,
            double decontoIr) {
        super(nome, cpf, dataNascimento);
        this.salarioBruto = salarioBruto;
        this.descontoInss = descontoInss;
        this.descontoIr = decontoIr;
        this.dependentes = new ArrayList<>();
    }

    public double CalcularInss() {
        double SalarioBruto = getSalarioBruto();
        double valorCalculado = 0.0; 

        if (SalarioBruto <= 1518) {
            valorCalculado = SalarioBruto * 0.075;
        } else if (SalarioBruto > 1518 && SalarioBruto <= 2794) {
            valorCalculado = (SalarioBruto * 0.09) - 22.77;
        } else if (SalarioBruto > 2794 && SalarioBruto <= 4190) {
            valorCalculado = (SalarioBruto * 0.12) - 107;
        } else if (SalarioBruto > 4190 && SalarioBruto <= 8160) {
            valorCalculado = (SalarioBruto * 0.14) - 191;
        } else {
            valorCalculado = (8161 * 0.14);
        }

        this.descontoInss = valorCalculado;
        return this.descontoInss;
    }

    public double CalcularIr() {
        CalcularInss(); 
        
        double baseCalculoReal = getSalarioBruto() - this.descontoInss;
        double abatimentoDependentes = getDependentes().size() * 189.59;
        double baseParaTabela = baseCalculoReal - abatimentoDependentes;
        double valorCalculado = 0.0;

        if (baseParaTabela <= 2259) {
            valorCalculado = 0.0;
        
        } else if (baseParaTabela > 2260 && baseParaTabela <= 2827) {
            valorCalculado = (baseParaTabela * 0.075) - 169; 
       
        } else if (baseParaTabela > 2827 && baseParaTabela <= 3752) {
            valorCalculado = (baseParaTabela * 0.15) - 382; 
       
        } else if (baseParaTabela > 3752 && baseParaTabela <= 4665) {
            valorCalculado = (baseParaTabela * 0.225) - 663; 
       
        } else {         
        	valorCalculado = (baseParaTabela * 0.275) - 896; 
        }

        this.descontoIr = Math.max(0, valorCalculado);
        return this.descontoIr;
    }

    public double calcularSalarioLiquido() {
        CalcularInss();
        CalcularIr();
        return this.salarioBruto - this.descontoInss - this.descontoIr;
    }

    public void adicionarDependente(Dependente dependente) {
        this.dependentes.add(dependente);
    }
    
    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public double getDescontoInss() {
        return descontoInss;
    }

    public void setDescontoInss(double descontoInss) {
        this.descontoInss = descontoInss;
    }

    public double getDescontoIr() {
        return descontoIr;
    }

    public void setDescontoIr(double descontoIr) {
        this.descontoIr = descontoIr;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }
}