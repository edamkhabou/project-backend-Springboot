package soa.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class DetailsFactures {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Facture.class)
    @JoinColumn(name = "facture_id")
    private Facture facture;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Produit.class)
    @JoinColumn(name = "product_id")
    private Produit produit;

    private int qte;
    private double PU;

    // Constructeur par défaut
    public DetailsFactures() {
    }

    // Constructeur avec tous les attributs
    public DetailsFactures(Facture facture, Produit produit, int qte, double PU) {
        this.facture = facture;
        this.produit = produit;
        this.qte = qte;
        this.PU = PU;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Produit getProduct() {
        return produit;
    }

    public void setProduct(Produit produit) {
        this.produit = produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getPU() {
        return PU;
    }

    public void setPU(double PU) {
        this.PU = PU;
    }
    public Date getDateF() {
        return facture != null ? facture.getDateF() : null;
    }
    // Méthode toString
    @Override
    public String toString() {
        return "DetailsFactures{" +
                "id=" + id +
                ", facture=" + facture +
                ", product=" + produit +
                ", qte=" + qte +
                ", PU=" + PU +
                '}';
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

}