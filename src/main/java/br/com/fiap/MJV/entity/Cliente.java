package br.com.fiap.MJV.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(max = 50, min = 3)
    private String nomeCliente;

    @NotBlank
    private String cnpj;

    @OneToMany(mappedBy = "cliente")
    private List<Produto> produto;

    public Cliente() {
        super();
    }

    public Cliente(Long id, String nomeCliente, String cnpj, List<Produto> produto) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.cnpj = cnpj;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }
}
