import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pFoods.dbo.restaurant.IRestaurantProfileDBORegistration;
import com.pFoods.dbo.restaurant.RestaurantProfileDBORegistration;
import com.pFoods.dbo.restaurant.couponModal.Coupon;
import com.pFoods.dbo.restaurant.couponModal.DiscountOnAmount;
import com.pFoods.dbo.restaurant.couponModal.DiscountOnItem;
import com.pFoods.dbo.restaurant.helper.RestaurantProfileDBOHelper;
import com.pFoods.dbo.restaurant.imageModel.Images;
import com.pFoods.dbo.restaurant.imageModel.Images.Profile;
import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.restaurant.menuModel.Menu;
import com.pFoods.dbo.restaurant.to.RestaurantInsertImagesRequestTO;
import com.pFoods.dbo.singleton.SessionFactoryBuilderSinglton;
import com.pFoods.dbo.userProfile.profileModal.UserProfile;







public class CallRegistration {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		SessionFactoryBuilderSinglton.INSTANCE.buildHibernateSessionFactory();
		/*SessionFactory sessionFactory = SessionFactoryBuilderSinglton.INSTANCE.getInstance();
		Session session = sessionFactory.openSession();
			session.beginTransaction();
			String hql = "FROM com.pFoods.dbo.restaurant.menuModel.Menu";
			 List<Menu> recordList= (List<Menu>)session.createQuery(hql).list();
			
			File file = new File("/Users/hardikpatel/Downloads/sorbet.jpg");
			byte[] bFile = new byte[(int) file.length()];
			try{
			FileInputStream fileInputStream = new FileInputStream(file);
		     //convert file into array of bytes
		     fileInputStream.read(bFile);
		     fileInputStream.close();
		     int batchCounter = 0;
			for(Menu menu:recordList){
				if(menu!=null && menu.getMenuId()==1){
					menu.setImage(bFile);
					session.saveOrUpdate(menu);
					batchCounter++;
					if( batchCounter % 50 == 0 ) { // Same as the JDBC batch size
				        //flush a batch of inserts and release memory:
				        session.flush();
				        session.clear();
				        batchCounter = 0;
				    }
				}
			}
			}catch(Exception e){
				
			}
			session.getTransaction().commit(); */
		/*File file = new File("/Users/hardikpatel/Downloads/Toro's.jpg");
		//File file2 = new File("/Users/pritipatel/Downloads//insideVatan1.jpg");
        byte[] bFile = new byte[(int) file.length()];
        //byte[] bFile2 = new byte[(int) file2.length()];
 
        try {
	     FileInputStream fileInputStream = new FileInputStream(file);
	     //convert file into array of bytes
	     fileInputStream.read(bFile);
	     fileInputStream.close();
	     RestaurantInsertImagesRequestTO restaurantInsertImagesRequestTO = new RestaurantInsertImagesRequestTO();
	     restaurantInsertImagesRequestTO.setRid("Toros123");
	     List<Images>  imgList = new ArrayList<Images>();
	     Images img = new Images();
	     imgList.add(img);
	     restaurantInsertImagesRequestTO.setImageList(imgList);
	     img.setImage(bFile);
	     img.setProfileImage(Profile.yes);
	     IRestaurantProfileDBORegistration iRestaurantProfileDBORegistration = new RestaurantProfileDBORegistration();
	     
	     iRestaurantProfileDBORegistration.insertRestuarantProfileImages(restaurantInsertImagesRequestTO);
        }catch(Exception e){
        	System.out.println(e.getStackTrace());
        }*/
	     
	    // fileInputStream1 = new FileInputStream(file2);
	     //convert file into array of bytes
	   //  fileInputStream1.read(bFile2);
	    // fileInputStream1.close();
		
		
		
		
		
		IRestaurantProfileDBORegistration iRestaurantProfileDBORegistration = new RestaurantProfileDBORegistration();
		/*Coupon coupon = new Coupon();
		coupon.setCreationDate(new Date());
		DiscountOnAmount discountOnAmount = new DiscountOnAmount();
		discountOnAmount.setAmountDiscountOn("150");
		discountOnAmount.setPercentOfDiscount("10");
		coupon.setDiscountOnAmount(discountOnAmount);
		coupon.setExpirationDate(new Date(2015, 07, 20));
		coupon.setPromoCode("15010U");
		coupon.setPromoDetail("Get 10% off on $150");
		iRestaurantProfileDBORegistration.insertCoupon(coupon, false, 0);*/
		
		
		Coupon coupon = new Coupon();
		coupon.setCreationDate(new Date());
		DiscountOnItem discountOnItem = new DiscountOnItem();
		discountOnItem.setNumberOfItemRequire(3);
		discountOnItem.setNumberOfFreeItem(1);
		coupon.setDiscountOnItem(discountOnItem);
		coupon.setExpirationDate(new Date(2015, 8, 14));
		coupon.setPromoCode("ITLKSS");
		coupon.setPromoDetail("Get 3 Intalian Kiss and get 1 free");
		iRestaurantProfileDBORegistration.insertCoupon(coupon, true, 6);
		
