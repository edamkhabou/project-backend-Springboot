package soa.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Date;

import javax.xml.crypto.Data;

@Entity
public class Facture {
    @Id
    @GeneratedValue
    private Long id;
    private String num;
    private Date dateF;
    private int client_id;
    private int devise_id;

    public Facture(String num, Date dateF, int client_id,int devise_id) {
super();
        this.num = num;
        this.dateF = dateF;
        this.client_id = client_id;
        this.devise_id=devise_id;
    }
    public Facture(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getDateF() {
        return dateF;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
}