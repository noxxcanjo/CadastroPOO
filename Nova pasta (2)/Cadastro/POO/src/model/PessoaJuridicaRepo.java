package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaRepo {
    private List<PessoaJuridica> lista = new ArrayList<>();

    // Método para inserir uma nova Pessoa Jurídica
    public void inserir(PessoaJuridica pj) {
        lista.add(pj);
    }

    // Método para alterar os dados de uma Pessoa Jurídica existente
    public void alterar(int id, PessoaJuridica novaPj) {
        for (PessoaJuridica pj : lista) {
            if (pj.getId() == id) {
                pj.setNome(novaPj.getNome());
                pj.setCnpj(novaPj.getCnpj());
                pj.setRazaoSocial(novaPj.getRazaoSocial());
                break;
            }
        }
    }

    // Método para excluir uma Pessoa Jurídica pelo ID
    public void excluir(int id) {
        lista.removeIf(pj -> pj.getId() == id);
    }

    // Método para obter uma Pessoa Jurídica pelo ID
    public PessoaJuridica obter(int id) {
        return lista.stream().filter(pj -> pj.getId() == id).findFirst().orElse(null);
    }

    // Método para obter todas as Pessoas Jurídicas
    public List<PessoaJuridica> obterTodos() {
        return lista;
    }

    // Método para persistir os dados em um arquivo
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        }
    }

    // Método para recuperar os dados de um arquivo
    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            lista = (List<PessoaJuridica>) ois.readObject();
        }
    }
}
