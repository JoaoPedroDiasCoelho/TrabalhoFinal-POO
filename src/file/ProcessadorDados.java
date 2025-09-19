package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import conexao.DBconnection;
import entidade.Dependente;
import entidade.Funcionario;

public class ProcessadorDados {
	public void verificacao(String entrada, String rejeitados) {
		Set<String> arquivoDuplicado = new HashSet<>();
		List<String> rejeitado = new ArrayList<>();
		Funcionario funcionario;
		Dependente dependentes;

		try (BufferedReader br = new BufferedReader(new FileReader(entrada));
				Connection conn = DBconnection.getConnection()) {

			String linha;
			while ((linha = br.readLine()) != null) {
				String cpf = linha.split(";")[0];

				if (!arquivoDuplicado.add(cpf)) {
					rejeitado.add(linha);
				}
			}

			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(";");
				String cpf = dados[0];

				if (arquivoDuplicado.contains(cpf)) {
					continue;
				}
				if (cpfExisteNoBanco(conn, cpf)) {
					System.out.println("Cpf já exististente!" + cpf);
					rejeitado.add(linha);
				} else {
					System.out.println("Em manutenção");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		salvarRejeitados(rejeitados, rejeitado);
	}

	public boolean cpfExisteNoBanco(Connection conn, String cpf) throws SQLException {
		String sql = "SELECT COUNT(*) FROM Funcionario WHERE cpf = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cpf);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		}
		return false;
	}

	public void salvarRejeitados(String arquivo, List<String> linhas) {
		if (!linhas.isEmpty()) {
			try (FileWriter fw = new FileWriter(arquivo)) {
				for (String linha : linhas) {
					fw.write(linha + System.lineSeparator());
				}

				System.out.println("Dados rejeitados salvos em: " + arquivo);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}