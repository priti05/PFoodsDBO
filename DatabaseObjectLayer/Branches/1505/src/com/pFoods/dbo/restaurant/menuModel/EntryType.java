package com.pFoods.dbo.restaurant.menuModel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


/**
 * This Class Contain type of menu entries like "Bread" , "MainDish" ,  Drinks,
 * "Desert" , "Appetizer", "Soup"
 * @author Priti Patel
 *
 */

@Entity
@Table (name="ENTRY_TYPE")
public class EntryType {
	
	@Id
	@Column (name="ENTRY_TYPE_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int entryTypeId;
	
	@NotNull
	@Column (name="ENTRY_TYPE")
	private String entryType;
	
	@OneToMany (mappedBy="entryType")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Menu> menu;

	public int getEntryTypeId() {
		return entryTypeId;
	}

	public void setEntryTypeId(int entryTypeId) {
		this.entryTypeId = entryTypeId;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	
	
	
	
}
