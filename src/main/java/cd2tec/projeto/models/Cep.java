package cd2tec.projeto.models;

public class Cep {

       private String cep;
       private String uf;
       private String ddd;

       public Cep() {
       }

       public Cep(String cep, String uf, String ddd) {
              this.cep = cep;
              this.uf = uf;
              this.ddd = ddd;
       }

       public String getCep() {
              return cep;
       }

       public void setCep(String cep) {
              this.cep = cep;
       }

       public String getUf() {
              return uf;
       }

       public void setUf(String uf) {
              this.uf = uf;
       }

       public String getDdd() {
              return ddd;
       }

       public void setDdd(String ddd) {
              this.ddd = ddd;
       }

       @Override
       public String toString() {
              return "CEP{" +
                      "cep='" + cep + '\'' +
                      ", uf='" + uf + '\'' +
                      ", ddd='" + ddd + '\'' +
                      '}';
       }
       
}
