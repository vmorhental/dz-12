import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.testng.Assert.*;

public class ManWomanTests {

    /*
    Man methods:
    1 isRetired
    2 deregisterPartnership
    3 getJob - getter
    4 setJob - setter

    Woman methods:
    1 isRetired
    2 deregisterPartnership
    3 setGirlSurname - setter
    4 getGirlSurname - getter

    Person methods:
    1 registerPartnership
    2 getFirstName - getter
    3 setFirstName - setter
    4 getLastName  - getter
    5 setLastName - setter
    6 getAge - getter
    7 setAge - setter
    8 getHasPartner - getter
    9 setHasPartner - setter
    10 getGender - getter
    11 setGender - setter
    12 getPartnerSurname - getter
    13 setPartnerSurname - setter
    14 setHasPartnerDivorsed - setter - this is service setter , it is used in other methods that will be tested.

     */
    @DataProvider(name = "manEntity")
    public static Object[][] manEntities() {
        return new Object[][]{{new Man("Valera", "Morhental", 67, null, "Male", "QA")}};
    }
    @DataProvider(name = "womanEntity")
    public static Object[][] womanEntities() {
        return new Object[][]{{new Woman("Slavka", "Loia", 61, null, "Female")}};
    }
    @DataProvider(name = "bothEntities")
    public static Object[][] getBothPersons(){
        return combine(manEntities(),  womanEntities());
    }
    public static Object[][] combine(Object[][] a1, Object[][] a2){
        List<Object[]> objectCodesList = new LinkedList<Object[]>();
        for(Object[] o : a1){
            for(Object[] o2 : a2){
                objectCodesList.add(concatAll(o, o2));
            }
        }
        return objectCodesList.toArray(new Object[0][0]);
    }
    @SafeVarargs
    public static <T> T[] concatAll(T[] first, T[]... rest) {
        //calculate the total length of the final object array after the concat
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        //copy the first array to result array and then copy each array completely to result
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }

    /*
                                                            Man methods tests
     */
    @Test(dataProvider = "manEntity")
    public void testIsManOlderThanSixtyFiveRetired(Man valera) {
        assertTrue(valera.isRetired());
    }

    @Test (dataProvider = "bothEntities" )
    public void testDeregisterPartnershipForMan (Man valera, Woman slavka){
        valera.registerPartnership(slavka,valera);
        valera.deregisterPartnership(true);
        assertFalse(valera.getHasPartner(),"Man is not divorsed");
    }

    @Test (dataProvider = "bothEntities" )
    public void testRegisterPartnershipMan(Man valera, Woman slavka){
        valera.registerPartnership(slavka,valera);
        assertEquals(valera.getPartnerSurname(),"Loia", "man is not in marriage");
    }
    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testGetJobMan(Man valera){
        String job = valera.getJob();
        assertEquals(job,"QA","Man has incorrect job");
    }

    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testSetJobMan(Man valera){
        valera.setJob("AQA");
        String newJob = valera.getJob();
        assertEquals(newJob,"AQA","Job is not updated");
    }

    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testGetFirstNameMan(Man valera){
        String firstName = valera.getFirstName();
        String message = String.format("First name is [%s]", firstName);
        assertEquals(firstName, "Valera", message);
    }

    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testSetFirstNameMan(Man valera){
        valera.setFirstName("Slavik");
        String firstName = valera.getFirstName();
        String message = String.format("First name is [%s]", firstName);
        assertEquals(firstName, "Slavik", message);
    }

    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testGetLastNameMan(Man valera){
        String lastName = valera.getLastName();
        String message = String.format("Last name is [%s]", lastName);
        assertEquals(lastName, "Morhental", message);
    }

    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testSetLastNameMan(Man valera){
        valera.setLastName("Petrov");
        String lastName = valera.getLastName();
        String message = String.format("Last name is [%s]", lastName);
        assertEquals(lastName, "Petrov", message);
    }

    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testGetAgeMan(Man valera){
        int age = valera.getAge();
        String message = "Age is " + age + " years";
        assertEquals(age, 67, message);
    }

    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testSetAgeMan(Man valera){
        valera.setAge(55);
        int age = valera.getAge();
        String message = "Age is " + age + " years";
        assertEquals(age, 55, message);
    }

    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testGetHasPartnerMan(Man valera){
        boolean hasPartner = valera.getHasPartner();
        String message = "Person has partner";
        assertFalse(hasPartner, message);
    }

    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testSetHasPartnerMan(Man valera){
        valera.setHasPartner("Slavka");
        boolean hasPartner = valera.getHasPartner();
        String message = "Person has no partner";
        assertTrue(hasPartner, message);
    }

    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testGetGenderMan(Man valera){
        String gender = valera.getGender();
        String message = String.format("Gender is [%s]",gender);
        assertEquals(gender, "Male", message);
    }

    @Test (groups = "setter and getter", dataProvider = "manEntity")
    public void testSetGender(Man valera){
        valera.setGender("Female");
        String gender = valera.getGender();
        String message = String.format("Gender is [%s]",gender);
        assertEquals(gender, "Female", message);
    }

