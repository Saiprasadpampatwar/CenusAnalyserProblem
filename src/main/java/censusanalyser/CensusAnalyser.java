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

            Iterator<IndiaCensusCSV> censusCSVIterator = this.getCSVIterator(reader,IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
            return this.getNoOFEntriesInCSV(csvIterable);

        } catch (IOException | RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            Iterator<CSVStates> stateCSVIterator = this.getCSVIterator(reader,CSVStates.class);
            Iterable<CSVStates> csvIterable = () -> stateCSVIterator;
            return this.getNoOFEntriesInCSV(csvIterable);
        }  catch (IOException | RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }

    private <E>Iterator<E> getCSVIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
       try {
           CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
           csvToBeanBuilder.withType(csvClass);
           csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
           CsvToBean<E> csvToBean = csvToBeanBuilder.build();
           return csvToBean.iterator();
       }catch (IllegalStateException e) {
           throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
       }

    }

    private <E>int getNoOFEntriesInCSV(Iterable<E> csvIterable){
        return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
    }
}
