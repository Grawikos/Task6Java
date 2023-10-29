import java.util.TreeMap;
import java.util.Iterator;

class TelephoneNumber implements Comparable<TelephoneNumber> {
    private String countryCode;
    private String localNumber;

    public TelephoneNumber(String countryCode, String localNumber) {
        this.countryCode = countryCode;
        this.localNumber = localNumber;
    }

    @Override
    public int compareTo(TelephoneNumber other) {
    int countryCodeComparison = this.countryCode.compareTo(other.countryCode);
    if (countryCodeComparison != 0) {
        return countryCodeComparison;
    }
    return this.localNumber.compareTo(other.localNumber);
}

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }
}

class Address{
    public String streetName;
    public int streetNum;

    public Address(String sName, int sNum){
        this.streetName = sName;
        this.streetNum = sNum;
    }
}

abstract class TelephoneEntry {
    private TelephoneNumber telephoneNumber;

    public TelephoneEntry(TelephoneNumber telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public abstract String description();

    public TelephoneNumber getTelephoneNumber() {
        return telephoneNumber;
    }
}

class Person extends TelephoneEntry {
    private String firstName;
    private String lastName;
    private Address address;

    public Person(String firstName, String lastName, Address address, TelephoneNumber telephoneNumber) {
        super(telephoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public String description() {
        return "Person: " + firstName + " " + lastName + ", Address: " + 
                address.streetName + " " + address.streetNum + ", Phone: " + 
                getTelephoneNumber().getCountryCode() + "-" + getTelephoneNumber().getLocalNumber();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

class Company extends TelephoneEntry {
    private String companyName;
    private Address address;

    public Company(String companyName, Address address, TelephoneNumber telephoneNumber) {
        super(telephoneNumber);
        this.companyName = companyName;
        this.address = address;
    }

    @Override
    public String description() {
        return "Company: " + companyName + ", Address: " + address.streetName + 
                " " + address.streetNum + ", Phone: " + getTelephoneNumber().getCountryCode() 
                + "-" + getTelephoneNumber().getLocalNumber();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

class TelephoneBook{
    public static void main(String[] args) {
        Person p1 = new Person("Marcin", "Grawinski", new Address("Politechniki", 1), new TelephoneNumber("+48", "123 456 789"));
        Person p2 = new Person("Bartosz", "Czekaj", new Address("Piotrkowska", 321), new TelephoneNumber("+47", "000 000 000"));
        Person p3 = new Person("Jan", "Kowalski", new Address("Narutowicza", 25), new TelephoneNumber("+11", "333 666 999"));
        Company c1 = new Company("Polex", new Address("Rzgowska", 100), new TelephoneNumber("+48", "222 222 222"));
        Company c2 = new Company("Gerex", new Address("Wladyslawowska", 456), new TelephoneNumber("+48", "333 333 333"));
        Company c3 = new Company("Mepex", new Address("Rzgowska", 159), new TelephoneNumber("+48", "444 444 444"));
        TreeMap<TelephoneNumber, TelephoneEntry> tMap = new TreeMap<>();
        tMap.put(p1.getTelephoneNumber(), p1);
        tMap.put(p2.getTelephoneNumber(), p2);
        tMap.put(p3.getTelephoneNumber(), p3);
        tMap.put(c1.getTelephoneNumber(), c1);
        tMap.put(c2.getTelephoneNumber(), c2);
        tMap.put(c3.getTelephoneNumber(), c3);

        Iterator<TelephoneNumber> iterator = tMap.keySet().iterator();
        while (iterator.hasNext()) {
            TelephoneNumber phoneNumber = iterator.next();
            TelephoneEntry entry = tMap.get(phoneNumber);
            System.out.println(phoneNumber.getCountryCode() + "-" + phoneNumber.getLocalNumber() + ": " + entry.description());
        }

    }
}