package br.com.thiagofood.deliverydecomida.model;


import br.com.thiagofood.deliverydecomida.controller.request.AtualizaClienteRequest;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.*;

@Entity
@Table(uniqueConstraints =
@UniqueConstraint(name = "UK_cpf_email"
        ,columnNames = {"email","cpf"}))
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDateTime criadoEm = now();

    @UpdateTimestamp
    private LocalDateTime modificadoEm;

    @OneToMany(mappedBy = "cliente",cascade = {CascadeType.REMOVE})
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente(String nome, String email, String celular, String cpf) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }


    /**
     * @Deprecated: Uso do Hibernate
     */
    @Deprecated
    public Cliente() {
    }

    public Long getId() {
        return id;
    }



    public void atualiza(AtualizaClienteRequest request) {
        request.getNome().ifPresent(possivelNome -> this.nome = possivelNome);
        request.getTelefone().ifPresent(possivelTelefone -> this.celular = possivelTelefone);
        request.getEmail().ifPresent(possivelEmail -> this.email = possivelEmail);


    }
}
