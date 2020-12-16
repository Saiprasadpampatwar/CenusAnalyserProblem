package censusanalyser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jar.builder.CSVBuilderException;
import jar.builder.CSVBuilderFactory;
import jar.builder.ICSVBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser implements Comparator{
    public static final String PATH_JSON = "C:\\Users\\saiprasad\\IdeaProjects\\CenusAnalyserProblem\\CenusAnalyserProblem\\src\\test\\resources\\IndiaStateCensusDataJson.json";
    public static final String PATH_JSON1 = "C:\\Users\\saiprasad\\IdeaProjects\\CenusAnalyserProblem\\CenusAnalyserProblem\\src\\test\\resources\\IndiaStateCodeDataJson.json";
    public static final String PATH_JSON_SORTING_POPULATION = "C:\\Users\\saiprasad\\IdeaProjects\\CenusAnalyserProblem\\CenusAnalyserProblem\\src\\test\\resources\\IndiaStateCodeDataJson.json";

    public  static  List<IndiaCensusCSV> record = null;
    public static  List<CSVStates> stateCSVList = null;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
             CSVReader csvReader = new CSVReader(reader);){

             record = getCSVFileList(reader,IndiaCensusCSV.class);
             return record.size();

        } catch (IOException | RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
             CSVReader csvReader = new CSVReader(reader);) {

            stateCSVList = getCSVFileList(reader,CSVStates.class);
            return stateCSVList.size();

        }  catch (IOException | RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }

    public int loadCSVusingCommonCSV(String csvfilePath) throws CensusAnalyserException {
        try(
                Reader reader = Files.newBufferedReader(Paths.get(csvfilePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
                ){
            List<CSVRecord> csvRecords = csvParser.getRecords();
            return csvRecords.size();
        }catch (IOException e){
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }

    public <E>List getCSVFileList(Reader reader, Class csvClass) throws CensusAnalyserException {

        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
        csvToBeanBuilder.withType(csvClass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        CsvToBean<E> csvToBean = csvToBeanBuilder.build();
        return csvToBean.parse();
    }


    @Override
    public int compare(Object o1, Object o2) {
        IndiaCensusCSV state1 = (IndiaCensusCSV)o1;
        IndiaCensusCSV state2 = (IndiaCensusCSV)o2;

        return state1.state.compareTo(state2.state);
    }

    public void sortData() throws IOException {
        Collections.sort(record,new CensusAnalyser());
        Gson gson = new Gson();
        String json = gson.toJson(record);
        FileWriter fileWriter = new FileWriter(PATH_JSON);
        fileWriter.write(json);
        fileWriter.close();
    }

    public void sortBasedOnStateCode() throws IOException {
        Comparator com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                CSVStates s1 = (CSVStates)o1;
                CSVStates s2 = (CSVStates)o2;
                return s1.stateCode.compareTo(s2.stateCode);
            }
        };
        Collections.sort(stateCSVList,com);
        Gson gson = new Gson();
        String json = gson.toJson(record);
        FileWriter fileWriter = new FileWriter(PATH_JSON1);
        fileWriter.write(json);
        fileWriter.close();
    }

    public void sortDataByPopulation() throws IOException {
        Comparator com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                IndiaCensusCSV s1 = (IndiaCensusCSV) o1;
                IndiaCensusCSV s2 = (IndiaCensusCSV) o2;
                if(s1.population>s2.population)
                    return -3;
                else
                    return 3;
            }
        };
        Collections.sort(record,com);
        Gson gson = new Gson();
        String json = gson.toJson(record);
        FileWriter fileWriter = new FileWriter(PATH_JSON_SORTING_POPULATION);
        fileWriter.write(json);
        fileWriter.close();
    }
}


