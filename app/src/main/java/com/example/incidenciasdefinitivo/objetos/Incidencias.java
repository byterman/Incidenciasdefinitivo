package com.example.incidenciasdefinitivo.objetos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Incidencias {
    public int id;
    public String contenido;
    public String prioridad;
    public String desc;
    public long fecha;


    public Incidencias(String contenido,String prioridad,String desc,int id) {
        this.contenido = contenido;
        this.prioridad =prioridad;
        this.desc=desc;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public String dimeFecha(){
        Date actual_hora=new java.util.Date(this.fecha*1000);
        String hora=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(actual_hora);
        return hora;
    }
}
