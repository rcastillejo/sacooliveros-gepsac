/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaweka2;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author DENNYZ
 */
public class Util {

    private Instances data;

    public Instances getData() {
        return data;
    }

    public void setData(Instances data) {
        this.data = data;
    }

    public Instances obtenerData(String ruta) throws Exception {
        //el archivo csv debe estar delimitado por comas
        DataSource ds = new DataSource(ruta);
        data = ds.getDataSet();
        // setting class attribute if the data format does not provide this information
        // For example, the XRFF format saves the class attribute information as well
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
        
        for (int i = 0; i < data.numAttributes() - 1; i++) {
            System.out.println("METADATA>"+data.attribute(i).toString());            
        }
        
        
        int numClasses = data.numClasses();
        
        for (int i = 0; i < numClasses; i++) {
            System.out.println("class value ["+i+"]="+data.classAttribute().value(i)+"");
        }
        
        data.deleteAttributeAt(0);
        return data;
    }

    public void guardarModelo(String ruta, Classifier cls) throws Exception {
//        escribe el clasificador cls en la ruta ruta
        weka.core.SerializationHelper.write(ruta, cls);
    }

    public Classifier cargarModelo(String ruta) throws Exception {
//        obtiene el clasificador cls de la ruta ruta
        Classifier cls = (Classifier) weka.core.SerializationHelper.read(ruta);
        return cls;
    }
}
