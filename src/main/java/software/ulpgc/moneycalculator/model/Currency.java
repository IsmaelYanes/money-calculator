package software.ulpgc.moneycalculator.model;

public record Currency(String code, String rate) {
    @Override
    public String toString() {
        return code + "-" + rate;
    }
}
