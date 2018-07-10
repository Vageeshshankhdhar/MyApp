package com.trainingbasket.loginapp.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import com.trainingbasket.loginapp.beans.*;



public class CallSummaryService {
	FileReader fileReader;
	BufferedReader br;
	FileWriter fileWriter;
	List<CallRecord> listOfCallRecords;
	Map<String,List<Summary>> map=new TreeMap<String,List<Summary>>();
	/**
	 * This is the constructor of the class
	 */
	public CallSummaryService(String inputFIlePath, String outputFilePath){
		try {
			fileReader=new FileReader(inputFIlePath);//fileReader=new FileReader("123.txt");
				br=new BufferedReader(fileReader);
				fileWriter=new FileWriter(outputFilePath);	//fileWriter=new FileWriter("CallSummary.csv");
				listOfCallRecords=new ArrayList<CallRecord>();
			}catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
	}
	/**
	 * This method reads the text from the text file and store it into the buffer if the line is valid.
	 *
	 */
	public void readFile(){
		StringBuffer sb=new StringBuffer();
		String str;
		try {

				while((str = br.readLine())!=null){
					if(checkValidLine(str)){
						sb.append(str+"\n");
					}
				}
				createListOfCallRecords(sb);
				createListForCallSummary();
		}catch (IOException e) {
				System.out.println(e.getMessage());
		}

	}
	/**
	 * This method create a list of call records
	 * @param sb String Buffer Object
	 */
	private void createListOfCallRecords(StringBuffer sb){


		String line=sb.toString();
		String[] str=line.split("\n");

		int size=0;
		for(int i=0;i<str.length;i++)
		{
			CallRecord callrecord=new CallRecord();
			String[] lineParts=str[i].split("\\s+");
			size=lineParts.length;

			int pos=0;
			for( ; pos < lineParts.length ; pos++){
				if(lineParts[pos].length()>=7){
					break;
				}
			}
			callrecord.setSrNo(lineParts[0]);
			callrecord.setExtension(lineParts[1]);
			if(pos==3){
				callrecord.setMics(" ");
				callrecord.setJunction(lineParts[2]);
				callrecord.setDirectoryNo(lineParts[3]);
				callrecord.setDate(lineParts[4]);
				callrecord.setTime(lineParts[5]);
				callrecord.setDuration(lineParts[6]);
				callrecord.setBillAmount(lineParts[7]);
				if(size==8){
					callrecord.setAcc(" ");
					callrecord.setFlag(" ");
				}
				else
					if(size==9 && lineParts[8].equals("S")){
						callrecord.setAcc(lineParts[8]);
						callrecord.setFlag(" ");
					}
					else
						if(size==10 && lineParts[8].equals("S") && (lineParts[9].equals("R") ||lineParts[9].equals("U") || lineParts[9].equals("*") || lineParts[9].equals("#") || lineParts[9].equals("R*") || lineParts[9].equals("R#"))){
							callrecord.setAcc(lineParts[8]);
							callrecord.setFlag(lineParts[9]);
						}
						else
							if(size==9 && (lineParts[8].equals("R") ||lineParts[8].equals("U") || lineParts[8].equals("*") || lineParts[8].equals("#") || lineParts[8].equals("R*") || lineParts[8].equals("R#"))){
								callrecord.setAcc(" ");
								callrecord.setFlag(lineParts[8]);
							}
				}
				else
					if(pos==4){
						callrecord.setMics(lineParts[2]);
						callrecord.setJunction(lineParts[3]);
						callrecord.setDirectoryNo(lineParts[4]);
						callrecord.setDate(lineParts[5]);
						callrecord.setTime(lineParts[6]);
						callrecord.setDuration(lineParts[7]);
						callrecord.setBillAmount(lineParts[8]);
						if(size==11 && lineParts[9].equals("S") && (lineParts[10].equals("R") ||lineParts[10].equals("U") || lineParts[10].equals("*") || lineParts[10].equals("#") || lineParts[10].equals("R*") || lineParts[10].equals("R#"))){
							callrecord.setAcc(lineParts[8]);
							callrecord.setFlag(lineParts[9]);
						}
						else
							if(size==10 && lineParts[9].equals("S")){
								callrecord.setAcc(lineParts[9]);
								callrecord.setFlag(" ");
							}
							else
								if(size==10 && lineParts[9].equals("R") ||lineParts[9].equals("U") || lineParts[9].equals("*") || lineParts[9].equals("#") || lineParts[9].equals("R*") || lineParts[9].equals("R#")){
									callrecord.setAcc(" ");
									callrecord.setFlag(lineParts[9]);
								}
								else
									if(size==9){

										callrecord.setAcc(" ");
										callrecord.setFlag(" ");
									}
						}
			listOfCallRecords.add(callrecord);
		}
	}
	/**
	 * This method creates the list for call summary
	 *
	 */
	private void createListForCallSummary(){
		Set setExtensionDate=new HashSet();
		Iterator<CallRecord> listIterator=listOfCallRecords.iterator();
		while(listIterator.hasNext()){
			CallRecord callrecord=listIterator.next();
			Object extension=callrecord.getExtension();
			Object date=callrecord.getDate();
			setExtensionDate.add(extension+" "+date);
		}

		Iterator iterator=setExtensionDate.iterator();
		while(iterator.hasNext()){
			Object object=iterator.next();
			String[] data=object.toString().split(" ");
			getList(data[0],data[1]);


		}
	}
/**
 * This method gets the list of call records
 * @param extension extention number
 * @param date date wrt that extention no.
 */
	private void getList(String extension, String date){
		List<Summary> listSummary=new ArrayList<Summary>();
		int hr=0,min=0,totalCallNumbers=0;
		Iterator<CallRecord> listIterator=listOfCallRecords.iterator();
		while(listIterator.hasNext()){
			CallRecord callrecord=listIterator.next();
			if(callrecord.getExtension().equalsIgnoreCase(extension)&& callrecord.getDate().equalsIgnoreCase(date)){

				String[] dateParts=callrecord.getDuration().split(":");
					min=min+Integer.parseInt(dateParts[1]);
					hr=hr+Integer.parseInt(dateParts[0]);
			         while(min>=60)
			         {
			             hr++;
			             min=min-60;
			         }
			         totalCallNumbers++;
			}
		}

		String totalCallTime=Integer.toString(hr)+":"+Integer.toString(min);

		double averageTime=getAverageCall(hr,min,totalCallNumbers);
		Summary summary=new Summary();

		summary.setDate(date);
		summary.setTotalCallTime(totalCallTime);
		summary.setTotalCallNumber(Integer.toString(totalCallNumbers));
		summary.setAverageCall(Double.toString(averageTime));
		listSummary.add(summary);
		String exDate=extension+" "+date;
		map.put(exDate, listSummary);
}

