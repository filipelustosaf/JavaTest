package cd2tec.projeto.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_entrega")
public class Entrega implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Destinatário é um atributo obrigatório!")
    @Column(name = "destinatario")
    private String destinatario;

    @NotNull(message = "Peso é um atributo obrigatório!")
    @Column(name = "peso")
    private Double peso;

    @Column(name = "ddd_origem")
    private String dddOrigem;

    @Column(name = "ddd_destino")
    private String dddDestino;

    @NotBlank(message = "CEP é um atributo obrigatório!")
    @Size(min = 1, max = 10)
    @Column(name = "cep_origem")
    private String cepOrigem;

    @NotBlank(message = "CEP é um atributo obrigatório!")
    @Size(min = 1, max = 10)
    @Column(name = "cep_destino")
    private String cepDestino;

    @Column(name = "estado_origem")
    private String estadoOrigem;

    @Column(name = "estado_destino")
    private String estadoDestino;

    @Size(max = 100)
    @Column(name = "endereco_destino")
    private String enderecoDestino;

    @Column(name = "frete")
    private Double frete;

    @Column(name = "prazo")
    private Integer prazo;

    public Entrega(@NotBlank(message = "Destinatário é um atributo obrigatório!") String destinatario,
            @NotNull(message = "Peso é um atributo obrigatório!") Double peso,
            @NotBlank(message = "CEP é um atributo obrigatório!") @Size(min = 1, max = 10) String cepOrigem,
            @NotBlank(message = "CEP é um atributo obrigatório!") @Size(min = 1, max = 10) String cepDestino) {
        this.destinatario = destinatario;
        this.peso = peso;
        this.cepOrigem = cepOrigem;
        this.cepDestino = cepDestino;
    }

    public Entrega() {
    }

    public Double getFrete() {
        return frete;
    }
    public void setFrete(Double frete) {
        this.frete = frete;
    }
    public Integer getPrazo() {
        return prazo;
    }
    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }
    public Long getId() {
        return id;
    }
    public String getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    public Double getPeso() {
        return peso;
    }
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    public String getDddOrigem() {
        return dddOrigem;
    }
    public void setDddOrigem(String dddOrigem) {
        this.dddOrigem = dddOrigem;
    }
    public String getDddDestino() {
        return dddDestino;
    }
    public void setDddDestino(String dddDestino) {
        this.dddDestino = dddDestino;
    }
    public String getCepOrigem() {
        return cepOrigem;
    }
    public void setCepOrigem(String cepOrigem) {
        this.cepOrigem = cepOrigem;
    }
    public String getCepDestino() {
        return cepDestino;
    }
    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }
    public String getEstadoOrigem() {
        return estadoOrigem;
    }
    public void setEstadoOrigem(String estadoOrigem) {
        this.estadoOrigem = estadoOrigem;
    }
    public String getEstadoDestino() {
        return estadoDestino;
    }
    public void setEstadoDestino(String estadoDestino) {
        this.estadoDestino = estadoDestino;
    }
    public String getEnderecoDestino() {
        return enderecoDestino;
    }
    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

}
