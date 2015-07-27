package com.novatronic.sca.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.novatronic.pscabas.core.model.Usuario;

/**

 * Clase que contiene los métodos de exportación de archivos a formatos PDF y Excel
 */
public class ExportUtil {
    
    private static final Logger logger = Logger.getLogger(ExportUtil.class);

    /**
     * Método que cenvía el exporta archivos a PDF
     * @param form Objeto que representa el formulario (archivo AuditoriaBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     */

    
    /**
     * Método que cenvía el exporta archivos a Excel
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     */
   
    
    

    /**
     * Método que arma la cabecera del archivo antes de ser exportado
     * @param document Archivo a exportar
     * @param report Objeto que representa el formulario (archivo AuditoriaBuscar.jsp)
     * @param esSuperUsuario Objeto que valida si el usuario es el superadministrador
     */
    
   

    private static void addHeaderTable(PdfPTable table, Document document) throws DocumentException {
        PdfPCell headerTable = new PdfPCell(new Phrase("N°"));
        headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerTable.setBackgroundColor(BaseColor.GRAY);
        table.addCell(headerTable);

        headerTable = new PdfPCell(new Phrase("Fecha"));
        headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerTable.setBackgroundColor(BaseColor.GRAY);
        table.addCell(headerTable);

        headerTable = new PdfPCell(new Phrase("Usuario"));
        headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);

        headerTable.setBackgroundColor(BaseColor.GRAY);
        table.addCell(headerTable);
        
        headerTable = new PdfPCell(new Phrase("Men\u00fa"));
        headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);

        headerTable.setBackgroundColor(BaseColor.GRAY);
        table.addCell(headerTable);
        
//        headerTable = new PdfPCell(new Phrase("Clase"));
//        headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);

//        headerTable.setBackgroundColor(BaseColor.GRAY);
//        table.addCell(headerTable);

        headerTable = new PdfPCell(new Phrase("M\u00e9todo"));
        headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerTable.setBackgroundColor(BaseColor.GRAY);
        table.addCell(headerTable);

//        headerTable = new PdfPCell(new Phrase("M\u00f3dulo"));
//        headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
//        headerTable.setBackgroundColor(BaseColor.GRAY);
//        table.addCell(headerTable);

        headerTable = new PdfPCell(new Phrase("Ip"));
        headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerTable.setBackgroundColor(BaseColor.GRAY);
        table.addCell(headerTable);

        table.setHeaderRows(1);
        document.add(table);
    }

    /**
     * Método que genera el nombre del archivo a ser exportado
     * @param extension Extensión del archivo a ser exportado
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     */
    public static String gerenarNombreArchivo(HttpServletRequest request, String extension) {
        String finalFilename = Constantes.EMPTY;
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new Date());
        Usuario usuario = ActionUtil.obtenerUsuarioLogeado(request);
        String userId = Constantes.EMPTY;
        if (usuario != null) {
            userId = usuario.getId() + Constantes.EMPTY;
        } else {
            userId = Constantes.EMPTY;
        }
        finalFilename = date + "_" + userId + extension;
        return finalFilename;
    }
}