		/*SessionFactoryBuilderSinglton.INSTANCE.buildHibernateSessionFactory();
		//session.delete((LoginInfo)session.get(LoginInfo.class,"Pritip009"));
		IUserProfileDBORegistration reg = new UserProfileDBORegistration();
		NewSecurityCodeRequestTO NewSecurityCodeRequestTO = new NewSecurityCodeRequestTO();
		NewSecurityCodeRequestTO.setUid("Pritip009");
		String newcode = reg.newSecurityCode(NewSecurityCodeRequestTO);
		System.out.println(newcode);
		VerifySecurityCodeTO VerifySecurityCodeTO = new VerifySecurityCodeTO();
		VerifySecurityCodeTO.setUid("Pritip009");
		VerifySecurityCodeTO.setVerifySecurityCode(newcode);
		Boolean flag = reg.verifySecurityCode(VerifySecurityCodeTO);
		System.out.println(flag.toString());
		RetrieveUserAddressListRequestTO retrieveUserAddressListRequestTO = new RetrieveUserAddressListRequestTO();
		retrieveUserAddressListRequestTO.setUid("Pritip009");
		List<AddressVO> list = reg.retriveUserAddresses(retrieveUserAddressListRequestTO);
		if(list!=null && list.size()>0){
			for(AddressVO addressVO:list){
				System.out.println(addressVO.getAddressId() + " "+ addressVO.getStreet());
			}
		}
		UpdateAddressDBOTO UpdateAddressDBOTO = new UpdateAddressDBOTO();
		Address address = new Address();
		address.setAddressType(AddressType.s);
		address.setStreet_Numer("90");
		address.setStreet("Duncan Ave");
		address.setTown("Jersey city");
		address.setState("New Jersey");
		address.setZip("07307");
		UpdateAddressDBOTO.setAddress(address);
		UpdateAddressDBOTO.setUserID("Pritip009");
		long addressId = reg.updateAddress(UpdateAddressDBOTO);
		System.out.println(addressId + " ");*/
		/*IRestaurantProfileDBORegistration restaurantProfileDBORegistration = new RestaurantProfileDBORegistration();
		for(RestaurantNameandAddressInfo restaurantNameandAddressInfo :restaurantProfileDBORegistration.retrieveRestaurantNameandAddressInfo()){
			System.out.println(restaurantNameandAddressInfo.getName() + " " + restaurantNameandAddressInfo.getTown() + " " + restaurantNameandAddressInfo.getState() + 
					" "+ restaurantNameandAddressInfo.getZipCode());
		}
		
		RestaurantSearchRequestTO restaurantSearchRequestTO = new RestaurantSearchRequestTO();
		restaurantSearchRequestTO.setName("vatan");
		restaurantSearchRequestTO.setState("NJ");
		restaurantSearchRequestTO.setTown("Hobken");
		restaurantSearchRequestTO.setZipCode("07067");
		
		restaurantProfileDBORegistration.searchRestaurantDBO(restaurantSearchRequestTO);*/
		/*RestaurantRegistrationDBORequestTO RestaurantRegistrationDBORequestTO = new RestaurantRegistrationDBORequestTO();
		RestaurantLoginInfo restaurantLoginInfo = new RestaurantLoginInfo();
		RestaurantProfile pr = new RestaurantProfile();
		RestaurantAddress adr  = new RestaurantAddress();
		RestaurantRegistrationDBORequestTO.setRestaurantLoginInfo(restaurantLoginInfo);
		RestaurantRegistrationDBORequestTO.setRestaurantProfile(pr);
		RestaurantRegistrationDBORequestTO.setRestaurantAddress(adr);

		restaurantLoginInfo.setUserId("ndl1234");
		restaurantLoginInfo.setPassword("test1ng");
		
		pr.setDelieverOption(Deliever.yes);
		pr.setDelieveryCharge("3");
		pr.setDelieveryMinimum("15");
		pr.setDescription("This registration is for test purpose");
		pr.setName("Noodle plus");
		pr.setPickUpOption(PickUp.yes);
		pr.setRating(0);
		pr.setReserveTableOption(ReserveTable.no);
		pr.setRestaurantLoginInfo(restaurantLoginInfo);
		pr.setType("Thai");
		pr.setPhoneNumber("347-890-7781");
		
		adr.setAddress1("");
		adr.setAddress2("");
		adr.setRestaurantLoginInfo(restaurantLoginInfo);
		adr.setState("NJ");
		adr.setStreet("Manchester Ct");
		adr.setStreetNumber("38");
		adr.setTown("Clifton");
		adr.setZip("07012");
		
		
		IRestaurantProfileDBORegistration restaurantProfileDBORegistration = new RestaurantProfileDBORegistration();
		restaurantProfileDBORegistration.registerRestaurant(RestaurantRegistrationDBORequestTO);
		System.out.println("Suucess:" + ResultDBOHelper.SUCCESS +" Result:"+ ResultDBOHelper.RESULT +" Error:" + ResultDBOHelper.ERROR);
		
		RestaurantMenuRegistrationDBORequestTO restaurantMenuRegistrationDBORequestTO = new  RestaurantMenuRegistrationDBORequestTO();
		restaurantMenuRegistrationDBORequestTO.setRid("ndl1234");
		Bread bread = new Bread();
		bread.setCurrentRating("4.5");
		bread.setDescription("This is a description of bread 1");
		bread.setName("Bread1");
		bread.setNumberOfPeopleRated("50");
		bread.setPrice("3");
		bread.setRating("4");
		bread.setSpecialEntry(SpecialEntry.no);
		
		Bread bread1 = new Bread();
		bread1.setCurrentRating("4.5");
		bread1.setDescription("This is a description of bread 2");
		bread1.setName("Bread2");
		bread1.setNumberOfPeopleRated("40");
		bread1.setPrice("4");
		bread1.setRating("4");
		bread1.setSpecialEntry(SpecialEntry.yes);
		
		Bread bread2 = new Bread();
		bread2.setCurrentRating("4.5");
		bread2.setDescription("This is a description of bread 3");
		bread2.setName("Bread3");
		bread2.setNumberOfPeopleRated("20");
		bread2.setPrice("5");
		bread2.setRating("3.3");
		bread2.setSpecialEntry(SpecialEntry.yes);
		
		Drink drink = new Drink();
		drink.setCurrentRating("4.5");
		drink.setDescription("This is a description of drink 1");
		drink.setName("drink1");
		drink.setNumberOfPeopleRated("25");
		drink.setPrice("8");
		drink.setRating("4.4");
		drink.setSpecialEntry(SpecialEntry.yes);
		
		Dish dish = new Dish();
		dish.setCurrentRating("4.5");
		dish.setDescription("This is a description of dish 2");
		dish.setName("dish2");
		dish.setNumberOfPeopleRated("34");
		dish.setPrice("15");
		dish.setRating("3.4");
		dish.setSpecialEntry(SpecialEntry.no);
		
		Dish dish1 = new Dish();
		dish1.setCurrentRating("4.5");
		dish1.setDescription("This is a description of dish 1");
		dish1.setName("dish1");
		dish1.setNumberOfPeopleRated("23");
		dish1.setPrice("13");
		dish1.setRating("4.1");
		dish1.setSpecialEntry(SpecialEntry.yes);
		
		List<Bread> breadList = new ArrayList<>();
		breadList.add(bread);
		breadList.add(bread1);
		breadList.add(bread2);
		List<Drink> drinkList = new ArrayList<>();
		drinkList.add(drink);
		List<Dish> dishList = new ArrayList<>();
		dishList.add(dish);
		dishList.add(dish1);
		
		restaurantMenuRegistrationDBORequestTO.setBreadList(breadList);
		restaurantMenuRegistrationDBORequestTO.setDishList(dishList);
		restaurantMenuRegistrationDBORequestTO.setDrinkList(drinkList);
		
		restaurantProfileDBORegistration.registerRestaurantMenuProfile(restaurantMenuRegistrationDBORequestTO);
		System.out.println("Suucess:" + ResultDBOHelper.SUCCESS +" Result:"+ ResultDBOHelper.RESULT +" Error:" + ResultDBOHelper.ERROR);
		
		LoginInfo lgnInfo = new LoginInfo();
		UserProfile profile = new UserProfile(); 
		Address adr1 = new Address();
		lgnInfo.setUserId("Priti05");
		lgnInfo.setPassword("Project2015");
		profile.setFirstname("Priti");
		profile.setLastName("Patel");
		profile.setPhone_number("3478217781");
		profile.setEmail("priti_patel05@ymail.com");
		profile.setProfileCreationDate();
		Collection<Address> adrList = new ArrayList<>();
		adrList.add(adr1);
		adr1.setStreet_Numer("224");
		adr1.setStreet("Manchester ct");
		adr1.setState("NJ");
		
		IUserProfileDBORegistration reg = new UserProfileDBORegistration();
		RegistrationDBORequestTO registrationDBORequestTO = new RegistrationDBORequestTO();
		registrationDBORequestTO.setLogininfo(lgnInfo);
		registrationDBORequestTO.setUserProfile(profile);
		registrationDBORequestTO.setAddress(adr1);
		reg.submitUserProfile(registrationDBORequestTO);

		Ilogin lgn = new Login();
		String password = lgn.retrievePassword("Pritip009");
		System.out.println("Your password is " + password + "Result is " + ResultDBOHelper.RESULT+" " + ResultDBOHelper.ERROR);*/

	}

}
