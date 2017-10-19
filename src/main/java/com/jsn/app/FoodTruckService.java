/**
 * 
 */
package com.jsn.app;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
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
		checkPermitExpiry();
		foodTruckRepository.save(foodTruck);
	}

	public List<FoodTruck> getAllFoodTrucks() {
		checkPermitExpiry();
		List<FoodTruck> truckList = new ArrayList<FoodTruck>();
		foodTruckRepository.findAll().forEach(truckList::add);

		return truckList;
	}

	public FoodTruck getFoodTruckById(Integer id) {
		checkPermitExpiry();
		return foodTruckRepository.findOne(id);
	}

	public List<FoodTruck> getFoodTruckByApplicant(String applicant) {
		checkPermitExpiry();
		List<FoodTruck> truckList = (List<FoodTruck>) foodTruckRepository.findAll();

		List<FoodTruck> result = new ArrayList<FoodTruck>();

		Long presentDate = getPresentDate();

		for (FoodTruck truck : truckList) {
			if (truck.getExpirationdate() != null) {
				//					System.out.println("Expiration Date : " + truck.getExpirationdate().getTime());
				Long expirationDate = truck.getExpirationdate().getTime();
				if (!truck.getStatus().equals("EXPIRED") && Long.compareUnsigned(expirationDate, presentDate) <= 0) {
					//						System.out.println("Truck permit expired : " + truck.getId());
					truck.setStatus("EXPIRED");
				}
			}
			if (truck.getApplicant().toLowerCase().equals(applicant.toLowerCase()))
				result.add(truck);
		}

		return result;
	}

	public List<FoodTruck> getFoodTruckByStreetName(String street) {
		checkPermitExpiry();
		List<FoodTruck> truckList = (List<FoodTruck>) foodTruckRepository.findAll();

		List<FoodTruck> result = new ArrayList<FoodTruck>();

		Long presentDate = getPresentDate();

		for (FoodTruck truck : truckList) {
			if (truck.getExpirationdate() != null) {
				//					System.out.println("Expiration Date : " + truck.getExpirationdate().getTime());
				Long expirationDate = truck.getExpirationdate().getTime();
				if (!truck.getStatus().equals("EXPIRED") && Long.compareUnsigned(expirationDate, presentDate) <= 0) {
					//						System.out.println("Truck permit expired : " + truck.getId());
					truck.setStatus("EXPIRED");
				}
			}
			if (truck.getAddress().toLowerCase().equals(street.toLowerCase()))
				result.add(truck);
		}

		return result;
	}

	public void addFoodTruck(FoodTruck foodTruck) {
		checkPermitExpiry();
		foodTruckRepository.save(foodTruck);
	}

	public void deleteFoodTruck(Integer Id) {
		checkPermitExpiry();
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
		Long presentDate = getPresentDate();
		String expiryDate;
		try {
			expiryDate = expiryDateJson.getString("searchdate");

			//			System.out.println("ExpiryDate : " + expiryDate);
			@SuppressWarnings("deprecation")
			Long searchDate = Timestamp.parse(expiryDate);
			//			System.out.println("SearchDate : " + searchDate);
			List<FoodTruck> truckList = (List<FoodTruck>) foodTruckRepository.findAll();

			for (FoodTruck truck : truckList) {

				if (truck.getExpirationdate() != null) {
					//					System.out.println("Expiration Date : " + truck.getExpirationdate().getTime());

					Long expirationDate = truck.getExpirationdate().getTime();
					if (!truck.getStatus().equals("EXPIRED") && Long.compareUnsigned(expirationDate, presentDate) <= 0) {
						//						System.out.println("Truck permit expired : " + truck.getId());
						truck.setStatus("EXPIRED");
					}
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

	public Long getPresentDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa");
		Date date = new Date();
		System.out.println("Present Date : " + dateFormat.format(date)); //2016/11/16 12:08:43

		//			System.out.println("ExpiryDate : " + expiryDate);
		return date.getTime();
	}

	public void checkPermitExpiry() {

		List<FoodTruck> expired = new ArrayList<FoodTruck>();
		try {
			List<FoodTruck> truckList = (List<FoodTruck>) foodTruckRepository.findAll();

			//			System.out.println("ExpiryDate : " + expiryDate);
			Long presentDate = getPresentDate();
			//			System.out.println("SearchDate : " + searchDate);

			for (FoodTruck truck : truckList) {

				if (truck.getExpirationdate() != null) {
					//					System.out.println("Expiration Date : " + truck.getExpirationdate().getTime());
					Long expirationDate = truck.getExpirationdate().getTime();
					if (!truck.getStatus().equals("EXPIRED") && Long.compareUnsigned(expirationDate, presentDate) <= 0) {
						//						System.out.println("Truck permit expired : " + truck.getId());
						truck.setStatus("EXPIRED");
						expired.add(truck);
					}
				}
			}

			System.out.println(expired.size() + " trucks permit newly expired");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public FoodTruck getFoodTruckByLocation(String locations) {

		System.out.println("locations input : " + locations);
		JSONObject locationsJson = new JSONObject(locations);
		JSONArray locationArray = locationsJson.getJSONArray("locations");

		//		System.out.println("Location Array : " + locationArray.toString());

		List<FoodTruck> truckList = (List<FoodTruck>) foodTruckRepository.findAll();

		Double[][] distances = new Double[truckList.size()][locationArray.length()];

		FoodTruck nearestTruck = new FoodTruck();

		Double minDistance = Double.MAX_VALUE;
		Integer minIndex = null;

		Long presentDate = getPresentDate();

		for (int i = 0; i < truckList.size(); i++) {
			FoodTruck truck = truckList.get(i);

			if (truck.getExpirationdate() != null) {
				//					System.out.println("Expiration Date : " + truck.getExpirationdate().getTime());
				Long expirationDate = truck.getExpirationdate().getTime();
				if (!truck.getStatus().equals("EXPIRED") && Long.compareUnsigned(expirationDate, presentDate) <= 0) {
					//						System.out.println("Truck permit expired : " + truck.getId());
					truck.setStatus("EXPIRED");
				}
			}
			Double tempMinDistance = 0.0;
			for (int j = 0; j < locationArray.length(); j++) {
				Double latitude = locationArray.getJSONObject(j).getDouble("latitude");
				Double longitude = locationArray.getJSONObject(j).getDouble("longitude");

				Double truckLatitude = truck.getLatitude() == null ? 0.0 : truck.getLatitude();
				Double truckLongitude = truck.getLongitude() == null ? 0.0 : truck.getLongitude();
				Double distance = findDistance(truckLatitude, truckLongitude, latitude, longitude);
				distances[i][j] = distance;
				tempMinDistance += distance;
			}

			if (Double.compare(tempMinDistance, minDistance) <= 0) {
				minDistance = tempMinDistance;
				minIndex = i;
				//				System.out.println("Min value changed to : " + minIndex);
			}
		}

		if (minIndex != null) {
			nearestTruck = truckList.get(minIndex);
		}

		return nearestTruck;
	}

	/**
	 * @param latitude
	 * @param longitude
	 * @param latitude2
	 * @param longitude2
	 * @return
	 */
	private Double findDistance(Double truckLatitude, Double truckLongitude, Double latitude, Double longitude) {
		//		System.out.println("truckLatitude : " + truckLatitude + "\ttruckLongitude : " + truckLongitude + "\tlatitude : " + latitude + "\tlongitude : " + longitude);
		Double a = Math.pow((truckLatitude - latitude), 2);
		Double b = Math.pow((truckLongitude - longitude), 2);
		return Math.sqrt(a + b);
	}
}
