package org.example;
import javax.persistence.*;
@Entity
@Table(name="trcliente")
public class Cliente {
    @Id
    @Column(nullable=false)
    private int codigo;
    @Column(nullable=false,length=40)
    private String nombre;
    @Column(nullable=true,length=30)
    private String direc;
    @Column(nullable=true)
    private int postal;
    @Column(nullable=true,length=20)
    private String tel;
    public Cliente() {}
    public void setCodigo(int c) { codigo=c; }
    public void setNombre(String s) { nombre=s; }
    public void setDirec(String s) { direc=s; }
    public void setPostal(int p) { postal=p; }
    public void setTel(String t) { tel=t; }
    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDirec() { return direc; }
    public int getPostal() { return postal; }
    public String getTel() { return tel; }
}