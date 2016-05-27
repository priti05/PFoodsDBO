package com.pFoods.dbo.order.modal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.pFoods.dbo.restaurant.couponModal.Coupon;

@Entity
@Table (name="ORDER_DETAIL")
public class OrderDetail {
	
	/**
	 * PK
	 */
	@Id
	@Column (name="ORDER_DETAIL_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long oderDetailId;
	
	/**
	 * User note for one order (e.g less cheese, ring bell, call when you come to deliver)
	 */
	@Lob
	@Column (name="USER_NOTE")
	private String userNote;
	
	/**
	 * Amount of order without tax on it
	 */
	@NotNull
	@Column (name="TOTAL_WITHOUT_TAX")
	private String totalWithOutTax;
	
	/**
	 * Amount of tax
	 */
	@NotNull
	@Column (name="TAX")
	private String tax;
	
	/**
	 * If PromoCode is used
	 */
	@ManyToOne
	@JoinColumn (name="COUPON_ID")
	private Coupon coupon;
	
	/**
	 * If there is any discount on tab (e.g. using promocode)
	 */
	@Column (name="DISCOUNT")
	private String discount;
	
	/**
	 * Total amount including tax, and discount
	 */
	@NotNull
	@Column (name="TOTAL")
	private String total;
	
	/**
	 * List of item order in one order
	 */
	@OneToMany (mappedBy="orderDetail")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<ItemList> itemList;
	
	@OneToOne (mappedBy="orderDetail")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Order order;

	public long getOderDetailId() {
		return oderDetailId;
	}

	public void setOderDetailId(long oderDetailId) {
		this.oderDetailId = oderDetailId;
	}

	public String getUserNote() {
		return userNote;
	}

	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}

	public String getTotalWithOutTax() {
		return totalWithOutTax;
	}

	public void setTotalWithOutTax(String totalWithOutTax) {
		this.totalWithOutTax = totalWithOutTax;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<ItemList> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemList> itemList) {
		this.itemList = itemList;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	

}
