package com.pFoods.dbo.order.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pFoods.dbo.restaurant.menuModel.Menu;

@Entity
@Table (name="ITEMS")
public class ItemList {
	
	/**
	 * PK
	 */
	@Id
	@Column (name="ITEM_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long itemId;
	
	
	/**
	 * FK
	 * e.g. Id from Table "BREAD", "DISH", "DRINK", As Object is super class, need to to assign right
	 * class as per foodType
	 */
	@NotNull
	@ManyToOne
	@JoinColumn (name="MENU_ID")
	private Menu menuId;
	
	
	@NotNull
	@Column (name="QUANTITY")
	private int quantity;
	
	/**
	 * FK
	 */
	@NotNull
	@ManyToOne
	@JoinColumn (name="ORDER_DETAIL_ID")
	private OrderDetail orderDetail;

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public Menu getMenuId() {
		return menuId;
	}

	public void setMenuId(Menu menuId) {
		this.menuId = menuId;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
		

}
