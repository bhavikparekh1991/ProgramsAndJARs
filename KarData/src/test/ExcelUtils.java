package test;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {
	 private static XSSFSheet ExcelWSheet;
     private static XSSFWorkbook ExcelWBook;
     private static org.apache.poi.ss.usermodel.Cell Cell;
     private static XSSFRow Row;
     public static  int RowCount;
    // static Logger log = Logger.getLogger(ExcelUtils.class.getName());

     	public static void setExcelFile(String Path) throws Exception {
			try {
				FileInputStream ExcelFile = new FileInputStream(Path);
				ExcelWBook = new XSSFWorkbook(ExcelFile);
			} catch (Exception e){
				//log.info("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
				}
			}

		public static String getCellData(int RowNum, int ColNum, String SheetName ) throws Exception{
			try{
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
				Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
				String CellData = Cell.getStringCellValue().toString();
				System.out.println(CellData);
				return CellData;
			 }catch (Exception e){
				 //log.info("Class Utils | Method getCellData | Exception desc : "+e.getMessage());
				 return"";
				 }
			 }

		public static int getRowCount(String SheetName){
			int iNumber=0;
			try {
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
				iNumber=ExcelWSheet.getLastRowNum()+1;
				System.out.println(iNumber);
				RowCount=iNumber;
			} catch (Exception e){
				//log.info("Class Utils | Method getRowCount | Exception desc : "+e.getMessage());
				
				}
			return iNumber;
			}

		public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
			int iRowNum=0;	
			try {
				//ExcelWSheet = ExcelWBook.getSheet(SheetName);
				int rowCount = ExcelUtils.getRowCount(SheetName);
				for (; iRowNum<rowCount; iRowNum++){
					if  (ExcelUtils.getCellData(iRowNum,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
						break;
					}
				}       			
			} catch (Exception e){
				//log.info("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
				
				}
			return iRowNum;
			}

		/*public static int getDataSetsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
			try {
				int rowcount = ExcelUtils.getRowCount(SheetName);
				for(int i=iTestCaseStart;i<=rowcount;i++){
					if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))){
						int number = i-1;
						return number;      				
						}
					}
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
				int number=ExcelWSheet.getLastRowNum()+1;
				return number;
			} catch (Exception e){
				log.info("Class Utils | Method getDataSetsCount | Exception desc : "+e.getMessage());
				
				return 0;
			}
		}*/
		
		/*public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    {
			   try{
		 
				   ExcelWSheet = ExcelWBook.getSheet(SheetName);
				   Row  = ExcelWSheet.getRow(RowNum);
				   Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
				   if (Cell == null) {
					   Cell = Row.createCell(ColNum);
					   Cell.setCellValue(Result);
				   } else {
						Cell.setCellValue(Result);
						}
					// Constant variables Test Data path and Test Data file name
					FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData);
					ExcelWBook.write(fileOut);
					//fileOut.flush();
					fileOut.close();
					ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestData));
				 }catch(Exception e){
					 log.info("Class Utils | Method setCellData | Exception desc : "+e.getMessage());
					}
				}
*/
		
		}
