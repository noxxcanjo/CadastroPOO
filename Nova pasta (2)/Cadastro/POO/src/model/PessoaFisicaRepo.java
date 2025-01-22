package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaRepo {
    private List<PessoaFisica> lista = new ArrayList<>();

    public void inserir(PessoaFisica pf) {
        lista.add(pf);
    }

    public void alterar(int id, PessoaFisica novaPf) {
        for (PessoaFisica pf : lista) {
            if (pf.getId() == id) {
                pf.setNome(novaPf.getNome());
                pf.setCpf(novaPf.getCpf());
                pf.setIdade(novaPf.getIdade());
                break;
            }
        }
    }

    public void excluir(int id) {
        lista.removeIf(pf -> pf.getId() == id);
    }

    public PessoaFisica obter(int id) {
        return lista.stream().filter(pf -> pf.getId() == id).findFirst().orElse(null);
    }

    public List<PessoaFisica> obterTodos() {
        return lista;
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            lista = (List<PessoaFisica>) ois.readObject();
        }
    }
}
