package censusanalyser;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jar.builder.CSVBuilderException;
import jar.builder.CSVBuilderFactory;
import jar.builder.ICSVBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
             CSVReader csvReader = new CSVReader(reader);){
            List<String[]> records = csvReader.readAll();
            return records.size();

        } catch (IOException | RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
             CSVReader csvReader = new CSVReader(reader);) {
            List<String[]> records = csvReader.readAll();
            return records.size();
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
}
