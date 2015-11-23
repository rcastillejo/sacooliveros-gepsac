/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaweka2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import weka.core.Instances;

/**
 *
 * @author Ricardo
 */
public class MyUtilsForWeka {

    // "datas\\SampleWithKnownHeader.arff"
    public static Instances getSampleInstances(String ruta) {
        Instances data = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    ruta));
            data = new Instances(reader);
            reader.close();
            // setting class attribute
            data.setClassIndex(data.numAttributes() - 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;

    }

    public static void saveInstancesToFile(String contents, String filename) {

        FileWriter fstream;
        try {
            fstream = new FileWriter(filename);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(contents);
            out.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
