/**
 * 
 */
package com.jsn.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jsn.data.DataLoader;

/**
 * @author Surendranath Reddy
 *
 */

@RestController
public class FoodTruckController {

	@Autowired
	private FoodTruckService foodTruckService;

	@RequestMapping("/hello")
	public String sayHello() {
		return "HI!!!";
	}

	@RequestMapping("/foodtrucks")
	public List<FoodTruck> getAllFoodTrucks() {
		return foodTruckService.getAllFoodTrucks();
	}

	@RequestMapping("/foodtrucks/id/{id}")
	public FoodTruck getFoodTruckById(@PathVariable Integer id) {
		return foodTruckService.getFoodTruckById(id);
	}

	@RequestMapping("/foodtrucks/applicant/{applicant}")
	public List<FoodTruck> getFoodTruckByApplicant(@PathVariable String applicant) {
		return foodTruckService.getFoodTruckByApplicant(applicant);
	}

	@RequestMapping("/foodtrucks/street/{street}")
	public List<FoodTruck> getFoodTruckByStreetName(@PathVariable String street) {
		return foodTruckService.getFoodTruckByStreetName(street);
	}

	@RequestMapping("/foodtrucks/expirydate/{expirydate}")
	public List<FoodTruck> getFoodTruckByExpiryDate(@PathVariable String expiryDate) {
		return foodTruckService.getFoodTruckByExpiryDate(expiryDate);
	}

	@RequestMapping("/foodtrucks/location/{location}")
	public FoodTruck getFoodTruckByLocation(@PathVariable String locations) {

		return foodTruckService.getFoodTruckByLocation(locations);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/foodtrucks")
	public void addFootTruck(@RequestBody FoodTruck foodtruck) {
		foodTruckService.addFoodTruck(foodtruck);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/foodtrucks/{id}")
	public void deleteFootTruck(@PathVariable Integer id) {
		foodTruckService.deleteFoodTruck(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/foodtrucks/upload")
	public String uploadData() {
		System.out.println("in Controller");
		Boolean result = DataLoader.loadData(foodTruckService);

		return result ? "1" : "0";
	}
}