    @Test (groups = "setter and getter", dataProvider = "bothEntities")
    public void testGetPartnerSurname(Man valera, Woman slavka){
        valera.registerPartnership(slavka,valera);
        String partnerSurname = valera.getPartnerSurname();
        String message = String.format("Partner surname is [%s]",partnerSurname);
        assertEquals(partnerSurname, "Loia", message);
    }

    @Test (groups = "setter and getter", dataProvider = "bothEntities")
    public void testSetPartnerSurname(Man valera, Woman slavka){
        valera.registerPartnership(slavka,valera);
        valera.setPartnerSurname("Nothing");
        String partnerSurname = valera.getPartnerSurname();
        String message = String.format("Partner surname is [%s]",partnerSurname);
        assertEquals(partnerSurname, "Nothing", message);
    }

/*
                                                        Woman methods tests
 */
    @Test(dataProvider = "womanEntity")
    public void testIsWomanOlderThanSixtyRetired(Woman slavka){
        assertTrue(slavka.isRetired(), "Woman is not retired");
    }

    @Test (dataProvider = "bothEntities")
    public void testDeregisterPartnershipForWoman (Man valera, Woman slavka){
        slavka.registerPartnership(slavka,valera);
        slavka.deregisterPartnership(true);
        assertFalse(slavka.getHasPartner(),"Woman is not divorsed");
    }

    @Test (dataProvider = "bothEntities")
    public void testRegisterPartnershipWoman(Man valera, Woman slavka){
        slavka.registerPartnership(slavka,valera);
        assertEquals(slavka.getPartnerSurname(),"Morhental", "woman is not in marriage");
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testGetGirlSurname(Woman slavka){
        String girlSurname = slavka.getGirlSurname();
        assertEquals(girlSurname,"Loia","Surname is incorrect");
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testSetGirlSurname(Woman slavka){
        slavka.setGirlSurname("Gladun");
        String girlSurname = slavka.getGirlSurname();
        assertEquals(girlSurname,"Gladun","Surname is incorrect");
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testGetFirstNameWoman(Woman slavka){
        String firstName = slavka.getFirstName();
        String message = String.format("First name is [%s]", firstName);
        assertEquals(firstName, "Slavka", message);
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testSetFirstNameWoman(Woman slavka){
        slavka.setFirstName("Slavik");
        String firstName = slavka.getFirstName();
        String message = String.format("First name is [%s]", firstName);
        assertEquals(firstName, "Slavik", message);
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testGetLastNameWoman(Woman slavka){
        String lastName = slavka.getLastName();
        String message = String.format("Last name is [%s]", lastName);
        assertEquals(lastName, "Loia", message);
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testSetLastNameWoman(Woman slavka){
        slavka.setLastName("Petrova");
        String lastName = slavka.getLastName();
        String message = String.format("Last name is [%s]", lastName);
        assertEquals(lastName, "Petrova", message);
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testGetAgeWoman(Woman slavka){
        int age = slavka.getAge();
        String message = "Age is " + age + " years";
        assertEquals(age, 61, message);
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testSetAgeWoman(Woman slavka){
        slavka.setAge(55);
        int age = slavka.getAge();
        String message = "Age is " + age + " years";
        assertEquals(age, 55, message);
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testGetHasPartnerWoman(Woman slavka){
        boolean hasPartner = slavka.getHasPartner();
        String message = "Person has partner";
        assertFalse(hasPartner, message);
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testSetHasPartnerWoman(Woman slavka){
        slavka.setHasPartner("Valera");
        boolean hasPartner = slavka.getHasPartner();
        String message = "Person has no partner";
        assertTrue(hasPartner, message);
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testGetGenderWoman(Woman slavka){
        String gender = slavka.getGender();
        String message = String.format("Gender is [%s]",gender);
        assertEquals(gender, "Female", message);
    }

    @Test (groups = "setter and getter", dataProvider = "womanEntity")
    public void testSetGenderWoman(Woman slavka){
        slavka.setGender("Male");
        String gender = slavka.getGender();
        String message = String.format("Gender is [%s]",gender);
        assertEquals(gender, "Male", message);
    }

    @Test (groups = "setter and getter", dataProvider = "bothEntities")
    public void testGetPartnerSurnameWoman(Man valera, Woman slavka){
        slavka.registerPartnership(slavka,valera);
        String partnerSurname = slavka.getPartnerSurname();
        String message = String.format("Partner surname is [%s]",partnerSurname);
        assertEquals(partnerSurname, "Morhental", message);
    }

    @Test (groups = "setter and getter", dataProvider = "bothEntities")
    public void testSetPartnerSurnameWoman(Man valera, Woman slavka){
        slavka.registerPartnership(slavka,valera);
        slavka.setPartnerSurname("Nothing");
        String partnerSurname = slavka.getPartnerSurname();
        String message = String.format("Partner surname is [%s]",partnerSurname);
        assertEquals(partnerSurname, "Nothing", message);
    }
}