	/**
	 * This method writes the data into the call summary file
	 *
	 */
	public void writeInFile(){
		Iterator<String> mapIterator=map.keySet().iterator();
		try{
			String heading="Extension,Date,TotalCallTime,TotalCallNumber,AverageCall\n";
			fileWriter.write(heading);
			while(mapIterator.hasNext()) {
				String str=mapIterator.next();
				List<Summary> summary=map.get(str);
				String extensionDate[]=str.split(" ");
				Iterator<Summary> summaryIterator=summary.iterator();

				while(summaryIterator.hasNext()){
						Summary summaryData=summaryIterator.next();

						fileWriter.write(extensionDate[0]+",");
						fileWriter.write(extensionDate[1]+",");
						fileWriter.write(summaryData.getTotalCallTime()+",");
						fileWriter.write(summaryData.getTotalCallNumber()+",");
						fileWriter.write(summaryData.getAverageCall()+"\n");
						fileWriter.flush();
				}
			}

		}
		catch(IOException e){
			System.out.println("exception");
		}
	}

	/**
	 * This method gets the average of all calls
	 * @param hr hours
	 * @param min minutes
	 * @param totalCallNumbers total no. of calls
	 * @return average time
	 */
	private double getAverageCall(int hr,int min,int totalCallNumbers){
		String time=Integer.toString(hr)+"."+Integer.toString(min);
		double totalTime=Double.parseDouble(time);
		double average=totalTime/totalCallNumbers;
		double averageTime=roundTwoDecimals(average);
		return averageTime;
	}

	private double roundTwoDecimals(double d){
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}
	/**
	 * This method checks the whether the string is valid or not.
	 * @param sb string object
	 * @return boolean value
	 */
	private static boolean checkValidLine(String sb) {
		try{
			Integer.parseInt(sb.split("\\s+")[0]);
			return true;
		}catch(NumberFormatException e){
			return false;
		}catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

}
