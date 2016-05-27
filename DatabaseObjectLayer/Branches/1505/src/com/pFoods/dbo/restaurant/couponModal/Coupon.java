package com.pFoods.dbo.restaurant.couponModal;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.pFoods.dbo.order.modal.OrderDetail;
import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.restaurant.menuModel.Menu;




@Entity
@Table (name="COUPON")
public class Coupon {
	
	@Id
	@Column (name="COUPON_ID")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long CouponId;
	
	@NotNull
	@Column (name="PROMO_CODE")
	private String promoCode;
	
	
	/**
	 * FK
	 */
	@NotNull
	@ManyToOne
	@JoinColumn (name="RID")
	private RestaurantLoginInfo restaurantLoginInfo;

	@NotNull
	@Lob
	@Column (name="PROMO_DETAIL")
	private String promoDetail;
	
	/**
	 * This we are passing by setter method
	 */
	@NotNull
	@Column (name="CREATED_DATE")
	private Date creationDate;
	
	
	/**
	 * Need to collect from Restaurant
	 */
	@NotNull
	@Column (name="EXPIRATION_DATE")
	private Date expirationDate;
	
	@Embedded
	private DiscountOnAmount discountOnAmount;

	@Embedded
	private DiscountOnItem discountOnItem;
	
	@ManyToOne
	@JoinColumn (name="MENU_ID")
	private Menu menu;
	
	@OneToMany (mappedBy="coupon")
	@Cascade({CascadeType.SAVE_UPDATE})
	private List<OrderDetail> orderDetail;
	

	public long getCouponId() {
		return CouponId;
	}

	public void setCouponId(long couponId) {
		CouponId = couponId;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public RestaurantLoginInfo getRestaurantLoginInfo() {
		return restaurantLoginInfo;
	}

	public void setRestaurantLoginInfo(RestaurantLoginInfo restaurantLoginInfo) {
		this.restaurantLoginInfo = restaurantLoginInfo;
	}

	public String getPromoDetail() {
		return promoDetail;
	}

	public void setPromoDetail(String promoDetail) {
		this.promoDetail = promoDetail;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate() {
		this.creationDate = new Date();
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public DiscountOnAmount getDiscountOnAmount() {
		return discountOnAmount;
	}

	public void setDiscountOnAmount(DiscountOnAmount discountOnAmount) {
		this.discountOnAmount = discountOnAmount;
	}

	public DiscountOnItem getDiscountOnItem() {
		return discountOnItem;
	}

	public void setDiscountOnItem(DiscountOnItem discountOnItem) {
		this.discountOnItem = discountOnItem;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	

}
