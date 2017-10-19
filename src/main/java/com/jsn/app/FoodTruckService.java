/**
 * 
 */
package com.jsn.app;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public List<FoodTruck> getFoodTruckByExpiryDate(String expiryDate) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa");
		List<FoodTruck> result = new ArrayList<FoodTruck>();
		try {
			Date searchDate = df.parse(expiryDate);

			List<FoodTruck> truckList = (List<FoodTruck>) foodTruckRepository.findAll();

			for (FoodTruck truck : truckList) {
				if (truck.getExpirationdate().before(searchDate))
					result.add(truck);
			}

			return result;
		} catch (ParseException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public FoodTruck getFoodTruckByLocation(String locations) {
		List<FoodTruck> truckList = (List<FoodTruck>) foodTruckRepository.findAll();

		FoodTruck nearestTruck = null;

		return nearestTruck;
	}
}
