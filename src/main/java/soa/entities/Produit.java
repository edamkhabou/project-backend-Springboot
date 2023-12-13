package soa.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Produit {
	@Id
	@GeneratedValue
	private Long id;

	private String code ;
	@Column(length = 50)
	private String designation;

	private double prix;
	private int quantite;

	@Temporal(TemporalType.DATE)
	private Date dateAchat;

	@ManyToOne (cascade = {CascadeType.MERGE})
	private Categorie categorie;

	private float mb;
	

public Produit(){
	super();
}
	public Produit(String designation,String code, double prix, int quantite, Date dateAchat, Categorie categorie,float mb) {
		super();
		this.designation = designation;
		this.code = code;
		this.prix = prix;
		this.quantite = quantite;
		this.dateAchat = dateAchat;
		this.categorie = categorie;
		this.mb = mb;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getMb() {
		return mb;
	}

	public void setMb(float mb) {
		this.mb = mb;
	}



	@Override
	public String toString() {
		return "Produit{" +
				"id=" + id +
				", code='" + code + '\'' +
				", designation='" + designation + '\'' +
				", prix=" + prix +
				", quantite=" + quantite +
				", dateAchat=" + dateAchat +
				", categorie=" + categorie +
				", mb=" + mb +
				'}';
	}


}
