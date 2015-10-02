package br.edu.ifpe.garanhuns.projetoProvaPc.dominio;

import java.util.Objects;

public class Aluno {
    
    private String matricula;

    public Aluno(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.matricula);
        return hash;
    }
    
}
