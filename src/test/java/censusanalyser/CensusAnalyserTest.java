package censusanalyser;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.List;

public class CensusAnalyserTest {


    private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\saiprasad\\IdeaProjects\\CenusAnalyserProblem\\CenusAnalyserProblem\\src\\test\\resources\\IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String CORRECT_PATH_WRONG_CSV_FILE = "C:\\Users\\saiprasad\\IdeaProjects\\CenusAnalyserProblem\\CenusAnalyserProblem\\src\\test\\resources\\IndiaStateCensusData.txt";
    private static final String WRONG_DELIMITER_PATH = "C:\\Users\\saiprasad\\IdeaProjects\\CenusAnalyserProblem\\CenusAnalyserProblem\\src\\test\\resources\\StateCensusData.csv";
    private static final String WRONG_HEADER_PATH = "C:\\Users\\saiprasad\\IdeaProjects\\CenusAnalyserProblem\\CenusAnalyserProblem\\src\\test\\resources\\StateCensusDataWrongHeader.csv";
    private static final String INDIA_STATECODE_CSV_FILE_PATH = "C:\\Users\\saiprasad\\IdeaProjects\\CenusAnalyserProblem\\CenusAnalyserProblem\\src\\test\\resources\\IndiaStateCode.csv";
    private static final String WRONG_STATECODE_CSV_FILE_PATH = "./src/main/resources/IndianStateCode.txt";
    private static final String CORRECT_PATH_WRONG_STATECODE_CSV_FILE = "C:\\Users\\saiprasad\\IdeaProjects\\CenusAnalyserProblem\\CenusAnalyserProblem\\src\\test\\resources\\StateCodeData.txt";
    private static final String WRONG_STATECODE_CSV_DELIMITER_PATH = "C:\\Users\\saiprasad\\IdeaProjects\\CenusAnalyserProblem\\CenusAnalyserProblem\\src\\test\\resources\\StateCodeData.csv";
    private static final String WRONG_STATECODE_HEADER_PATH = "C:\\Users\\saiprasad\\IdeaProjects\\CenusAnalyserProblem\\CenusAnalyserProblem\\src\\test\\resources\\StateCodeDataWrongHeader.csv";


    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithCorrectPath_WrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(CORRECT_PATH_WRONG_CSV_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithCorrectPath_RemovedDelimeter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_DELIMITER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithCorrectPath_WrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndianStateCode(INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithCorrectPath_WrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(CORRECT_PATH_WRONG_STATECODE_CSV_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithCorrectPath_RemovedDelimeter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_STATECODE_CSV_DELIMITER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeData_WithCorrectPath_WrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_STATECODE_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecordsUsingCommonCSV() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCSVusingCommonCSV(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(30,numOfRecords);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndianStateCodeFileReturnsCorrectRecordsUsingCommonCSV() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCSVusingCommonCSV(INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(38, numOfRecords);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndianStateCodeFileReecordsUsingCommonCSV() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void sortingOfCensusDataAlphabetically() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        censusAnalyser.sortData();
        Assert.assertEquals("Andhra Pradesh",CensusAnalyser.record.get(0).state);
        Assert.assertEquals("West Bengal",CensusAnalyser.record.get(CensusAnalyser.record.size()-1).state);

    }

    @Test
    public void sortingOfCensusDataBasedOnStateCode() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndianStateCode(INDIA_STATECODE_CSV_FILE_PATH);
        censusAnalyser.sortBasedOnStateCode();
        Assert.assertEquals("AD",CensusAnalyser.stateCSVList.get(0).stateCode);

    }

    @Test
    public void sortingOfCensusDataByPopulation() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        censusAnalyser.sortDataByPopulation();
        Assert.assertEquals("Uttar Pradesh",CensusAnalyser.record.get(0).state);
    }

    @Test
    public void sortingOfCensusDataByDensity() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        censusAnalyser.sortDataByDensity();
        Assert.assertEquals("Bihar",CensusAnalyser.record.get(0).state);
    }

    @Test
    public void sortingOfCensusDataByArea() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        censusAnalyser.sortDataByArea();
        Assert.assertEquals("Rajasthan",CensusAnalyser.record.get(0).state);
    }

}
