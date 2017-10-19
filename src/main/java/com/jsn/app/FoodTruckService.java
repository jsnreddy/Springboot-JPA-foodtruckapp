/**
 * 
 */
package com.jsn.app;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Surendranath Reddy
 *
 */

@Service
public class FoodTruckService {

	@Autowired
	private FoodTruckRepository foodTruckRepository;

	public void createFoodTruck(FoodTruck foodTruck) {
		foodTruckRepository.save(foodTruck);
	}

	public List<FoodTruck> getAllFoodTrucks() {
		return (List) foodTruckRepository.findAll();
	}

	public FoodTruck getFoodTruckById(Integer id) {
		return foodTruckRepository.findOne(id);
	}

	public List<FoodTruck> getFoodTruckByApplicant(String applicant) {
		List<FoodTruck> truckList = (List<FoodTruck>) foodTruckRepository.findAll();

		List<FoodTruck> result = new ArrayList<FoodTruck>();

		for (FoodTruck truck : truckList) {
			if (truck.getApplicant().toLowerCase().equals(applicant.toLowerCase()))
				result.add(truck);
		}

		return result;
	}

	public List<FoodTruck> getFoodTruckByStreetName(String street) {
		List<FoodTruck> truckList = (List<FoodTruck>) foodTruckRepository.findAll();

		List<FoodTruck> result = new ArrayList<FoodTruck>();

		for (FoodTruck truck : truckList) {
			if (truck.getAddress().toLowerCase().equals(street.toLowerCase()))
				result.add(truck);
		}

		return result;
	}

	public void addFoodTruck(FoodTruck foodTruck) {
		foodTruckRepository.save(foodTruck);
	}

	public void deleteFoodTruck(Integer Id) {
		foodTruckRepository.delete(Id);
	}

	/**
	 * @param expiryDate
	 * @return
	 */
	public List<FoodTruck> getFoodTruckByExpiryDate(String expiryDateJsonString) {
		// TODO Auto-generated method stub
		//		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa");

		//		System.out.println("expiryDateJson : " + expiryDateJsonString);
		JSONObject expiryDateJson = new JSONObject(expiryDateJsonString);
		List<FoodTruck> result = new ArrayList<FoodTruck>();
		String expiryDate;
		try {
			expiryDate = expiryDateJson.getString("searchdate");

			//			System.out.println("ExpiryDate : " + expiryDate);
			Long searchDate = Timestamp.parse(expiryDate);
			//			System.out.println("SearchDate : " + searchDate);
			List<FoodTruck> truckList = (List<FoodTruck>) foodTruckRepository.findAll();

			for (FoodTruck truck : truckList) {

				if (truck.getExpirationdate() != null) {
					//					System.out.println("Expiration Date : " + truck.getExpirationdate().getTime());
					Long expirationDate = truck.getExpirationdate().getTime();
					if (Long.compareUnsigned(expirationDate, searchDate) <= 0) {
						//						System.out.println("Truck permit expired : " + truck.getId());
						result.add(truck);
					}
				}
			}

			System.out.println(result.size() + " trucks permit expired");
			return result;
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

	}

	public FoodTruck getFoodTruckByLocation(String locations) {
		List<FoodTruck> truckList = (List<FoodTruck>) foodTruckRepository.findAll();

		FoodTruck nearestTruck = null;

		return nearestTruck;
	}
}
