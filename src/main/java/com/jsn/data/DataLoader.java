package com.jsn.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.jsn.app.FoodTruck;
import com.jsn.app.FoodTruckService;

public class DataLoader {

	static final String DELIMITER = "\t";
	static final String fileName = "data/Mobile_Food_Facility_Permit.tsv";

	public static boolean loadData(FoodTruckService foodTruckService) {

		System.out.println("Loading data....");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			//		CSVReader reader = new CSVReader(new FileReader(fileName), ',');
			String row = null;
			String[] record = null;
			int count = 0;
			boolean ignoreHeader = true;
			if (ignoreHeader) {
				row = reader.readLine();
			}
			while ((row = reader.readLine()) != null) {
				record = row.split(DELIMITER);
				if (record.length > 0) {
					//					System.out.println(record[0]);
					FoodTruck newFoodTruck = new FoodTruck();
					newFoodTruck.setLocationid(record[0]);
					newFoodTruck.setApplicant(record[1]);
					newFoodTruck.setFacilitytype(record[2]);
					newFoodTruck.setCnn(record[3]);
					newFoodTruck.setLocationdescription(record[4]);
					newFoodTruck.setAddress(record[5]);
					newFoodTruck.setBlocklot(record[6]);
					newFoodTruck.setBlock(record[7]);
					newFoodTruck.setLot(record[8]);
					newFoodTruck.setPermit(record[9]);
					newFoodTruck.setStatus(record[10]);
					newFoodTruck.setFooditems(record[11]);
					if (!record[12].isEmpty())
						newFoodTruck.setX(Double.parseDouble(record[12]));
					if (!record[13].isEmpty())
						newFoodTruck.setY(Double.parseDouble(record[13]));
					if (!record[14].isEmpty())
						newFoodTruck.setLatitude(Double.parseDouble(record[14]));
					if (!record[15].isEmpty())
						newFoodTruck.setLongitude(Double.parseDouble(record[15]));
					newFoodTruck.setSchedule(record[16]);
					newFoodTruck.setDayshours(record[17]);
					DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa");
					DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
					try {
						if (!record[18].isEmpty())
							newFoodTruck.setNoisent(df1.parse(record[18]));
						if (!record[19].isEmpty())
							newFoodTruck.setApproved(df1.parse(record[19]));
						if (!record[22].isEmpty())
							newFoodTruck.setExpirationdate(df1.parse(record[22]));
						if (!record[20].isEmpty())
							newFoodTruck.setReceived(df2.parse(record[20]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					newFoodTruck.setPriorpermit(record[21]);
					newFoodTruck.setLocation(record[23]);
					count++;
					foodTruckService.createFoodTruck(newFoodTruck);
				}
			}

			System.out.println(count + " records uploaded");
			reader.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
