package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trfactura")
public class Factura {

    @Id
    @Column(nullable=false)
    private int numero;
    @Column(nullable=false)
    private int codigo;
    @Column(nullable=true)
    private double importe;

    public Factura(){

    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getNumero() {
        return numero;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getImporte() {
        return importe;
    }
}
