package basic.knowledge.henry.stringCreation;

public class StringComparison {
    public static void main(String[] args) {
        System.out.println(Integer.valueOf("011"));
        String str1 = "Jimmy";
        String str2 = "Jimmy";

        System.out.println(str1 == str2);        // true

        str1 = "Jim";
        String str3 = "Jim";

        System.out.println(str1 == str3);        // true

        str1 = str1+"my";

        System.out.println("str1 = " + str1);    // str1 = "Jimmy"
        System.out.println(str1 == str2);        // false



        String str5 = "henry";
        String str6 = new String("henry");

        System.out.println(str5 == str6);         // false
        System.out.println(str5.equals(str6));    // true

    }


}
