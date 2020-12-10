package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVIterator(reader,IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
            return this.getNoOFEntriesInCSV(csvIterable);

        } catch (IOException | RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            Iterator<CSVStates> stateCSVIterator = new OpenCSVBuilder().getCSVIterator(reader,CSVStates.class);
            Iterable<CSVStates> csvIterable = () -> stateCSVIterator;
            return this.getNoOFEntriesInCSV(csvIterable);
        }  catch (IOException | RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }



    private <E>int getNoOFEntriesInCSV(Iterable<E> csvIterable){
        return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
    }
}
