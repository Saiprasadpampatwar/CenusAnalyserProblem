package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
    @CsvBindByName(column = "SrNo", required = true)
    public String srNo;
    @CsvBindByName(column = "State Name", required = true)
    public String stateName;
    @CsvBindByName(column = "TIN", required = true)
    public int tin;
    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    @Override
    public String toString() {
        return "IndiaStateCSV{" + "srNo='" + srNo + '\'' + ", stateName='" + stateName + '\'' + ", tin=" + tin
                + ", stateCode='" + stateCode + '\'' + '}';
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
