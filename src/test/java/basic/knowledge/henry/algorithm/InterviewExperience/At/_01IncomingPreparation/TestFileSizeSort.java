package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;

import basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._07FileCollection.CollectionTag;
import basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._07FileCollection.File;
import basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._07FileCollection.FileSizeSort;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

public class TestFileSizeSort {
    FileSizeSort fz;

    File file =new File("0",100,"a");
    File file1 =new File("1",100,"b");
    File file2 =new File("2",200,"a");
    File file3 =new File("3",200,"d");
    File file4 =new File("4",200,"c");
    File file5 =new File("5",100,"c");
    File file6 =new File("6",100,"c");
    File file7 =new File("7",200,"e");
    File file8 =new File("8",100,"f");

    File file9 =new File("9",600,"f");

    //c 400, d 200 ,a,200
    @Before
    public void setup() throws ParseException {
        fz = new FileSizeSort();

    }


    @Test
    public void testAddFile(){
        fz.addFile(file);
        fz.addFile(file1);
        fz.addFile(file2);
        fz.addFile(file3);
        fz.addFile(file4);
        fz.addFile(file5);
        fz.addFile(file6);
        fz.addFile(file7);
        fz.addFile(file8);
        fz.addFile(file9);

        List<CollectionTag> strings = fz.topN(4);
        System.out.println(strings);
    }
}
