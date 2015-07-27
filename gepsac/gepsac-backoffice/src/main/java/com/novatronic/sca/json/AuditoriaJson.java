/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.json;

import com.novatronic.pscabas.core.model.Auditoria;
import java.util.List;

/**
 *
 * @author ccondor
 */
public class AuditoriaJson {

    private int iDraw;
    private int iTotalDisplayRecords;
    private int recordsFiltered;
    private List<Auditoria> aaData;

    public AuditoriaJson(int draw, int iTotalDisplayRecords, int recordsFiltered, List<Auditoria> data) {
        this.iDraw = draw;
        this.iTotalDisplayRecords = iTotalDisplayRecords;
        this.recordsFiltered = recordsFiltered;
        this.aaData = data;
    }

    public int getiDraw() {
        return iDraw;
    }

    public void setiDraw(int iDraw) {
        this.iDraw = iDraw;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<Auditoria> getAaData() {
        return aaData;
    }

    public void setAaData(List<Auditoria> aaData) {
        this.aaData = aaData;
    }

    @Override
    public String toString() {
        String s = +iDraw + ", " + iTotalDisplayRecords + ", " + recordsFiltered + " ";

        boolean first = true;
        for (Auditoria auditoria : aaData) {
            if (first) {
                first = false;
            } else {
                s += ", ";
            }
            s += auditoria.toString();

        }
        return s;
    }

}
