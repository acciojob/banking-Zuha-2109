package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        if(balance < 5000){
            throw new Exception("Insufficient Balance");
        }
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        // rearrange licence id ro create new valid license id

        if(!isTradeIdValid(tradeLicenseId)){
            String newTradeLicenseId = newString(tradeLicenseId);
            if(newTradeLicenseId == ""){
                throw new Exception("Valid License can not be generated");
            }
            else{
                this.tradeLicenseId = newTradeLicenseId;
            }
        }
    }
    public boolean isTradeIdValid(String licenseId){
        for(int i=0;i<licenseId.length()-1;i++){
            if(licenseId.charAt(i) == licenseId.charAt(i+1)){
                return false;
            }
        }
        return true;
    }

    public String newString(String tradeId){
        int len = tradeId.length();

        int alpha = 26; //26 alphabets

        int[] count = new int[alpha];
        for(int i=0;i<alpha;i++){
            count[i] = 0;
        }
        for(char ch :tradeId.toCharArray()){
            count[(int) ch - (int) 'A']++;
        }
        char ch_maxcount = getCountChar(count);
        int maxCount = count[(int) ch_maxcount-(int)'A'];

        if(maxCount > (len+1)/2){
            return "";
        }

        String result = "";
        for(int i=0;i<len;i++){
            result+=' ';
        }
        int index = 0;
        while(maxCount > 0){
            result = result.substring(0, index) + ch_maxcount + result.substring(index+1);
            index = index+2;
            maxCount--;
        }
        count[(int) ch_maxcount - (int) 'A'] = 0;
        for (int i=0; i<alpha; i++) {
            while (count[i] > 0) {
                index = (index >= len) ? 1 : index;
                result = result.substring(0, index) + (char) ((int) 'A' + i) + result.substring(index+1);
                index += 2;
                count[i]--;
            }
        }
        return result;
    }
    public char getCountChar(int[] counting) {
        int max = 0;
        char ch = 0;
        int alphabet = 26;
        for (int i=0; i<alphabet; i++) {
            if (counting[i] > max) {
                max = counting[i];
                ch = (char) ((int) 'A' + i);
            }
        }
        return ch;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}


